package net.joins.domain.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import net.joins.domain.entity.MemberRole;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
public class MemberInfo {
    Long memberSeq;
    String memberId;
    String memberPw;
    String name;

    String mobile;
    String email;

    Timestamp regdate;
    Timestamp updatedate;

    List<MemberRole> roles;
}
