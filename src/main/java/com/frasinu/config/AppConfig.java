package com.frasinu.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.frasinu.exception.DataBaseException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import com.frasinu.repository.mysql_db.UserRepository;
import com.frasinu.service.UserService;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by bjz on 5/5/2017.
 */
@Configuration
public class AppConfig {
    @Bean
    public DataSource dataSource() {
        Properties properties = new Properties();
        MysqlDataSource dataSource = null;
        InputStream inputStream = AppConfig.class.getResourceAsStream("/mysql_db.config");
        try {
            properties.load(inputStream);
            String check = properties.getProperty("db");
            dataSource = new MysqlDataSource();
            dataSource.setURL(properties.getProperty("db"));
            dataSource.setUser(properties.getProperty("user"));
            dataSource.setPassword(properties.getProperty("password"));

        } catch (IOException e) {
            throw new DataBaseException("Cannot read db properties!" + e);
        }
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        try {
            return new JdbcTemplate(dataSource());
        } catch (DataBaseException e) {
            throw new DataBaseException("Cannot create jdbcTemplate!" + e);
        }
    }

}
