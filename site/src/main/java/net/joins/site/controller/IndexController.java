package net.joins.site.controller;

import com.misolab.core.exception.BadRequestException;
import com.misolab.core.vo.ApiResponse;
import net.joins.domain.entity.Member;
import net.joins.domain.mapper.MemberMapper;
import net.joins.domain.repository.MemberRepository;
import net.joins.domain.dto.MemberInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
public class IndexController {

    final MemberRepository memberRepository;

    @ResponseBody
    @GetMapping("/api")
    public ResponseEntity api(@RequestParam String userId, String name) {

        if (StringUtils.isEmpty(name)) {
            throw new BadRequestException("name is required parameter");
        }

        Member member = new Member();
        member.setUserId(userId);
        member.setName(StringUtils.isEmpty(name) ? userId : name);

        memberRepository.save(member);

        List<MemberInfo> list = new ArrayList<>(); //result.stream().map(UserInfo::new).collect(Collectors.toList());

        ApiResponse response = ApiResponse.of("list", list);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public String index(Model model, String msg) {
        Member member = new Member();
        member.setName(msg);

        MemberInfo userInfo = MemberMapper.INSTANCE.memberToMemberInfo(member);
        model.addAttribute("msg", userInfo);
        return "index";
    }
}
