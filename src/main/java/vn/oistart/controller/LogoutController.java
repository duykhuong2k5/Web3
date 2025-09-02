package vn.oistart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate(); // Xóa session
        }

        // Xóa cookie "username"
        Cookie cookie = new Cookie("username", "");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);

        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
