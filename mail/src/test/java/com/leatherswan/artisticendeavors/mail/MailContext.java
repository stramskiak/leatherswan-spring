package com.leatherswan.artisticendeavors.mail;

import com.leatherswan.artisticendeavors.jpa.JpaLauncher;
import com.leatherswan.artisticendeavors.jpa.enums.DataConfigProfile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MailLauncher.class, JpaLauncher.class},  loader=AnnotationConfigContextLoader.class)
@ActiveProfiles({ DataConfigProfile.H2 })
@TestPropertySource("classpath:/test.properties")
public class MailContext {

	@Test
	public void contextLoads() {
	}
}