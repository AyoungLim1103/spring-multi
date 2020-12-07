package net.joins.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Getter
@Setter
@Entity
@Table(name = "TB_BOARD")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BNO")
    private Long bno;
    @Column(name = "TITLE", length = 300)
    private String title;
    @Column(name = "CONTENT", length = 2000)
    private String content;
    @Column(name = "DEL_YN", length = 1)
    private String delYN; //삭제유쿠

    @CreationTimestamp
    private Timestamp regdate;
    @CreationTimestamp
    private Timestamp updatedate;

    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name = "MEMBER_SEQ")
    Member member;
    
    //카테고리 추가 고민
}
