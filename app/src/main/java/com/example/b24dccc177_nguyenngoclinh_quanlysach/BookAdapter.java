package com.example.b24dccc177_nguyenngoclinh_quanlysach;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book> {
    private Context context;
    private ArrayList<Book> bookList;

    public BookAdapter(Context context, ArrayList<Book> bookList) {
        super(context, 0, bookList);
        this.context = context;
        this.bookList = bookList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        }

        Book book = bookList.get(position);

        TextView tvName = convertView.findViewById(R.id.tvItemName);
        TextView tvAuthor = convertView.findViewById(R.id.tvItemAuthor);
        TextView tvCategory = convertView.findViewById(R.id.tvItemCategory);
        TextView tvStatus = convertView.findViewById(R.id.tvItemStatus);
        RatingBar rbRating = convertView.findViewById(R.id.rbItemRating);

        tvName.setText(book.getTenSach());
        tvAuthor.setText("Tác giả: " + book.getTacGia());
        tvCategory.setText(book.getTheLoai());
        tvStatus.setText(book.getTinhTrang());
        rbRating.setRating(book.getDanhGia());

        return convertView;
    }
}