package net.joins.api.controller;

import lombok.RequiredArgsConstructor;
import net.joins.domain.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class IndexController {

    @GetMapping
    public ResponseEntity index(String msg) {
        User user = new User();
        user.setName(msg);
        user.setUserId(msg);

        return ResponseEntity.ok(user);
    }
}
