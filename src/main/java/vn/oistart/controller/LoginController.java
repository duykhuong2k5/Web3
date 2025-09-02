package vn.oistart.controller;

import vn.oistart.model.User;
import vn.oistart.service.UserService;
import vn.oistart.service.impl.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {

    public static final String COOKIE_REMEMBER = "username";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            resp.sendRedirect(req.getContextPath() + "/category"); // 👉 chuyển về category
            return;
        }

        // Kiểm tra cookie (Remember Me)
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (COOKIE_REMEMBER.equals(cookie.getName())) {
                    String username = cookie.getValue();
                    if (username != null && !username.isEmpty()) {
                        UserService service = new UserServiceImpl();
                        User user = service.get(username);
                        if (user != null) {
                            session = req.getSession(true);
                            session.setAttribute("account", user);
                            resp.sendRedirect(req.getContextPath() + "/category"); // 👉 category
                            return;
                        }
                    }
                }
            }
        }

        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");

        String alertMsg = "";

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            alertMsg = "Tài khoản hoặc mật khẩu không được rỗng!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            return;
        }

        UserService service = new UserServiceImpl();
        User user = service.login(username, password);

        if (user != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("account", user);

            // Nếu chọn Remember Me → lưu cookie 30 phút
            if ("on".equals(remember)) {
                Cookie cookie = new Cookie(COOKIE_REMEMBER, username);
                cookie.setMaxAge(30 * 60); // 30 phút
                resp.addCookie(cookie);
            }

            // 👉 sau khi login thì vào list category
            resp.sendRedirect(req.getContextPath() + "/category");
        } else {
            alertMsg = "Tài khoản hoặc mật khẩu không đúng!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }
}
