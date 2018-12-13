package com.enlink.elasticsearch.controller;

import com.enlink.elasticsearch.entity.AdminLog;
import com.google.gson.Gson;
import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.threadpool.ThreadPool;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 9:37 2018/9/20
 */
public class ESBulkInsert {
    private static String index = "admin";
    private static String type = "doc";

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            BulkProcessor();
        }
    }

    public void BulkInsert() {

        AdminLog admin = resource.admin1();
        Gson gson = new Gson();
        String s = gson.toJson(admin);
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(new IndexRequest(index, type)
                .source(s, XContentType.JSON));

    }

    public static void BulkProcessor() {
        RestHighLevelClient client = getElastic();
        Settings settings = Settings.EMPTY;
        //构建线程池
        ThreadPool threadPool = new ThreadPool(settings);
        //构建buld listener
        BulkProcessor.Listener listener = new BulkProcessor.Listener() {

            @Override
            //重写beforeBulk，在每次bulk request发出前执行，在这个方法里面可以知道本次批量有多少操作
            public void beforeBulk(long executionId, BulkRequest request) {
                int i = request.numberOfActions();
                System.out.println("Executing bulk [{}] with {} requests" + "----executionId:" + executionId + "----i:" + i);
            }

            @Override
            //重写afterBulk，在每次批量请求结束后执行，可以看到是否有错误发生
            public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
                if (request.hasIndexRequestsWithPipelines()) {
                    System.out.println("Bulk [{}] executed with failures" + executionId);
                } else {
                    System.out.println("Bulk [{}] completed in {} milliseconds" + "----executionId:"+executionId + "----response.getTook().getMillis():"+response.getTook().getMillis());
                }
            }

            @Override
            //重写方法，如果发生错误就会调用
            public void afterBulk(long executionId, BulkRequest request, Throwable failure) {

            }
        };
        //使用builder做批量操作的控制
        BiConsumer<BulkRequest, ActionListener<BulkResponse>> bulkConsumer =
                (request, bulkListener) -> client.bulkAsync(request, bulkListener);

        //在这里调用build()方法构造bulkProcessor,在底层实际上是用了bulk的异步操作
        BulkProcessor.Builder builder = BulkProcessor.builder(bulkConsumer, listener);
        BulkProcessor bulkProcessor = builder.build();
        //执行多少次后刷新bulk，默认1000，-1禁用
        builder.setBulkActions(100);
        //执行的动作大小超过多少时，刷新bulk，默认5M，-1禁用
        builder.setBulkSize(new ByteSizeValue(1L, ByteSizeUnit.MB));
        //最多允许多少请求同时执行，默认时1，0是只允许一个
        builder.setConcurrentRequests(1);
        //设置刷新bulk的时间间隔，默认不刷新
        builder.setFlushInterval(TimeValue.timeValueSeconds(10L));
        //设置补偿机制，由于资源限制（比如线程池满，批量操作会失败，在这里定义重试次数）
        builder.setBackoffPolicy(BackoffPolicy
                .constantBackoff(TimeValue.timeValueSeconds(1L), 3));
        //新建请求
        AdminLog admin = resource.admin1();
        Gson gson = new Gson();
        String s = gson.toJson(admin);
        IndexRequest one = new IndexRequest(index, type, UUID.randomUUID().toString())
                .source(s,XContentType.JSON);
        IndexRequest two = new IndexRequest(index, type, UUID.randomUUID().toString())
                .source(s,XContentType.JSON);
        IndexRequest three = new IndexRequest(index, type, UUID.randomUUID().toString())
                .source(s,XContentType.JSON);
        IndexRequest four = new IndexRequest(index, type, UUID.randomUUID().toString())
                .source(s,XContentType.JSON);
        //将新建的请求加入上面配置的bulkProcessor中
        bulkProcessor.add(one);
        bulkProcessor.add(two);
        bulkProcessor.add(three);
        bulkProcessor.add(four);
        //bulkProcessor必须关闭才能执行上面的操作，第一种、立即关闭
//        bulkProcessor.close();
        //第二种、调用awaitClose。就是在规定时间里，全部操作完成true，未完成false
        try {
            bulkProcessor.awaitClose(30L, TimeUnit.SECONDS);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //获取客户端
    private static RestHighLevelClient getElastic() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")
                )
        );
    }
}
