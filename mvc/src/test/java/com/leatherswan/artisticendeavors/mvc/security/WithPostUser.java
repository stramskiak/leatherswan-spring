package com.leatherswan.artisticendeavors.mvc.security;

import com.leatherswan.artisticendeavors.jpa.enums.DataConfigProfile;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithUserDetails(value = "keith", userDetailsServiceBeanName = "currentUserDetailsService")
@ActiveProfiles(DataConfigProfile.H2)
public @interface WithPostUser {
}



