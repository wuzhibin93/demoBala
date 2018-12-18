package ES_MySql;


import com.enlink.Application;
import com.enlink.dao.OperationDAO;
import com.enlink.entity.Operation;
import com.enlink.entity.Throughput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 17:26 2018/12/6
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class Test_2 {

    @Resource
    private OperationDAO operationDAO;
    @Test
    public void test1(){
        List<Operation> byId = operationDAO.findById("1");
        System.out.println(byId);
        System.out.println(1);
    }
    @Test
    public void test2(){
        Operation operation = new Operation("1","1","1","1","1","1","1","1",
                "1","1","1","1","1","1","1",
                "1","1","1","1","1","1","1","1",
                "1","1","1","1","1","1","1",
                "1","1","1",new Timestamp(123456),new Throughput());

        operationDAO.insertOperation(operation);
    }

    @Test
    public void test3() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        System.out.println(simpleDateFormat.parse("2018-12-03T13:33:03Z").toString());

    }
    @Test
    public void test4(){
        String time = "20181203T133303Z";
        StringBuilder stringBuilder = new StringBuilder(time);
        stringBuilder.insert(4,"-");
        stringBuilder.insert(7,"-");
        stringBuilder.insert(13,":");
        stringBuilder.insert(16,":");
        stringBuilder.replace(10,11," ");
        stringBuilder.replace(19,20,"");
        String s = stringBuilder.toString();
        System.out.println(s);



    }
}
