package com.enlink.util;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.max.InternalMax;
import org.elasticsearch.search.aggregations.metrics.min.InternalMin;
import org.elasticsearch.search.aggregations.metrics.sum.InternalSum;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by LCY on 4/8/2018.
 *
 * 使用JAVA API对ES进行查询删除操作
 */

public class ESUtil {

    /**
     * 简单的查询
     * select * from {index} where {field}={Accept} limit {size};
     * @param index  查询的索引
     * @param type   查询的type,可以使用heda查看
     * @param field  查询的字段
     * @param Accept 查询的内容
     * @param size   查询结果的条数
     * @return SearchResponse的Json
     */
    public List<String> queryByFilter_Accept(String index, String type, String field, String Accept, int size) throws UnknownHostException {
        TransportClient client=getClient();
        SearchResponse response = client.prepareSearch(index)//设置要查询的索引(index)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes(type)//设置type, 这个在建立索引的时候同时设置了, 或者可以使用head工具查看
                .setQuery(QueryBuilders.matchQuery(field, Accept)) //在这里"message"是要查询的field,"Accept"是要查询的内容
                .setFrom(0)
                .setSize(size)
                .setExplain(true)
                .execute()
                .actionGet();
        List<String> docList = new ArrayList<String>();
        for (SearchHit hit : response.getHits()) {
            docList.add(hit.getSourceAsString());
        }
        client.close();
        return docList;
    }

    /**
     * 简单的查询
     * select * from {index} limit {size};
     * @param index 查询的索引
     * @param type  查询的type,可以使用heda查看
     * @param size  查询结果的条数
     * @return 结果list
     */
    public List<String> queryByFilter(String index, String type, int size) throws UnknownHostException {
        TransportClient client=getClient();
        SearchResponse response = client.prepareSearch(index)//设置要查询的索引(index)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes(type)//设置type, 这个在建立索引的时候同时设置了, 或者可以使用head工具查看
                .setFrom(0)
                .setSize(size)
                .setExplain(true)
                .execute()
                .actionGet();
        List<String> docList = new ArrayList<String>();
        for (SearchHit hit : response.getHits()) {
            docList.add(hit.getSourceAsString());
        }
        client.close();
        return docList;
    }

    /**
     * 删除一条数据
     *DELETE FROM {index} WHERE id = {id};
     * @param index
     * @param type
     * @param id
     */
//    public boolean deleteDoc(String index, String type, String id) throws UnknownHostException {
//        boolean flg = false;
//        TransportClient client=getClient();
//        DeleteResponse deleteResponse = client
//                .prepareDelete()
//                .setIndex(index)
//                .setType(type)
//                .setId(id)
//                .get();
//        if (deleteResponse.isFound()) {
//            flg = true;
//        }
//        client.close();
//        return flg;
//    }

    /**
     * 使用min聚合查询某个字段上最小的值。
     *SELECT Min({field}) FROM index；
     * @param index
     * @param type
     */
    public double minDoc(String index, String type, String field) throws UnknownHostException {
        TransportClient client=getClient();
        SearchResponse response = client
                .prepareSearch(index)
                .setTypes(type)
                .addAggregation(AggregationBuilders.min("min").field(field))
                .get();

        InternalMin min = response.getAggregations().get("min");
        client.close();
        return  min.getValue();
    }
    /**
     * 使用max聚合查询某个字段上最大的值。
     * SELECT MAX(field) FROM index；
     * @param index
     * @param type
     */
    public double maxDoc(String index, String type, String field) throws UnknownHostException {
        TransportClient client=getClient();
        SearchResponse response = client
                .prepareSearch(index)
                .setTypes(type)
                .addAggregation(AggregationBuilders.max("max").field(field))
                .get();

        InternalMax max = response.getAggregations().get("max");
        return  max.getValue();
    }
    /**
     * 使用max聚合查询某个字段上最大的值。
     * SELECT SUM(field) FROM index；
     * @param index
     * @param type
     */
    public double sumDoc(String index, String type, String field) throws UnknownHostException {
        TransportClient client=getClient();
        SearchResponse response = client
                .prepareSearch(index)
                .setTypes(type)
                .addAggregation(AggregationBuilders.sum("sum").field(field))
                .get();

        InternalSum sum = response.getAggregations().get("sum");
        client.close();
        return  sum.getValue();
    }

    /**
     *  聚合查询
     *
     * @param index
     * @param type
     * @param group_name
     * @param size
     */
    public void testSumGroupBy(String index, String type, String group_name, int size) throws UnknownHostException {
        TransportClient client=getClient();
        TermsAggregationBuilder group_return = AggregationBuilders
                .terms("group_return").field(group_name);
        SearchRequestBuilder searchRequestBuilder =client.prepareSearch(index).setTypes(type)
                .addAggregation(group_return)
                .setSize(size);
        SearchResponse sr = searchRequestBuilder.execute().actionGet();
        Terms genders = sr.getAggregations().get("group_return");
        for (Terms.Bucket entry : genders.getBuckets()) {
            System.out.println("Key: "+entry.getKey()+"\t\tDoc:"+entry.getDocCount());
        }
        client.close();
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
    public void getGroupBy(String index, String type, String group_name1, String group_name2,int size,String timeOut) throws UnknownHostException {
        TransportClient client=getClient();
        TermsAggregationBuilder group_return1 = AggregationBuilders
                .terms("group_return1").field(group_name1);
        TermsAggregationBuilder group_return2 = AggregationBuilders
                .terms("group_return2").field(group_name2);
        group_return1.subAggregation(group_return2);
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTypes(type)
                .addAggregation(group_return1).setSize(size);

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

    /**
     *  嵌套聚合查询存储
     * select {group_name1},{group_name2}  from {index} GROUP BY {group_name1} limit {size};
     * @param index
     * @param type
     * @param group_name1 外层分组的字段
     * @param group_name2 内层分组的字段
     * @param size
     *
     * @return 查询结果的map，三层map，为json对应的位置
     */
    public HashMap getGroupByMap(String index, String type, String group_name1, String group_name2,int size,String timeOut) throws UnknownHostException {
        TransportClient client=getClient();
        TermsAggregationBuilder group_return1 = AggregationBuilders
                .terms("group_return1").field(group_name1);
        TermsAggregationBuilder group_return2 = AggregationBuilders
                .terms("group_return2").field(group_name2);
        group_return1.subAggregation(group_return2);
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTypes(type)
                .addAggregation(group_return1).setSize(size);
                //.setTimeout(timeOut);
        SearchResponse sr = searchRequestBuilder.execute().actionGet();
        Terms group1 = sr.getAggregations().get("group_return1");
        HashMap<Integer, HashMap<String, Object>> res=new HashMap<>();
        for (Terms.Bucket entry_group1 : group1.getBuckets()) {
            HashMap<String,Object> res1=new HashMap<String,Object>();
            res1.put("Key",entry_group1.getKey());
            res1.put("DocCount",entry_group1.getDocCount());
            Terms group2 = entry_group1.getAggregations().get("group_return2");
            for (Terms.Bucket entry_group2 : group2.getBuckets()) {
                HashMap<Object, Long> res2=new HashMap<>();
                res2.put(entry_group2.getKey(),entry_group2.getDocCount());
                res1.put("Buckets",res2);
            }
            res.put(size,res1);
            size--;
        }
        client.close();
        return res;
    }
    /**
     *  嵌套聚合查询存储
     * select {group_name1},{group_name2}  from {index} GROUP BY {group_name1} ;
     * @param index
     * @param type
     * @param group_name1 外层分组的字段
     * @param group_name2 内层分组的字段
     *
     * @return 查询结果的map，三层map，为json的Aggregation对应的三层
     */
    public HashMap getGroupByMap(String index, String type, String group_name1, String group_name2,String timeOut) throws UnknownHostException {
        TransportClient client=getClient();
        int size=0;
        TermsAggregationBuilder group_return1 = AggregationBuilders
                .terms("group_return1").field(group_name1);
        TermsAggregationBuilder group_return2 = AggregationBuilders
                .terms("group_return2").field(group_name2);
        group_return1.subAggregation(group_return2);
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTypes(type)
                .addAggregation(group_return1).setSize(size);
                //.setTimeout(timeOut);
        SearchResponse sr = searchRequestBuilder.execute().actionGet();
        Terms group1 = sr.getAggregations().get("group_return1");
        HashMap<Integer, HashMap<String, Object>> res=new HashMap<>();
        for (Terms.Bucket entry_group1 : group1.getBuckets()) {
            HashMap<String,Object> res1=new HashMap<String,Object>();
            res1.put("Key",entry_group1.getKey());
            res1.put("DocCount",entry_group1.getDocCount());
            Terms group2 = entry_group1.getAggregations().get("group_return2");
            for (Terms.Bucket entry_group2 : group2.getBuckets()) {
                HashMap<Object, Long> res2=new HashMap<>();
                res2.put(entry_group2.getKey(),entry_group2.getDocCount());
                res1.put("Buckets",res2);
            }
            res.put(size,res1);
            size++;
        }
        client.close();
        return res;
    }
    /**
     * 嵌套查询获取打印结果打印
     * * select {group_name1},{group_name2}  from {index} GROUP BY {group_name1} limit {size};
     * @param index
     * @param type
     * @param group_name1 外层分组的字段
     * @param group_name2 内层分组的字段
     * @param size  获取的结果的大小
     *
     */
    public void getGroupPrint(String index, String type, String group_name1, String group_name2,int size,String timeOut) throws UnknownHostException {
        HashMap hashMap=getGroupByMap(index, type,  group_name1, group_name2, size, timeOut);
        for (Object h : hashMap.keySet()) {
            HashMap hashMap1 = (HashMap) hashMap.get(h);
            System.out.println("Key: "+ hashMap1.get("Key")+"\t\tCountDoc:"+ hashMap1.get("DocCount"));
            HashMap hashMap2= (HashMap)hashMap1.get("Buckets");
            for (Object key :hashMap2.keySet()) {
                System.out.println("\t\t"+"Key: "+key+"\t\tCountDoc:"+ hashMap2.get(key));
            }
        }
    }

    /**
     * 聚合查询获得Sum结果结果打印
     * @SQL  select sum{group_name}  from {index} WHERE field_name=field_value GROUP BY {group_name};
     * @param index
     * @param type
     * @param  field_name 要精确查询的字段名
     * @param  field_value 要精确查询的字段值
     * @param group_name 分组的字段名
     * @param timeOut  查询时间
     *
     */
    public double getGroupSum(String index, String type, String field_name,String field_value, String group_name,String timeOut) throws UnknownHostException {
        TransportClient client=getClient();
        SumAggregationBuilder group_sum = AggregationBuilders.sum("group_sum").field(group_name);
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setTypes(type)
                .setQuery(QueryBuilders.matchQuery(field_name, field_value))
                .addAggregation(group_sum)
                .setSize(0);
                //.setTimeout(timeOut);
        SearchResponse sr = searchRequestBuilder.execute().actionGet();
        InternalSum amount = sr.getAggregations().get("group_sum");
        client.close();
        return amount.value();
    }
    //获取客户端
    private RestHighLevelClient getElastic() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")
                )
        );
    }
    //获取客户端---TransportClient
    private TransportClient getClient() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("client.transport.sniff", true)      //自动嗅探，连接集群只要写一个地址就可以
                //.put("cluster.name", "enlink")                          //设置ES实例的名称
                .build();
        return new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"),9300));
    }
}