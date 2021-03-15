package net.joins.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ReplyParam {
    Long rno;
    String replyText;
    Timestamp regdate;
    Timestamp updatedate;
    MemberInfo memberInfo;
    BoardInfo boardInfo;
}
