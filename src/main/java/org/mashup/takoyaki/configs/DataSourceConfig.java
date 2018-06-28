package org.mashup.takoyaki.configs;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class DataSourceConfig {

    private final PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();

    @Bean(destroyMethod = "close")
    public DataSource dataSource() throws IOException {
        Properties dataSourceProps = PropertiesLoaderUtils.loadProperties(
                this.pathMatchingResourcePatternResolver.getResource("classpath:database.properties"));

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
    public PlatformTransactionManager transactionManager() throws IOException {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setConfigLocation(this.pathMatchingResourcePatternResolver.getResource("classpath:mapper/mapper-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(this.pathMatchingResourcePatternResolver.getResources("classpath:mapper/**/*Mapper.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();

        mapperScannerConfigurer.setBasePackage("org.mashup.takoyaki.api");
        mapperScannerConfigurer.setBeanName("sqlSessionFactory");

        return mapperScannerConfigurer;
    }

}
