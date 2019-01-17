package Boolean_Filter;

import org.junit.Assert;
import org.junit.Test;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 19:48 2019/1/16
 */
public class bloomFilterTest {
    @Test
    public void bloomFilterTesss(){
        long start = System.currentTimeMillis();
        BloomFilters bloomFilters = new BloomFilters(10000000);
        for (int i = 0; i < 100000000; i++) {
            bloomFilters.add(i+"");
        }
        Assert.assertTrue(bloomFilters.check(1+""));
        Assert.assertTrue(bloomFilters.check(2+""));
        Assert.assertTrue(bloomFilters.check(3+""));
        Assert.assertTrue(bloomFilters.check(999999+""));
        Assert.assertFalse(bloomFilters.check(400230340+""));
        long end = System.currentTimeMillis();
        System.out.println("测试时间："+(end-start));
    }
}
