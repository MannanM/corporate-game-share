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
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class AppController implements ErrorController {

    private final Logger log = LoggerFactory.getLogger(AppController.class);

    @Value("${apiUrl}")
    private String apiUrl;

    @Value("${react-bundle}")
    private String reactBundle;

    @Value("${info.git}")
    private String git;

    @Value("${info.version}")
    private String version;

    @Value("#{new java.text.SimpleDateFormat(\"yyyy-MM-dd'T'HH:mm:ss\").parse(\"${info.date}\")}")
    private Date date;

    @RequestMapping(value = "/")
    public String index(Model model) {
        return singlePageApp(model);
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, HttpServletResponse response, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            //todo: for api requests return JSON

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                response.setStatus(200);
                return singlePageApp(model);
            }
        }
        return singlePageApp(model);
    }

    private String singlePageApp(Model model) {
        model.addAttribute("apiUrl", apiUrl);
        model.addAttribute("git", git);
        model.addAttribute("version", version);
        model.addAttribute("date", date);
        model.addAttribute("reactBundle", reactBundle);
        log.debug("bootstrapping app...");
        return "index";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
