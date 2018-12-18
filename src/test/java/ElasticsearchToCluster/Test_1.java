package ElasticsearchToCluster;

import com.enlink.Application;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 9:20 2018/12/6
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class Test_1 {
    private String index;
    private String type;

    @Before
    public void prepare(){
        index = "rally-races-2018-12";
        type = "races";
    }
    @Test
    public void test1(){
        RestHighLevelClient elastic = getElastic();
        SearchRequest searchRequest = new SearchRequest(index,type);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        try {
            SearchResponse search = elastic.search(searchRequest);
            System.out.println(search);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void test2(){
        System.out.println("----------");
    }


    //获取ElasticClient对象
    private static RestHighLevelClient getElastic() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.100.50", 9200, "http")
                )
        );
    }
}
