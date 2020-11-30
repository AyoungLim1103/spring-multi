package net.joins.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "TB_MEMBER")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_SEQ")
    Long memberSeq;

    @Column(name = "MEMBER_ID", unique = true, nullable = false)
    String memberId;
    @Column(name = "NAME", length = 50)
    String name;

    @Column(name = "MOBILE", length = 50)
    String mobile;
    @Column(name = "EMAIL", length = 200)
    String email;
    @Column(name = "ADMIN_YN", length = 1)
    String adminYn;
    //admin role 도 고려필요
    @CreationTimestamp
    Timestamp regdate;
    @CreationTimestamp
    Timestamp updatedate;

    @OneToMany
    @JoinColumn(name = "MEMBER_SEQ")
    List<MemberRole> roles;

}
