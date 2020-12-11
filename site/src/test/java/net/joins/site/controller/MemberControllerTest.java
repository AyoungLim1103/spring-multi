package net.joins.site.controller;

import lombok.extern.java.Log;
import net.joins.domain.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active:arumu")
@Log
@Commit
public class MemberControllerTest {
    @Autowired
    private MemberRepository repo;

    @Autowired
    PasswordEncoder pwEncoder;

    @Test
    public void testUpdateOldMember(){
        List<Long> ids = new ArrayList<>();

        for(Long i=1L; i<=11L; i++){
            ids.add(i);
        }

        repo.findAllById(ids).forEach(member ->{
            member.setMemberPw(pwEncoder.encode(member.getMemberPw()));
            repo.save(member);
        });
    }

}