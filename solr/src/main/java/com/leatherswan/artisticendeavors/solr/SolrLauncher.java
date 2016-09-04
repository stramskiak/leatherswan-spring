package com.leatherswan.artisticendeavors.solr;

import com.leatherswan.artisticendeavors.solr.common.SolrUI;
import com.leatherswan.artisticendeavors.solr.config.SolrApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.SpringVersion;

public class SolrLauncher {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(SolrApplicationConfig.class);
		ctx.refresh();
		System.out.println("Using Spring Framework Version: " + SpringVersion.getVersion());
		System.out.println("Solr Active Profile: " + ctx.getEnvironment().getActiveProfiles()[0]);
		SolrUI ui = ctx.getBean(SolrUI.class);
		ui.init();
		ctx.close();
	}
}
