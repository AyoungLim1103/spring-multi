package net.joins.site.controller;

import com.misolab.core.exception.BadRequestException;
import com.misolab.core.vo.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import net.joins.domain.entity.Member;
import net.joins.domain.dto.MemberInfo;
import lombok.RequiredArgsConstructor;
import net.joins.domain.mapper.MemberMapper;
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

@Slf4j
@RequiredArgsConstructor
@Controller
//@RequestMapping("/")
public class IndexController {

    //@ResponseBody
    @GetMapping("/test")
    public String test(@RequestParam String userId, String name) {

        if (StringUtils.isEmpty(name)) {
            throw new BadRequestException("name is required parameter");
        }

        Member member = new Member();
        member.setUserId(userId);

        member.setName(StringUtils.isEmpty(name) ? userId : name);

        MemberInfo memberInfo = MemberInfo.builder().userId(userId).name(name).build();

//        member = MemberMapper.INSTANCE.toEntity(memberInfo);
        //MemberInfo memberInfo = new MemberInfo();
        //memberInfo.setUserId(userId);
        //memberInfo.setName(name);

        //List<Member> result = memberDao.allMembers();
        //List<MemberInfo> list = new ArrayList<>(); //result.stream().map(UserInfo::new).collect(Collectors.toList());

        //ApiResponse response = ApiResponse.of("list", list);
        return memberInfo.getUserId();//ResponseEntity.ok(response);
    }

    @GetMapping
    public String index(Model model, String msg) {
        model.addAttribute("msg", msg);
       // model.addAttribute("current", memberDao.getDate());

        return "index";
    }
}
