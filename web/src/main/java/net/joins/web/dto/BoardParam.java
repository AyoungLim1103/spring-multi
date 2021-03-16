package net.joins.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BoardParam {

    private Long bno;

    @NotBlank(message = "제목을 입력해주세요")
    private String title;

    @NotBlank(message = "내용을 입력해주세요")
    private String content;

    private String delYN;

    Long memberSeq;

}
