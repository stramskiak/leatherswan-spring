package com.leatherswan.artisticendeavors.mail;

import com.leatherswan.artisticendeavors.mail.components.MailUI;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class MailLauncher {

	public static void main(String[] args) {

		ApplicationContext ctx = new
				AnnotationConfigApplicationContext("com.leatherswan.artisticendeavors.mail",
				"com.leatherswan.artisticendeavors.jpa");
//		ApplicationContext ctx = SpringApplication.run(MailLauncher.class, args);
		MailUI ui = ctx.getBean(MailUI.class);
		ui.init();
		((ConfigurableApplicationContext) ctx).close();
	}

}
