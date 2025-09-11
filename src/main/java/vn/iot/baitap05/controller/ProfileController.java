package vn.iot.baitap05.controller;

import jakarta.servlet.ServletContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.iot.baitap05.model.UserProfile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final ServletContext servletContext;
    private UserProfile currentProfile = new UserProfile("Nguyen Van A", "0123456789", null);

    public ProfileController(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @GetMapping
    public String showProfile(Model model) {
        model.addAttribute("userProfile", currentProfile);
        return "profile"; // /WEB-INF/views/profile.jsp
    }

    @PostMapping("/update")
    public String updateProfile(@ModelAttribute("userProfile") UserProfile userProfile,
                                @RequestParam("image") MultipartFile image,
                                Model model) {
        try {
            if (!image.isEmpty()) {
                // Lưu file upload vào thư mục /uploads trong webapp
                String uploadDir = servletContext.getRealPath("/uploads");
                if (!Files.exists(Paths.get(uploadDir))) {
                    Files.createDirectories(Paths.get(uploadDir));
                }

                String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                File dest = new File(uploadDir, fileName);
                image.transferTo(dest);

                userProfile.setImagePath(fileName);
            } else {
                // Giữ ảnh cũ nếu không upload mới
                userProfile.setImagePath(currentProfile.getImagePath());
            }

            // Cập nhật profile hiện tại (lưu tạm trong bộ nhớ, có thể thay bằng DB)
            currentProfile = userProfile;

            model.addAttribute("userProfile", currentProfile);
            model.addAttribute("message", "Cập nhật thành công!");
        } catch (IOException e) {
            model.addAttribute("error", "Lỗi upload file: " + e.getMessage());
        }

        return "profile";
    }
}
