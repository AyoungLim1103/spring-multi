package net.joins.domain.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@ToString
@Getter
@Setter
@Builder
public class UserInfo {
    Long userSeq;
    String userId;
    String name;

    String mobile;
    String email;

    Timestamp regdate;

}
