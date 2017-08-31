package cn.springboot.wechat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Created with IntelliJ IDEA.
 * Author: fuyuan
 * Date: 2017/7/27 16:57
 */
@Configuration
public class WebSocket {

    /*@Bean*/
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
