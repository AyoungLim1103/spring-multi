package net.joins.domain.mapper;

import net.joins.domain.dto.BoardInfo;
import net.joins.domain.dto.MemberInfo;
import net.joins.domain.entity.Board;
import net.joins.domain.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BoardMapper {
    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

    BoardInfo boardToBoardInfo(Board board);
    Board boardInfoToBoard(BoardInfo boardInfo);

    @Mapping(source = "member", target = "memberInfo")
    List<BoardInfo> boardsToBoardInfos(List<Board> boards);


}
