package com.enlink;

import com.enlink.config.ElasticsearchConfig;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 14:22 2018/8/31
 */

@SpringBootApplication
@MapperScan("com.enlink.dao")
public class Application {

    @Autowired
    private ElasticsearchConfig esConfig;
//    @Bean
//    public RestHighLevelClient esClient() {
//        LOGGER.info("Init Elasticsearch Client: start ......");
//        List<HttpHost> httpHosts = new ArrayList<>();
//        for (String host : esConfig.genHosts()) {
//            httpHosts.add(HttpHost.create(host));
//        }
//        RestClientBuilder builder = RestClient.builder(httpHosts.toArray(new HttpHost[esConfig.genHosts().length]));
//        if (Strings.isNotBlank(esConfig.getUsername()) && Strings.isNotBlank(esConfig.getPassword())) {
//            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(esConfig.getUsername(), esConfig.getPassword()));
//            builder.setHttpClientConfigCallback(httpClientBuilder -> {
//                httpClientBuilder.disableAuthCaching();
//                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
//            });
//        }
//        RestHighLevelClient client = new RestHighLevelClient(builder);
//        LOGGER.info("Init Elasticsearch Client: success !");
//        return client;
//    }


//    public static ApplicationContext context = null;
//    @Bean
//    public static Object getInstance() {
//
//        if (null == context) {
//            context = ApplicationContextLoaderListener.getApplicationContext();
//        }
//        return null;
//    }

    @Bean
    public RestHighLevelClient reClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")
                )
        );
        return client;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
