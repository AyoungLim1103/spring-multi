package net.joins.web.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BoardInfo {

    private Long bno;
    @NotBlank(message = "제목을 입력해주세요") 
    private String title;
    @NotBlank(message = "내용을 입력해주세요")
    private String content;
    private String delYN;

    private Timestamp regdate;
    private Timestamp updatedate;

    MemberInfo memberInfo;
    List<ReplyInfo> repliesInfo;

}
