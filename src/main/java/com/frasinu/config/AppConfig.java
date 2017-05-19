package com.frasinu.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.frasinu.exception.DataBaseException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by bjz on 5/5/2017.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.frasinu.persistance.repository")
@EnableTransactionManagement
public class AppConfig {
    @Bean
    public DataSource dataSource() {
        Properties properties = new Properties();
        MysqlDataSource dataSource = null;
        InputStream inputStream = AppConfig.class.getResourceAsStream("/mysql_db.config");
        try {
            properties.load(inputStream);
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

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"com.frasinu.persistance.model"});

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }


    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return properties;
    }

}
