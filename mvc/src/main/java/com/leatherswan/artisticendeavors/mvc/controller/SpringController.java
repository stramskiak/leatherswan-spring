package com.leatherswan.artisticendeavors.mvc.controller;

import com.leatherswan.artisticendeavors.jpa.dto.SelectOptionDTO;
import com.leatherswan.artisticendeavors.mail.service.TemplateService;
import com.leatherswan.artisticendeavors.mvc.components.WebUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
//@RequestMapping(value = "/spring")
public class SpringController {

    private static final Logger logger = LoggerFactory.getLogger(SpringController.class);

    public static final String SPRING_VIEW = "springHome";

    private final TemplateService templateService;
    private final WebUI webUI;

    @Autowired
    Environment environment;

    @Autowired
    public SpringController(TemplateService templateService, WebUI webUI) {
        this.templateService = templateService;
        this.webUI = webUI;
    }

    @RequestMapping(value = "/spring", method = GET)
    public String home(Model model) {
        String springVersion = webUI.parameterizedMessage("home.spring.version", SpringBootVersion.getVersion(), SpringVersion.getVersion());
        model.addAttribute("springVersion", springVersion);
        model.addAttribute("gitHubStats", webUI.getGitHubStats());
        return SPRING_VIEW;
    }


    @RequestMapping(value = "/json/badges/update", method = RequestMethod.POST)
    public
    @ResponseBody
    String updateBadges(@RequestBody List<String> badgeboys) {
        if (badgeboys != null) {
            String badges = badgeboys.stream().collect(joining(", "));
            logger.info("Badge Boy Items: " + badges);
            return webUI.getMessage("js.badgeboy.result", badges);
        } else
            return "No badges selected...";
    }

    @RequestMapping(value = "/json/badges", method = RequestMethod.GET)
    public
    @ResponseBody
    List<SelectOptionDTO> getBadges() {
        return badgeSelectOptions();
    }

    private List<SelectOptionDTO> badgeSelectOptions() {
        List<SelectOptionDTO> selectOptionDTOs = new ArrayList<>();
        selectOptionDTOs.add(new SelectOptionDTO("Innovative", "Innovative", false));
        selectOptionDTOs.add(new SelectOptionDTO("Creator", "Creator", true));
        selectOptionDTOs.add(new SelectOptionDTO("Spiritual", "Spiritual", false));
        selectOptionDTOs.add(new SelectOptionDTO("Worldly", "Worldly", false));
        selectOptionDTOs.add(new SelectOptionDTO("Leader", "Leader", false));
        selectOptionDTOs.add(new SelectOptionDTO("Worthy", "Worthy", false));
        return selectOptionDTOs;
    }
}