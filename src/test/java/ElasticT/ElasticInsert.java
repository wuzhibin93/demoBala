package ElasticT;

import com.enlink.Application;
import com.enlink.elasticsearch.dao.InsertTimeDao;
import com.enlink.elasticsearch.entity.*;
import com.enlink.util.DateUtils;
import com.google.gson.Gson;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 9:42 2018/9/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class ElasticInsert{
    @Resource
    private InsertTimeDao insertTimeDao;


    RestHighLevelClient client = getElastic();


    /**
     * 对ES集群插入数据
     */
    @Test
    public void testInsert() throws Exception {
        try {
            insertAdmin();
            insertRes();
            insertSys();
            insertUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.close();
    }

    //插入资源日志
    @Test
    public void insertRes() throws Exception {
        long start = System.currentTimeMillis();
        String index = "res";
        String type = "doc";
        IndexRequest indexRequest = new IndexRequest(index, type);
        ResourceLog res = res1();
        Gson gson = new Gson();
        String s = gson.toJson(res);
        indexRequest.source(s, XContentType.JSON);

        SearchRequest totalRequest = new SearchRequest();
        SearchSourceBuilder totalSource = new SearchSourceBuilder();
        totalSource.query(QueryBuilders.matchAllQuery());
        SearchResponse total = client.search(totalRequest);
        int x = 0;
        //插入100条
        for (int i = 0; i < 10000; i++) {
            client.index(indexRequest);
            x++;
        }

        long end = System.currentTimeMillis();
        System.out.println("程序运行时间： " + (end - start) + "ms");
        insertTimeDao.insertTime(new InsertTime(UUID.randomUUID().toString(), x + "", "res", "insert", (end - start) + "ms", total.getHits().totalHits + "",DateUtils.datetime2string(new Date())));
    }

    //插入系统日志
    @Test
    public void insertSys() throws Exception {
        long start = System.currentTimeMillis();
        String index = "sys";
        String type = "doc";
        IndexRequest indexRequest = new IndexRequest(index, type);
        SystemLog sys = sys1();
        Gson gson = new Gson();
        String s = gson.toJson(sys);
        indexRequest.source(s, XContentType.JSON);

        SearchRequest totalRequest = new SearchRequest();
        SearchSourceBuilder totalSource = new SearchSourceBuilder();
        totalSource.query(QueryBuilders.matchAllQuery());
        SearchResponse total = client.search(totalRequest);
        int x = 0;
        //插入100条
        for (int i = 0; i < 10000; i++) {
            client.index(indexRequest);
            x++;
        }

        long end = System.currentTimeMillis();
        System.out.println("程序运行时间： " + (end - start) + "ms");
        insertTimeDao.insertTime(new InsertTime(UUID.randomUUID().toString(), x + "", "sys", "insert", (end - start) + "ms", total.getHits().totalHits + "",DateUtils.datetime2string(new Date())));
    }

    //用户日志插入
    @Test
    public void insertUser() throws Exception {
        long start = System.currentTimeMillis();
        String index = "user";
        String type = "doc";
        IndexRequest indexRequest = new IndexRequest(index, type);
        UserLog user = user1();
        Gson gson = new Gson();
        String s = gson.toJson(user);
        indexRequest.source(s, XContentType.JSON);

        SearchRequest totalRequest = new SearchRequest();
        SearchSourceBuilder totalSource = new SearchSourceBuilder();
        totalSource.query(QueryBuilders.matchAllQuery());
        SearchResponse total = client.search(totalRequest);

        int x = 0;
        //插入100条
        for (int i = 0; i < 10000; i++) {
            client.index(indexRequest);
            x++;
        }

        long end = System.currentTimeMillis();
        System.out.println("程序运行时间： " + (end - start) + "ms");
        insertTimeDao.insertTime(new InsertTime(UUID.randomUUID().toString(), x + "", "user", "insert", (end - start) + "ms", total.getHits().totalHits + "",DateUtils.datetime2string(new Date())));
    }

    //管理员日志插入
    @Test
    public void insertAdmin() throws Exception {
        long start = System.currentTimeMillis();
        String index = "admin";
        String type = "doc";
        IndexRequest indexRequest = new IndexRequest(index, type);
        AdminLog admin = admin1();
        Gson gson = new Gson();
        String s = gson.toJson(admin);
        indexRequest.source(s, XContentType.JSON);

        SearchRequest totalRequest = new SearchRequest();
        SearchSourceBuilder totalSource = new SearchSourceBuilder();
        totalSource.query(QueryBuilders.matchAllQuery());
        SearchResponse total = client.search(totalRequest);
        int x = 0;
        //插入100条
        for (int i = 0; i < 10000; i++) {
            client.index(indexRequest);
            x++;
        }

        long end = System.currentTimeMillis();
        System.out.println("程序运行时间： " + (end - start) + "ms");
        insertTimeDao.insertTime(new InsertTime(UUID.randomUUID().toString(), x + "", "admin", "insert", (end - start) + "ms", total.getHits().totalHits + "",DateUtils.datetime2string(new Date())));
    }

    //准备数据源
    //资源日志
    public ResourceLog res1() throws Exception {
        ResourceLog resourceLog = new ResourceLog();
        resourceLog.setLog_level("INFO");
        resourceLog.setSession_id("39da3f232d592d4de133c230602e200e");
        resourceLog.setUser_id("304");
        resourceLog.setUser_name("vpntest");
        resourceLog.setUser_group("vpntest");
        resourceLog.setIp_address("183.206.20.205");
        resourceLog.setStatus("200");
        resourceLog.setFloat_response_time(0.095f);
        resourceLog.setLog_timestamp(DateUtils.datetime2string(new Date()));
        resourceLog.setResource_name("list.php?fid=39");
        resourceLog.setUri("10.90.0.35/images/default/jquery-1.4.2.min.js");
        resourceLog.setUplink_traffic(2561);
        resourceLog.setDownlink_traffic(414);
        resourceLog.setTotal_traffic(2975);
        resourceLog.setBrowser_info("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
        resourceLog.setRequest_referer("text/html");
        resourceLog.setRequest_count("9");
        resourceLog.setUrl_http("GET");
        resourceLog.setFile_format("/images/default/cms.css HTTP/1.1");
        resourceLog.setApp_type("Enwas");
        return resourceLog;
    }
    public ResourceLog res2() throws Exception {
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
        resourceLog.setRequest_count("7");
        resourceLog.setUrl_http("http://d0ff8459a0ddaadabd9cc067f2404a6c.vpn1.njucm.edu.cn/css/index.css");
        resourceLog.setFile_format("image/jpeg");
        resourceLog.setApp_type("Enwas");
        return resourceLog;
    }

    //系统日志
    public SystemLog sys1() {
        SystemLog systemLog = new SystemLog();
        systemLog.setLog_info("ERROR");
        systemLog.setLog_level("SERVICE");
        systemLog.setLog_timestamp(DateUtils.datetime2string(new Date()));
        systemLog.setOperate_type("文件索引服务异常，重启中");
        return systemLog;
    }
    public SystemLog sys2() {
        SystemLog systemLog = new SystemLog();
        systemLog.setLog_info("ERROR");
        systemLog.setLog_level("HD");
        systemLog.setLog_timestamp(DateUtils.datetime2string(new Date()));
        systemLog.setOperate_type("磁盘已使用98%");
        return systemLog;
    }

    //用户日志
    public UserLog user1() {
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
        userLog.setCertificate_server("117.89.131.123:52049");
        userLog.setLink_interface("interface 2");
        userLog.setDevice_os("Windows");
        userLog.setDevice_type("web");
        userLog.setClient_info("web app v3.0");
        userLog.setMac_address("39da3f22d4de133c230632d5902e200e");
        return userLog;
    }
    public UserLog user2() {
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
        userLog.setMac_address("e200e39da3e133c230602f232d592d4d");
        return userLog;
    }

    //管理员日志
    public AdminLog admin1() {
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
        adminLog.setIp_address("192.168.5.135");
        adminLog.setSession_id("0254799d-4898-46bc-9a26-74d8b8e03fac");
        adminLog.setKeyword_status("失败原因：License 不合法");
        return adminLog;
    }
    public AdminLog admin2() {
        AdminLog adminLog = new AdminLog();
        adminLog.setLog_level("INFO");
        adminLog.setLog_timestamp(DateUtils.datetime2string(new Date()));
        adminLog.setOs("Windows");
        adminLog.setRight_name("超级权限");
        adminLog.setOperation_method("查询");
        adminLog.setLog_info("获取ip列表");
        adminLog.setUser_id("2");
        adminLog.setUser_name("");
        adminLog.setFull_name("admin");
        adminLog.setRole_name("超级管理员");
        adminLog.setIp_address("192.168.5.125");
        adminLog.setSession_id("0254799d-4898-46bc-9a26-74d8b8e03fac");
        adminLog.setKeyword_status("成功");
        return adminLog;
    }


    //获取客户端
    private RestHighLevelClient getElastic() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.100.50", 9200, "http")
                )
        );
    }


}
