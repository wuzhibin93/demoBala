package ElasticSearchLocalTest;

import com.enlink.de.util.ESUtil;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(使用transportclient进行聚合操作)
 * @Date : Created in 13:37 2018/9/14
 */
public class ElasticsearchTest3 {
    /**
     * 使用min聚合查询某个字段上最小的值。
     * SELECT Min({field}) FROM index；
     */
    @Test
    public void test1() throws Exception{
        TransportClient client = getClient();
        SearchResponse response = client
                .prepareSearch("hotel")
                .setTypes("doc")
                .addAggregation(AggregationBuilders
                        .terms("*.jpg")
                        .field("attach_id")
                        .subAggregation(AggregationBuilders
                                .avg("avg")
                                .field("class_id"))
                )
                .get();
        Aggregation avg = response.getAggregations().get("avg");
        client.close();
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //System.out.println(simpleDateFormat.format(max.getName()));
        System.out.println("max.getName()---"+avg.getName()+"max.getName()---"+avg.getType()+"max.getMetaData()---"+avg.getMetaData());
    }


    @Test
    public void test2() throws Exception{
        //所有的索引
        SearchRequestBuilder sbuilder = getClient().prepareSearch("hotel").setTypes("doc");
        TermsAggregationBuilder field = AggregationBuilders.terms("player_count ").field("team");
        sbuilder.addAggregation(field);
        SearchResponse response = sbuilder.execute().actionGet();
        SearchRequest totalRequest = new SearchRequest();
        ActionFuture<SearchResponse> search = getClient().search(totalRequest);
        System.out.println(search.actionGet().getHits().totalHits);

    }
    @Test
    public void test3() throws Exception{
        SearchResponse sr = getClient().prepareSearch("hotel")
                .setTypes("course")
                .addAggregation(
                        AggregationBuilders.terms("all_author")
                                .field("create_by") )
                .get();
        Aggregation all_author = sr.getAggregations().get("all_author");
        System.out.println(all_author);
    }


    private static TransportClient getClient() throws Exception{
        Settings settings = Settings.builder()
                .build();
        return new PreBuiltTransportClient(settings)
                //.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.63.183"), 9200))
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300))
                ;
    }
    /**
     *  嵌套聚合查询
     * select {group_name1},{group_name2}  from {index} GROUP BY {group_name1} limit {size};
     * @param index
     * @param type
     * @param group_name1 外层分组的字段
     * @param group_name2 内层分组的字段
     * @param size
     */
    public void getGroupBy(String index, String type, String group_name1, String group_name2,int size,String timeOut) throws Exception {
        TransportClient client=getClient();
        TermsAggregationBuilder termsBuilder = AggregationBuilders
                .terms("group_return1").field(group_name1);
        TermsAggregationBuilder termsBuilder1=  AggregationBuilders
                .terms("group_return2").field(group_name2);
        termsBuilder.subAggregation(termsBuilder1);
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTypes(type)
                .addAggregation(termsBuilder).setSize(size)
                .setTimeout(TimeValue.MINUS_ONE);
        SearchResponse sr = searchRequestBuilder.execute().actionGet();
        Terms group1 = sr.getAggregations().get("group_return1");
        for (Terms.Bucket entry_group1 : group1.getBuckets()) {
            System.out.println("Key: "+entry_group1.getKey()+"\t\tCountDoc:"+entry_group1.getDocCount());
            Terms group2 = entry_group1.getAggregations().get("group_return2");
            for (Terms.Bucket entry_group2 : group2.getBuckets()) {
                System.out.println("\t\t"+"Key: "+entry_group2.getKey()+"\t\tCountDoc:"+entry_group2.getDocCount());
            }
        }
        client.close();
    }
    @Test
    public void test5() throws UnknownHostException {
        ESUtil esUtil = new ESUtil();
        double groupSum = esUtil.getGroupSum("hotel", "doc", "file_size", "0.04MB", "size", "");
        System.out.println(groupSum);

    }
}
