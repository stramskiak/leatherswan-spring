package com.leatherswan.artisticendeavors.jsoup.parsers;

import com.leatherswan.artisticendeavors.jpa.enums.DataConfigProfile;
import com.leatherswan.artisticendeavors.jsoup.base.JsoupHtmlParser;
import com.leatherswan.artisticendeavors.jsoup.dto.TestDTO;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(DataConfigProfile.H2)
public class TestDTOParser extends JsoupHtmlParser<TestDTO> {

    public TestDTOParser(Class<TestDTO> classModel) {
        super(classModel);
    }

}