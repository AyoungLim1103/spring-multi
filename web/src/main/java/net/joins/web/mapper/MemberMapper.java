package net.joins.web.mapper;

import net.joins.web.dto.MemberInfo;
import net.joins.domain.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    MemberInfo memberToMemberInfo(Member member);

    Member memberInfoToMember(MemberInfo memberInfo);
}