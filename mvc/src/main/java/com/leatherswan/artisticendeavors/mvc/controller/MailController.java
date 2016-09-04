package com.leatherswan.artisticendeavors.mvc.controller;

import com.leatherswan.artisticendeavors.mvc.components.WebUI;
import com.leatherswan.artisticendeavors.mail.dto.MailDTO;
import com.leatherswan.artisticendeavors.mail.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class MailController {

    private static final Logger logger = LoggerFactory.getLogger(MailController.class);

    public static final String MAIL_CONTACT_VIEW = "users/contact";
    public static final String EMAIL_SENT_MESSAGE_KEY = "mail.contact.sent";

    private WebUI webUI;
    private MailService mailService;

    @Autowired
    public MailController(WebUI webUI, MailService mailService) {
        this.webUI = webUI;
        this.mailService = mailService;
    }

    @RequestMapping(value = "/users/contact", method = GET)
    public String home(Model model) {
        model.addAttribute("mailDTO", new MailDTO());
        return MAIL_CONTACT_VIEW;
    }

    @RequestMapping(value = "/users/contact", method = RequestMethod.POST)
    public String handleContactEmail(@Valid MailDTO mailDTO, BindingResult result,
                                     RedirectAttributes attributes)  {

        if (result.hasErrors()) {
            logger.info("Email Errors for email from: {}", mailDTO.getFrom());
            return MAIL_CONTACT_VIEW;
        } else {
            logger.info("Sending email from: {}", mailDTO.getFrom());
            mailService.sendContactMail(mailDTO);
            webUI.addFeedbackMessage(attributes, EMAIL_SENT_MESSAGE_KEY);
            return "redirect:/users/contact";
        }
    }

}
