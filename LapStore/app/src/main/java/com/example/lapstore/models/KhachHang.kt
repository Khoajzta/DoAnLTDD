package com.example.lapstore.models

data class KhachHang(
    var MaKhachHang: Int,
    var HoTen: String,
    var GioiTinh: String,
    var NgaySinh: String,
    var Email: String,
    var SoDienThoai: String,
    var MaDiaChi: Int,
)
data class KhachHangResponse(
    val khachhang:List<KhachHang>
)
