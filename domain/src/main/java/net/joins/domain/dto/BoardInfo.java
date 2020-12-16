package net.joins.domain.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BoardInfo {

    private Long bno;
    private String title;
    private String content;
    private String delYN;

    private Timestamp regdate;
    private Timestamp updatedate;

    MemberInfo memberInfo;
    List<ReplyInfo> repliesInfo;

    @Builder
    public BoardInfo(Long bno) {
        this.bno = bno;
    }
}
