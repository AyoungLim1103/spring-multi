package net.joins.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "persistent_logins")
public class Persistent_logins {

    @Column(length = 64, nullable = false)
    private String username;
    @Id
    @Column(length = 64, nullable = false)
    private String series;
    @Column(length = 64, nullable = false)
    private String token;
    @CreationTimestamp
    private Timestamp last_used;

}
