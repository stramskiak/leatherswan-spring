package com.leatherswan.artisticendeavors.solr;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leatherswan.artisticendeavors.solr.common.SolrSettings;
import com.leatherswan.artisticendeavors.solr.config.SolrApplicationConfig;

/**
 * 
 * NixMash Spring Notes: ---------------------------------------------------
 * 
 * Based on Christoph Strobl's Spring Solr Repository Example for Spring Boot
 * 
 * On GitHub: https://goo.gl/JoAYaT
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SolrApplicationConfig.class)
@ActiveProfiles("dev")
public class SolrContext {

	@Autowired
	private SolrSettings solrSettings;

	@Test
	public void contextLoads() {
		assertNotNull(solrSettings.getSolrServerUrl());
	}

}
