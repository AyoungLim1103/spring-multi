package net.joins.api.controller;

import net.joins.domain.entity.Member;
import net.joins.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class IndexController {

    @GetMapping
    public ResponseEntity index(String msg) {
        Member member = new Member();
        member.setName(msg);
        member.setUserId(msg);

        MemberDto memberDto = new MemberDto(member);
        return ResponseEntity.ok(memberDto);
    }
}
