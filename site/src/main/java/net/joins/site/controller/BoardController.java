package net.joins.site.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import net.joins.domain.dto.BoardInfo;
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
    public void list(Model model){
        log.info("list() called...");
        List<BoardInfo> listBoardInfo = new ArrayList<>();

        listBoardInfo = boardService.getListAll();
        log.info(listBoardInfo.toString());
        model.addAttribute("listBoardInfo", listBoardInfo);
    }

    @GetMapping("/write")
    public void write(Model model){
        log.info("write() called...");
        model.addAttribute("write", "글작성");
    }

}
