package com.enlink.elasticsearch.controller;

import com.enlink.elasticsearch.entity.HotelCourseFile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 19:07 2018/9/11
 */
public class ESController {
    public static void main(String[] args) throws Exception{
        String jsonString = "{" + "\"user\":\"xiefg\"," + "\"date\":\"2018-01-12\","
                + "\"message\":\"trying out Elasticsearch\"" + "}";

        for (int i = 0; i < 30; i++) {
            postRequest("casebase", "doc", "2018-01-05 15:33:54" , jsonString);
        }
        getElastic().close();
    }
    public static SearchResponse twwwa(String keyword1, String keyword2, String startTime, String endTime) {
        //keyword1 关键字1
        //keyword2 关键字2
        //startTime 起始时间
        //endTime 结束时间

        //searchsorcebuilde 类似查询语句中最外层的部分，包括分页的起始
        //查询语句的核心，查询结果的排序，查询结果截取部分返回等一系列配置
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //结果开始处
        searchSourceBuilder.from();
        //查询结果终止处
        searchSourceBuilder.size();
        //查询的等待时间
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("","");
        QueryBuilders.matchQuery("message", keyword1 + "" + keyword2);
        //同时满足两个关键字
        matchQueryBuilder.operator(Operator.AND);
        //查询在时间区间范围的查询结果
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("data");
        if (!"".equals(startTime)) {
            rangeQueryBuilder.gte(startTime);
        }
        if (!"".equals(endTime)) {
            rangeQueryBuilder.lte(endTime);
        }
        //等同bool，将两个查询合并
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(matchQueryBuilder);
        boolQueryBuilder.must(rangeQueryBuilder);
        //排序
        FieldSortBuilder fieldSortBuilder = SortBuilders.fieldSort("data");
        fieldSortBuilder.order(SortOrder.DESC);
        searchSourceBuilder.sort(fieldSortBuilder);
        SearchRequest searchRequest = new SearchRequest("request");
        searchRequest.types("course");
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = null;
        try {
            response = getElastic().search(searchRequest);
            SearchHits hits = response.getHits();
            int totalHits = (int) hits.getTotalHits();
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .create();
            for (SearchHit searchHit : hits) {
                Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
                HotelCourseFile hotelCourseFile = gson.fromJson(gson.toJson(sourceAsMap), HotelCourseFile.class);
                System.out.println(hotelCourseFile);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static IndexResponse postRequest(String index, String type, String id, String jsonSource) throws Exception{
        //构建请求
        IndexRequest indexRequest = new IndexRequest(index, type, id);
        //将保存数据以Json格式关联到请求
        indexRequest.source(jsonSource, XContentType.JSON);
        //Java客户端发起保存数据请求
        IndexResponse re = getElastic().index(indexRequest);
        //等待结果
        System.out.println(re);
        return re;
    }

    //获取ElasticClient对象
    private static RestHighLevelClient getElastic() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.174.123", 9200, "http")
                )
        );
    }
}
