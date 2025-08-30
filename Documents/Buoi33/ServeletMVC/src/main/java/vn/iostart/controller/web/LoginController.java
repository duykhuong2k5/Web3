package vn.iostart.controller.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import vn.iostart.model.User;
import vn.iostart.service.UserService;
import vn.iostart.service.impl.UserServiceImpl;
import vn.iostart.util.Constant;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            // Nếu đã đăng nhập thì chuyển đến trang waiting
            resp.sendRedirect(req.getContextPath() + "/waiting");
            return;
        }

        // Check cookie để auto login
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (Constant.COOKIE_REMEMBER.equals(cookie.getName())) {
                    String username = cookie.getValue();
                    UserService service = new UserServiceImpl();
                    User user = service.login(username, ""); // login bằng username
                    if (user != null) {
                        session = req.getSession(true);
                        session.setAttribute("account", user);
                        resp.sendRedirect(req.getContextPath() + "/waiting");
                        return;
                    }
                }
            }
        }

        // Nếu chưa login thì show form login.jsp
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Đảm bảo UTF-8
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");

        boolean isRememberMe = "on".equals(remember);
        String alertMsg = "";

        // Validate dữ liệu rỗng
        if (username == null || username.isEmpty() ||
            password == null || password.isEmpty()) {

            alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            return;
        }

        // Gọi service để kiểm tra login
        UserService service = new UserServiceImpl();
        User user = service.login(username, password);

        if (user != null) {
            // Đăng nhập thành công -> lưu session
            HttpSession session = req.getSession(true);
            session.setAttribute("account", user);

            // Nếu user chọn "Ghi nhớ đăng nhập" thì lưu cookie
            if (isRememberMe) {
                saveRememberMe(resp, username);
            }

            // Chuyển tới WaitingController để định hướng role
            resp.sendRedirect(req.getContextPath() + "/waiting");
        } else {
            // Sai tài khoản hoặc mật khẩu
            alertMsg = "Tài khoản hoặc mật khẩu không đúng";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }

    /** Lưu cookie remember me */
    private void saveRememberMe(HttpServletResponse response, String username) {
        Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
        cookie.setMaxAge(30 * 24 * 60 * 60); // 30 ngày
        cookie.setPath("/"); // áp dụng toàn bộ context path
        response.addCookie(cookie);
    }
}
