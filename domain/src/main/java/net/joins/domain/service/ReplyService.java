package net.joins.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.joins.domain.dto.BoardInfo;
import net.joins.domain.dto.ReplyInfo;
import net.joins.domain.entity.Board;
import net.joins.domain.entity.Reply;
import net.joins.domain.mapper.BoardMapper;
import net.joins.domain.mapper.ReplyMapper;
import net.joins.domain.repository.BoardRepository;
import net.joins.domain.repository.MemberRepository;
import net.joins.domain.repository.ReplyRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReplyService {
    final BoardRepository boardRepository;
    final MemberRepository memberRepository;
    final ReplyRepository replyRepository;

    BoardService boardService;

    public BoardInfo save(Long bno, ReplyInfo replyInfo){
        //BoardInfo boardInfo = boardService.getContent(bno).get();
        Board board = boardRepository.findById(bno).get();
        BoardInfo boardInfo = BoardMapper.INSTANCE.boardToBoardInfo(board);

        boardInfo.setBno(bno);
        replyInfo.setBoardInfo(boardInfo);

        Reply reply = ReplyMapper.INSTANCE.replyInfoToReply(replyInfo);

        replyRepository.save(reply);

        return boardInfo;
    }

    public void remove(Long rno) {
        replyRepository.deleteById(rno);
    }

    public void modify(ReplyInfo replyInfo){
        replyRepository.findById(replyInfo.getRno()).ifPresent(origin -> {origin.setReplyText(replyInfo.getReplyText());
        replyRepository.save(origin);
        });
    }

    public List<ReplyInfo> getListByBoard(BoardInfo boardInfo){
        Board board = BoardMapper.INSTANCE.boardInfoToBoard(boardInfo);
        List<Reply> replies = replyRepository.getRepliesOfBoard(board);
        List<ReplyInfo> repliesInfo = new ArrayList<>();

        if(replies != null)
            repliesInfo = ReplyMapper.INSTANCE.repliesInfoToReplies(replies);

        return repliesInfo;
    }


}

