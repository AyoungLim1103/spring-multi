package net.joins.site.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Log
@RequestMapping("/boards/")
public class BoardController {

    @GetMapping("/list")
    public void list(Model model){
        log.info("list() called...");
        model.addAttribute("greeting", "안녕하세요");
    }

}
