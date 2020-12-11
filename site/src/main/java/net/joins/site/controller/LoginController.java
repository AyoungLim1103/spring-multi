package net.joins.site.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
@Log
public class LoginController {

    @GetMapping("/login")
    public void login(){

    }

    @GetMapping("/accessDenied")
    public void accessDenied(){

    }

    @GetMapping("/logout")
    public void logout(){

    }

}
