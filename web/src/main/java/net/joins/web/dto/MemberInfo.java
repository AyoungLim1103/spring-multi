package net.joins.web.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
public class MemberInfo {
    Long memberSeq;
    @NotNull(message = "사용자ID는 필수값 입니다.")
    String memberId;
    String memberPw;
    @NotNull(message = "사용자 이름은 필수값 입니다.")
    String name;

    String mobile;
    String email;

    Timestamp regdate;
    Timestamp updatedate;

    List<MemberRoleInfo> roles;
}
