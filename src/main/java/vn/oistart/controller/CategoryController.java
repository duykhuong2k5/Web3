package vn.oistart.controller;

import vn.oistart.model.Category;
import vn.oistart.model.User;
import vn.oistart.service.CategoryService;
import vn.oistart.service.impl.CategoryServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/category", "/category/add", "/category/edit", "/category/delete"})
public class CategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        String action = url.substring(req.getContextPath().length());

        // Lấy user từ session
        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("account") : null;

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        int userId = user.getId();

        switch (action) {
            case "/category":
                List<Category> categories = categoryService.getAllByUser(userId);
                req.setAttribute("list", categories);
                req.getRequestDispatcher("/views/category-list.jsp").forward(req, resp);
                break;

            case "/category/add":
                req.getRequestDispatcher("/views/category-form.jsp").forward(req, resp);
                break;

            case "/category/edit":
                try {
                    int cateId = Integer.parseInt(req.getParameter("id"));
                    Category category = categoryService.get(cateId);
                    
                    // Kiểm tra quyền sở hữu
                    if (category != null && category.getUserId() == userId) {
                        req.setAttribute("category", category);
                        req.getRequestDispatcher("/views/category-form.jsp").forward(req, resp);
                    } else {
                        resp.sendRedirect(req.getContextPath() + "/category");
                    }
                } catch (NumberFormatException e) {
                    resp.sendRedirect(req.getContextPath() + "/category");
                }
                break;

            case "/category/delete":
                try {
                    int deleteId = Integer.parseInt(req.getParameter("id"));
                    Category deleteCategory = categoryService.get(deleteId);
                    
                    // Kiểm tra quyền sở hữu trước khi xóa
                    if (deleteCategory != null && deleteCategory.getUserId() == userId) {
                        categoryService.delete(deleteId);
                    }
                } catch (NumberFormatException e) {
                    // Ignore invalid ID
                }
                resp.sendRedirect(req.getContextPath() + "/category");
                break;

            default:
                resp.sendRedirect(req.getContextPath() + "/category");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        // Lấy user từ session
        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("account") : null;

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        int userId = user.getId();

        String idStr = req.getParameter("id");
        String name = req.getParameter("name");
        String icon = req.getParameter("icon");

        // Xử lý đường dẫn ảnh
        if (icon != null && !icon.trim().isEmpty()) {
            icon = icon.trim();
            if (!icon.startsWith("images/")) {
                icon = "images/" + icon;
            }
            // Đảm bảo có extension
            if (!icon.contains(".")) {
                icon += ".png";
            }
        } else {
            icon = "images/default.png";
        }

        Category category = new Category();
        category.setCatename(name);
        category.setIcon(icon);
        category.setUserId(userId);

        if (idStr == null || idStr.isEmpty()) {
            // Thêm mới category
            categoryService.insert(category);
        } else {
            try {
                // Cập nhật category - kiểm tra quyền sở hữu
                int categoryId = Integer.parseInt(idStr);
                Category existingCategory = categoryService.get(categoryId);
                
                if (existingCategory != null && existingCategory.getUserId() == userId) {
                    category.setCateid(categoryId);
                    categoryService.edit(category);
                }
            } catch (NumberFormatException e) {
                // Invalid ID, treat as new category
                categoryService.insert(category);
            }
        }

        resp.sendRedirect(req.getContextPath() + "/category");
    }
}