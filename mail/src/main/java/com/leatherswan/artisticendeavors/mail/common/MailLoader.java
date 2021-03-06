package com.leatherswan.artisticendeavors.mail.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MailLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MailLoader.class);

    @Autowired
    Environment environment;

    @Override
    public void run(String... args) throws Exception {
        String applicationVersion = environment.getProperty("leatherswan.artisticendeavors.mail.version");
        logger.info(String.format("LeatherSwan Spring Mail Application Version: %s", applicationVersion));
    }
}
