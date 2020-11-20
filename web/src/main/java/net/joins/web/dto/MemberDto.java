package net.joins.web.dto;

import net.joins.domain.entity.Member;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MemberDto {
    String name;
    String mail;

    public MemberDto(Member member) {
        if (member.getName() != null) {
            this.name = member.getName().toUpperCase();
            this.mail = member.getUserId() + "@joins.com";
        }
    }
}
