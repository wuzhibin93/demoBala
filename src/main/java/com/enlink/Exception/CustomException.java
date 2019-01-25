package com.enlink.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 11:23 2019/1/25
 */
@Data
@AllArgsConstructor
public class CustomException extends RuntimeException{
    private int code;

    public CustomException(){
        super();
    }
    public CustomException(int code ,String message){
        super(message);
        this.setCode(code);
    }
    public int getCode(){
        return code;
    }
    public void setCode(int code){
        this.code = code;
    }
}
