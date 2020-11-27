package net.joins.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.reflect.Member;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BoardInfo {

    private Long bno;
    private String title;
    private Long userSeq;
    //private String userName;
    private String content;
    private String delYN; //삭제유쿠

    private Timestamp regdate;
    private Timestamp updatedate;

    MemberInfo memberInfo;
}
