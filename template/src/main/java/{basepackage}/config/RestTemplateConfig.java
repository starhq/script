package net.shinsoft.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by starhq on 2017/2/28.
 */
@Configuration
public class RestTemplateConfig {

    @Autowired
    private FastJsonHttpMessageConverter fastJsonHttpMessageConverter;

//    @Bean
//    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
//        // 长连接保持30秒
//        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(30, TimeUnit
//                .SECONDS);
//        //连接池最大连接数
//        connectionManager.setMaxTotal(1000);
//        //同路由的并发数
//        connectionManager.setDefaultMaxPerRoute(1000);
//        return connectionManager;
//    }
//
//    @Bean
//    public HttpClientBuilder httpClientBuilder() {
//        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
//        clientBuilder.setConnectionManager(poolingHttpClientConnectionManager());
//
//        // 重试次数，默认是3次，没有开启
//        HttpRequestRetryHandler retryHandler = new DefaultHttpRequestRetryHandler(2, true);
//        clientBuilder.setRetryHandler(retryHandler);
//        // 保持长连接配置，需要在头添加Keep-Alive
//        clientBuilder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());
//
//        List<Header> headers = new ArrayList<>();
//        headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like " +
//                "Gecko) Chrome/31.0.1650.16 Safari/537.36"));
//        headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
//        headers.add(new BasicHeader("Accept-Language", "zh-CN"));
//        headers.add(new BasicHeader("Connection", "Keep-Alive"));
//        clientBuilder.setDefaultHeaders(headers);
//
//        return clientBuilder;
//    }
//
//    @Bean
//    public HttpClient httpClient() {
//        return httpClientBuilder().build();
//    }
//
//    @Bean
//    public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory() {
//        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory
//                (httpClient());
//        httpRequestFactory.setConnectTimeout(5000);
//        httpRequestFactory.setReadTimeout(10000);
//        return httpRequestFactory;
//    }

    @Bean
    public SimpleClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(5000);
        return factory;
    }

    @Bean
    public RestTemplate restTemplate() {
        //带连接池的，需要httpclient
//        RestTemplate template = new RestTemplate(httpComponentsClientHttpRequestFactory());
        //基于httpconnection
        RestTemplate template = new RestTemplate(simpleClientHttpRequestFactory());

        DefaultResponseErrorHandler handler = new DefaultResponseErrorHandler();
        template.setErrorHandler(handler);

        // 添加内容转换器
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(fastJsonHttpMessageConverter);

        template.setMessageConverters(messageConverters);
        return template;
    }
}
