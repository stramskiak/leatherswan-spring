package com.leatherswan.artisticendeavors.jpa.config.db;

import com.leatherswan.artisticendeavors.jpa.enums.DataConfigProfile;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
//import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/META-INF/spring/myremotesql.properties")
@Profile(DataConfigProfile.MYREMOTESQL)
public class MyRemoteSqlConfig extends JpaCommonConfig {

    @Override
    @Bean
    public DataSource dataSource() {
        BasicDataSource remoteDataSource = new BasicDataSource();
        remoteDataSource.setDriverClassName(getDriverClassName());
        remoteDataSource.setUrl(getUrl());
        remoteDataSource.setUsername(getUser());
        remoteDataSource.setPassword(getPassword());
        remoteDataSource.setValidationQuery(getDatabaseValidationQuery());
        remoteDataSource.setTestOnBorrow(true);
        remoteDataSource.setTestOnReturn(true);
        remoteDataSource.setTestWhileIdle(true);
        remoteDataSource.setTimeBetweenEvictionRunsMillis(1800000);
        remoteDataSource.setNumTestsPerEvictionRun(3);
        remoteDataSource.setMinEvictableIdleTimeMillis(1800000);
        return remoteDataSource;
    }

    @Override
    protected Class<? extends Dialect> getDatabaseDialect() {
        return MySQL5InnoDBDialect.class;
    }

    @Bean
    public DatabasePopulator databasePopulator() {
        return null;
    }

}

