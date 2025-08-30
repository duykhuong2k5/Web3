package vn.iostart.controller.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iostart.model.User;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/waiting")
public class WaitingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false); // không tạo mới
        if (session != null && session.getAttribute("account") != null) {

            User u = (User) session.getAttribute("account");
            req.setAttribute("username", u.getUserName());

            String context = req.getContextPath();
            switch (u.getRoleid()) {
                case 1:
                    resp.sendRedirect(context + "/admin/home");   // admin
                    break;
                case 2:
                    resp.sendRedirect(context + "/manager/home"); // manager
                    break;
                default:
                    resp.sendRedirect(context + "/web/home");        // user bình thường
                    break;
            }

        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
