package vn.iot.baitap05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("message", "Chào mừng Admin đến Dashboard!");
        return "home"; // có thể thay bằng "admin-dashboard.jsp" nếu muốn riêng
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("message", "Quản lý người dùng");
        return "home";
    }

    @GetMapping("/reports")
    public String reports(Model model) {
        model.addAttribute("message", "Trang báo cáo");
        return "home";
    }
}
