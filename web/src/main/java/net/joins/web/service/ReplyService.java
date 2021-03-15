package net.joins.web.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.joins.web.dto.BoardInfo;
import net.joins.web.dto.MemberInfo;
import net.joins.web.dto.ReplyInfo;
import net.joins.domain.entity.Board;
import net.joins.domain.entity.Member;
import net.joins.domain.entity.Reply;
import net.joins.web.dto.ReplyParam;
import net.joins.web.mapper.BoardMapper;
import net.joins.web.mapper.MemberMapper;
import net.joins.web.mapper.ReplyMapper;
import net.joins.domain.repository.BoardRepository;
import net.joins.domain.repository.MemberRepository;
import net.joins.domain.repository.ReplyRepository;
import net.joins.web.mapper.ReplyParamMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReplyService {
    final BoardRepository boardRepository;
    final MemberRepository memberRepository;
    final ReplyRepository replyRepository;

    BoardService boardService;

    public BoardInfo save(Long bno, ReplyParam replyParam, String memberId){
        //BoardInfo boardInfo = boardService.getContent(bno).get();
        Board board = boardRepository.findById(bno).get();
        BoardInfo boardInfo = BoardMapper.INSTANCE.boardToBoardInfo(board);

        boardInfo.setBno(bno);
        replyParam.setBoardInfo(boardInfo);

        Member member = memberRepository.findMemberByMemberId(memberId);
        MemberInfo memberInfo = MemberMapper.INSTANCE.memberToMemberInfo(member);

        memberInfo.setMemberId(memberId);
        replyParam.setMemberInfo(memberInfo);

        Reply reply = ReplyParamMapper.INSTANCE.replyParamToReply(replyParam);

        replyRepository.save(reply);

        return boardInfo;
    }

    public void remove(Long rno) {
        replyRepository.deleteById(rno);
    }

    public void modify(ReplyParam replyParam){
        replyRepository.findById(replyParam.getRno()).ifPresent(origin -> {origin.setReplyText(replyParam.getReplyText());
        replyRepository.save(origin);
        });
    }

    public List<ReplyInfo> getListByBoard(BoardInfo boardInfo){
        Board board = BoardMapper.INSTANCE.boardInfoToBoard(boardInfo);
        List<Reply> replies = replyRepository.getRepliesOfBoard(board);
        List<ReplyInfo> repliesInfo = new ArrayList<>();

        if(replies != null) {
            repliesInfo = ReplyMapper.INSTANCE.repliesToRepliesInfo(replies);
            log.info("댓글 리스트 불러오기 [getListByBoard]");

            for(int i = 0; i < replyRepository.countByBno(board); i++){
                String insertUserNm = replies.get(i).getMember().getName();
                String insertUserId = replies.get(i).getMember().getMemberId();
                repliesInfo.get(i).setInsertUserNm(insertUserNm);
                repliesInfo.get(i).setInsertUserId(insertUserId);
                repliesInfo.get(i).setBno(boardInfo.getBno());
            }
        }
        return repliesInfo;
    }
}

