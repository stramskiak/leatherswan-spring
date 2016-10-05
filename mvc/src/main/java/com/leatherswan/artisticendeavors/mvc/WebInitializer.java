package com.leatherswan.artisticendeavors.mvc;

import com.leatherswan.artisticendeavors.mvc.common.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.core.annotation.Order;

/*
@Order added to original from nixmash-spring due to runtime exceptions
http://stackoverflow.com/questions/28306031/apparent-spring-boot-race-condition-causing-duplicate-springsecurityfilterchain
@Order(2)
*/

@SpringBootApplication
public class WebInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

}


