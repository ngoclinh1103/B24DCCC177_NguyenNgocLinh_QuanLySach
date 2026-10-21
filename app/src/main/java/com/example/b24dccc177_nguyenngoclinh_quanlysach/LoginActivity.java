package com.example.b24dccc177_nguyenngoclinh_quanlysach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    TextView tvError;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Ánh xạ các thành phần từ giao diện XML sang Java
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        tvError = findViewById(R.id.tvError);
        btnLogin = findViewById(R.id.btnLogin);

        // Bắt sự kiện khi click nút Đăng nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUsername.getText().toString().trim();
                String pass = edtPassword.getText().toString().trim();

                // Kiểm tra xem có để trống không
                if (user.isEmpty() || pass.isEmpty()) {
                    tvError.setText("Không được để trống thông tin!");
                    tvError.setVisibility(View.VISIBLE);
                }
                // Kiểm tra đúng tài khoản/mật khẩu
                else if (user.equals("admin") && pass.equals("123456")) {
                    tvError.setVisibility(View.GONE);
                    // Chuyển sang màn hình MainActivity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Kết thúc màn hình này để không thể ấn nút Back quay lại Login
                }
                // Xử lý khi sai thông tin
                else {
                    tvError.setText("Sai tên đăng nhập hoặc mật khẩu");
                    tvError.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}