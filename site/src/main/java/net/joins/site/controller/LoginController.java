package net.joins.site.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import net.joins.domain.dto.BoardInfo;
import net.joins.domain.dto.MemberInfo;
import net.joins.domain.service.BoardService;
import net.joins.domain.vo.PageMaker;
import net.joins.domain.vo.PageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Controller
@Log
public class LoginController {

    @GetMapping("/login")
    public void login(){

    }

    @GetMapping("/accessDenied")
    public void accessDenied(){

    }

    @GetMapping("/logout")
    public void logout(){

    }

}
