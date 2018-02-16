package com.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class JpaConfiguration {
    private Environment environment;

    @Autowired
    public JpaConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource()
    {
        DriverManagerDataSource data = new DriverManagerDataSource();
        data.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        data.setUrl(environment.getRequiredProperty("jdbc.url"));
        data.setUsername(environment.getRequiredProperty("jdbc.username"));
        data.setPassword(environment.getRequiredProperty("jdbc.password"));
        return data;
    }


    @Bean
    public JpaVendorAdapter jpaVendorAdapter()
    {
        return new HibernateJpaVendorAdapter();
    }


    public Properties jpaProperties()
    {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));

        return properties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean()
    {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan(new String[]{"com.app"});
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.setJpaProperties(jpaProperties());
        return factoryBean;
    }


    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(EntityManagerFactory entity)
    {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entity);
        return txManager;
    }
}
