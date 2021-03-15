package net.joins.site.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import net.joins.web.dto.BoardInfo;
import net.joins.web.dto.BoardParam;
import net.joins.web.dto.MemberInfo;
import net.joins.web.vo.PageMaker;
import net.joins.web.vo.PageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import net.joins.web.service.BoardService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

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

        //vo.setMemberInfo(mvo);

        log.info("vo : "+vo);
    }
/*
    @PostMapping("/register")
    public String registerPOST(@Valid @ModelAttribute("vo") BoardInfo vo,
                               BindingResult bindingResult,
                               @ModelAttribute("mvo") MemberInfo mvo,
                               RedirectAttributes rttr){

        System.out.println("error:"+bindingResult.hasErrors());

        if(bindingResult.hasErrors()){
            List<ObjectError> list =  bindingResult.getAllErrors();
            for(ObjectError e : list) {
                rttr.addFlashAttribute("msg", e.getDefaultMessage());
                System.out.println(e.getDefaultMessage());
            }
            return "redirect:/boards/register";
        }

        log.info("register post");
        vo.setMemberInfo(mvo);
        log.info("vo : "+vo);

        boardService.saveContent(vo);
        rttr.addFlashAttribute("msg", "success");

        return "redirect:/boards/list";
    }
*/

    @PostMapping("/register")
    public String registerPOST(@Valid @ModelAttribute("boardParam") BoardParam boardParam,
                               BindingResult bindingResult,
                               RedirectAttributes rttr){

        log.info("register post");

        System.out.println("error:"+bindingResult.hasErrors());

        if(bindingResult.hasErrors()){
            List<ObjectError> list =  bindingResult.getAllErrors();
            for(ObjectError e : list) {
                rttr.addFlashAttribute("msg", e.getDefaultMessage());
                System.out.println(e.getDefaultMessage());
            }
            return "redirect:/boards/register";
        }

        log.info("register post");
        boardService.saveContent(boardParam);
        log.info("boardParam : "+boardParam);
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

    @Secured(value={"ROLE_BASIC","ROLE_MANAGER","ROLE_ADMIN"})
    @GetMapping("/modify")
    public void modifyGET(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model){
        log.info("MODIFY BNO : "+bno);

        boardService.modifyContent(bno).ifPresent(board ->model.addAttribute("vo",board));
    }

    @Secured(value={"ROLE_BASIC","ROLE_MANAGER","ROLE_ADMIN"})
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

    @Secured(value={"ROLE_BASIC","ROLE_MANAGER","ROLE_ADMIN"})
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
