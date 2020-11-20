package net.joins.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.misolab.core.exception.BadRequestException;
import com.misolab.core.vo.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping
    public ResponseEntity index() {
        ApiResponse response = ApiResponse.error(new BadRequestException("no userInfo"));
        return ResponseEntity.badRequest().body(response);
    }

    @RequestMapping(value = "/login/account", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity login() {
        Map<String, Object> data = new HashMap<>();
        data.put("message", "this is Login");
        data.put("token", "token-from-api");
        data.put("code", 200);

        ApiResponse response = ApiResponse.of(data);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/currentUser", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity getInfo() {
        Map<String, Object> data = new HashMap<>();
        data.put("name", "Joins");
        data.put("avatar", "http://www.joins.net/img/common/logo.gif");

        List<Map<String, String>> permissions = new ArrayList<>();

        // front의 router.confing.js에 필요한 permission을 추가한다
        permissions.add(makePermission("dashboard"));
        permissions.add(makePermission("form"));

        // permission이 따로 필요하지 않을 경우 any로
        permissions.add(makePermission("any"));

        Map<String, Object> role = new HashMap<>();
        role.put("permissions", permissions);
        data.put("role", role);

        ApiResponse response = ApiResponse.of(data);
        return ResponseEntity.ok(response);
    }

    private Map<String, String> makePermission(String value) {
        Map<String, String> permission = new HashMap<>();
        permission.put("permissionId", value);
        return permission;
    }
}
