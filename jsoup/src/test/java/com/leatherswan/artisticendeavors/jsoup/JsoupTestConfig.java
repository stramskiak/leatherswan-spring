package com.leatherswan.artisticendeavors.jsoup;

import com.leatherswan.artisticendeavors.jpa.enums.DataConfigProfile;
import com.leatherswan.artisticendeavors.jsoup.dto.TestDTO;
import com.leatherswan.artisticendeavors.jsoup.base.JsoupHtmlParser;
import com.leatherswan.artisticendeavors.jsoup.parsers.TestDTOParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(DataConfigProfile.H2)
@Configuration
public class JsoupTestConfig {

    @Bean
    public JsoupHtmlParser<TestDTO> testDTOParser() {
        return new TestDTOParser(TestDTO.class);
    }
}
