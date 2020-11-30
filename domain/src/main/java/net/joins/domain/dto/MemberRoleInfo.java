package net.joins.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
public class MemberRoleInfo {
    Long fno;
    String roleName;
}
