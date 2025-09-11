package vn.iot.baitap05.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm() {
        return "login"; // /WEB-INF/views/login.jsp
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          HttpSession session,
                          Model model) {
        // ✅ Kiểm tra giả lập: admin/admin → vào trang admin, user/user → vào home
        if ("admin".equals(username) && "admin".equals(password)) {
            session.setAttribute("role", "ADMIN");
            session.setAttribute("username", username);
            return "redirect:/admin/dashboard";
        } else if ("user".equals(username) && "user".equals(password)) {
            session.setAttribute("role", "USER");
            session.setAttribute("username", username);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Sai username hoặc password!");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
