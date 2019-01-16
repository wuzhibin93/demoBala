package MyBatisTest;

import com.enlink.Application;
import com.enlink.dao.MyUserDao;
import com.enlink.entity.MyUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 13:20 2019/1/2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class MyBatisTest {
    @Resource
    private MyUserDao myUserDao;

    @Test
    public void test1(){
        List<MyUser> list = myUserDao.list();
        System.out.println(list);
    }

    @Test
    public void test2(){
        List<MyUser> myUsers = myUserDao.listById("1");
        System.out.println(myUsers);
    }

    @Test
    public void test3(){
        List<MyUser> myUsers = myUserDao.listByUsername("1","1");
        System.out.println(myUsers);
    }
}
