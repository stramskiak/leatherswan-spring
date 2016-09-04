package com.leatherswan.artisticendeavors.jsoup;

import com.leatherswan.artisticendeavors.jsoup.dto.TestDTO;
import com.leatherswan.artisticendeavors.jsoup.base.JsoupHtmlParser;
import com.leatherswan.artisticendeavors.jsoup.parsers.TestDTOParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by daveburke on 5/21/16.
 */
@Configuration
public class JsoupTestConfig {

    @Bean
    public JsoupHtmlParser<TestDTO> testDTOParser() {
        return new TestDTOParser(TestDTO.class);
    }
}
