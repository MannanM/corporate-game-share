package com.mannanlive.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

    private final Logger log = LoggerFactory.getLogger(AppController.class);

    @Value("${apiUrl}")
    private String apiUrl;

    @RequestMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("name", "CorpGameShare");
        model.addAttribute("apiUrl", apiUrl);
        log.debug("bootstrapping app...");
        return "index";
    }
}
