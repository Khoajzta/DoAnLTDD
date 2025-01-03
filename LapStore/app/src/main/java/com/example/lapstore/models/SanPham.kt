package com.example.lapstore.models

data class SanPham(
    var MaSanPham: Int,
    var TenSanPham: String,
    var MaLoaiSanPham: Int,
    var MaHangSanXuat: Int,
    var MaCPU: String,
    var MaRAM: Int,
    var MaCardDoHoa: String,
    var MaROM: Int,
    var MaManHinh: String,
    var MaMauSac: Int,
    var Gia: Int,
    var SoLuong: Int,
    var MoTa: String,
    var HinhAnh: String,
    var TrangThai: Int,
)
data class SanPhamResponse(
    val sanpham:List<SanPham>
)
