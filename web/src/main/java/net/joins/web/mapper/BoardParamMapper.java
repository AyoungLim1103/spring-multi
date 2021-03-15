package net.joins.web.mapper;

import net.joins.domain.entity.Board;
import net.joins.web.dto.BoardParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BoardParamMapper {
    BoardParamMapper INSTANCE = Mappers.getMapper(BoardParamMapper.class);

    @Mapping(target = "regdate", ignore = true)
    @Mapping(target = "updatedate", ignore = true)
    @Mapping(source = "boardParam.memberSeq", target = "member.memberSeq")
    Board boardParamToBoard(BoardParam boardParam);
}
