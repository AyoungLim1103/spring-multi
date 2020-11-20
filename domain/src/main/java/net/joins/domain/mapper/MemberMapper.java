package net.joins.domain.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface MemberMapper {

    @Select("select CURRENT_TIMESTAMP()")
    String getDate();
}
