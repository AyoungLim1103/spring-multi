package net.joins.site.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import net.joins.domain.dto.BoardInfo;
import net.joins.domain.dto.MemberInfo;
import net.joins.domain.vo.PageMaker;
import net.joins.domain.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import net.joins.domain.service.BoardService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@Log
@RequestMapping("/boards/")
public class BoardController {

    final BoardService boardService;

    @GetMapping("/list")
    public void list(PageVO vo, Model model){
        Pageable page = vo.makePageable(0, "bno");

        Page<BoardInfo> result = boardService.getList(page,vo);

        log.info("Page 는? " + page);
        log.info("Result 는? " + result);

        log.info("TOTAL PAGE NUMBER: " + result.getTotalPages());
        log.info(result.toString());

        model.addAttribute("result", new PageMaker(result));
    }

    @GetMapping("/register")
    public void registerGET(@ModelAttribute("vo") BoardInfo vo,
                            @ModelAttribute("mvo") MemberInfo mvo){
        log.info("register get");

        //추후 로그인한 Member의 정보로 넣기
        mvo.setMemberSeq(1L);
        mvo.setName("user01");
        vo.setMemberInfo(mvo);

        log.info("vo : "+vo);
    }

    @PostMapping("/register")
    public String registerPOST(@ModelAttribute("vo") BoardInfo vo,
                               @ModelAttribute("mvo") MemberInfo mvo,
                               RedirectAttributes rttr){
        log.info("register post");
        //추후 로그인한 Member의 정보로 넣기
        mvo.setMemberSeq(1L);
        vo.setMemberInfo(mvo);
        log.info("vo : "+vo);

        boardService.saveContent(vo);
        rttr.addFlashAttribute("msg", "success");

        return "redirect:/boards/list";
    }

    @GetMapping("/view")
    public void viewGET(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model){
        log.info("BNO : "+bno);

        //Optional<T> 복잡한 조건문 없이도 널(null) 값으로 인해 발생하는 예외를 처리
        //Optional 객체가 감싸고 있는 값이 존재할 경우에만, 실행될 로직을 함수형 인자로 넘김
        boardService.getContent(bno).ifPresent(board ->model.addAttribute("vo",board));
    }

    @GetMapping("/modify")
    public void modifyGET(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model){
        log.info("MODIFY BNO : "+bno);

        boardService.modifyContent(bno).ifPresent(board ->model.addAttribute("vo",board));
    }

    @PostMapping("/modify")
    public String modifyPOST(BoardInfo boardInfo, PageVO vo, RedirectAttributes rttr){
        log.info("MODIFY BoardInfo : "+boardInfo);

        boardService.modifyContent(boardInfo.getBno()).ifPresent(origin -> {
            origin.setTitle(boardInfo.getTitle());
            origin.setContent(boardInfo.getContent());
            origin.setUpdatedate(new Timestamp(System.currentTimeMillis())); //수정한 시간

            boardService.saveContent(origin);
            rttr.addFlashAttribute("msg","success");
            rttr.addAttribute("bno",origin.getBno());
        });

        rttr.addAttribute("page",vo.getPage());
        rttr.addAttribute("size",vo.getSize());
        rttr.addAttribute("type",vo.getType());
        rttr.addAttribute("keyword",vo.getKeyword());

        return "redirect:/boards/list";
    }

    @PostMapping("/delete")
    public String delete(Long bno, @ModelAttribute("pageVO") PageVO vo, RedirectAttributes rttr){
        log.info("MODIFY BNO : "+bno);

        boardService.deleteContent(bno);

        rttr.addFlashAttribute("msg","success");

        //list로 이동
        rttr.addAttribute("page",vo.getPage());
        rttr.addAttribute("size",vo.getSize());
        rttr.addAttribute("type",vo.getType());
        rttr.addAttribute("keyword",vo.getKeyword());

        return "redirect:/boards/list";
    }

}
