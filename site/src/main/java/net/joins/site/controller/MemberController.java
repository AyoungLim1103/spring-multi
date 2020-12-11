package net.joins.site.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import net.joins.domain.entity.Member;
import net.joins.domain.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log
@Controller
@RequestMapping("/member/")
public class MemberController {

    @Autowired
    PasswordEncoder pwEncoder;

    @Autowired
    MemberRepository repo;

    @GetMapping("/join")
    public void join(){

    }

    @PostMapping("/join")
    public String joinPost(@ModelAttribute("member") Member member){
        log.info("MEMBER : "+member);

        String encryptPw = pwEncoder.encode(member.getMemberPw());
        log.info("en : "+encryptPw);
        member.setMemberPw(encryptPw);

        repo.save(member);

        return "/member/joinResult";
    }

    @GetMapping("/logout")
    public void logout(){

    }

}
