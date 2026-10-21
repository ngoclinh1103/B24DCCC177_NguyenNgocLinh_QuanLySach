package com.example.b24dccc177_nguyenngoclinh_quanlysach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Book> bookList;
    BookAdapter adapter;
    ListView listViewBooks;
    TextView tvTotal, tvRead, tvUnread;
    Button btnAddBook;

    // Bộ lắng nghe kết quả trả về từ màn hình Thêm/Sửa/Xóa
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    String action = data.getStringExtra("action");
                    int pos = data.getIntExtra("position", -1);

                    if ("add".equals(action)) {
                        Book newBook = (Book) data.getSerializableExtra("book");
                        bookList.add(newBook);
                    } else if ("update".equals(action) && pos != -1) {
                        Book updatedBook = (Book) data.getSerializableExtra("book");
                        bookList.set(pos, updatedBook);
                    } else if ("delete".equals(action) && pos != -1) {
                        bookList.remove(pos);
                    }

                    adapter.notifyDataSetChanged();
                    updateStats();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        tvTotal = findViewById(R.id.tvTotal);
        tvRead = findViewById(R.id.tvRead);
        tvUnread = findViewById(R.id.tvUnread);
        listViewBooks = findViewById(R.id.listViewBooks);
        btnAddBook = findViewById(R.id.btnAddBook);

        // Tạo dữ liệu mẫu ban đầu
        bookList = new ArrayList<>();
        bookList.add(new Book("Lập trình Android cơ bản", "Nguyễn Văn A", "Công nghệ", "Đang đọc", 4f, ""));
        bookList.add(new Book("Đắc nhân tâm", "Dale Carnegie", "Kỹ năng sống", "Đã đọc", 5f, ""));
        bookList.add(new Book("Dế Mèn phiêu lưu ký", "Tô Hoài", "Văn học", "Chưa đọc", 0f, ""));

        adapter = new BookAdapter(this, bookList);
        listViewBooks.setAdapter(adapter);

        updateStats();

        // Chuyển sang màn hình Thêm sách
        btnAddBook.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
            launcher.launch(intent);
        });

        // Bấm vào 1 cuốn sách để xem chi tiết
        listViewBooks.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, BookDetailActivity.class);
            intent.putExtra("book", bookList.get(position));
            intent.putExtra("position", position);
            launcher.launch(intent);
        });
    }

    private void updateStats() {
        int total = bookList.size();
        int read = 0;
        for (Book b : bookList) {
            if (b.getTinhTrang().equals("Đã đọc")) {
                read++;
            }
        }
        tvTotal.setText(String.valueOf(total));
        tvRead.setText(String.valueOf(read));
        tvUnread.setText(String.valueOf(total - read));
    }
}