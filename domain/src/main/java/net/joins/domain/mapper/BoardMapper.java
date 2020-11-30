package net.joins.domain.mapper;

import net.joins.domain.dto.BoardInfo;
import net.joins.domain.entity.Board;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {MemberMapper.class})
public interface BoardMapper {
    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

    @Mapping(source = "board.member", target = "memberInfo")
    BoardInfo boardToBoardInfo(Board board);

    @Mapping(source = "boardInfo.memberInfo", target = "member")
    Board boardInfoToBoard(BoardInfo boardInfo);

    @Mapping(source = "boards.member", target = "memberInfo")
    List<BoardInfo> boardsToBoardInfos(List<Board> boards);
    // 이거 여쭤보기
}
