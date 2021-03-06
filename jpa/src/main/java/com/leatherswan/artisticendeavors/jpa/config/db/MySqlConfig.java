package com.leatherswan.artisticendeavors.jpa.config.db;

import com.leatherswan.artisticendeavors.jpa.enums.DataConfigProfile;
//import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
//import org.apache.tomcat.jdbc.pool.DataSource;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/META-INF/spring/mysql.properties")
@Profile(DataConfigProfile.MYSQL)
public class MySqlConfig extends JpaCommonConfig {

    @Override
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(getDriverClassName());
        dataSource.setUrl(getUrl());
        dataSource.setUsername(getUser());
        dataSource.setPassword(getPassword());
        dataSource.setValidationQuery(getDatabaseValidationQuery());
        dataSource.setInitialSize(1);
        dataSource.setMaxActive(15);
        dataSource.setMaxIdle(15);
        dataSource.setMaxWait(25000);
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(300);
        dataSource.setLogAbandoned(true);
        return dataSource;
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

