package net.joins.domain.mapper;

import net.joins.domain.dto.BoardInfo;
import net.joins.domain.entity.Board;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {MemberMapper.class, ReplyMapper.class})
public interface BoardMapper {
    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

    @Mapping(source = "board.member", target = "memberInfo")
    @Mapping(source = "board.replies", target = "repliesInfo")
    BoardInfo boardToBoardInfo(Board board);

    @Mapping(source = "boardInfo.memberInfo", target = "member")
    @Mapping(source = "boardInfo.repliesInfo", target = "replies")
    Board boardInfoToBoard(BoardInfo boardInfo);

    @Mapping(source = "boards.member", target = "memberInfo")
    @Mapping(source = "boards.replies", target = "repliesInfo")
    List<BoardInfo> boardsToBoardInfos(List<Board> boards);

}
