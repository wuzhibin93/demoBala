package com.enlink.pemca.entity;

import lombok.Data;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(证书上传)
 * @Date : Created in 9:52 2018/9/5
 */
@Data
public class Certificate<T extends GeneralModel>  {
    private String cerName;
    private String cerMainLogin;
    private String cerShortLogin;

}
