//package ElasticSearchLocalTest;
//
//import com.enlink.de.base.IndicesCreateInfo;
//import com.enlink.de.elasticsearch.entity.HotelCourseFile;
//import com.enlink.de.elasticsearch.entity.User;
//import com.google.gson.Gson;
//import org.apache.http.HttpHost;
//import org.apache.logging.log4j.util.Strings;
//import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
//import org.elasticsearch.action.admin.indices.refresh.RefreshRequest;
//import org.elasticsearch.action.delete.DeleteRequest;
//import org.elasticsearch.action.delete.DeleteResponse;
//import org.elasticsearch.action.get.GetRequest;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.unit.TimeValue;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.elasticsearch.index.query.*;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.elasticsearch.search.sort.SortOrder;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
///**
// * @ClassName :
// * @Author Aprwu
// * @Description : TODO(类的作用)
// * @Date : Created in 21:06 2018/9/2
// */
//
//public class ElasticsearchTest1 {
//    private String index;
//
//    private String type;
//
//    @Before
//    public void prepare() {
//        index = "demo"; //索引类型
//        type = "demo";  //类型名称
//    }
//
//    RestHighLevelClient client = getElasticSearchClient();
//
//    //创建索引------IndexRequest
//    @Test
//    public void demo1() throws IOException {
//        IndexRequest indexRequest = new IndexRequest(index, type);
//        User news = new User();
//        news.setBrith("中国产小型无人机的“对手”来了，俄微型拦截导弹便宜量又多");
//        news.setPassword("军事");
//        news.setWork("2018-01-24T23:59:30Z");
//        Gson g = new Gson();
//        String source = g.toJson(news);
//        indexRequest.source(source, XContentType.JSON);
//        try {
//            client.index(indexRequest);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        client.close();
//    }
//
//    //搜索索引------GetRequest
//    @Test
//    public void demo2() throws IOException {
//        GetRequest getRequest = new GetRequest("hotel", "course", "SC0-x2UBrgsilirLtCVv");
//        GetResponse getResponse = client.get(getRequest);
//        if (getResponse.isExists()) {
//            System.out.println(getResponse.getSourceAsString());
//        } else {
//            System.out.println("Index : " + getResponse.getIndex() + " type : " + getResponse.getType() + " id : " + getResponse.getId() + " is not exists!");
//        }
//        client.close();
//
//    }
//
//    //使用SearchRequest高级查询------支持多文档查询，聚合操作。可以完全取代GetRequest
//    @Test
//    public void demo5() throws Exception {
//        //创建
//        SearchRequest searchRequest = new SearchRequest("hotel");//指定索引库
//        String indexName = "hotel";
//        if (client.indices().exists(new GetIndexRequest().indices(indexName))) {
//            System.out.println("索引存在------");
//        }
//        searchRequest.indices(indexName);
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//
//        //创建QueryBuilder
//        BoolQueryBuilder booleanQuery = QueryBuilders.boolQuery();
//        QueryBuilder must = booleanQuery.must(QueryBuilders.rangeQuery("create_time").format("yyyy-MM-dd HH:mm:ss").from("2016-12-15 5:20:45").to("2016-12-15 15:20:45"));
//
//        //搜索
//        searchSourceBuilder.query(must);
//        searchSourceBuilder.sort("create_time", SortOrder.DESC);
//        searchRequest.source(searchSourceBuilder);
//
//        SearchResponse response = client.search(searchRequest);
//        long totalHits = response.getHits().getTotalHits();
//        System.out.println("totalHits------" + totalHits);
//        if (response.getHits().getHits().length > 0) {
//            for (SearchHit hit : response.getHits().getHits()) {
//                System.out.println("hit------" + hit.getSourceAsString());
//            }
//        }
//    }
//
//    //test5查询失败，测试test6
//    //使用QueryBuilder
//    @Test
//    public void test6() {
//        //创建------
//        //可以在创建的时候指定index，SearchRequest searchRequest = new SearchRequest("some_index*");,支持带*号的模糊匹配
//        SearchRequest searchRequest = new SearchRequest("hotel");
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//
//        //termQuery("key",obj)          完全匹配
//        //termsQuery("key",obj1,obj2)   一次匹配多个值，感觉只能精准查询
//        //rangeQuery();                 区间查询
//        //matchAllQuery();              匹配所有文件
//        //matchQuery("key",obj)         单个匹配，field不支持通配符，前缀具有高级特性
//        //multiMatchQuery()             匹配多个字段，field有通配符特性，
//        //boolquery();                  复合查询
//        //wildcardQuery();              模糊查询，支持通配符
//        String startTime = "2016-12-15 5:20:45";
//        String endTime = "2016-12-15 15:20:45";
//        //sourceBuilder.query(QueryBuilders.termQuery("file_name","jpg"));                                      //完全匹配
//        //sourceBuilder.query(QueryBuilders.termsQuery("file_name","陌生的来客.doc","能不能不送开水？.doc"));    //一次查多个值，感觉之只能精准查询，无法模糊查询模糊匹配，在字段中，有相应的即可
//        //sourceBuilder.query(QueryBuilders.rangeQuery("create_time").format("yyyy-MM-dd HH:mm:ss").from(startTime).to(endTime));//date数据模型，可以使用时间区间进行查询
//        //sourceBuilder.query(QueryBuilders.matchAllQuery());                                                   //匹配该索引中所有数据
//        //sourceBuilder.query(QueryBuilders.matchQuery("create_by","aprwu"));                                   //单个匹配,需要完全相同
//        sourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.wildcardQuery("file_name","*陌生*")));
//
//        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));//设置超时时间，该超时不是指的到时间停止搜索，而是指的是展示该时间之前的数据，后面有数据还是会在搜索
//        sourceBuilder.size(300);       //设置展示几条信息
//        searchRequest.source(sourceBuilder);
//        List<String> queryAll = new ArrayList<>();
//        try {
//            SearchResponse response = client.search(searchRequest);
//
//            /*String scrollId = response.getScrollId();
//            SearchHit[] hits = response.getHits().getHits();
//            List<Map<String, Object>> listData = new ArrayList<>();
//            while (hits != null && hits.length > 0) {
//                for (SearchHit sh : hits) {
//                    listData.add(sh.getSourceAsMap());
//                }
//            }*/
//            Arrays.stream(response.getHits().getHits())
//                    .forEach(i -> {
//                        //queryAll.add(i.getSourceAsString());
//                        System.out.println("sourceAsString------" + i.getSourceAsString());
//                        /*System.out.println("id" + i.getId());
//                        System.out.println("index------" + i.getIndex());
//                        System.out.println("score------" + i.getScore());
//                        System.out.println("type------" + i.getType());*/
//                    });
//
//            System.out.println(response.getHits().totalHits);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        for (String s : queryAll) {
//            System.out.println(s);
//        }
//
//    }
//
//    //删除索引---删除后即时刷新索引,保证数据一致------DeleteRequest
//    @Test
//    public void demo4() throws IOException {
//        DeleteRequest request = new DeleteRequest("demo", "demo", "1");
//        DeleteResponse response = client.delete(request);
//        RefreshRequest refreshRequest = new RefreshRequest("demo");
//        client.indices().refresh(refreshRequest);
//        client.close();
//    }
//
//    //ping elasticsearch
//    @Test
//    public void demo3() throws IOException {
//        boolean ping = client.ping();
//        System.out.println(ping);
//        client.close();
//    }
//
//    //QueryBuilder的使用
//    //使用QueryBuilder
//    //termQuery("key",obj)          完全匹配
//    //termsQuery("key",obj1,obj2)   一次匹配多个值
//    //matchQuery("key",obj)         单个匹配，field不支持通配符，前缀具有高级特性
//    //multiMatchQuery()             匹配多个字段，field有通配符特性，
//    //matchAllQuery();              匹配所有文件
//    @Test
//    public void test4() {
//        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
//        String year = "2016";
//        String startTime = "19:00:00";
//        String endTime = "20:00:00";
//        RangeQueryBuilder create_time = QueryBuilders.rangeQuery("create_time").format("yyyy-MM-dd HH:mm:ss").from(startTime).to(endTime);
//        boolQuery.must(create_time);
//
//    }
//
//    private RestHighLevelClient getElasticSearchClient() {
//        return new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost("localhost", 9200, "http")
//                )
//        );
//    }
//
//    //创建查询条件
//    public QueryBuilder createQueryBuilder(HotelCourseFile request) {
//        BoolQueryBuilder booleanQuery = QueryBuilders.boolQuery();
//        if (null != request) {
//            booleanQuery.must(QueryBuilders.rangeQuery("create_time").format("yyyy-MM-dd HH:mm:ss").from("2016-12-05 12:20:00").to("2016-12-06 12:20:00"));
//            if (Strings.isNotBlank(request.getStatus())) {
//                booleanQuery.must(QueryBuilders.wildcardQuery("status", "*" + request.getStatus() + "*"));
//            }
//            if (Strings.isNotBlank(request.getAttach_id())) {
//                booleanQuery.must(QueryBuilders.wildcardQuery("attach_id", "*" + request.getAttach_id() + "*"));
//            }
//            if (Strings.isNotBlank(request.getClass_id())) {
//                booleanQuery.must(QueryBuilders.wildcardQuery("class_id", "*" + request.getClass_id() + "*"));
//            }
//            if (Strings.isNotBlank(request.getCreate_by())) {
//                booleanQuery.must(QueryBuilders.wildcardQuery("create_by", "*" + request.getCreate_by() + "*"));
//            }
//            if (Strings.isNotBlank(request.getFile_name())) {
//                booleanQuery.must(QueryBuilders.wildcardQuery("file_name", "*" + request.getFile_name() + "*"));
//            }
//            if (Strings.isNotBlank(request.getFile_path())) {
//                booleanQuery.must(QueryBuilders.wildcardQuery("file_path", "*" + request.getFile_path() + "*"));
//            }
//            if (Strings.isNotBlank(request.getFile_size())) {
//                booleanQuery.must(QueryBuilders.wildcardQuery("file_size", "*" + request.getFile_size() + "*"));
//            }
//        }
//        return booleanQuery;
//    }
//
//    @Test
//    public void getIndicesCI() {
//        new IndicesCreateInfo.IndicesCIBuilder("hotel")
//                .setMappings("{\n" +
//                        "  \"doc\": {\n" +
//                        "    \"properties\": {\n" +
//                        "      \"attach_id\": {\n" +
//                        "        \"type\": \"keyword\"\n" +
//                        "      },\n" +
//                        "      \"class_id\": {\n" +
//                        "        \"type\": \"keyword\"\n" +
//                        "      },\n" +
//                        "      \"file_name\": {\n" +
//                        "        \"type\": \"keyword\"\n" +
//                        "      },\n" +
//                        "      \"file_type\": {\n" +
//                        "        \"type\": \"keyword\"\n" +
//                        "      },\n" +
//                        "      \"file_path\": {\n" +
//                        "        \"type\": \"keyword\"\n" +
//                        "      },\n" +
//                        "      \"file_size\": {\n" +
//                        "        \"type\": \"keyword\"\n" +
//                        "      },\n" +
//                        "      \"status\": {\n" +
//                        "        \"type\": \"keyword\"\n" +
//                        "      },\n" +
//                        "      \"create_by\": {\n" +
//                        "        \"type\": \"keyword\"\n" +
//                        "      },\n" +
//                        "      \"create_time\": {\n" +
//                        "        \"type\": \"date\",\n" +
//                        "        \"format\": \"strict_date_optional_time||yyyy-MM-dd HH:mm:ss||yyyy-MM-dd HH:mm:ss.SSS||dd/MMM/yyyy:HH:mm:ss Z||epoch_millis\"\n" +
//                        "      }\n" +
//                        "    }\n" +
//                        "  }\n" +
//                        "}")
//                .create();
//    }
//}
