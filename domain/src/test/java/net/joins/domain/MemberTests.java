package net.joins.domain;

import lombok.extern.java.Log;
import net.joins.domain.entity.Member;
import net.joins.domain.entity.MemberRole;
import net.joins.domain.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active:ayoung")
@Log
@Commit
public class MemberTests {
    @Autowired
    private MemberRepository repo;

    @Test
    public void testInsert(){
        for(int i = 0; i <= 10; i++) {
            Member member = new Member();
            member.setMemberId("user"+ i);
            member.setMemberPw("{noop}pw" + i);
            member.setName("사용자" + i);
            member.setEmail("user"+ i + "@joins.com");
            member.setMobile("010-0000-000" + i);
            MemberRole role = new MemberRole();
            if(i <= 5) {
                role.setRoleName("BASIC");
            } else if(i <= 9) {
                role.setRoleName("MANAGER");
            } else {
                role.setRoleName("ADMIN");
            }
            member.setRoles(Arrays.asList(role));
            log.info("Member내용" + member.getMemberId());
            repo.save(member);
        }
    }

    @Test
    public void testRead() {
        Optional<Member> result = repo.findById(2L);
        result.ifPresent(member -> log.info("member is : " + member));
    }
}
