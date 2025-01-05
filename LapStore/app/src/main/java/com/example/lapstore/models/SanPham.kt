package com.example.lapstore.models

import com.google.gson.annotations.SerializedName

data class SanPham(
    @SerializedName("MaSanPham") var MaSanPham: Int,
    @SerializedName("TenSanPham") var TenSanPham: String,
    @SerializedName("MaLoaiSanPham") var MaLoaiSanPham: Int,
    @SerializedName("MaHangSanXuat") var MaHangSanXuat: Int,
    @SerializedName("MaCPU") var MaCPU: String,
    @SerializedName("MaRAM") var MaRAM: Int,
    @SerializedName("MaCardManHinh") var MaCardDoHoa: String,
    @SerializedName("MaROM") var MaROM: Int,
    @SerializedName("MaManHinh") var MaManHinh: String,
    @SerializedName("MaMauSac") var MaMauSac: Int,
    @SerializedName("Gia") var Gia: Int,
    @SerializedName("SoLuong") var SoLuong: Int,
    @SerializedName("MoTa") var MoTa: String,
    @SerializedName("HinhAnh") var HinhAnh: String,
    @SerializedName("TrangThai") var TrangThai: Int,
)

