package com.payjs.pay.web;

/**
 * @Author chengtianqi
 * @create 2020/6/14 17:42
 * 平台服务层异常，主要是在业务数据或者状态异常时使用
 */
public class BusinessException  extends RuntimeException {

    private Integer code;

    public BusinessException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
    }
    public BusinessException(String message, String i18n) {
        super(message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
