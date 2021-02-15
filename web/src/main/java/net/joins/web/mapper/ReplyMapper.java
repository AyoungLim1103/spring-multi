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

    @Mapping(source = "reply.board", target = "boardInfo")
    @Mapping(source = "reply.member", target = "memberInfo")
    ReplyInfo replyToReplyInfo(Reply reply);

    @Mapping(source = "replyInfo.boardInfo", target = "board")
    @Mapping(source = "replyInfo.memberInfo", target = "member")
    Reply replyInfoToReply(ReplyInfo replyInfo);

    @Mapping(source = "replies.board", target = "boardInfo")
    @Mapping(source = "replies.member", target = "memberInfo")
    List<ReplyInfo> repliesInfoToReplies(List<Reply> replies);
}
