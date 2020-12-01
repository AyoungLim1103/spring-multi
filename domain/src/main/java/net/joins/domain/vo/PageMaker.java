package net.joins.domain.vo;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;

@Getter
@ToString(exclude = "pageList")
public class PageMaker<T> {

    private Page<T> result;

}
