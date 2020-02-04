package com.mainacad.controller.jsp;

import com.mainacad.model.User;
import com.mainacad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("user")
@Profile("jsp")
public class UserJspController {

    @Autowired
    UserService userService;

    @PostMapping("auth")
    public String getAuthUser(Model model,
                              @RequestParam(value="login") String login,
                              @RequestParam(value="pass") String password) {

        User user = userService.getByLoginAndPassword(login, password);
        if (user != null){
            model.addAttribute("name", user.getFirstName());
            model.addAttribute("surname", user.getLastName());
            return "good-login";
        }
        return "wrong-login";
    }
}