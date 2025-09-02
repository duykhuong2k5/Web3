package vn.oistart.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import vn.oistart.service.UserService;
import vn.oistart.service.impl.UserServiceImpl;

@WebServlet("/forget-password")
public class ForgetPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/forget-password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");

        if (userService.existsByEmail(email)) {
            // Ở đây bạn có thể:
            // 1. Sinh mật khẩu tạm thời và update DB
            // 2. Hoặc gửi link reset qua email

            String tempPassword = "123456"; // TODO: sinh random
            userService.updatePasswordByEmail(email, tempPassword);

            request.setAttribute("message", "Mật khẩu mới đã được gửi đến email: " + email);
        } else {
            request.setAttribute("message", "Email không tồn tại trong hệ thống!");
        }
        request.getRequestDispatcher("/views/forget-password.jsp").forward(request, response);
    }
}

