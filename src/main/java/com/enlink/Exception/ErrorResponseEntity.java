package com.enlink.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 11:24 2019/1/25
 */
@Data
@AllArgsConstructor
public class ErrorResponseEntity {
    private int code;
    private String message;
}
