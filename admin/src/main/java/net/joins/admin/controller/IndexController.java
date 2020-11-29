package net.joins.admin.controller;

import lombok.RequiredArgsConstructor;
import net.joins.domain.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class IndexController {

    @GetMapping(value = {"/", "/login"})
    public String entry() {
        return "index";
    }

    @ResponseBody
    @GetMapping("/ajax")
    public String api(String msg) {
        User user = new User();
        user.setName(msg);
        user.setUserId(msg);

        return "This is spring-multi Admin " + user;
    }


}
