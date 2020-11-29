package net.joins.domain.mapper;

import net.joins.domain.dto.UserInfo;
import net.joins.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserInfo userToUserInfo(User user);

    User userInfoToUser(UserInfo userInfo);
}