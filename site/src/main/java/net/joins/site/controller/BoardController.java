package net.joins.site.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import net.joins.domain.dto.BoardInfo;
import net.joins.domain.vo.PageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import net.joins.domain.service.BoardService;

import java.util.ArrayList;
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

        Page<BoardInfo> result = boardService.getList(page);

        log.info("Page 는? " + page);
        log.info("Result 는? " + result);

        model.addAttribute("result", result);
    }

    @GetMapping("/write")
    public void write(Model model){
        log.info("write() called...");
        model.addAttribute("write", "글작성");
    }

}
