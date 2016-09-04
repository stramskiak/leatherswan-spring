package com.leatherswan.artisticendeavors.jsoup.service;

import com.leatherswan.artisticendeavors.jsoup.dto.PagePreviewDTO;

/**
 * Created by daveburke on 5/29/16.
 */
public interface JsoupService {
    PagePreviewDTO getPagePreview(String url);
}
