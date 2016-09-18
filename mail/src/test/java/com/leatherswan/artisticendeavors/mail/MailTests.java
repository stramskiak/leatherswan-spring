package com.leatherswan.artisticendeavors.mail;

import com.leatherswan.artisticendeavors.jpa.enums.DataConfigProfile;
import com.leatherswan.artisticendeavors.mail.common.MailSettings;
import com.leatherswan.artisticendeavors.mail.components.MailSender;
import com.leatherswan.artisticendeavors.mail.dto.MailDTO;
import com.leatherswan.artisticendeavors.mail.service.MailService;
import com.leatherswan.artisticendeavors.mail.service.MailServiceImpl;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(DataConfigProfile.H2)
public class MailTests extends MailContext {

    private MailSender mockMailSender;
    private MailDTO mailDTO;

    private MailService mockMailService;
    private MailSettings mailSettings;
    private VelocityEngine velocityEngine;

    @Before
    public void setUp() {
        mockMailSender = mock(MailSender.class);
        mockMailService =
                new MailServiceImpl(mockMailSender, mailSettings, velocityEngine);
        mailDTO = MailTestUtils.testMailDTO();
    }

    @Test
    public void mailSenderSendsMimeMessage() throws MessagingException {
        mockMailService.sendContactMail(mailDTO);
        verify(mockMailSender, Mockito.times(1)).send(any(MimeMessagePreparator.class));
    }


}
