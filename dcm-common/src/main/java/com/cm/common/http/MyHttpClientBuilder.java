package com.cm.common.http;

import lombok.Data;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;


@Data
public class MyHttpClientBuilder {

    private int maxTotal = 20;

    private int maxPerRoute = 20;

    private int connectTimeout = 3000;

    private int socketTimeout = 1000;

    private int keepAliveDuration = 30000;

    public static MyHttpClientBuilder create(){
        return new MyHttpClientBuilder();
    }

    public MyHttpClientBuilder maxTotal(int maxTotal){
        this.maxTotal = maxTotal;
        return this;
    }

    public MyHttpClientBuilder maxPerRoute(int maxPerRoute){
        this.maxPerRoute = maxPerRoute;
        return this;
    }

    public MyHttpClientBuilder connectTimeout(int connectTimeout){
        this.connectTimeout = connectTimeout;
        return this;
    }

    public MyHttpClientBuilder socketTimeout(int socketTimeout){
        this.socketTimeout = socketTimeout;
        return this;
    }

    public MyHttpClientBuilder keepAliveDuration(int keepAliveDuration){
        this.keepAliveDuration = keepAliveDuration;
        return this;
    }

    public MyHttpClient build(){
        // TODO more configs to add
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotal);
        connectionManager.setDefaultMaxPerRoute(maxPerRoute);
        // request config
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connectTimeout)
                .setSocketTimeout(socketTimeout)
                .build();
        // socketconfig
        SocketConfig socketConfig = SocketConfig.custom()
                .setSoKeepAlive(true)
                .setTcpNoDelay(true)
                .build();
        // build httpclient
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .setDefaultSocketConfig(socketConfig)
                .setKeepAliveStrategy((response, context) -> {
                    HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                    while (it.hasNext()) {
                        HeaderElement he = it.nextElement();
                        String param = he.getName();
                        String value = he.getValue();
                        if (value != null && param.equalsIgnoreCase("timeout")) {
                            return Long.parseLong(value) * 1000;
                        }
                    }
                    return keepAliveDuration;
                })
                .build();
        return new MyHttpClient(httpClient);
    }
}
