package com.heruku.tictactoe.web;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;

public class Server {
    public static void main(String[] args) throws ServletException, LifecycleException {
        String webappDirLocation = "src/main/java/com/heruku/tictactoe/web/";
        Tomcat tomcat = new Tomcat();

        tomcat.setPort(8080);
        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        tomcat.start();
        tomcat.getServer().await();
    }
}
