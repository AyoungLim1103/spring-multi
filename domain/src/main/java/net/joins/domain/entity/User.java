package net.joins.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "TB_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_SEQ")
    Long userSeq;

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
    @CreationTimestamp
    Timestamp regdate;

}