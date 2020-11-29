package net.joins.site.controller;

import com.misolab.core.exception.BadRequestException;
import com.misolab.core.vo.ApiResponse;
import net.joins.domain.entity.User;
import net.joins.domain.mapper.UserMapper;
import net.joins.domain.repository.UserRepository;
import net.joins.domain.dto.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class IndexController {

    final UserRepository userRepository;

    @ResponseBody
    @GetMapping("/api")
    public ResponseEntity api(@RequestParam String userId, String name) {

        if (StringUtils.isEmpty(name)) {
            throw new BadRequestException("name is required parameter");
        }

        User user = new User();
        user.setUserId(userId);
        user.setName(StringUtils.isEmpty(name) ? userId : name);

        userRepository.save(user);

        List<UserInfo> list = new ArrayList<>(); //result.stream().map(UserInfo::new).collect(Collectors.toList());

        ApiResponse response = ApiResponse.of("list", list);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public String index(Model model, String msg) {
        User user = new User();
        user.setName(msg);

        UserInfo userInfo = UserMapper.INSTANCE.userToUserInfo(user);
        model.addAttribute("msg", userInfo);
        return "index";
    }
}
