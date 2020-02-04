package com.mainacad.controller.jsp;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Profile("jsp")
public class MainController {

    @GetMapping()
    public String getLandingPage() {
        return "authorization";
    }
}