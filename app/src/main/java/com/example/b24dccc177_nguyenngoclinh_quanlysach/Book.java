package com.example.b24dccc177_nguyenngoclinh_quanlysach;
import java.io.Serializable;

// implements Serializable để có thể truyền object này qua lại giữa các màn hình
public class Book implements Serializable {
    private String tenSach, tacGia, theLoai, tinhTrang, ghiChu;
    private float danhGia;

    public Book(String tenSach, String tacGia, String theLoai, String tinhTrang, float danhGia, String ghiChu) {
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
        this.tinhTrang = tinhTrang;
        this.danhGia = danhGia;
        this.ghiChu = ghiChu;
    }

    public String getTenSach() { return tenSach; }
    public String getTacGia() { return tacGia; }
    public String getTheLoai() { return theLoai; }
    public String getTinhTrang() { return tinhTrang; }
    public void setTinhTrang(String tinhTrang) { this.tinhTrang = tinhTrang; }
    public float getDanhGia() { return danhGia; }
    public void setDanhGia(float danhGia) { this.danhGia = danhGia; }
    public String getGhiChu() { return ghiChu; }
}