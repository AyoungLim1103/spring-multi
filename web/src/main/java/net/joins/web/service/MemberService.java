package net.joins.web.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.joins.domain.entity.Member;
import net.joins.domain.repository.MemberRepository;
import net.joins.web.dto.MemberInfo;
import net.joins.web.mapper.MemberMapper;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
    final MemberRepository repo;

    public void saveMember(MemberInfo memberInfo){
        log.info("[saveMember] MemberInfo : "+memberInfo);
        Member member = MemberMapper.INSTANCE.memberInfoToMember(memberInfo);

        repo.save(member);
    }
}
