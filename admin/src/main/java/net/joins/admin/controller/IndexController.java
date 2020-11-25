package net.joins.admin.controller;

import lombok.RequiredArgsConstructor;
import net.joins.domain.entity.Member;
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
        Member member = new Member();
        member.setName(msg);
        member.setUserId(msg);

        return "This is spring-multi Admin " + member;
    }


}
