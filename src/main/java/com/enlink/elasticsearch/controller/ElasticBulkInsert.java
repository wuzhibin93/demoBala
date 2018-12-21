package com.enlink.elasticsearch.controller;

import com.enlink.elasticsearch.entity.AdminLog;
import com.enlink.elasticsearch.entity.ResourceLog;
import com.enlink.elasticsearch.entity.SystemLog;
import com.enlink.elasticsearch.entity.UserLog;
import com.enlink.util.DateUtils;
import com.google.gson.Gson;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 9:53 2018/9/19
 */
public class ElasticBulkInsert {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 40, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(4));
        System.out.println("启动时间："+ DateUtils.datetime2string(new Date()));
        for (int i = 0; i < 5; i++) {
            executor.submit(new resourceInsert());
            executor.submit(new systemInsert());
            executor.submit(new userInsert());
            executor.submit(new adminInsert());
        }

    }

    static class resourceInsert implements Runnable {
        RestHighLevelClient client = getClient.getElastic();
        //private static InsertTimeDao timeDao = SpringContextHolder.getBean(InsertTimeDao.class);

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            String index = "res";
            String type = "doc";
            ResourceLog res = null;
            try {
                res = resource.res1();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Gson gson = new Gson();
            String s = gson.toJson(res);
            BulkRequest bulkRequest = new BulkRequest();
            bulkRequest.add(new IndexRequest(index, type)
                    .source(s, XContentType.JSON));
            int x = 0;
            //插入100条
            for (int i = 0; i < 10000; i++) {
                try {
                    client.bulk(bulkRequest);
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

    static class userInsert implements Runnable {
        RestHighLevelClient client = getClient.getElastic();
        //private static InsertTimeDao timeDao = SpringContextHolder.getBean(InsertTimeDao.class);

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            String index = "user";
            String type = "doc";
            UserLog user = resource.user1();
            Gson gson = new Gson();
            String s = gson.toJson(user);
            BulkRequest bulkRequest = new BulkRequest();
            bulkRequest.add(new IndexRequest(index, type)
                    .source(s, XContentType.JSON));
            int x = 0;
            //插入100条
            for (int i = 0; i < 10000; i++) {
                try {
                    client.bulk(bulkRequest);
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

    static class adminInsert implements Runnable {
        RestHighLevelClient client = getClient.getElastic();
        //private static InsertTimeDao timeDao = SpringContextHolder.getBean(InsertTimeDao.class);

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            String index = "admin";
            String type = "doc";
            AdminLog admin = resource.admin1();
            Gson gson = new Gson();
            String s = gson.toJson(admin);
            BulkRequest bulkRequest = new BulkRequest();
            bulkRequest.add(new IndexRequest(index, type)
                    .source(s, XContentType.JSON));
            int x = 0;
            //插入100条
            for (int i = 0; i < 10000; i++) {
                try {
                    client.bulk(bulkRequest);
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
            for (int i = 0; i < 10000; i++) {
                try {
                    client.bulk(bulkRequest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                x++;
            }

            long end = System.currentTimeMillis();
            System.out.println("程序运行时间： " + (end - start) + "ms");
            //timeDao.insertTime(new InsertTime(UUID.randomUUID().toString(), x + "", "user", "insert", (end - start) + "ms"));
        }

        //获取客户端
        private static RestHighLevelClient getElastic() {
            return new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost("192.168.63.179", 9200, "http")
                    )
            );
        }
    }
}