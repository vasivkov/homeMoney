package com.vasivkov.bootstrap;

import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by vasya on 02/09/17.
 */
public class H2JdbcConnectionPool {
    private static H2JdbcConnectionPool ourInstance = new H2JdbcConnectionPool();
    private JdbcConnectionPool jdbcConnectionPool;
    private static final String H2_URL = "jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:createTable.sql'";
    private static final String H2_NAME = "sa";
    private static final String H2_PASSWORD = "";

    public static H2JdbcConnectionPool getInstance() {
        return ourInstance;
    }

    private H2JdbcConnectionPool() {

    }

    public void init() {
        if (jdbcConnectionPool == null) {
            jdbcConnectionPool = JdbcConnectionPool.create(H2_URL, H2_NAME, H2_PASSWORD);
            jdbcConnectionPool.setMaxConnections(10);
            try {
                jdbcConnectionPool.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() throws SQLException {

        return jdbcConnectionPool.getConnection();
    }
}
