package com.leatherswan.artisticendeavors.jpa.components;

import com.leatherswan.artisticendeavors.jpa.common.ApplicationSettings;
import com.leatherswan.artisticendeavors.jpa.common.ISiteOption;
import com.leatherswan.artisticendeavors.jpa.common.SiteOptions;
import com.leatherswan.artisticendeavors.jpa.dto.PostDTO;
import com.leatherswan.artisticendeavors.jpa.dto.SiteOptionDTO;
import com.leatherswan.artisticendeavors.jpa.enums.PostDisplayType;
import com.leatherswan.artisticendeavors.jpa.enums.PostType;
import com.leatherswan.artisticendeavors.jpa.exceptions.ContactNotFoundException;
import com.leatherswan.artisticendeavors.jpa.exceptions.DuplicatePostNameException;
import com.leatherswan.artisticendeavors.jpa.exceptions.SiteOptionNotFoundException;
import com.leatherswan.artisticendeavors.jpa.model.UserConnection;
import com.leatherswan.artisticendeavors.jpa.service.ContactService;
import com.leatherswan.artisticendeavors.jpa.service.PostService;
import com.leatherswan.artisticendeavors.jpa.service.SiteService;
import com.leatherswan.artisticendeavors.jpa.service.UserService;
import com.leatherswan.artisticendeavors.jpa.utils.ContactUtils;
import com.leatherswan.artisticendeavors.jpa.utils.PostUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class JpaUI {

    // region  Beans

    @Autowired
    private PostService postService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @Autowired
    private SiteService siteService;

    @Autowired
    private ApplicationSettings applicationSettings;

    @Autowired
    DefaultListableBeanFactory beanfactory;

    @Autowired
    private SiteOptions siteOptions;

    // endregion

    private Boolean reset = true;

    public void init() {
        displayRandomUserIdString();
    }

    private void displayRandomUserIdString() {
        System.out.println(RandomStringUtils.randomAlphanumeric(16));
    }

    private void addPostDemo() throws DuplicatePostNameException {
        String title = "Best way to create SEO friendly URI string";
        PostDTO postDTO = PostDTO.getBuilder(
                1L,
                title,
                PostUtils.createSlug(title),
                "http://nixmash.com/java/variations-on-json-key-value-pairs-in-spring-mvc/",
                "This is the post content",
                PostType.LINK,
                PostDisplayType.LINK_FEATURE
        ).build();
        postService.add(postDTO);
    }

    // region Unused demos

    private void siteOptionsDemo() {
        System.out.println("Initialized SiteOptions Bean Property: " +
                siteOptions.getGoogleAnalyticsTrackingId());

        String siteName = reset ? "My Site" : "My Updated Site Name";
        String integerProperty = reset ? "1" : "8";

        try {
            siteService.update(new SiteOptionDTO(ISiteOption.SITE_NAME, siteName));
            siteService.update(new SiteOptionDTO(ISiteOption.INTEGER_PROPERTY, integerProperty));
        } catch (SiteOptionNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("New SiteOptions values: " + siteOptions.getSiteName() + " -- " + siteOptions.getIntegerProperty());
        System.out.println("GoogleAnalyticsId: " + siteOptions.getGoogleAnalyticsTrackingId());
    }

    public void entityDemo() {

        UserConnection userConnection = userService.getUserConnectionByUserId("daver");
        ContactUtils.listUserConnection("My User Connection", userConnection);
        ContactUtils.listUsersWithDetail(userService.getUsersByAuthorityId(1L));
        ContactUtils.listUser("USER BY EMAIL",
                userService.getByEmail("user@aol.com").get());
        try {
            ContactUtils.listContact("CONTACT BY EMAIL",
                    contactService.findContactById(1L));
        } catch (ContactNotFoundException e) {
            e.printStackTrace();
        }

        ContactUtils.listContacts("ENTITIES FIND ALL", contactService.findAll());
        ContactUtils.listContacts("ENTITIES FIND BY FIRST NAME",
                contactService.findByFirstName("Barry"));
        ContactUtils.listContacts("ENTITIES FIND BY FIRST AND LAST NAME",
                contactService.findByFirstNameAndLastName("Tad", "Grant"));

        ContactUtils.listContact("SINGLE CONTACT: ",
                contactService.getContactByEmail("Nam.nulla@pedenonummyut.edu"));
        ContactUtils.listContactsWithDetail(contactService.getContactsWithDetail());

        ContactUtils.listContactWithDetail(contactService.getContactByIdWithDetail(2L));

        ContactUtils.listContacts("FIND BY FIRST NAME",
                contactService.findByFirstName("Summer"));

        ContactUtils.listContactWithDetail(contactService.getContactByIdWithDetail(1L));
        ContactUtils.contactToContactDTO(contactService.getContactByIdWithDetail(2L));

        try {
            contactService.update(ContactUtils.contactToContactDTO(contactService.getContactByIdWithDetail(2L)));
        } catch (ContactNotFoundException e) {
            e.printStackTrace();
        }
    }

    // endregion

}
