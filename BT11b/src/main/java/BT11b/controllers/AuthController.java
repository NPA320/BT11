package BT11b.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AuthController {

    // Hiển thị trang login
    @GetMapping("login")
    public String index() {
        return "login";   // trả về file login.html trong templates
    }

    // Hiển thị trang profile
    @GetMapping("user/profile")
    public String profile() {
        return "profile"; // trả về file profile.html trong templates
    }
}
