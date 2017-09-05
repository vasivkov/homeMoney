package com.vasivkov.bootstrap;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.h2.tools.Server;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.sql.*;

@WebListener
public class H2ServletInitializer implements ServletContextListener {
    private static final String[] H2_TCP_SERVER_ARGS = {"-tcp", "-tcpAllowOthers", "-tcpPort", "9092"};
    private static final String[] H2_WEB_SERVER_ARGS = {"-web", "-webAllowOthers", "-webPort", "8082"};
    private Server tcpServer;
    private Server webServer;
    private static final Logger LOGGER = Logger.getLogger(H2ServletInitializer.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        String log4jConfigFile = context.getInitParameter("log4j-config-location");
        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;
        PropertyConfigurator.configure(fullPath);
        try {
            tcpServer = Server.createTcpServer(H2_TCP_SERVER_ARGS).start();
            webServer = Server.createWebServer(H2_WEB_SERVER_ARGS).start();
            H2JdbcConnectionPool.getInstance().init();
        } catch (SQLException e) {
            LOGGER.error("Failed to ");
            throw new RuntimeException(e);
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

