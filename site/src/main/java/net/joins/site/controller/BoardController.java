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
        log.info("vo : "+vo);
       // mvo.setMemberSeq(1L);
       //mvo.setMemberId("user01");
        log.info("mvo : "+mvo);
    }

    @PostMapping("/register")
    public String registerPOST(@ModelAttribute("vo") BoardInfo vo,
                               @ModelAttribute("mvo") MemberInfo mvo,
                               RedirectAttributes rttr){
        log.info("register post");
        log.info("vo : "+vo);
        mvo.setMemberSeq(1L);
        vo.setMemberInfo(mvo);
        log.info("mvo : "+mvo);

        //MemberInfo memeberInfo = new MemberInfo(); error

        boardService.saveContent(vo);
        rttr.addFlashAttribute("msg", "success");

        return "redirect:/boards/list";
    }

}
