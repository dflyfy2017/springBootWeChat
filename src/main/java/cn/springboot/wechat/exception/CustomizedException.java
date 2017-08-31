package cn.springboot.wechat.exception;

/**
 * Created with IntelliJ IDEA.
 * Author: fuyuan
 * Date: 2017/7/20 15:20
 * 自定义的异常
 */
public class CustomizedException extends RuntimeException {

    //异常码
    private Integer exceptionCode;

    public CustomizedException() {
        super();
    }

    public CustomizedException(Integer exceptionCode, String message) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public CustomizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public Integer getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(Integer exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
