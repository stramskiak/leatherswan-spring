package com.leatherswan.artisticendeavors.jsoup;

import com.leatherswan.artisticendeavors.jsoup.components.JsoupUI;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class JsoupLauncher {

	public static void main(String[] args) {

		ApplicationContext ctx = new
				AnnotationConfigApplicationContext("com.leatherswan.artisticendeavors.jsoup",
																										"com.leatherswan.artisticendeavors.jpa");

		JsoupUI ui = ctx.getBean(JsoupUI.class);
		ui.init();
		((ConfigurableApplicationContext) ctx).close();
	}
}
