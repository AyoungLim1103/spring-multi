package net.joins.domain.mapper;

import net.joins.domain.dto.UserInfo;
import net.joins.domain.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    UserInfo memberToUserInfo(Member member);
}
