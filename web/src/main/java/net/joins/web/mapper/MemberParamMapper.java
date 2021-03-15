package net.joins.web.mapper;

import net.joins.web.dto.MemberParam;
import net.joins.domain.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberParamMapper {
    MemberParamMapper INSTANCE = Mappers.getMapper(MemberParamMapper.class);

    @Mapping(target = "regdate", ignore = true)
    @Mapping(target = "updatedate", ignore = true)
    Member memberParamToMember(MemberParam memberParam);
}
