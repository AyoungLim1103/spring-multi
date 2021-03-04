package net.joins.site.controller;

import lombok.extern.java.Log;
import net.joins.web.dto.MemberInfo;
import net.joins.domain.entity.Member;
import net.joins.web.mapper.MemberMapper;
import net.joins.domain.repository.MemberRepository;
import net.joins.web.service.BoardService;
import net.joins.web.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import java.util.Map;

@Log
@Controller
@RequestMapping("/member/")
public class MemberController {

    MemberService memberService;

    @Autowired
    PasswordEncoder pwEncoder;

    @Autowired
    MemberRepository repo;

    @GetMapping("/join")
    public String join(Model model){
        model.addAttribute("memberInfo", new MemberInfo());
        return "/member/join";

    }
/*
    //@Transactional
    @PostMapping("/join")
    public String joinPost(@ModelAttribute("memberInfo") Member member){
        log.info("MEMBER : "+member);

        String encryptPw = pwEncoder.encode(member.getMemberPw());
        log.info("en : "+encryptPw);s
        member.setMemberPw(encryptPw);

        repo.save(member);

        return "/member/joinResult";
    }
 */
    @PostMapping("/join")
    public String joinPost(@Valid @ModelAttribute("memberInfo") MemberInfo memberInfo, BindingResult bindingResult){
        log.info("MEMBER : "+memberInfo);

        if(bindingResult.hasErrors()){
            return "/member/join";
        }

        String encryptPw = pwEncoder.encode(memberInfo.getMemberPw());
        log.info("en : "+encryptPw);
        memberInfo.setMemberPw(encryptPw);

        //service 단으로 옮겨도 됨
        Member member = MemberMapper.INSTANCE.memberInfoToMember(memberInfo);

        repo.save(member);

        return "/member/joinResult";
    }

    @GetMapping("/logout")
    public void logout(){

    }

}
