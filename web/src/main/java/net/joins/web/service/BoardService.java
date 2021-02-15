package net.joins.web.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.joins.domain.entity.QBoard;
import net.joins.web.vo.PageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import net.joins.web.dto.BoardInfo;
import net.joins.domain.entity.Board;
import net.joins.domain.repository.BoardRepository;
import net.joins.domain.repository.MemberRepository;
import net.joins.web.mapper.BoardMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Page<BoardInfo> getList(Pageable page, PageVO vo){
        Page<Board> result = boardRepository.findAll(makePredicate(vo.getType(), vo.getKeyword()), page);

        return result.map(board -> BoardMapper.INSTANCE.boardToBoardInfo(board));
    }

    public Predicate makePredicate(String type, String keyword){
        BooleanBuilder builder = new BooleanBuilder();
        QBoard board = new QBoard("board");

        //bno>0
        builder.and(board.bno.gt(0));

        if(type == null){
            return builder;
        }

        switch (type){
            case "t":
                builder.and(board.title.like("%" + keyword + "%"));
                break;
            case "c":
                builder.and(board.content.like("%" + keyword + "%"));
                break;
            case "w":
                builder.and(board.member.name.like("%" + keyword + "%"));
                break;
        }
        return builder;
    }

    public Optional<BoardInfo> getContent(Long bno) {
        Optional<Board> board = boardRepository.findById(bno);

        return Optional.ofNullable(BoardMapper.INSTANCE.boardToBoardInfo(board.get()));
        //Controller 에서 값 받아서 ifPresent(board -> model.addAttribute.....)  사용 필요
    }

    public void saveContent(BoardInfo vo) {
        Board board = BoardMapper.INSTANCE.boardInfoToBoard(vo);

        boardRepository.save(board);
    }

    public Optional<BoardInfo> modifyContent(Long bno) {
        Optional<Board> board = boardRepository.findById(bno);

        return Optional.ofNullable(BoardMapper.INSTANCE.boardToBoardInfo(board.get()));
    }

    public void deleteContent(Long bno) {
        boardRepository.deleteById(bno);
    }
}
