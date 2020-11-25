package net.joins.domain.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "TB_MEMBER")
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    Long id;

    @Column(name = "USER_ID", length = 50, unique = true, nullable = false)
    String userId;

    @Column(name = "PASSWORD")
    String password;

    @Column(name = "NAME", length = 50)
    String name;

    @Column(name = "PHONE", length = 50)
    String phone;

    @Column(name = "EMAIL", length = 100)
    String email;

    @Column(name = "ADMIN_YN", length = 1)
    String adminYN;

    @CreationTimestamp
    LocalDateTime regdate;
}
