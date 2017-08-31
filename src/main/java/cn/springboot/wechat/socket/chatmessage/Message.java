package cn.springboot.wechat.socket.chatmessage;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by fuyuan on 2017/7/14.
 */
public class Message {
    private String welcome;//欢迎用户登录或者提示用户退出的消息
    private List<String> usernames;//需要发送的用户的名字集合

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setContent(String name, String content) {
        this.content = name + " " + formatDateToString(new Date()) + " :<br/>" + content + "<br/>";
    }

    public Message() {
    }

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    public List<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }

    private static Gson gson = new Gson();

    public String toJson(){
        return gson.toJson(this);
    }

    public String formatDateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        return sdf.format(date);
    }
}
