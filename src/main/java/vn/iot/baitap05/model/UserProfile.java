package vn.iot.baitap05.model;

public class UserProfile {
    private String fullname;
    private String phone;
    private String imagePath; // tên file ảnh đã upload

    public UserProfile() {}

    public UserProfile(String fullname, String phone, String imagePath) {
        this.fullname = fullname;
        this.phone = phone;
        this.imagePath = imagePath;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
