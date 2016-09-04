package com.leatherswan.artisticendeavors.jsoup.parsers;

import com.leatherswan.artisticendeavors.jsoup.base.JsoupHtmlParser;
import com.leatherswan.artisticendeavors.jsoup.dto.TestDTO;

public class TestDTOParser extends JsoupHtmlParser<TestDTO> {

    public TestDTOParser(Class<TestDTO> classModel) {
        super(classModel);
    }

}