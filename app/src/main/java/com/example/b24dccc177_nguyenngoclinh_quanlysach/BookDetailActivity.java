package com.example.b24dccc177_nguyenngoclinh_quanlysach;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class BookDetailActivity extends AppCompatActivity {

    TextView tvDetailName, tvDetailAuthorCategory, tvDetailStatus, tvDetailNote;
    RatingBar rbDetailRating;
    Button btnMarkRead, btnBack, btnDelete;
    LinearLayout btnBackTopBarDetail;

    Book currentBook;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        // Ẩn thanh ActionBar mặc định màu tím/xanh của hệ thống
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Ánh xạ View
        tvDetailName = findViewById(R.id.tvDetailName);
        tvDetailAuthorCategory = findViewById(R.id.tvDetailAuthorCategory);
        tvDetailStatus = findViewById(R.id.tvDetailStatus);
        tvDetailNote = findViewById(R.id.tvDetailNote);
        rbDetailRating = findViewById(R.id.rbDetailRating);
        btnMarkRead = findViewById(R.id.btnMarkRead);
        btnBack = findViewById(R.id.btnBack);
        btnDelete = findViewById(R.id.btnDelete);
        btnBackTopBarDetail = findViewById(R.id.btnBackTopBarDetail);

        // Lấy dữ liệu sách được truyền sang từ MainActivity
        Intent intent = getIntent();
        currentBook = (Book) intent.getSerializableExtra("book");
        position = intent.getIntExtra("position", -1);

        if (currentBook != null) {
            tvDetailName.setText(currentBook.getTenSach());
            tvDetailAuthorCategory.setText(currentBook.getTacGia() + " • " + currentBook.getTheLoai());
            tvDetailStatus.setText(currentBook.getTinhTrang());
            rbDetailRating.setRating(currentBook.getDanhGia());

            // Xử lý nếu người dùng không nhập ghi chú
            if (currentBook.getGhiChu() == null || currentBook.getGhiChu().trim().isEmpty()) {
                tvDetailNote.setText("(Không có ghi chú)");
            } else {
                tvDetailNote.setText(currentBook.getGhiChu());
            }

            // Nếu sách đã ở trạng thái "Đã đọc", vô hiệu hóa nút "Đánh dấu đã đọc"
            if (currentBook.getTinhTrang().equals("Đã đọc")) {
                btnMarkRead.setEnabled(false);
            }
        }

        // 1. Xử lý nút Đánh dấu đã đọc
        btnMarkRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentBook.setTinhTrang("Đã đọc");
                currentBook.setDanhGia(rbDetailRating.getRating()); // Cập nhật luôn số sao nếu người dùng có kéo chỉnh

                Intent returnIntent = new Intent();
                returnIntent.putExtra("action", "update");
                returnIntent.putExtra("position", position);
                returnIntent.putExtra("book", currentBook);
                setResult(RESULT_OK, returnIntent);

                Toast.makeText(BookDetailActivity.this, "Đã cập nhật trạng thái!", Toast.LENGTH_SHORT).show();
                finish(); // Đóng màn hình
            }
        });

        // 2. Xử lý nút Xóa sách
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("action", "delete");
                returnIntent.putExtra("position", position);
                setResult(RESULT_OK, returnIntent);

                Toast.makeText(BookDetailActivity.this, "Đã xóa sách!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        // 3. Xử lý nút Quay lại (Nút màu trắng ở dưới)
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backAndUpdateRating();
            }
        });

        // 4. Xử lý nút Quay lại (Mũi tên <- trên thanh Top Bar màu xanh)
        btnBackTopBarDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backAndUpdateRating();
            }
        });
    }

    // Hàm hỗ trợ: Vừa quay lại vừa lưu lại số sao (đề phòng người dùng chỉ đổi sao rồi bấm Back luôn)
    private void backAndUpdateRating() {
        if (currentBook != null) {
            currentBook.setDanhGia(rbDetailRating.getRating());
            Intent returnIntent = new Intent();
            returnIntent.putExtra("action", "update");
            returnIntent.putExtra("position", position);
            returnIntent.putExtra("book", currentBook);
            setResult(RESULT_OK, returnIntent);
        }
        finish();
    }
}