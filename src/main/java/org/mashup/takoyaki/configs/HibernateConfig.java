package org.mashup.takoyaki.configs;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "org.mashup.takoyaki.repository")
@EnableTransactionManagement
public class HibernateConfig {

    private final PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
    private static final String ENTITY_LOCATE = "org.mashup.takoyaki.entity";

    @Bean(destroyMethod = "close")
    public DataSource dataSource() throws IOException {
        Properties dataSourceProps = PropertiesLoaderUtils.loadProperties(
                this.pathMatchingResourcePatternResolver.getResource("classpath:datasource.properties"));

        DataSource dataSource = new DataSource();

        dataSource.setDriverClassName(dataSourceProps.getProperty("datasource.driverClassName"));
        dataSource.setUrl(dataSourceProps.getProperty("datasource.url"));
        dataSource.setUsername(dataSourceProps.getProperty("datasource.username"));
        dataSource.setPassword(dataSourceProps.getProperty("datasource.password"));

        dataSource.setInitialSize(Integer.parseInt(dataSourceProps.getProperty("datasource.dbcp2.initial-size")));
        dataSource.setMinIdle(Integer.parseInt(dataSourceProps.getProperty("datasource.dbcp2.min-idle")));
        dataSource.setMaxActive(Integer.parseInt(dataSourceProps.getProperty("datasource.dbcp2.max-total")));
        dataSource.setMaxIdle(Integer.parseInt(dataSourceProps.getProperty("datasource.dbcp2.max-idle")));
        dataSource.setMaxWait(Integer.parseInt(dataSourceProps.getProperty("datasource.dbcp2.max-wait-millis")));

        dataSource.setValidationQuery(dataSourceProps.getProperty("datasource.dbcp2.validation-query"));
        dataSource.setValidationQueryTimeout(Integer.parseInt(dataSourceProps.getProperty("datasource.dbcp2.validation-query-timeout")));
        dataSource.setMinEvictableIdleTimeMillis(-1);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan(ENTITY_LOCATE);

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setGenerateDdl(true);

        emf.setJpaVendorAdapter(jpaVendorAdapter);
        emf.setPersistenceUnitName("takoyaki");
        emf.setJpaProperties(jpaProperties());

        return emf;
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.format_sql", Boolean.toString(true));

        return properties;
    }

}
