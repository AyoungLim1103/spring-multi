package net.joins.site.controller;

import com.misolab.core.exception.BadRequestException;
import com.misolab.core.vo.ApiResponse;
import lombok.extern.java.Log;
import net.joins.domain.dto.MemberInfo;
import net.joins.domain.entity.Member;
import net.joins.domain.mapper.MemberMapper;
import net.joins.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
@Log
public class IndexController {

    final MemberRepository memberRepository;

    @ResponseBody
    @GetMapping("/api")
    public ResponseEntity api(@RequestParam String memberId, String name) {

        if (StringUtils.isEmpty(name)) {
            throw new BadRequestException("name is required parameter");
        }

        Member member = new Member();
        member.setMemberId(memberId);
        member.setName(StringUtils.isEmpty(name) ? memberId : name);

        memberRepository.save(member);

        List<MemberInfo> list = new ArrayList<>(); //result.stream().map(UserInfo::new).collect(Collectors.toList());

        ApiResponse response = ApiResponse.of("list", list);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public String index(Model model, String msg) {
        Member member = new Member();
        member.setName(msg);

        MemberInfo memberInfo = MemberMapper.INSTANCE.memberToMemberInfo(member);
        model.addAttribute("msg", memberInfo);
        return "index";
    }

    @RequestMapping("/guest")
    public String forGuest(){
        log.info("Guest");

        //임시
        return "index";
    }

    @RequestMapping("/manager")
    public String forManager(){
        log.info("manager");

        //임시
        return "index";
    }

    @RequestMapping("/admin")
    public String forAdmin(){
        log.info("admin");

        //임시
        return "index";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/adminSecret")
    public void forAdminSecret(){
        log.info("admin secret");
    }

    @Secured("ROLE_MANAGER")
    @RequestMapping("/managerSecret")
    public void forManagerSecret(){
        log.info("manager secret");
    }

}
