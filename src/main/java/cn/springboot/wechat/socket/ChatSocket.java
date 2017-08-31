package cn.springboot.wechat.socket;

import cn.springboot.wechat.socket.chatmessage.ContentVo;
import cn.springboot.wechat.socket.chatmessage.Message;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Author: fuyuan
 * Date: 2017/7/20 11:31
 */
@ServerEndpoint("/chatSocket")
@Component
public class ChatSocket {

    private final static Logger logger = LoggerFactory.getLogger(ChatSocket.class);

    //通信管道传过来的username
    private String username;

    //通信管道集合 ，用于广播
    private static List<Session> sessions = new ArrayList<Session>();

    //用户集合
    private static List<String> names = new ArrayList<String>();

    //用户姓名和通信管道Map集合
    private static Map<String, Session> map = new HashMap<String, Session>();

    @OnOpen
    public void open(Session session) {
        logger.info("连接成功-----SessionID：" + session.getId());
        String queryString = session.getQueryString();
        username = queryString.split("=")[1];

        try {
            username = URLDecoder.decode(username, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        this.names.add(username);
        System.out.println(names);
        this.sessions.add(session);

        /**
         * 每打开一个通信管道
         * 就把当前的用户信息和session存进Map集合
         * 用于后面单聊实现时可以根据用户名获取其通信管道
         */
        this.map.put(this.username, session);

        //欢迎信息
        String msg = "欢迎" + this.username + "进入聊天室";
        Message message = new Message();
        message.setWelcome(msg);
        message.setUsernames(this.names);

        //广播消息
        broadcast(sessions, message.toJson());
    }

    /**
     * 广播
     * @param sessions
     * @param msg
     */
    protected void broadcast(List<Session> sessions, String msg){
        for (Session session: sessions) {
            try {
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnMessage
    public void message(Session session, String json) {
        Gson gson = new Gson();
        ContentVo contentVo = gson.fromJson(json, ContentVo.class);
        if (contentVo.getType() == 1) {
            Message message = new Message();
            message.setContent(this.username, contentVo.getMsg());
            broadcast(this.sessions, message.toJson());
        } else {
            Session to_session = this.map.get(contentVo.getTo());
            Message message = new Message();
            message.setContent(this.username, contentVo.getMsg());
            try {
                to_session.getBasicRemote().sendText(message.toJson());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClose
    public void close(Session session) {
        logger.info(this.username + "-----断开连接");
        String msg = this.username + "退出聊天室";
        this.names.remove(this.username);
        this.sessions.remove(session);
        Message message = new Message();
        message.setUsernames(this.names);
        message.setWelcome(msg);
        broadcast(this.sessions, message.toJson());
    }
}
