package net.joins.domain.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import net.joins.domain.dto.MemberInfo;
import net.joins.domain.entity.Member;

@Mapper
public interface MemberMapper {

//    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    MemberInfo toDto(Member member);

    @Mapping(target = "id", constant = "OL")
    @Mapping(target = "adminYn", constant = "N")
    Member toEntity(MemberInfo memberInfo);

   /* @Select("select CURRENT_TIMESTAMP()")
    String getDate();*/
}
