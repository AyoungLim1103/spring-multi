package net.joins.web.mapper;

import net.joins.web.dto.ReplyInfo;
import net.joins.domain.entity.Reply;
import net.joins.web.dto.ReplyParam;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {BoardMapper.class, MemberMapper.class})
public interface ReplyParamMapper {
    ReplyParamMapper INSTANCE = Mappers.getMapper(ReplyParamMapper.class);

    @Mapping(source = "replyParam.boardInfo", target = "board")
    @Mapping(source = "replyParam.memberInfo", target = "member")
    Reply replyParamToReply(ReplyParam replyParam);
}
