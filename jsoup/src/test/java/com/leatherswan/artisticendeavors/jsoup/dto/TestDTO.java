package com.leatherswan.artisticendeavors.jsoup.dto;

import com.leatherswan.artisticendeavors.jpa.enums.DataConfigProfile;
import com.leatherswan.artisticendeavors.jsoup.annotations.*;
import com.leatherswan.artisticendeavors.jsoup.base.JsoupTwitter;
import com.leatherswan.artisticendeavors.jsoup.base.JsoupImage;
import com.leatherswan.artisticendeavors.jsoup.base.JsoupLink;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SuppressWarnings("WeakerAccess")
@ActiveProfiles(DataConfigProfile.H2)
public class TestDTO {

    @Selector(".myclass")
    @TextValue
    public String myClassText;

    @Selector(".myclass")
    @AttributeValue(name="myattr")
    public String myClassAttribute;

    @Selector(".myclass")
    @HtmlValue
    public String myClassHtml;

    @Selector("#myid")
    @TextValue
    public String myIdText;

    @MetaName("keywords")
    public String metaKeywords;

    @MetaName("description")
    public String description;

    @MetaProperty("og:image")
    public String facebookImage;

    @ImageSelector( value = "#content")
    public List<JsoupImage> testImagesInContentArea;

    @ImageSelector
    public List<JsoupImage> testImagesInPage;

    @ImageSelector(".myimage")
    public JsoupImage testImage;

    @LinkSelector("#content")
   public List<JsoupLink> testLinksInContentArea;

    @LinkSelector
    public List<JsoupLink> testLinksInPage;

    @LinkSelector(".mylink")
    public JsoupLink testLink;

    @TwitterSelector
    public JsoupTwitter twitterDTO;

    // region getters setters

    public String getMyClassAttribute() {
        return myClassAttribute;
    }

    public String getMyClassHtml() {
        return myClassHtml;
    }

    public String getMyIdText() {
        return myIdText;
    }

    public String getMyClassText() {
        return myClassText;
    }

    public void setMyClassAttribute(String myClassAttribute) {
        this.myClassAttribute = myClassAttribute;
    }

    public void setMyClassHtml(String myClassHtml) {
        this.myClassHtml = myClassHtml;
    }

    public void setMyIdText(String myIdText) {
        this.myIdText = myIdText;
    }

    public void setMyClassText(String myClassText) {
        this.myClassText = myClassText;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public String getFacebookImage() {
        return facebookImage;
    }

    public void setFacebookImage(String facebookImage) {
        this.facebookImage = facebookImage;
    }

    public List<JsoupImage> getTestImagesInContentArea() {
        return testImagesInContentArea;
    }

    public void setTestImagesInContentArea(List<JsoupImage> testImagesInContentArea) {
        this.testImagesInContentArea = testImagesInContentArea;
    }

    public List<JsoupImage> getTestImagesInPage() {
        return testImagesInPage;
    }

    public void setTestImagesInPage(List<JsoupImage> testImagesInPage) {
        this.testImagesInPage = testImagesInPage;
    }

    public JsoupImage getTestImage() {
        return testImage;
    }

    public void setTestImage(JsoupImage testImage) {
        this.testImage = testImage;
    }

    public List<JsoupLink> getTestLinksInContentArea() {
        return testLinksInContentArea;
    }

    public void setTestLinksInContentArea(List<JsoupLink> testLinksInContentArea) {
        this.testLinksInContentArea = testLinksInContentArea;
    }

    public List<JsoupLink> getTestLinksInPage() {
        return testLinksInPage;
    }

    public void setTestLinksInPage(List<JsoupLink> testLinksInPage) {
        this.testLinksInPage = testLinksInPage;
    }

    public JsoupLink getTestLink() {
        return testLink;
    }

    public void setTestLink(JsoupLink testLink) {
        this.testLink = testLink;
    }

    public JsoupTwitter getTwitterDTO() {
        return twitterDTO;
    }

    public void setTwitterDTO(JsoupTwitter twitterDTO) {
        this.twitterDTO = twitterDTO;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // endregion



}
