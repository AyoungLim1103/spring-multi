package net.joins.web.mapper;

import net.joins.web.dto.ReplyInfo;
import net.joins.domain.entity.Reply;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {BoardMapper.class, MemberMapper.class})
public interface ReplyMapper {
    ReplyMapper INSTANCE = Mappers.getMapper(ReplyMapper.class);

    @Mapping(target = "bno", ignore = true)
    @Mapping(target = "insertUserNm", ignore = true)
    @Mapping(target = "insertUserId", ignore = true)
    ReplyInfo replyToReplyInfo(Reply reply);

    //Reply replyInfoToReply(ReplyInfo replyInfo);

    @Mapping(target = "bno", ignore = true)
    @Mapping(target = "insertUserNm", ignore = true)
    @Mapping(target = "insertUserId", ignore = true)
    List<ReplyInfo> repliesToRepliesInfo(List<Reply> replies);
}
