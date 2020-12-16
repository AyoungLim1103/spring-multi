package net.joins.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "TB_BOARD_REPLY")
@EqualsAndHashCode(of = "rno")
@ToString(exclude = "board")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RNO")
    Long rno;

    @Column(name = "REPLY_TEXT", length = 1000)
    String replyText;

    @CreationTimestamp
    Timestamp regdate;
    @UpdateTimestamp
    Timestamp updatedate;

    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name = "MEMBER_SEQ")
    Member member;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    Board board;

}
