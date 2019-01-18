package MixT;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 16:30 2018/9/10
 */
public class MixTest {
    @Test
    public void test1(){
        String zipUrl = "D:\\usr\\local\\enlink\\upload\\download\\logs\\1536567765880\\ww.zip";
        File file = new File(zipUrl);
        String path = file.getPath();
        System.out.println(path);
    }
    @Test
    public void test2(){
        String args[] = {"/bin/sh", "-c", "sssssssssssss"};


    }

    //matchAllQuery
    @Test
    public void test3() throws IOException {
        RestHighLevelClient client = getElastic();
        String index = "res";//admin,res,user,sys
        long start = System.currentTimeMillis();
        //查询总数
        SearchRequest totalRequest = new SearchRequest();
        SearchSourceBuilder totalSource = new SearchSourceBuilder();
        totalSource.query(QueryBuilders.matchAllQuery());
        try {
            SearchResponse total = client.search(totalRequest);
            long end = System.currentTimeMillis();
            System.out.println("查询总时间：" + (end - start));
            System.out.println(total.getHits().totalHits);
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.close();
    }



//    @Test
//    public void test5(){
//        ElasticsearchConfig elasticsearchConfig = new ElasticsearchConfig();
//        String hosts = elasticsearchConfig.getHosts();
//        System.out.println(hosts);
//    }

    //获取客户端
    private RestHighLevelClient getElastic() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.63.179", 9200, "http")
                )
        );
    }
    @Test
    public void test1111(){
        int i = fun2(2016);
        System.out.println(i);
    }
    int fun2(int n)
    {
        int num = 0;
        int i, j;

        for (i = 5; i <= n; i += 5)
        {
            j = i;
            while (j % 5 == 0)
            {
                num++;
                j /= 5;
            }
        }
        return num;
    }
}
