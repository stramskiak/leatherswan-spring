package com.leatherswan.artisticendeavors.jsoup.utils;

import com.leatherswan.artisticendeavors.jpa.enums.DataConfigProfile;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.net.URISyntaxException;

@ActiveProfiles(DataConfigProfile.H2)
public class JsoupTestUtil {

    public static File getFile(String resourceName) {
        try {
            File file = new File(JsoupTestUtil.class.getResource(resourceName).toURI());
            return file;
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }

}
