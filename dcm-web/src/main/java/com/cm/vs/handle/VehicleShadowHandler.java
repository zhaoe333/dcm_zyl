package com.cm.vs.handle;

import com.cm.utils.JacksonUtil;
import com.cm.utils.MyHttpClient;
import com.cm.vs.config.VehicleShadowProperties;
import com.cm.vs.entity.VehicleLocation;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * 调用vs服务的处理类
 */
@Slf4j
public class VehicleShadowHandler {

    private final String frament ="fragment info on Vehicle{vehicle{travelledDistance{value}}channel{lsc{timestampVehicle{value}timestampFinalizationUtcVehicle{value}timestampBackend{value}}}cabin{infotainment{vehicleTrackingAllowed{value}navigation{currentLocation{longitude{value}latitude{value}canBeTrusted{value}}}}}}";

    private final String queryVin_pre=":vehicle(id:\\\"";

    private final String queryVin_suf="\\\"){...info}";

    private static final String TOKEN_KEY="keyId";

    private static final String COOKIE_KEY = "cookie";

    private static final String SET_COOKIE_KEY = "set-cookie";

    private static String cookie = "";

    @Resource
    private VehicleShadowProperties properties;

    @Resource
    private MyHttpClient myHttpClient;

    private HttpClientContext context;

    public VehicleShadowHandler() {
    }

    public List<VehicleLocation> getVehicleInfo(List<String> vinList) {
        String query = buildQuery(vinList);
        log.info(query);
        try{
            // 记录vs的请求结果
            return doRequest(query,0);
        }catch(Exception e){
            // 记录错误日志
            log.error(query,e);
        }
        return null;
    }

    public List<VehicleLocation> doRequest(String query, int retryNum)throws Exception{
        if(retryNum >= properties.getRetryNum()){
            throw new RuntimeException("vehicle shadow api has too many retries!");
        }
        String result = request(query);
        log.info(result);
        if(null == result){
            doRequest(query, retryNum+1);
        }
        List<VehicleLocation> vehicleLocations = convertData(result);
        if(null==vehicleLocations){
            doRequest(query, retryNum+1);
        }
        // 记录vs的请求结果
        return vehicleLocations;
    }

    private String request(String query) throws IOException {
        CloseableHttpClient httpClient = myHttpClient.getHttpClient();
        HttpPost httpPost = new HttpPost(properties.getUrl());
        httpPost.setEntity(EntityBuilder.create().setContentType(ContentType.APPLICATION_JSON)
                .setText(query).build());
        httpPost.setHeader(TOKEN_KEY,properties.getKeyId());
        httpPost.setHeader(COOKIE_KEY, cookie);
        CloseableHttpResponse httpResponse = null;
        String result = "{}";
        httpResponse = httpClient.execute(httpPost, getContext());
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if(statusCode == 200) {
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            if(null!=httpResponse.getLastHeader(SET_COOKIE_KEY)){
                cookie = httpResponse.getLastHeader(SET_COOKIE_KEY).getValue();
            }
        }else{
            log.info(String.valueOf(statusCode));
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        }
        httpResponse.close();
        return result;
    }

    /**
     * 构建查询参数
     * 由于业务单一 这里就不造轮子了 简单拼接性能最快
     * @param vinList
     * @return
     */
    private String buildQuery(List<String> vinList){
        StringBuilder sb = new StringBuilder();
        sb.append("{\"query\":\"{");
        for(String vin:vinList){
            sb.append(vin);
            sb.append(queryVin_pre);
            sb.append(vin);
            sb.append(queryVin_suf);
        }
        sb.append("}");
        sb.append(frament);
        sb.append("\"}");
        return sb.toString();
    }

    /**
     * 转换数据结构 如果数据结构解析失败会抛出异常
     * @param json vs返回数据
     * @throws Exception 数据结构解析失败
     */
    private List<VehicleLocation> convertData(String json) throws Exception{
        List<VehicleLocation> locationList = new ArrayList<>();
        JsonNode rootNode = JacksonUtil.get().json2Node(json);
        // 如果是vs的datasource timeout需要返回null 重新请求
        if(!rootNode.get("errors").isNull()&&rootNode.get("errors").get(0).get("extensions").get("code").intValue()==1004){
            return null;
        }
        JsonNode dataNode = rootNode.get("data");
        for (Iterator<Map.Entry<String,JsonNode>> it = dataNode.fields(); it.hasNext(); ) {
            VehicleLocation vehicleLocation = new VehicleLocation();
            Map.Entry<String,JsonNode> entry = it.next();
            String vin = entry.getKey();
            vehicleLocation.setVin(vin);
            vehicleLocation.setGpsTime(new Date(entry.getValue().get("channel").get("lsc").get("timestampFinalizationUtcVehicle").get("value").longValue()));
            JsonNode locationNode = entry.getValue().get("cabin").get("infotainment").get("navigation").get("currentLocation");
            vehicleLocation.setCanBeTrusted(locationNode.get("canBeTrusted").get("value").booleanValue());
            vehicleLocation.setLon(locationNode.get("longitude").get("value").doubleValue());
            vehicleLocation.setLat(locationNode.get("latitude").get("value").doubleValue());
            vehicleLocation.setOdo(entry.getValue().get("vehicle").get("travelledDistance").get("value").doubleValue());
            locationList.add(vehicleLocation);
        }
        return locationList;
    }

    /**
     * 授权账号密码
     * @return
     */
    private HttpClientContext getContext(){
        if(null!=context){
            return context;
        }
        context = HttpClientContext.create();
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(AuthScope.ANY,new UsernamePasswordCredentials(properties.getUsername(), properties.getPassword()));
        context.setCredentialsProvider(credsProvider);
        return context;
    }

}
