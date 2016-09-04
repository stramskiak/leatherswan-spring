package com.leatherswan.artisticendeavors.mvc.controller;

import com.leatherswan.artisticendeavors.jpa.repository.ProductRepository;
import com.leatherswan.artisticendeavors.jpa.service.ProductService;
import com.leatherswan.artisticendeavors.mvc.components.WebUI;
import com.leatherswan.artisticendeavors.mail.service.TemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

import static java.util.stream.Collectors.joining;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class GeneralController {

    private static final Logger logger = LoggerFactory.getLogger(GeneralController.class);

    public static final String HOME_VIEW = "home";
    public static final String ITEM_LIST_VIEW = "products/list";
    public static final String ERROR_403_VIEW = "errors/custom";

    private final TemplateService templateService;
    private final WebUI webUI;

    @Autowired
    private ProductService productService;

    @Autowired
    Environment environment;

    @Autowired
    public GeneralController(TemplateService templateService, WebUI webUI) {
        this.templateService = templateService;
        this.webUI = webUI;
    }

    @RequestMapping(value = "/", method = GET)
    public String home(Model model) {
        String springVersion = webUI.parameterizedMessage("home.spring.version", SpringBootVersion.getVersion(), SpringVersion.getVersion());
        model.addAttribute("springVersion", springVersion);
        model.addAttribute("gitHubStats", webUI.getGitHubStats());

        return HOME_VIEW;
//        return ITEM_LIST_VIEW;
    }


    @RequestMapping(value = "/robots.txt", method = RequestMethod.GET)
    @ResponseBody
    public String plaintext(HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        return templateService.getRobotsTxt();
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied(Principal user) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("errortitle", "Not Authorized");
        mav.addObject("errorbody", "You are not authorized to view this page.");
        mav.setViewName(ERROR_403_VIEW);
        return mav;

    }

}
