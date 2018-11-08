package com.enlink.de.elasticsearch.controller;

import com.enlink.de.elasticsearch.entity.AdminLog;
import com.enlink.de.elasticsearch.entity.ResourceLog;
import com.enlink.de.elasticsearch.entity.SystemLog;
import com.enlink.de.elasticsearch.entity.UserLog;
import com.enlink.de.util.DateUtils;
import com.google.gson.Gson;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 17:30 2018/9/13
 */
@RestController
public class ESClusterInsert {

    public static void main(String[] args) throws Exception {
//        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 10; i++) {
//            executorService.execute(new userInsert());
//            executorService.execute(new adminInsert());
//            executorService.execute(new systemInsert());
//            executorService.execute(new resourceInsert());
//            executorService.execute(new userInsert());
//            executorService.execute(new adminInsert());
//            executorService.execute(new systemInsert());
//            executorService.execute(new resourceInsert());


//            userInsert userInsert = new userInsert();
//            new Thread(userInsert).start();
//            adminInsert adminInsert = new adminInsert();
//            new Thread(adminInsert).start();
//            systemInsert systemInsert = new systemInsert();
//            new Thread(systemInsert).start();
//            resourceInsert resourceInsert = new resourceInsert();
//            new Thread(resourceInsert).start();

        }
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 40, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(4));

        System.out.println("启动时间："+DateUtils.datetime2string(new Date()));
        int x = 5;
        for(int i=0;i<x;i++){
            userInsert myTask = new userInsert();
            systemInsert systemInsert = new systemInsert();
            resourceInsert resourceInsert = new resourceInsert();
            adminInsert adminInsert = new adminInsert();
            executor.execute(myTask);
            executor.execute(systemInsert);
            executor.execute(resourceInsert);
            executor.execute(adminInsert);

            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
        }

        executor.shutdown();
    }
 }


class getClient{
    //获取客户端
    static RestHighLevelClient getElastic() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.174.141", 9200, "http")
                )
        );
    }
}
class userInsert implements Runnable {
    RestHighLevelClient client = getClient.getElastic();
    //private static InsertTimeDao timeDao = SpringContextHolder.getBean(InsertTimeDao.class);

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        String index = "user";
        String type = "doc";
        IndexRequest indexRequest = new IndexRequest(index, type);
        UserLog user = resource.user1();
        Gson gson = new Gson();
        String s = gson.toJson(user);
        indexRequest.source(s, XContentType.JSON);
        int x = 0;
        //插入100条
        for (int i = 0; i < 1000; i++) {
            try {
                client.index(indexRequest);
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
class adminInsert  implements Runnable {
    RestHighLevelClient client = getClient.getElastic();
    //private static InsertTimeDao timeDao = SpringContextHolder.getBean(InsertTimeDao.class);

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        String index = "admin";
        String type = "doc";
        IndexRequest indexRequest = new IndexRequest(index, type);
        AdminLog admin = resource.admin1();
        Gson gson = new Gson();
        String s = gson.toJson(admin);
        indexRequest.source(s, XContentType.JSON);
        int x = 0;
        //插入100条
        for (int i = 0; i < 1000; i++) {
            try {
                client.index(indexRequest);
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
class resourceInsert  implements Runnable {
    RestHighLevelClient client = getClient.getElastic();
    //private static InsertTimeDao timeDao = SpringContextHolder.getBean(InsertTimeDao.class);

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        String index = "res";
        String type = "doc";
        IndexRequest indexRequest = new IndexRequest(index, type);
        ResourceLog res = resource.res1();
        Gson gson = new Gson();
        String s = gson.toJson(res);
        indexRequest.source(s, XContentType.JSON);
        int x = 0;
        //插入100条
        for (int i = 0; i < 1000; i++) {
            try {
                client.index(indexRequest);
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
class systemInsert  implements Runnable {
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
        indexRequest.source(s, XContentType.JSON);
        int x = 0;
        //插入100条
        for (int i = 0; i < 1000; i++) {
            try {
                client.index(indexRequest);
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
//准备数据源
class resource {
    //资源日志1
    static ResourceLog res1(){
        ResourceLog resourceLog = new ResourceLog();
        resourceLog.setLog_level("INFO");
        resourceLog.setSession_id("6580ebe139b9b424c2c886cff6afc36b");
        resourceLog.setUser_id("304");
        resourceLog.setUser_name("vpntest");
        resourceLog.setUser_group("vpntest");
        resourceLog.setIp_address("183.206.20.205");
        resourceLog.setStatus("200");
        resourceLog.setFloat_response_time(0.003f);
        resourceLog.setLog_timestamp(DateUtils.datetime2string(new Date()));
        resourceLog.setResource_name("xyw.njucm.edu.cn/picture/article/27/ac/d2/12fb705049d6b11e3ea5c242b950/bd324a64-b11d-4dd3-a300-22f0fdb4abd7.jpg");
        resourceLog.setUri("xyw.njucm.edu.cn");
        resourceLog.setUplink_traffic(1618);
        resourceLog.setDownlink_traffic(457);
        resourceLog.setTotal_traffic(2075);
        resourceLog.setBrowser_info("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
        resourceLog.setRequest_referer("");
        resourceLog.setRequest_count("5");
        resourceLog.setUrl_http("http://204e039aba5119b51dc79e3efa0dbf1b.vpn1.njucm.edu.cn/s/27/t/7/e6/1d/info58909.htm");
        resourceLog.setFile_format("GET /picture/article/27/ac/d2/12fb705049d6b11e3ea5c242b950/bd324a64-b11d-4dd3-a300-22f0fdb4abd7.jpg HTTP/1.1");
        resourceLog.setApp_type("Enwas");
        return resourceLog;
    }
    //资源日志2
    static ResourceLog res2(){
        ResourceLog resourceLog = new ResourceLog();
        resourceLog.setLog_level("INFO");
        resourceLog.setSession_id("39da3f232d592d4de133c230602e200e");
        resourceLog.setUser_id("wisedu");
        resourceLog.setUser_name("vpntest");
        resourceLog.setUser_group("vpntest");
        resourceLog.setIp_address("183.206.20.205");
        resourceLog.setStatus("200");
        resourceLog.setFloat_response_time(0.002f);
        resourceLog.setLog_timestamp(DateUtils.datetime2string(new Date()));
        resourceLog.setResource_name("www.sinomed.ac.cn/images/libg4.jpg");
        resourceLog.setUri("www.sinomed.ac.cn");
        resourceLog.setUplink_traffic(1248);
        resourceLog.setDownlink_traffic(1249);
        resourceLog.setTotal_traffic(2497);
        resourceLog.setBrowser_info("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
        resourceLog.setRequest_referer("GET /images/libg4.jpg HTTP/1.1");
        resourceLog.setRequest_count("8");
        resourceLog.setUrl_http("http://d0ff8459a0ddaadabd9cc067f2404a6c.vpn1.njucm.edu.cn/css/index.css");
        resourceLog.setFile_format("image/jpeg");
        resourceLog.setApp_type("Enwas");
        return resourceLog;
    }


    //系统日志1
    static SystemLog sys1() {
        SystemLog systemLog = new SystemLog();
        systemLog.setLog_info("ERROR");
        systemLog.setLog_level("SERVICE");
        systemLog.setLog_timestamp(DateUtils.datetime2string(new Date()));
        systemLog.setOperate_type("管理控制引擎服务异常，重启中");
        return systemLog;
    }
    //系统日志2
    static SystemLog sys2() {
        SystemLog systemLog = new SystemLog();
        systemLog.setLog_info("ERROR");
        systemLog.setLog_level("HD");
        systemLog.setLog_timestamp(DateUtils.datetime2string(new Date()));
        systemLog.setOperate_type("磁盘已使用94%");
        return systemLog;
    }

    //用户日志1
     static UserLog user1() {
        UserLog userLog = new UserLog();
        userLog.setKeyword_log_level("INFO");
        userLog.setLog_timestamp(DateUtils.datetime2string(new Date()));
        userLog.setOperation("MSG");
        userLog.setStatus("READ");
        userLog.setLog_info("在Windows有登陆，若非本人操作，请及时修改密码");
        userLog.setUser_id("");
        userLog.setUser_name("跋山涉水");
        userLog.setUser_group("wyq");
        userLog.setUser_auth("zaf");
        userLog.setIp_address("");
        userLog.setCertificate_server("117.89.131.123:52042");
        userLog.setLink_interface("interface 1");
        userLog.setDevice_os("Windows");
        userLog.setDevice_type("web");
        userLog.setClient_info("web app v3.0");
        userLog.setMac_address("39da3f232d592d4de133c230602e200e");
        return userLog;
    }
    //用户日志2
    static UserLog user2() {
        UserLog userLog = new UserLog();
        userLog.setKeyword_log_level("INFO");
        userLog.setLog_timestamp(DateUtils.datetime2string(new Date()));
        userLog.setOperation("LOGIN");
        userLog.setStatus("SUCCESS");
        userLog.setLog_info("SUCCESS");
        userLog.setUser_id("1526");
        userLog.setUser_name("zaf");
        userLog.setUser_group("wyq");
        userLog.setUser_auth("Client_user");
        userLog.setIp_address("192.168.32.181");
        userLog.setCertificate_server("117.89.131.123:52042");
        userLog.setLink_interface("interface 1");
        userLog.setDevice_os("PC");
        userLog.setDevice_type("");
        userLog.setClient_info("web app v3.0");
        userLog.setMac_address("39da3f232d592d4de133c230602e200e");
        return userLog;
    }

    //管理员日志1
    static AdminLog admin1() {
        AdminLog adminLog = new AdminLog();
        adminLog.setLog_level("WARNING");
        adminLog.setLog_timestamp(DateUtils.datetime2string(new Date()));
        adminLog.setOs("Windows");
        adminLog.setRight_name("超级权限");
        adminLog.setOperation_method("修改");
        adminLog.setLog_info("更新LICENSE");
        adminLog.setUser_id("1");
        adminLog.setUser_name("");
        adminLog.setFull_name("admin");
        adminLog.setRole_name("超级管理员");
        adminLog.setIp_address("192.168.5.125");
        adminLog.setSession_id("0254799d-4898-46bc-9a26-74d8b8e03fac");
        adminLog.setKeyword_status("失败原因：License 不合法");
        return adminLog;
    }
    //管理员日志2
    static AdminLog admin2() {
        AdminLog adminLog = new AdminLog();
        adminLog.setLog_level("INFO");
        adminLog.setLog_timestamp(DateUtils.datetime2string(new Date()));
        adminLog.setOs("Windows");
        adminLog.setRight_name("超级权限");
        adminLog.setOperation_method("查询");
        adminLog.setLog_info("获取ip列表");
        adminLog.setUser_id("1");
        adminLog.setUser_name("");
        adminLog.setFull_name("admin");
        adminLog.setRole_name("超级管理员");
        adminLog.setIp_address("192.168.5.125");
        adminLog.setSession_id("0254799d-4898-46bc-9a26-74d8b8e03fac");
        adminLog.setKeyword_status("成功");
        return adminLog;
    }
}
