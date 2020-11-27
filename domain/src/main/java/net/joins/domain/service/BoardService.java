package net.joins.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import net.joins.domain.dto.BoardInfo;
import net.joins.domain.entity.Board;
import net.joins.domain.entity.Member;
import net.joins.domain.repository.BoardRepository;
import net.joins.domain.repository.MemberRepository;
import net.joins.domain.mapper.BoardMapper;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

    final BoardRepository boardRepository;
    final MemberRepository memberRepository;

    public List<BoardInfo> getListAll(){
        //log.info("getAll {}", userId);

        List<BoardInfo> boardInfos = new ArrayList<BoardInfo>();

        List<Board> boards = boardRepository.findAll();

        if(boards != null)
            boardInfos = BoardMapper.INSTANCE.boardsToBoardInfos(boards);

        return boardInfos;
    }
}
