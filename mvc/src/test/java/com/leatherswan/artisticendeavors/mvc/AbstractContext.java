package com.leatherswan.artisticendeavors.mvc;

import com.leatherswan.artisticendeavors.mvc.common.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.leatherswan.artisticendeavors.jpa.enums.DataConfigProfile;
//import com.leatherswan.artisticendeavors.solr.enums.SolrConfigProfile;

@SuppressWarnings("deprecation")
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
@ActiveProfiles({ DataConfigProfile.H2 })
public class AbstractContext {

	@Autowired
	protected WebApplicationContext context;

}
