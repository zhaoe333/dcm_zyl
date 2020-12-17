package com.cm.utils;


//import com.cm.datalog.DataLogHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
public class MyHttpClient {

    private final CloseableHttpClient httpClient;

    public MyHttpClient(CloseableHttpClient client){
        this.httpClient = client;
    }

    /**
     * 获取httpclient实例 用于自定义查询
     * @return
     */
    public CloseableHttpClient getHttpClient(){
        return httpClient;
    }

    /**
     * get方式请求
     * @param url
     * @return
     * @throws Exception
     */
    public String get(String url) throws Exception {
        return get(url, null, null);
    }

    /**
     * get方式请求
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public String get(String url, Map<String,Object> params) throws Exception {
        return get(url, params, null);
    }

    /**
     * get方式请求
     * @param url
     * @param params
     * @param headers
     * @return
     * @throws Exception
     */
    public String get(String url, Map<String,Object> params,Map<String,Object> headers) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        HttpClientContext context = createContext();
        if(LogicUtil.isNotNullAndEmpty(params)){
            if(!url.endsWith("?")){
                url+="?";
            }
            for(String key : params.keySet()){
                url = addParam(url, key,params.get(key));
            }
        }
        return doRequest(httpGet, context, headers, params);
    }

    /**
     * post方式请求
     * @param url
     * @return
     * @throws Exception
     */
    public String post(String url) throws Exception{
        return post(url, null, null);
    }

    /**
     * post方式请求
      * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public String post(String url, Map<String, Object> params) throws Exception{
        return post(url, params, null);
    }

    /**
     * post方式请求
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public String post(String url, Map<String, Object> params,Map<String,Object> headers) throws Exception{
        HttpPost httpPost = new HttpPost(url);
        HttpClientContext context = createContext();
        if(LogicUtil.isNotNullAndEmpty(params)){
            List<NameValuePair> nvps = new ArrayList<>();
            for (String key : params.keySet()) {
                addParam(nvps, key, params.get(key));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8));
        }
        return doRequest(httpPost, context, params, headers);
    }

    /**
     * post方式上传文件
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public String postFile(String url, Map<String, Object> params) throws Exception{
        return postFile(url, params, null);
    }

    /**
     * post方式上传文件
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public String postFile(String url, Map<String, Object> params, Map<String,Object> headers) throws Exception{
        HttpPost httpPost = new HttpPost(url);
        HttpClientContext context = createContext();
        MultipartEntityBuilder builder= MultipartEntityBuilder.create();
        // 设置兼容模式
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE).setCharset(StandardCharsets.UTF_8);
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            Object val = params.get(key);
            if(val instanceof File){
                builder.addPart(key,new FileBody((File)val));
            }else{
                builder.addTextBody(key, String.valueOf(val), ContentType.DEFAULT_BINARY);
            }
        }
        httpPost.setEntity(builder.build());
        return doRequest(httpPost, context, params, headers);
    }

    /**
     * post方式请求
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public String postJson(String url, Object params) throws Exception{
        return postJson(url, params,null);
    }


    /**
     * post方式请求
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public String postJson(String url, Object params,Map<String,Object> headers) throws Exception{
        HttpPost httpPost = new HttpPost(url);
        HttpClientContext context = createContext();
        httpPost.setEntity(EntityBuilder.create().setContentType(ContentType.APPLICATION_JSON)
                .setText(params instanceof String?(String)params: JacksonUtil.get().readAsString(params)).build());
        return doRequest(httpPost, context, params, headers);
    }

    /**
     * 根据url下载文件，保存到filepath中
     *
     * @param url
     * @return
     */
    public File download(String url, File folder) {
        File file = null;
        CloseableHttpResponse httpResponse=null;
        long start = System.currentTimeMillis();
        try {
            HttpGet httpget = new HttpGet(url);
            HttpClientContext context = createContext();
            HttpResponse response = httpClient.execute(httpget,context);
            HttpEntity entity = response.getEntity();
            String fileName = getFileName(response);
            if(null == folder){
                file = new File(fileName);
            }else{
                file = new File(folder,fileName);
            }
            FileOutputStream fileout = new FileOutputStream(file);
            fileout.write(EntityUtils.toByteArray(entity));
            fileout.flush();
            fileout.close();
            // 这里避免数据冗余 不记录下载文件的内容
//            DataLogHelper.sendInternalTracingLog(url, null, null, response.getStatusLine().getStatusCode(), "{}", (int)(System.currentTimeMillis()-start));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    private HttpClientContext createContext(){
        return HttpClientContext.create();
    }

    private String doRequest(HttpRequestBase httpRequest)throws Exception{
        return doRequest(httpRequest, createContext(), null, null);
    }

    private String doRequest(HttpRequestBase httpRequest, HttpClientContext context)throws Exception{
        return doRequest(httpRequest, context, null, null);
    }

    private String doRequest(HttpRequestBase httpRequest, HttpClientContext context, Object params)throws Exception{
        return doRequest(httpRequest, context, null, null);
    }

    private String doRequest(HttpRequestBase httpRequest, HttpClientContext context,  Object params, Map<String,Object> headers)throws Exception{
        CloseableHttpResponse httpResponse = null;
        String result = "{}";
        try{
            if(LogicUtil.isNotNullAndEmpty(headers)){
                setHeader(httpRequest, headers);
            }
            long start = System.currentTimeMillis();
            httpResponse = httpClient.execute(httpRequest, context);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if(statusCode == 200) {
                HttpEntity entity = httpResponse.getEntity();
                result = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
            }
//            DataLogHelper.sendInternalTracingLog(httpRequest.getURI().toString(), headers, params, statusCode, result, (int)(System.currentTimeMillis()-start));
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("http client error!");
        }finally{
            if(httpResponse!=null){
                httpResponse.close();
            }
        }
        return result;
    }

    private String addParam(String url,String key,Object valueObj) throws Exception{
        if(valueObj==null){
            return url;
        }
        if(valueObj.getClass().isArray()){
            Object[] valueObjArrays= (Object[])valueObj;
            for(Object obj:valueObjArrays){
                url=addParam(url, key, obj);
            }
        }else if(valueObj instanceof Collection){
            Collection collection = (Collection)valueObj;
            for(Object obj:collection){
                url=addParam(url, key, obj);
            }
        }else if(!"".equals(valueObj.toString())){
            url+="&"+key+"="+ URLEncoder.encode(valueObj.toString(), "UTF-8");
        }
        return url;
    }

    private void addParam(List<NameValuePair> nvps, String key, Object valueObj) {
        if(valueObj==null){
            return;
        }
        if(valueObj.getClass().isArray()){
            Object[] valueObjArrays= (Object[])valueObj;
            for(Object obj:valueObjArrays){
                addParam(nvps, key, obj);
            }
        }else if(valueObj instanceof Collection){
            Collection collection = (Collection)valueObj;
            for(Object obj:collection){
                addParam(nvps, key, obj);
            }
        }else if(!"".equals(valueObj.toString())){
            nvps.add(new BasicNameValuePair(key, valueObj.toString()));
        }
    }

    private void addParam(MultipartEntityBuilder builder, String key, Object valueObj) {
        if(valueObj==null){
            return;
        }
        if(valueObj.getClass().isArray()){
            Object[] valueObjArrays= (Object[])valueObj;
            for(Object obj:valueObjArrays){
                addParam(builder, key, obj);
            }
        }else if(valueObj instanceof Collection){
            Collection collection = (Collection)valueObj;
            for(Object obj:collection){
                addParam(builder, key, obj);
            }
        }else if(valueObj instanceof File){
            builder.addBinaryBody(key, (File)valueObj);
        }else if(!"".equals(valueObj.toString())){
            builder.addTextBody(key,  valueObj.toString());
        }
    }

    private void setHeader(HttpRequestBase request, Map<String,Object> headerParams){
        if(LogicUtil.isNotNullAndEmpty(headerParams)){
            for(String key:headerParams.keySet()){
                request.setHeader(key, headerParams.get(key).toString());
            }
        }
    }

    private static String getFileName(HttpResponse response) {
        Header contentHeader = response.getFirstHeader("Content-Disposition");
        String filename = null;
        if (contentHeader != null) {
            HeaderElement[] values = contentHeader.getElements();
            if (values.length == 1) {
                NameValuePair param = values[0].getParameterByName("filename");
                if (param != null) {
                    try {
                        filename = param.getValue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return filename;
    }
}
