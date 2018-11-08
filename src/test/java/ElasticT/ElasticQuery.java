package ElasticT;

import com.enlink.de.Application;
import com.enlink.de.elasticsearch.dao.InsertTimeDao;
import com.enlink.de.elasticsearch.entity.InsertTime;
import com.enlink.de.util.DateUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 14:14 2018/9/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class ElasticQuery {
    RestHighLevelClient client = getElastic();
    @Resource
    private InsertTimeDao insertTimeDao;

    @Test
    public void test6() {
        long start=System.currentTimeMillis();
        //创建------
        //可以在创建的时候指定index，SearchRequest searchRequest = new SearchRequest("some_index*");,支持带*号的模糊匹配
        SearchRequest searchRequest = new SearchRequest("hotel");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        //termQuery("key",obj)          完全匹配
        //termsQuery("key",obj1,obj2)   一次匹配多个值
        //rangeQuery();                 区间查询
        //matchAllQuery();              匹配所有文件
        //matchQuery("key",obj)         单个匹配，field不支持通配符，前缀具有高级特性
        //multiMatchQuery()             匹配多个字段，field有通配符特性，
        //boolquery();                  复合查询
        //wildcardQuery();              模糊查询，支持通配符
        String startTime = "2018-01-16 14:03:15";
        String endTime = "2018-01-16 18:03:16";
        //sourceBuilder.query(QueryBuilders.termQuery("file_name","jpg"));                                      //完全匹配
        //sourceBuilder.query(QueryBuilders.termsQuery("file_name","陌生的来客.doc","能不能不送开水？.doc"));    //一次查多个值，感觉之只能精准查询，无法模糊查询模糊匹配，在字段中，有相应的即可
        sourceBuilder.query(QueryBuilders.rangeQuery("create_time").format("yyyy-MM-dd HH:mm:ss").from(startTime).to(endTime));//date数据模型，可以使用时间区间进行查询
        //sourceBuilder.query(QueryBuilders.matchAllQuery());                                                   //匹配该索引中所有数据
        //sourceBuilder.query(QueryBuilders.matchQuery("create_by","aprwu"));                                   //单个匹配,需要完全相同
        //sourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.wildcardQuery("file_name","*陌生*")));

        //sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));//设置超时时间，该超时不是指的到时间停止搜索，而是指的是展示该时间之前的数据，后面有数据还是会在搜索
        //sourceBuilder.size(300);       //设置展示几条信息
        searchRequest.source(sourceBuilder);
        //查询总数
        SearchRequest totalRequest = new SearchRequest();
        SearchSourceBuilder totalSource = new SearchSourceBuilder();
        totalSource.query(QueryBuilders.matchAllQuery());
        try {
            SearchResponse response = client.search(searchRequest);
            SearchResponse total = client.search(totalRequest);
/*
            Arrays.stream(response.getHits().getHits())
                    .forEach(i -> {
                        System.out.println("sourceAsString------" + i.getSourceAsString());
                    });
*/
            long end=System.currentTimeMillis();
            System.out.println("查询总时间："+(end-start));
            insertTimeDao.insertTime(new InsertTime(UUID.randomUUID().toString(),response.getHits().totalHits+"","sys","query",(end-start)+"ms",total.getHits().totalHits+"", DateUtils.datetime2string(new Date())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取客户端
    private RestHighLevelClient getElastic() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")
                )
        );
    }
}
