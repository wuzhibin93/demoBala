package DocumentT;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.ShardSearchFailure;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.avg.Avg;
import org.elasticsearch.search.aggregations.metrics.max.MaxAggregator;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 14:41 2018/12/25
 */
public class DocumentT {
    @Test
    public void test1() throws IOException {
        SearchRequest searchRequest = new SearchRequest("my_index");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = client().search(searchRequest);
        System.out.println(search);
    }
    @Test
    public void test2() throws IOException {
        String[] strings = new String[2];
        strings[0] = "my_doc";
        strings[1] = "user";
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchRequest searchRequest = new SearchRequest(strings,searchSourceBuilder);
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = client().search(searchRequest);
        System.out.println(search);

    }

    //boolQuery()
    @Test
    public void test3() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchRequest searchRequest = new SearchRequest("car");

        searchSourceBuilder.query(QueryBuilders.boolQuery()
                //.must(QueryBuilders.matchAllQuery())
                .must(QueryBuilders.termQuery("price",2000))
                .mustNot(QueryBuilders.termQuery("make","ford")));

        searchRequest.source(searchSourceBuilder);
        SearchResponse search = client().search(searchRequest);
        System.out.println(search);
    }

    //排序
    @Test
    public void test4() throws IOException {
        SearchRequest searchRequest = new SearchRequest("car");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.sort(new FieldSortBuilder("user.age").order(SortOrder.DESC));
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = client().search(searchRequest);
        System.out.println(search);

    }

    //关闭source
    @Test
    public void test5() throws IOException {
        SearchRequest searchRequest = new SearchRequest("car");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.fetchSource(false);
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = client().search(searchRequest);
        System.out.println(search);
    }

    //高亮
    @Test
    public void test6() throws IOException {
        SearchRequest searchRequest = new SearchRequest("car");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field hightlightTitle = new HighlightBuilder.Field("user.name");
        hightlightTitle.highlighterType("unified");
        highlightBuilder.field(hightlightTitle);
        HighlightBuilder.Field highlightUser = new HighlightBuilder.Field("make");
        highlightBuilder.field(highlightUser);
        searchSourceBuilder.highlighter(highlightBuilder);
        SearchResponse search = client().search(searchRequest);
        System.out.println(search);
    }

    @Test
    public void test7() throws IOException {
        SearchRequest searchRequest = new SearchRequest("car");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        SearchSourceBuilder query = searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        SearchRequest source = searchRequest.source(query);
        SearchResponse search = client().search(source);
        
        System.out.println(search);

    }

    //searchResponse相关
    @Test
    public void test8() throws IOException {
        SearchRequest searchRequest = new SearchRequest("car");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client().search(searchRequest);

//        SearchResponse searchResponse = new SearchResponse();
        SearchHits hits = searchResponse.getHits();
        //是否超时
        boolean timedOut = searchResponse.isTimedOut();
        //耗时
        TimeValue took = searchResponse.getTook();
        //返回结果状态
        RestStatus status = searchResponse.status();
        //提前终止
        Boolean terminatedEarly = searchResponse.isTerminatedEarly();
        int totalShards = searchResponse.getTotalShards();
        int successfulShards = searchResponse.getSuccessfulShards();
        int failedShards = searchResponse.getFailedShards();
        ShardSearchFailure[] shardFailures = searchResponse.getShardFailures();
        System.out.println("shardFailures---"+shardFailures);
        for (ShardSearchFailure shardFailure : shardFailures) {
            System.out.println("shardFailure---"+shardFailure);
        }

        System.out.println("successfulShards------"+successfulShards);
        System.out.println("failedShards------"+failedShards);
        System.out.println("totalShards------"+totalShards);
        System.out.println("terminatedEarly---"+terminatedEarly);
        System.out.println("timeout---"+timedOut);
        System.out.println("took---"+took);
        System.out.println("status---"+status);
        System.out.println(hits.totalHits);
    }

    //SearchHits相关,
    @Test
    public void test9() throws IOException {
        SearchRequest searchRequest = new SearchRequest("car");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = client().search(searchRequest);
        SearchHits hits = search.getHits();
        //总共多少文档
        long totalHits = hits.getTotalHits();
        //最大得分
        float maxScore = hits.getMaxScore();
        //
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            System.out.println("documentFields---"+documentFields.getId());
            System.out.println("documentFields---"+documentFields.getSourceAsString());
        }
        //获取当前下标的数据
        SearchHit at = hits.getAt(2);
        System.out.println("at---"+at.getId());
        System.out.println("at---"+at.getSourceAsString());
        System.out.println("at---"+at.getIndex());
        System.out.println("totalHits---"+totalHits);
        System.out.println("maxScore---"+maxScore);
    }

    @Test
    public void test10() throws IOException {
        SearchRequest searchRequest = new SearchRequest("car");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = client().search(searchRequest);

        SearchHits hits = search.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (SearchHit documentFields : hits1) {
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
            Object make = sourceAsMap.get("make");
            //获取user
            Object user = sourceAsMap.get("user");
            System.out.println("user---"+user);
            System.out.println("make---"+make);
        }
    }

    //查询结果高亮,未测通
    @Test
    public void test11() throws IOException {
        SearchResponse search = search();
        SearchHits hits = search.getHits();
        for (SearchHit hit : hits) {
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField user = highlightFields.get("make");
            System.out.println(user);
            Text[] fragments = user.fragments();
            String string = fragments[0].string();
            System.out.println(string);
        }
        System.out.println(search.getHits().totalHits);
    }

    @Test
    public void test12() throws IOException {
        Aggregations aggregations = search().getAggregations();
        Terms name = aggregations.get("user.name");
        Terms.Bucket elastic = name.getBucketByKey("Elastic");
        MaxAggregator value_count = elastic.getAggregations().get("value_count");

        //System.out.println(value);

    }

    @Test
    public void test13() throws IOException {

    }


    private SearchResponse search() throws IOException {
        SearchRequest searchRequest = new SearchRequest("car");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        return client().search(searchRequest);
    }
    public RestHighLevelClient client(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.100.51", 9200, "http")
                )
        );
        return client;
    }
}
