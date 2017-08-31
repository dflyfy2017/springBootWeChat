package cn.springboot.wechat.socket.chatmessage;

/**
 * Created by fuyuan on 2017/7/16.
 */
public class ContentVo {

    private String sourceUser;//发送消息的人
    private String to;//发送消息的目标
    private String msg;//发送的消息内容
    int type;//本次发送消息的类型

    public ContentVo() {
    }

    public String getSourceUser() {
        return sourceUser;
    }

    public void setSourceUser(String sourceUser) {
        this.sourceUser = sourceUser;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
