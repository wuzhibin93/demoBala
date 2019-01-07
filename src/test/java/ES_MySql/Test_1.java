package ES_MySql;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.enlink.Application;
import com.enlink.dao.OperationDAO;
import com.enlink.entity.Key;
import com.enlink.entity.Operation;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 13:32 2018/12/6
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class Test_1 {

    private String index;
    private String type;

    @Resource
    private OperationDAO operationDAO;

    @Before
    public void param() {
        index = "rally-races-2019-01";
        type = "races";
    }

    @Test
    //依照ID取得文档内容
    public void test1() throws IOException {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("_id", "To7uhWcBs9X0R3Iv3clw"));
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = client().search(searchRequest);
        System.out.println(search);
    }

    //遍历JSON
    @Test
    public void test2() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key1","value1");
        jsonObject.put("key2","value2");
        jsonObject.put("key3","value3");

        for (String j:jsonObject.keySet()) {
            System.out.println(j);
        }
        String value = jsonObject.get("key1").toString();
        System.out.println("value------"+value);
    }

    @Test
    //取得索引中全部ID
    public void test3() throws IOException, ParseException {
        //获取ID总数
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = client().search(searchRequest);

        //使用聚合查询，获取ID的集合
        SearchResponse response = Tclient()
                .prepareSearch(index)
                .setQuery(QueryBuilders.matchAllQuery())
                .addAggregation(
                        AggregationBuilders.terms("result").field("_id").size((int) search.getHits().totalHits)
                )
                .get();
        Terms agg1 = response.getAggregations().get("result");
        System.out.println("agg1------"+agg1);
        //修改得到的字符串，使得更容易操作
        String agg2 = agg1.toString().substring(67);
        String substring = agg2.substring(0, agg2.length() - 2);
        System.out.println("substring------"+substring);
        //转化取得的json获得每个ID
        JSONObject parse = JSON.parseObject("{" + substring + "}");
        JSONArray json_id = parse.getJSONArray("buckets");
        List<Key> keys = JSON.parseObject(json_id.toJSONString(), new TypeReference<List<Key>>() {
        });
        //System.out.println(substring);
        for (Key key : keys) {
            System.out.println(key);
            //使用每个ID查出每个索引中的需要的数据
            GetRequestBuilder getRequestBuilder = Tclient().prepareGet(index, type, key.getKey());
            GetResponse documentFields = getRequestBuilder.get();
            //原始数据转JSON
            JSONObject jsonObject = JSON.parseObject(documentFields.toString());
            System.out.println("jsonObject------"+jsonObject);
            //取出时间
            JSONObject source = jsonObject.getJSONObject("_source");
            //取出节点信息
            JSONObject cluster = jsonObject.getJSONObject("_source").getJSONObject("cluster");
            //取出results中对应信息
            JSONObject results = jsonObject.getJSONObject("_source").getJSONObject("results");
            //取出标签
            JSONObject user_tags = jsonObject.getJSONObject("_source").getJSONObject("user-tags");
            //取出flush_time_per_shard中的信息
            JSONObject flush_time_per_shard = jsonObject.getJSONObject("_source").getJSONObject("results").getJSONObject("flush_time_per_shard");
            //取出merge_throttle_time_per_shard
            JSONObject merge_throttle_time_per_shard = jsonObject.getJSONObject("_source").getJSONObject("results").getJSONObject("merge_throttle_time_per_shard");
            //取出total_time_per_shard
            JSONObject total_time_per_shard = jsonObject.getJSONObject("_source").getJSONObject("results").getJSONObject("total_time_per_shard");
            //取出index-append对应的信息
            JSONArray op_metrics = jsonObject.getJSONObject("_source").getJSONObject("results").getJSONArray("op_metrics");
            System.out.println("op_metrics------" + op_metrics);
            for (int i = 0; i < op_metrics.size(); i++) {
                Object o = op_metrics.get(i);
                Operation operation = JSON.parseObject(o.toString(), new TypeReference<Operation>() {
                });
                if (operation.getOperation().equals("index-append") || operation.getOperation().equals("index")) {
                    operation.setId(key.getKey());
                    operation.setUnit(operation.getThroughput().getUnit());
                    operation.setMax(operation.getThroughput().getMax());
                    operation.setMedian(operation.getThroughput().getMedian());
                    operation.setMin(operation.getThroughput().getMin());
                    operation.setTrack(source.get("track").toString());
                    operation.setChallenge(source.get("challenge").toString());
                    operation.setEsVersion(cluster.get("distribution-version").toString());
                    operation.setPipeline(source.get("pipeline").toString());
                    operation.setRally_version(source.get("rally-version").toString());
                    operation.setNode_count(cluster.get("node-count").toString());
                    if (cluster.get("node-count").toString().equals("1")){
                        operation.setIsCluster("false");
                    }else {
                        operation.setIsCluster("true");
                    }
                    if (null == results.get("flush_time")){
                        operation.setFlush_time(null);
                    }else {
                        operation.setFlush_time(results.get("flush_time").toString());
                    }
                    operation.setRefresh_time(results.get("refresh_time").toString());
                    operation.setMemory_norms(results.get("memory_norms").toString());
                    operation.setSegment_count(results.get("segment_count").toString());
                    operation.setMemory_norms(results.get("memory_terms").toString());
                    operation.setMemory_doc_value(results.get("memory_doc_values").toString());
                    operation.setMemory_segments(results.get("memory_segments").toString());
                    operation.setOld_gc_time(results.get("old_gc_time").toString());
                    operation.setYoung_gc_time(results.get("young_gc_time").toString());
                    operation.setTranslog_size(results.get("translog_size").toString());
                    if(null == results.get("merge_throttle_time")){
                        operation.setMerge_throttle_time(null);
                    }else{
                        operation.setMerge_throttle_time(results.get("merge_throttle_time").toString());
                    }
                    operation.setTotal_time(results.get("total_time").toString());
                    operation.setMemory_terms(results.get("memory_terms").toString());
                    operation.setMemory_stored_field(results.get("memory_stored_fields").toString());
                    if (null == flush_time_per_shard.get("max")){
                        operation.setFlush_time_shard(null);
                    }else {
                        operation.setFlush_time_shard(flush_time_per_shard.get("max").toString());
                    }
                    if (null == merge_throttle_time_per_shard.get("max")){
                        operation.setMerge_throttle_time_per_shard_max(null);
                    }else {
                        operation.setMerge_throttle_time_per_shard_max(merge_throttle_time_per_shard.get("max").toString());
                    }
                    operation.setTotal_time_per_shard(total_time_per_shard.get("max").toString());
                    operation.setTrial_timestamp(change(source.get("trial-timestamp").toString()));
                    //下面是标签中的内容
                    if (null == user_tags.get("data")){
                        operation.setUser_tags(null);
                    }else {
                        operation.setUser_tags("data:"+user_tags.get("data").toString());
                    }
                    if(null == user_tags.get("car")){
                        operation.setCar(null);
                    }else {
                        operation.setCar(user_tags.get("car").toString());
                    }
                    System.out.println("o------" + operation);
                    operationDAO.insertOperation(operation);
                }
                if (operation.getOperation().equals("default") || operation.getOperation().equals("search")){
                    operation.setId(key.getKey());
                    operation.setSearch("default_search");
                    operation.setSearch_min(operation.getThroughput().getMin());
                    operation.setSearch_median(operation.getThroughput().getMedian());
                    operation.setSearch_max(operation.getThroughput().getMax());
                    operation.setSearch_unit(operation.getThroughput().getUnit());
                    System.out.println(op_metrics.get(i));
                    operationDAO.updateOperation(operation);
                }
            }
        }
    }

    @Test
    public void test4() throws IOException {
        //获取ID总数
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = client().search(searchRequest);

        //使用聚合查询，获取ID的集合
        SearchResponse response = Tclient()
                .prepareSearch(index)
                .setQuery(QueryBuilders.matchAllQuery())
                .addAggregation(
                        AggregationBuilders.terms("result").field("_id").size((int) search.getHits().totalHits)
                )
                .get();
        Terms agg1 = response.getAggregations().get("result");
        System.out.println("agg1------"+agg1);
        //修改得到的字符串，使得更容易操作
        String agg2 = agg1.toString().substring(67);
        String substring = agg2.substring(0, agg2.length() - 2);
        System.out.println("substring------"+substring);
        //转化取得的json获得每个ID
        JSONObject parse = JSON.parseObject("{" + substring + "}");
        JSONArray json_id = parse.getJSONArray("buckets");
        List<Key> keys = JSON.parseObject(json_id.toJSONString(), new TypeReference<List<Key>>() {
        });
        System.out.println(keys.size());
        for (Key key_id: keys) {
            System.out.println(key_id);
        }
    }
    private static java.sql.Timestamp change(String time) throws ParseException {
        StringBuilder stringBuilder = new StringBuilder(time);
        stringBuilder.insert(4,"-");
        stringBuilder.insert(7,"-");
        stringBuilder.insert(13,":");
        stringBuilder.insert(16,":");
        stringBuilder.replace(10,11," ");
        stringBuilder.replace(19,20,"");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = simpleDateFormat.parse(stringBuilder.toString());
        return new java.sql.Timestamp(parse.getTime());
    }

    /**
     * 获取客户端RestHighLevelClient
     *
     * @return
     */
    private static RestHighLevelClient client() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.226.133", 9200, "http")
                )
        );
    }

    /**
     * 获取客戶端TransportClient
     *
     * @return
     * @throws UnknownHostException
     */
    private static TransportClient Tclient() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("client.transport.sniff", false)
                .put("cluster.name", "elasticsearch")
                .build();
        return new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.226.133"), 9300));
    }
}
