package net.joins.site.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import net.joins.domain.dto.MemberInfo;
import net.joins.domain.entity.Board;
import net.joins.domain.entity.Member;
import net.joins.domain.mapper.BoardMapper;
import net.joins.domain.mapper.MemberMapper;
import net.joins.domain.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
/*
    //@Transactional
    @PostMapping("/join")
    public String joinPost(@ModelAttribute("memberInfo") Member member){
        log.info("MEMBER : "+member);

        String encryptPw = pwEncoder.encode(member.getMemberPw());
        log.info("en : "+encryptPw);
        member.setMemberPw(encryptPw);

        repo.save(member);

        return "/member/joinResult";
    }
 */
    @PostMapping("/join")
    public String joinPost(@ModelAttribute("memberInfo") MemberInfo memberInfo){
        log.info("MEMBER : "+memberInfo);

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