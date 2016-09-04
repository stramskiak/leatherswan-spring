package com.leatherswan.artisticendeavors.jsoup.configuration;

import com.leatherswan.artisticendeavors.jsoup.base.JsoupHtmlParser;
import com.leatherswan.artisticendeavors.jsoup.dto.PagePreviewDTO;
import com.leatherswan.artisticendeavors.jsoup.parsers.PagePreviewParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/jsoup.properties")
public class JsoupConfig {

    @Bean
    public JsoupHtmlParser<PagePreviewDTO> pagePreviewParser() {
        return new PagePreviewParser(PagePreviewDTO.class);
    }
}




