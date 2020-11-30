package net.joins.domain.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "TB_MEMBER_ROLES")
@EqualsAndHashCode(of = "fno")
@ToString
public class MemberRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FNO")
    private Long fno;

    @Column(name = "ROLE_NAME")
    private String roleName;

}
