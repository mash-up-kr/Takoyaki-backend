## 개발 환경
- Spring MVC 4.3.14 & Mybatis 3.4.1 & Mybatis-spring 1.3.1
- MySQL 5.7

## 설정
#### MapperScannerConfigurer클래스를 이용한 Mapper 스캐닝

```java
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();

        mapperScannerConfigurer.setBasePackage("org.mashup.takoyaki.api");
        mapperScannerConfigurer.setBeanName("sqlSessionFactory");

        return mapperScannerConfigurer;
    }
```

- Mapper interface들이 존재하는 경로 설정과 sqlSessionFactory빈을 주입

```java
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setConfigLocation(this.pathMatchingResourcePatternResolver.getResource("classpath:mapper/mapper-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(this.pathMatchingResourcePatternResolver.getResources("classpath:mapper/**/*Mapper.xml"));

        return sqlSessionFactoryBean.getObject();
    }
```

- mapper-config.xml파일과 *Mapper.xml들을 등록
> Mybatis는 SqlSessionFactory와 함께 사용됨
