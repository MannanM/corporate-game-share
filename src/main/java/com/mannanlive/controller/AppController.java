package com.mannanlive.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AppController implements ErrorController {

    private final Logger log = LoggerFactory.getLogger(AppController.class);

    @Value("${apiUrl}")
    private String apiUrl;

    @Value("${react-bundle}")
    private String reactBundle;

    @RequestMapping(value = "/")
    public String index(Model model) {
        return singlePageApp(model);
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return singlePageApp(model);
            }
        }
        //todo: add nice custom error page
        return singlePageApp(model);
    }

    private String singlePageApp(Model model) {
        model.addAttribute("name", "CorpGameShare");
        model.addAttribute("apiUrl", apiUrl);
        model.addAttribute("reactBundle", reactBundle);
        log.debug("bootstrapping app...");
        return "index";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
