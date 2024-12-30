package com.example.lapstore.models

data class SanPham(
    var MaSanPham: Int,
    var TenSanPham: String,
    var MaLoaiSanPham: Int,
    var MaHangSanXuat: Int,
    var MaCPU: Int,
    var MaRAM: Int,
    var MaCardDoHoa: String,
    var MaROM: Int,
    var MaManHinh: Int,
    var MaMauSac: Int,
    var Gia: Int,
    var SoLuong: Int,
    var MoTa: String,
    var HinhAnh: Int,
    var TrangThai: Int,
)

data class SanPhamResponse(
    val sanpham: List<SanPham>
)