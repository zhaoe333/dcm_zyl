package com.cm.utils;

import lombok.Data;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


@Data
public class MyHttpClientBuilder {

    private int maxTotal = 20;

    private int maxPerRoute = 20;

    private int connectTimeout = 3000;

    private int socketTimeout = 1000;

    private int keepAliveDuration = 30000;

    private boolean trustSSL = true;

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

    public MyHttpClientBuilder trustSSL(boolean trustSSL){
        this.trustSSL = trustSSL;
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
        HttpClientBuilder builder = HttpClientBuilder.create()
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
                });
        if(trustSSL){
            SSLContext sslContext = null;
            try {
                sslContext = new SSLContextBuilder().loadTrustMaterial(null,((chain, authType) -> true)).build();
                //NoopHostnameVerifier类:  作为主机名验证工具，实质上关闭了主机名验证，它接受任何
                //有效的SSL会话并匹配到目标主机。
                HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
                builder.setSSLSocketFactory(new SSLConnectionSocketFactory(sslContext, hostnameVerifier));
            } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
                e.printStackTrace();
            }
        }
        return new MyHttpClient(builder.build());
    }
}
