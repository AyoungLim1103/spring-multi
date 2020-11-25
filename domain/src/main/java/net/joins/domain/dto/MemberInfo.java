package net.joins.domain.dto;

import lombok.*;
import net.joins.domain.entity.Member;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
public class MemberInfo {
   // Long id;
    String userId;
    String name;

    String phone;
    String email;

    @Builder
    public MemberInfo(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

}
