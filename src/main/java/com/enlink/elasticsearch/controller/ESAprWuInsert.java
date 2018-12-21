package com.enlink.elasticsearch.controller;

import com.enlink.elasticsearch.entity.SystemLog;
import com.google.gson.Gson;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 13:59 2018/9/25
 */
public class ESAprWuInsert {
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 40, 200, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(4));


    public static void main(String[] args) {
        systemInsert systemInsert = new systemInsert();
        new Thread(systemInsert).start();

    }

    //获取客户端
    private static RestHighLevelClient getElastic() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("47.95.228.116", 9200, "http")
                )
        );
    }

    static class systemInsert implements Runnable {
        RestHighLevelClient client = getClient.getElastic();
        //private static InsertTimeDao timeDao = SpringContextHolder.getBean(InsertTimeDao.class);

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            String index = "sys";
            String type = "doc";
            IndexRequest indexRequest = new IndexRequest(index, type);
            SystemLog sys = resource.sys1();
            Gson gson = new Gson();
            String s = gson.toJson(sys);
            BulkRequest bulkRequest = new BulkRequest();
            bulkRequest.add(new IndexRequest(index, type)
                    .source(s, XContentType.JSON));
            int x = 0;
            //插入100条
            for (int i = 0; i < 1000; i++) {
                try {
                    getElastic().bulk(bulkRequest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                x++;
            }

            long end = System.currentTimeMillis();
            System.out.println("程序运行时间： " + (end - start) + "ms");
            //timeDao.insertTime(new InsertTime(UUID.randomUUID().toString(), x + "", "user", "insert", (end - start) + "ms"));
        }
    }
}
