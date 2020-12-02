package net.joins.domain.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.joins.domain.entity.QBoard;
import net.joins.domain.vo.PageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import net.joins.domain.dto.BoardInfo;
import net.joins.domain.entity.Board;
import net.joins.domain.repository.BoardRepository;
import net.joins.domain.repository.MemberRepository;
import net.joins.domain.mapper.BoardMapper;

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
    /*public void getList(String type, String keyword){
        Pageable paging = PageRequest.of(1, 10); //new PageRequest(0, 10, Sort.Direction.DESC);

        Page<Board> results =boardRepository.findByBnoGreaterThanOrderByBnoDesc(0L, paging);

        System.out.println("PAGE SIZE: " + results.getSize());
        System.out.println("TOTAL PAGES: " + results.getTotalPages());
        System.out.println("TOTAL COUNT: " + results.getTotalElements());
        System.out.println("NEXT: " + results.nextPageable());

        List<Board> list = results.getContent();

        list.forEach(board -> System.out.println(board));
    }*/

    public BoardInfo getContent(Long bno) {
        Board board = boardRepository.findById(bno).get();

        return BoardMapper.INSTANCE.boardToBoardInfo(board);
    }
}
