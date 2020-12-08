package net.joins.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "TB_MEMBER")
@EqualsAndHashCode(of = "memberId")
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_SEQ")
    Long memberSeq;

    @Column(name = "MEMBER_ID", unique = true, nullable = false)
    String memberId;
    @Column(name = "MEMBER_PW", nullable = false)
    String memberPw;

    @Column(name = "NAME", length = 50)
    String name;

    @Column(name = "MOBILE", length = 50)
    String mobile;
    @Column(name = "EMAIL", length = 200)
    String email;

    @CreationTimestamp
    Timestamp regdate;
    @CreationTimestamp
    Timestamp updatedate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "MEMBER")
    List<MemberRole> roles;

}
