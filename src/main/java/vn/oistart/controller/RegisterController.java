package vn.oistart.controller;

import vn.oistart.service.UserService;
import vn.oistart.service.impl.UserServiceImpl;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String REGISTER_PAGE = "/views/register.jsp";
    private UserService service;

    @Override
    public void init() throws ServletException {
        service = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        // Nếu đã đăng nhập thì redirect về /admin
        if (session != null && session.getAttribute("username") != null) {
            resp.sendRedirect(req.getContextPath() + "/admin");
            return;
        }

        // Kiểm tra cookie "username"
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    session = req.getSession(true);
                    session.setAttribute("username", cookie.getValue());
                    resp.sendRedirect(req.getContextPath() + "/admin");
                    return;
                }
            }
        }

        // Nếu chưa đăng nhập thì hiển thị form đăng ký
        req.getRequestDispatcher(REGISTER_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");

        String alertMsg = "";

        // Kiểm tra email tồn tại
        if (service.checkExistEmail(email)) {
            alertMsg = "Email đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(REGISTER_PAGE).forward(req, resp);
            return;
        }

        // Kiểm tra username tồn tại
        if (service.checkExistUsername(username)) {
            alertMsg = "Tài khoản đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(REGISTER_PAGE).forward(req, resp);
            return;
        }

        // Kiểm tra số điện thoại tồn tại
        if (service.checkExistPhone(phone)) {
            alertMsg = "Số điện thoại đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(REGISTER_PAGE).forward(req, resp);
            return;
        }

        // Thực hiện đăng ký
        boolean isSuccess = service.register(username, password, email, fullname, phone);

        if (isSuccess) {
            // Đăng ký thành công → chuyển sang login
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            // Lỗi hệ thống
            alertMsg = "Lỗi hệ thống, vui lòng thử lại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(REGISTER_PAGE).forward(req, resp);
        }
    }
}
