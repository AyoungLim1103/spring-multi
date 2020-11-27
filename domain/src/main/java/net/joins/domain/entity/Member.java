package net.joins.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "TB_MEMBER")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    Long id;

    @Column(name = "USER_ID", unique = true, nullable = false)
    String userId;
    @Column(name = "NAME", length = 50)
    String name;

    @Column(name = "MOBILE", length = 50)
    String mobile;
    @Column(name = "EMAIL", length = 200)
    String email;
    @Column(name = "ADMIN_YN", length = 1)
    String adminYn;

}
