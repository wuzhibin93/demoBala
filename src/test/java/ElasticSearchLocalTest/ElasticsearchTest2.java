package ElasticSearchLocalTest;

import com.enlink.Application;
import com.enlink.elasticsearch.dao.HotelCourseFileDao;
import com.enlink.elasticsearch.entity.HotelCourseFile;
import com.google.gson.Gson;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 13:50 2018/9/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class ElasticsearchTest2 {
    @Resource
    private HotelCourseFileDao hotelCourseFileDao;

    private String index;   //索引类型
    private String type;    //索引名称

    @Before
    public void prepare() {
        index = "hotel";
        type = "doc";
    }

    RestHighLevelClient client = getElastic();

    //查询hotel_course_file中所有数据并添加到ES中
    @Test
    public void test1() throws IOException {
        List<HotelCourseFile> hotelCourseFiles = hotelCourseFileDao.queryAllHotelCourseFile();
        IndexRequest indexRequest = new IndexRequest(index, type);
        for (HotelCourseFile hotelCourseFile : hotelCourseFiles) {
            Gson g = new Gson();
            String source = g.toJson(hotelCourseFile);
            indexRequest.source(source, XContentType.JSON);
            client.index(indexRequest);
        }
        client.close();
    }

    @Test
    public void test2() {
        List<HotelCourseFile> hotelCourseFiles = hotelCourseFileDao.queryAllHotelCourseFile();
        for (HotelCourseFile hotelCourseFile : hotelCourseFiles) {
            Gson g = new Gson();
            String source = g.toJson(hotelCourseFile);
            System.out.println(source);
        }
    }

    //获取ElasticClient对象
    private static RestHighLevelClient getElastic() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")
                )
        );
    }
}
