package com.example.b24dccc177_nguyenngoclinh_quanlysach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddBookActivity extends AppCompatActivity {

    EditText edtAddName, edtAddAuthor, edtAddNote;
    Spinner spnAddCategory;
    RadioGroup rgAddStatus;
    RatingBar rbAddRating;
    Button btnSaveBook;
    LinearLayout btnBackTopBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Ánh xạ View
        edtAddName = findViewById(R.id.edtAddName);
        edtAddAuthor = findViewById(R.id.edtAddAuthor);
        edtAddNote = findViewById(R.id.edtAddNote);
        spnAddCategory = findViewById(R.id.spnAddCategory);
        rgAddStatus = findViewById(R.id.rgAddStatus);
        rbAddRating = findViewById(R.id.rbAddRating);
        btnSaveBook = findViewById(R.id.btnSaveBook);
        btnBackTopBar = findViewById(R.id.btnBackTopBar);

        // NÚT BACK: Đóng màn hình khi bấm mũi tên quay lại
        btnBackTopBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String[] categories = {"Văn học", "Khoa học", "Công nghệ", "Kỹ năng sống", "Khác"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        spnAddCategory.setAdapter(adapter);

        btnSaveBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenSach = edtAddName.getText().toString().trim();
                String tacGia = edtAddAuthor.getText().toString().trim();

                if (tenSach.isEmpty()) {
                    edtAddName.setError("Tên sách không được để trống");
                    return;
                }
                if (tacGia.isEmpty()) {
                    edtAddAuthor.setError("Tác giả không được để trống");
                    return;
                }

                String theLoai = spnAddCategory.getSelectedItem().toString();
                String ghiChu = edtAddNote.getText().toString();
                float danhGia = rbAddRating.getRating();

                int selectedId = rgAddStatus.getCheckedRadioButtonId();
                RadioButton rbSelected = findViewById(selectedId);
                String tinhTrang = rbSelected.getText().toString();

                Book newBook = new Book(tenSach, tacGia, theLoai, tinhTrang, danhGia, ghiChu);

                Intent returnIntent = new Intent();
                returnIntent.putExtra("action", "add");
                returnIntent.putExtra("book", newBook);
                setResult(RESULT_OK, returnIntent);

                Toast.makeText(AddBookActivity.this, "Đã thêm sách thành công", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}