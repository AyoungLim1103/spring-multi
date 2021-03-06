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
public class ReplyInfo {
    Long rno;
    @NotNull(message = "댓글내용을 입력하세요.")
    String replyText;
    Timestamp regdate;
    String insertUserId;
    String insertUserNm;
    Long bno;
}
