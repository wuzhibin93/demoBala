package DocumentT;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;

import java.io.IOException;

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


    public RestHighLevelClient client(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.100.51", 9200, "http")
                )
        );
        return client;
    }
}
