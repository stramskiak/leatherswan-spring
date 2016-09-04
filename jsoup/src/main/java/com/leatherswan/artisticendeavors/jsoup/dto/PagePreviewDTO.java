package com.leatherswan.artisticendeavors.jsoup.dto;

import com.leatherswan.artisticendeavors.jsoup.annotations.ImageSelector;
import com.leatherswan.artisticendeavors.jsoup.annotations.MetaName;
import com.leatherswan.artisticendeavors.jsoup.annotations.Selector;
import com.leatherswan.artisticendeavors.jsoup.annotations.TwitterSelector;
import com.leatherswan.artisticendeavors.jsoup.base.JsoupImage;
import com.leatherswan.artisticendeavors.jsoup.base.JsoupTwitter;

import java.util.List;


@SuppressWarnings("WeakerAccess")
public class PagePreviewDTO {

    @Selector("title")
    public String title;

    @MetaName("keywords")
    public String keywords;

    @MetaName("description")
    public String description;

    @TwitterSelector
    public JsoupTwitter twitterDTO;

    @ImageSelector
    public List<JsoupImage> images;

    // region getters setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<JsoupImage> getImages() {
        return images;
    }

    public void setImages(List<JsoupImage> images) {
        this.images = images;
    }

    public JsoupTwitter getTwitterDTO() {
        return twitterDTO;
    }

    public void setTwitterDTO(JsoupTwitter twitterDTO) {
        this.twitterDTO = twitterDTO;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // endregion

}
