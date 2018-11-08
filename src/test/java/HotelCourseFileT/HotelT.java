package HotelCourseFileT;

import com.enlink.de.Application;
import com.enlink.de.elasticsearch.entity.HotelCourseFile;
import com.enlink.de.elasticsearch.service.HotelCourseFileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 10:53 2018/9/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class HotelT {
    @Resource
    private HotelCourseFileService hotelCourseFileService;
    @Test
    public void test1(){
        List<HotelCourseFile> hotelCourseFiles = hotelCourseFileService.queryAll();
        for (HotelCourseFile hotelCourseFile : hotelCourseFiles) {
            System.out.println(hotelCourseFile);
        }

    }
}
