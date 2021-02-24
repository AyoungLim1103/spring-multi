package net.joins.web.dto;

import lombok.*;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberRoleInfo {
    Long fno;
    String roleName;
}
