package com.leatherswan.artisticendeavors.jpa.config;

import com.leatherswan.artisticendeavors.jpa.model.auditors.CurrentTimeDateTimeService;
import com.leatherswan.artisticendeavors.jpa.model.auditors.DateTimeService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Modified by stramskiak
 */
@Configuration
@EnableConfigurationProperties
@EnableTransactionManagement
@ComponentScan(basePackages = "com.leatherswan.artisticendeavors.jpa")
@EnableJpaRepositories(basePackages = "com.leatherswan.artisticendeavors.jpa")
@PropertySource("classpath:/META-INF/spring/application.properties")
public class ApplicationConfig {


    @Bean
    DateTimeService currentTimeDateTimeService() {
        return new CurrentTimeDateTimeService();
    }

//    @Autowired
//    SiteOptionRepository siteOptionRepository;
//
//    @Bean(name="siteOptionsBean")
//    @DependsOn("siteOptionRepository")
//    public SiteOptions siteOptions() throws
//            IllegalAccessException, NoSuchMethodException, InvocationTargetException {
//        SiteOptions bean = new SiteOptions();
//        Collection<SiteOption> siteOptionKeyValues = siteOptionRepository.findAll();
//        Map<String,Object> options = new Hashtable<>();
//        for (SiteOption siteOption : siteOptionKeyValues) {
//            options.put(siteOption.getName(), siteOption.getValue());
//        }
//
//        for (String key : options.keySet()) {
//            for (Field f : bean.getClass().getDeclaredFields()) {
//                if (f.getName().toUpperCase().equals(key.toUpperCase())) {
//                    bean.setSiteOptionProperty(key, options.with(key));
//                }
//            }
//        }
//        return bean;
//
//    }
}