package cn.springboot.wechat.common;

/**
 * Created with IntelliJ IDEA.
 * Author: fuyuan
 * Date: 2017/7/20 16:59
 */
public enum LoginMsgEnum {
    ACCOUNT_NOT_EXIST(100, "输入的账号不存在"),
    PASSWORD_ERROR(102, "密码错误"),
    VERIFICATION_CODE_ERROR(103, "验证码输入错误")
    ;

    private Integer code;

    private String msg;

    LoginMsgEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
