package com.leatherswan.artisticendeavors.mail;

import com.leatherswan.artisticendeavors.jpa.enums.DataConfigProfile;
import com.leatherswan.artisticendeavors.mail.dto.MailDTO;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(DataConfigProfile.H2)
public class MailTestUtils {

    public static MailDTO testMailDTO() {
        MailDTO mailDTO = new MailDTO();
        mailDTO.setFrom("testdude@aol.com");
        mailDTO.setFromName("Test Dude");
        mailDTO.setBody("This is a message from test dude");
        return  mailDTO;
    }
}
