package com.example.lapstore.models

data class HoaDonBan(
    val MaHoaDonBan: Int,
    val MaKhachHang: Int,
    val NgayDatHang: String,
    val MaDiaChi: Int,
    val TongTien: Int,
    val TrangThai: Int // Đảm bảo là kiểu Int
)

