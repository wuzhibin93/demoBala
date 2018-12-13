package com.enlink.response;

/**
 * 相应结果处理
 *
 * @author changgq
 */
public class Results {

    public static AjaxResults resultOf(ResultCode resultCode, Object data) {
        String status = "FAILURE";
        if (resultCode.getCode().equals("200")) {
            status = "SUCCESS";
        }
        return new AjaxResults(resultCode.getCode(), status, resultCode.getDescription(), data);
    }

    public static AjaxResults errorOf(ResultCode resultCode, String errorMsg) {
        String status = "FAILURE";
        if (resultCode.getCode().equals("200")) {
            status = "SUCCESS";
        }
        return new AjaxResults(resultCode.getCode(), status, resultCode.getDescription() + " : " + errorMsg, null);
    }
}
