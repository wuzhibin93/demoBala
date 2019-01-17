package com.enlink.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 14:56 2019/1/17
 */
@Data
@Component
public class GlobalParameterT {
    @Value("${host.ip}")
    private String host;
}
