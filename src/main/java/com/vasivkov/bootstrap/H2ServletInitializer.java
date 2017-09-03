package com.vasivkov.bootstrap;

import org.h2.tools.Server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.*;

@WebListener
public class H2ServletInitializer implements ServletContextListener {
    private static final String[] H2_TCP_SERVER_ARGS = {"-tcp", "-tcpAllowOthers", "-tcpPort", "9092"};
    private static final String[] H2_WEB_SERVER_ARGS = {"-web", "-webAllowOthers", "-webPort", "8082"};
    private Server tcpServer;
    private Server webServer;

    @Override
    public void contextInitialized(ServletContextEvent e) {
        try {
            tcpServer = Server.createTcpServer(H2_TCP_SERVER_ARGS).start();
            webServer = Server.createWebServer(H2_WEB_SERVER_ARGS).start();
            H2JdbcConnectionPool.getInstance().init();
        } catch (SQLException e1) {
            e1.printStackTrace();
            throw new RuntimeException(e1);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent e) {
        if (tcpServer != null) {
            tcpServer.stop();
        }
        if (webServer != null) {
            webServer.stop();
        }
    }
}