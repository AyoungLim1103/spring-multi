package net.joins.site.controller;

import lombok.extern.java.Log;
import net.joins.web.dto.ReplyInfo;
import net.joins.web.service.BoardService;
import net.joins.web.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController /*Json 형태로 객체 데이터를 반환 @Controller + @ResponseBody*/
@RequestMapping("/replies/*")
@Log
public class ReplyController {

    @Autowired
    private ReplyService replyService;
    @Autowired
    private BoardService boardService;

    @Transactional
    @PostMapping("/{bno}")
    public ResponseEntity<List<ReplyInfo>> addReply(
            @PathVariable("bno") Long bno,
            @RequestBody ReplyInfo replyInfo, Authentication authentication){

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String memberId = userDetails.getUsername();

        log.info("addReply...");
        log.info("BNO: "+bno);
        log.info("REPLY: ");

        replyService.save(bno,replyInfo, memberId);

        return new ResponseEntity<>(replyService.getListByBoard(boardService.getContent(bno).get()),HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping("/{bno}/{rno}")
    public ResponseEntity<List<ReplyInfo>> remove(
            @PathVariable("bno") Long bno,
            @PathVariable("rno") Long rno){
        log.info("delete reply: "+rno);

        replyService.remove(rno);

        return new ResponseEntity<>(replyService.getListByBoard(boardService.getContent(bno).get()), HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/{bno}")
    public ResponseEntity<List<ReplyInfo>> modify(
            @PathVariable("bno") Long bno,
            @RequestBody ReplyInfo replyInfo){
        log.info("modify reply: "+replyInfo);

        replyService.modify(replyInfo);

        return new ResponseEntity<>(replyService.getListByBoard(boardService.getContent(bno).get()), HttpStatus.CREATED);
    }

    @GetMapping("/{bno}")
    public ResponseEntity<List<ReplyInfo>> getReplies(
            @PathVariable("bno") Long bno){
        log.info("get All Replies....... ");

        log.info(replyService.getListByBoard(boardService.getContent(bno).get()).toString());

        return new ResponseEntity<>(replyService.getListByBoard(boardService.getContent(bno).get()), HttpStatus.OK);
    }
}
