package com.enlink.Exception;

import com.enlink.entity.Race;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 11:25 2019/1/25
 */
@RestController
public class ExceptionController {
    @GetMapping("/testE")
    public String testR(Integer num){
        if (num == null){
            throw new CustomException(400,"num不能为空");
        }
        int i = 10/num;
        return "result:"+i;
    }
}
