package com.aptech.myapp.dtos.requests;

public class LoginRequest {
    private String email;
    private String password;

    // Constructor
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getter và Setter (nếu cần)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Phương thức validate thông tin đầu vào
    public boolean isValid() {
        return isPhoneValid() && isPasswordValid();
    }

    public boolean isPhoneValid() {
        // Kiểm tra số điện thoại: không null, không rỗng, và có độ dài từ 10-11 ký tự
        return email != null && !email.isEmpty() && email.matches("^\\d{10,11}$");
    }

    public boolean isPasswordValid() {
        // Kiểm tra mật khẩu: ít nhất 8 ký tự, có chữ hoa, chữ thường và số
        return password != null && password.length() >= 8
                && password.matches(".*[A-Z].*")  // Có ít nhất 1 chữ hoa
                && password.matches(".*[a-z].*")  // Có ít nhất 1 chữ thường
                && password.matches(".*\\d.*");   // Có ít nhất 1 chữ số
    }
}

