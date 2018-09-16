package com.mike.sql.hypersql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Config {

    @Bean
    @Scope("singleton")
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:mem:employees", "vinod", "vinod");
    }
}
