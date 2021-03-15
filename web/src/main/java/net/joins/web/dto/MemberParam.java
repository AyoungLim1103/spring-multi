package net.joins.web.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
public class MemberParam {
    Long memberSeq;
    @NotEmpty(message = "사용자ID는 필수값 입니다.")
    String memberId;
    @NotBlank(message = "비밀번호는 필수값 입니다.")
    String memberPw;
    @NotEmpty(message = "사용자 이름은 필수값 입니다.")
    String name;

    String mobile;
    String email;

    List<MemberRoleInfo> roles;
}
