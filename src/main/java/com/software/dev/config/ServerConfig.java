package com.software.dev.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
@Slf4j
public class ServerConfig implements ApplicationListener<WebServerInitializedEvent> {

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        try {
            InetAddress inetAddress = Inet4Address.getLocalHost();
            // 主机ip地址
            String hostAddress = inetAddress.getHostAddress();
            // tomcat启动端口
            int serverPort = event.getWebServer().getPort();
            // 新增动态path by zhengkai
            String serverPath = event.getApplicationContext().getApplicationName();
            log.info("项目启动成功！访问地址: http://{}:{}{}", hostAddress, serverPort, serverPath);
            log.info("本机地址: http://localhost:{}{}", serverPort, serverPath);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}

