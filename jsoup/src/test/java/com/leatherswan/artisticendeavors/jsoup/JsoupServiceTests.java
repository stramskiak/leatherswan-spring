package com.leatherswan.artisticendeavors.jsoup;


import com.leatherswan.artisticendeavors.jpa.enums.DataConfigProfile;
import com.leatherswan.artisticendeavors.jsoup.dto.PagePreviewDTO;
import com.leatherswan.artisticendeavors.jsoup.service.JsoupService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(DataConfigProfile.H2)
public class JsoupServiceTests extends JsoupContext {

    @Autowired
    JsoupService jsoupService;

    private static final String REPO_URL = "https://github.com/mintster/spring-data";

//    @Before
//    public void setup() throws IOException {
//    }


    @Test
    public void throwIOExceptionWithBadUrl() throws IOException {
        PagePreviewDTO pagePreviewDTO = jsoupService.getPagePreview("http://bad.url");
        assert (pagePreviewDTO == null);
    }

    @Test
    public void retrievePagePreviewDTOWithValidUrl() {
        assert (jsoupService.getPagePreview(REPO_URL) != null);
    }
}
