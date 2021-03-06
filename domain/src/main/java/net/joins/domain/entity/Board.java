package net.joins.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Getter
@Setter
@Entity
@org.hibernate.annotations.DynamicUpdate
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
    @Column(updatable = false)
    private Timestamp regdate;
    @UpdateTimestamp
    private Timestamp updatedate;

    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name = "MEMBER_SEQ")
    Member member;

    @OneToMany(mappedBy = "board", fetch=FetchType.LAZY)
    List<Reply> replies;
    
    //카테고리 추가 고민
}
