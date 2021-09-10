package com.market.stock.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class StockMarketConfiguration {

    private int maxConnectionPerRoute = 5;

    private int maxConnectionsTotal = 5;

    @Bean
    public HttpClient httpClient(){
        PoolingHttpClientConnectionManager poolingConnManager
                = new PoolingHttpClientConnectionManager();
        poolingConnManager.setDefaultMaxPerRoute(maxConnectionPerRoute);
        poolingConnManager.setMaxTotal(maxConnectionsTotal);

        return HttpClientBuilder.create().setConnectionManager(poolingConnManager).build();
    }

    @Bean
    public RestTemplate restTemplate(){
        final List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();

        final MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        final ObjectMapper mapper = mappingJackson2HttpMessageConverter.getObjectMapper();
        mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
        mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        messageConverters.add(mappingJackson2HttpMessageConverter);

        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient()));
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }
}
