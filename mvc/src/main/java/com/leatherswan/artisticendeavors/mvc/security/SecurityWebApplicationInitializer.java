
package com.leatherswan.artisticendeavors.mvc.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/*
@Order added to original from nixmash-spring due to runtime exceptions
http://stackoverflow.com/questions/28306031/apparent-spring-boot-race-condition-causing-duplicate-springsecurityfilterchain
*/

@Configuration
//@Order(1)
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
}
