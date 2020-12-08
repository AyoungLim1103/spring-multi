package net.joins.site.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import net.joins.domain.entity.Member;
import net.joins.domain.entity.MemberRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Getter
@Setter
public class SiteSecurityUser extends User {

    private static final String ROLE_PREFIX = "ROLE_";

    private Member member;

    public SiteSecurityUser(Member member){
        super(member.getMemberId(), member.getMemberPw(), makeGrantedAuthority(member.getRoles()));
        this.member = member;
    }

    private static List<GrantedAuthority> makeGrantedAuthority(List<MemberRole> roles){

        List<GrantedAuthority> list = new ArrayList<>();

        roles.forEach(role -> list.add(new SimpleGrantedAuthority((ROLE_PREFIX + role.getRoleName()))));

        return list;
    }
}
