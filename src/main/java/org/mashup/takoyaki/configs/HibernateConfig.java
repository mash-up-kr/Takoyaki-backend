package org.mashup.takoyaki.configs;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
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
                this.pathMatchingResourcePatternResolver.getResource("classpath:db.properties"));

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
        dataSource.setTimeBetweenEvictionRunsMillis(Integer.parseInt(dataSourceProps.getProperty("datasource.dbcp2.time-between-eviction-runs-millis")));
        dataSource.setNumTestsPerEvictionRun(Integer.parseInt(dataSourceProps.getProperty("datasource.dbcp2.num-tests-per-eviction-run")));

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
        emf.setJpaProperties(jpaDevProperties());

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

    private Properties jpaDevProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        properties.setProperty("hibernate.format_sql", Boolean.toString(true));
        properties.setProperty("spring.jpa.hibernate.naming_strategy", "org.hibernate.cfg.EJB3NamingStrategy");
        properties.setProperty("hibernate.connection.CharSet", "UTF-8");
        properties.setProperty("hibernate.connection.characterEncoding", "UTF-8");
        properties.setProperty("hibernate.connection.useUnicode", Boolean.toString(true));
        return properties;
    }

}
