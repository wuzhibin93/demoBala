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
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 20:06 2018/9/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class ElasticQueryMethod {
    RestHighLevelClient client = getElastic();
    @Resource
    private InsertTimeDao insertTimeDao;


    @Test
    public void test10() throws IOException {
        test1();
        test2();test2();
        test3();test3();
        test4();test4();
        test5();test5();
        test6();test6();
        test7();test7();
        client.close();
    }

    //matchAllQuery
    @Test
    public void test1() throws IOException {

        String index = "res";//admin,res,user,sys
        long start=System.currentTimeMillis();
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(sourceBuilder);
        //查询总数
        SearchRequest totalRequest = new SearchRequest();
        SearchSourceBuilder totalSource = new SearchSourceBuilder();
        totalSource.query(QueryBuilders.matchAllQuery());
        try {
            SearchResponse response = client.search(searchRequest);
            SearchResponse total = client.search(totalRequest);
            long end=System.currentTimeMillis();
            System.out.println("查询总时间："+(end-start));
            System.out.println(total.getHits().totalHits);
            //insertTimeDao.insertTime(new InsertTime(UUID.randomUUID().toString(),response.getHits().totalHits+"",index,"matchAllQuery",(end-start)+"ms",total.getHits().totalHits+"",DateUtils.datetime2string(new Date())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //termQuery("key",obj)          完全匹配
    @Test
    public void test2() throws IOException {
        String index = "sys";//admin,res,user,sys
        long start=System.currentTimeMillis();
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("log_level","SERVICE"));
        searchRequest.source(sourceBuilder);
        //当前索引中的总数据
        long l = thatIndexTotal(index);

        //集群中所有数据总数
        SearchRequest totalRequest = new SearchRequest();
        SearchSourceBuilder totalSource = new SearchSourceBuilder();
        totalSource.query(QueryBuilders.matchAllQuery());
        try {
            SearchResponse response = client.search(searchRequest);
            SearchResponse total = client.search(totalRequest);
            long end=System.currentTimeMillis();
            System.out.println("查询总时间："+(end-start));
            insertTimeDao.insertTime(new InsertTime(UUID.randomUUID().toString(),response.getHits().totalHits+"",index,"termQuery",(end-start)+"ms",total.getHits().totalHits+"",DateUtils.datetime2string(new Date()),l+""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //termsQuery("key",obj1,obj2)   一次匹配多个值,不区分大小写
    @Test
    public void test3() throws IOException {
        String index = "user";//admin,res,user,sys
        long start=System.currentTimeMillis();
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("status","SUCCESS"));
        searchRequest.source(sourceBuilder);
        //当前索引中的总数据
        long l = thatIndexTotal(index);
        //查询总数
        SearchRequest totalRequest = new SearchRequest();
        SearchSourceBuilder totalSource = new SearchSourceBuilder();
        totalSource.query(QueryBuilders.matchAllQuery());
        try {
            SearchResponse response = client.search(searchRequest);
            SearchResponse total = client.search(totalRequest);
            long end=System.currentTimeMillis();
            System.out.println("查询总时间："+(end-start));
            insertTimeDao.insertTime(new InsertTime(UUID.randomUUID().toString(),response.getHits().totalHits+"",index,"termsQuery",(end-start)+"ms",total.getHits().totalHits+"",DateUtils.datetime2string(new Date()),l+""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //rangeQuery();                 区间查询
    @Test
    public void test4() throws IOException {
        String index = "admin";//admin,res,user,sys
        long start=System.currentTimeMillis();
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        String startTime = "2018-09-17 13:30:15";
        String endTime = "2018-09-17 14:30:16";
        sourceBuilder.query(QueryBuilders.rangeQuery("log_timestamp").format("yyyy-MM-dd HH:mm:ss").from(startTime).to(endTime));//date数据模型，可以使用时间区间进行查询
        searchRequest.source(sourceBuilder);
        //当前索引中的总数据
        long l = thatIndexTotal(index);
        //查询总数
        SearchRequest totalRequest = new SearchRequest();
        SearchSourceBuilder totalSource = new SearchSourceBuilder();
        totalSource.query(QueryBuilders.matchAllQuery());
        try {
            SearchResponse response = client.search(searchRequest);
            SearchResponse total = client.search(totalRequest);
            long end=System.currentTimeMillis();
            System.out.println("查询总时间："+(end-start));
            insertTimeDao.insertTime(new InsertTime(UUID.randomUUID().toString(),response.getHits().totalHits+"",index,"rangeQuery",(end-start)+"ms",total.getHits().totalHits+"",DateUtils.datetime2string(new Date()),l+""));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //matchQuery("key",obj)         单个匹配,需要完全相同
    @Test
    public void test5() throws IOException{
        String index = "sys";//admin,res,user,sys
        long start=System.currentTimeMillis();
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();;
        sourceBuilder.query(QueryBuilders.matchQuery("log_level","SERVICE"));//单个匹配,需要完全相同
        searchRequest.source(sourceBuilder);
        //当前索引中的总数据
        long l = thatIndexTotal(index);
        //查询总数
        SearchRequest totalRequest = new SearchRequest();
        SearchSourceBuilder totalSource = new SearchSourceBuilder();
        totalSource.query(QueryBuilders.matchAllQuery());
        try {
            SearchResponse response = client.search(searchRequest);
            SearchResponse total = client.search(totalRequest);
            long end=System.currentTimeMillis();
            System.out.println("查询总时间："+(end-start));
            insertTimeDao.insertTime(new InsertTime(UUID.randomUUID().toString(),response.getHits().totalHits+"",index,"matchQuery",(end-start)+"ms",total.getHits().totalHits+"",DateUtils.datetime2string(new Date()),l+""));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //multiMatchQuery()             匹配多个字段，field有通配符特性，
    @Test
    public void test6() throws IOException {
        String index = "user";//admin,res,user,sys
        long start=System.currentTimeMillis();
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.wildcardQuery("log_info","*登陆*")));
        searchRequest.source(sourceBuilder);
        //当前索引中的总数据
        long l = thatIndexTotal(index);
        //查询总数
        SearchRequest totalRequest = new SearchRequest();
        SearchSourceBuilder totalSource = new SearchSourceBuilder();
        totalSource.query(QueryBuilders.matchAllQuery());
        try {
            SearchResponse response = client.search(searchRequest);
            SearchResponse total = client.search(totalRequest);
            long end=System.currentTimeMillis();
            System.out.println("查询总时间："+(end-start));
            insertTimeDao.insertTime(new InsertTime(UUID.randomUUID().toString(),response.getHits().totalHits+"",index,"multiMatchQuery",(end-start)+"ms",total.getHits().totalHits+"",DateUtils.datetime2string(new Date()),l+""));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //aggregation()             聚合查询
    @Test
    public void test7() throws IOException {
        String index = "res";//admin,res,user,sys
        long start=System.currentTimeMillis();
        TransportClient client = getClient();
        RestHighLevelClient elastic = getElastic();
        SearchResponse response = client
                .prepareSearch(index)
                .setTypes("doc")
                .addAggregation(AggregationBuilders.max("max").field("log_timestamp"))
                .get();
        Aggregation max = response.getAggregations().get("max");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //System.out.println(simpleDateFormat.format(max));
        System.out.println(max);
        System.out.println(max.getName());
        System.out.println(max.getMetaData());
        System.out.println(max.getType());
        //当前索引中的总数据
        long l = thatIndexTotal(index);
        //查询总数
        SearchRequest totalRequest = new SearchRequest();
        SearchSourceBuilder totalSource = new SearchSourceBuilder();
        totalSource.query(QueryBuilders.matchAllQuery());
        try {
            SearchResponse total = elastic.search(totalRequest);
            long end=System.currentTimeMillis();
            System.out.println("查询总时间："+(end-start));
            insertTimeDao.insertTime(new InsertTime(UUID.randomUUID().toString(),response.getHits().totalHits+"",index,"aggregation",(end-start)+"ms",total.getHits().totalHits+"",DateUtils.datetime2string(new Date()),l+""));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    //获取客户端---RestHighLevelClient
    private RestHighLevelClient getElastic() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.63.179", 9200, "http")
                )
        );
    }

    //获取客户端---TransportClient
    private TransportClient getClient() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("client.transport.sniff", true)      //自动嗅探，连接集群只要写一个地址就可以
                .put("cluster.name", "enlink")                          //设置ES实例的名称
                .build();
        return new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.63.179"),9300));
    }
    //获取当前索引中的总数
    private long thatIndexTotal(String index) throws IOException {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(sourceBuilder);
        SearchResponse response = client.search(searchRequest);
        return response.getHits().totalHits;
    }
}
