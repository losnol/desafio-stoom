package br.com.desafiostoom.application.configuration.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties
@ConditionalOnProperty(value = "relational-database.enable", havingValue = "true")
public class DataSourceConfig {

    @Value("${relational-database.jpa.properties.hibernate.dialect}")
    private String dialect;

    @Value("${relational-database.jpa.properties.generate-ddl}")
    private Boolean generateDDL;

    @Value("${relational-database.jpa.properties.hibernate.ddl-auto}")
    private String hibernateDDL;

    @Value("${relational-database.jpa.packages-to-scan}")
    private List<String> packagesToScan;

    @Value("${relational-database.jpa.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${relational-database.jpa.datasource.url}")
    private String databaseUrl;

    @Value("${relational-database.jpa.datasource.username}")
    private String databaseUsername;

    @Value("${relational-database.jpa.datasource.password}")
    private String databasePassword;

    private Properties jpaProperties() {
        var properties = new Properties();
        properties.put("hibernate.dialect", this.dialect);
        properties.put("hibernate.ddl-auto", this.hibernateDDL);
        properties.put("generate-ddl", this.generateDDL);
        return properties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Autowired DataSource dataSource) {
        var vendorAdapter = new HibernateJpaVendorAdapter();
        var factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(this.packagesToScan.toArray(new String[]{}));
        factory.setDataSource(dataSource);
        factory.setJpaProperties(this.jpaProperties());
        return factory;
    }

    @Bean
    public DataSource getDataSource() {
        var dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(this.driverClassName);
        dataSourceBuilder.url(this.databaseUrl);
        dataSourceBuilder.username(this.databaseUsername);
        dataSourceBuilder.password(this.databasePassword);
        return dataSourceBuilder.build();
    }

}