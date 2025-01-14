-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th1 14, 2025 lúc 06:48 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlybanlaptop`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `binhluandanhgia`
--

CREATE TABLE `binhluandanhgia` (
  `MaBinhLuan` int(11) NOT NULL,
  `MaKhachHang` int(11) NOT NULL,
  `MaSanPham` int(11) NOT NULL,
  `MaHoaDonBan` int(11) NOT NULL,
  `SoSao` int(11) NOT NULL,
  `NoiDung` text DEFAULT NULL,
  `NgayDanhGia` datetime NOT NULL,
  `TrangThai` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadonban`
--

CREATE TABLE `chitiethoadonban` (
  `MaChiTietHoaDonBan` int(11) NOT NULL,
  `MaHoaDonBan` int(11) DEFAULT NULL,
  `MaSanPham` int(11) DEFAULT NULL,
  `SoLuong` int(11) NOT NULL,
  `DonGia` int(20) DEFAULT NULL,
  `ThanhTien` int(20) GENERATED ALWAYS AS (`SoLuong` * `DonGia`) STORED,
  `GiamGia` int(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chitiethoadonban`
--

INSERT INTO `chitiethoadonban` (`MaChiTietHoaDonBan`, `MaHoaDonBan`, `MaSanPham`, `SoLuong`, `DonGia`, `GiamGia`) VALUES
(2, 1, 1, 2, 18000000, 0),
(3, 1, 2, 6, 8990000, 0),
(4, 2, 1, 1, 18000000, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `diachi`
--

CREATE TABLE `diachi` (
  `MaDiaChi` int(11) NOT NULL,
  `ThongTinDiaChi` varchar(200) NOT NULL,
  `TenNguoiNhan` varchar(100) NOT NULL,
  `SoDienThoai` char(10) NOT NULL,
  `MaKhachHang` int(11) NOT NULL,
  `MacDinh` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `diachi`
--

INSERT INTO `diachi` (`MaDiaChi`, `ThongTinDiaChi`, `TenNguoiNhan`, `SoDienThoai`, `MaKhachHang`, `MacDinh`) VALUES
(1, 'Số 102 ấp Đông B', 'Anh A', '0901234545', 1, 1),
(2, 'Số 102 ấp Đông A', '', '', 1, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `giohang`
--

CREATE TABLE `giohang` (
  `MaGioHang` int(11) NOT NULL,
  `MaKhachHang` int(11) DEFAULT NULL,
  `MaSanPham` int(11) DEFAULT NULL,
  `SoLuong` int(11) NOT NULL,
  `TrangThai` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hinhanh`
--

CREATE TABLE `hinhanh` (
  `MaHinhAnh` int(11) NOT NULL,
  `DuongDan` varchar(500) DEFAULT NULL,
  `MaSanPham` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `hinhanh`
--

INSERT INTO `hinhanh` (`MaHinhAnh`, `DuongDan`, `MaSanPham`) VALUES
(1, 'https://i.postimg.cc/1zQSrgFQ/011vn-88f465ddba134eb2854752de1a3910f7-1024x1024.webp', 1),
(2, 'https://i.postimg.cc/qMQfcXvZ/ava-c8a92176125145c5a743e6a836ebef42-1024x1024.png', 2),
(3, 'https://i.postimg.cc/50S1K8vJ/asus-gaming-vivobook-k3605zf-rp634w-d59d42a7451f48d48cce32645f03f90c-1024x1024.webp', 3),
(4, 'https://i.postimg.cc/Bb83SkkS/km095w-9e26278b10f642f18dd9855f30828c43-1024x1024.webp', 4),
(5, 'https://i.postimg.cc/cCQM92V9/03-fx507-1-8a446be6628445bc8586d1bec682e63a-1024x1024.webp', 1),
(6, 'https://i.postimg.cc/cCQM92V9/03-fx507-1-8a446be6628445bc8586d1bec682e63a-1024x1024.webp', 1),
(7, 'https://i.postimg.cc/cCQM92V9/03-fx507-1-8a446be6628445bc8586d1bec682e63a-1024x1024.webp', 1),
(8, 'https://i.postimg.cc/cCQM92V9/03-fx507-1-8a446be6628445bc8586d1bec682e63a-1024x1024.webp', 1),
(9, 'https://i.postimg.cc/cCQM92V9/03-fx507-1-8a446be6628445bc8586d1bec682e63a-1024x1024.webp', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadonban`
--

CREATE TABLE `hoadonban` (
  `MaHoaDonBan` int(11) NOT NULL,
  `MaKhachHang` int(11) DEFAULT NULL,
  `NgayDatHang` datetime NOT NULL,
  `MaDiaChi` int(11) DEFAULT NULL,
  `TongTien` int(11) DEFAULT NULL,
  `PhuongThucThanhToan` varchar(50) NOT NULL,
  `TrangThai` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `hoadonban`
--

INSERT INTO `hoadonban` (`MaHoaDonBan`, `MaKhachHang`, `NgayDatHang`, `MaDiaChi`, `TongTien`, `PhuongThucThanhToan`, `TrangThai`) VALUES
(1, 1, '2025-01-14 00:00:00', 1, 89970000, 'Thanh toán khi nhận hàng', '1'),
(2, 1, '2025-01-14 00:00:00', 1, 18030000, 'Thanh toán khi nhận hàng', '1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKhachHang` int(11) NOT NULL,
  `HoTen` varchar(100) NOT NULL,
  `GioiTinh` char(3) DEFAULT NULL,
  `NgaySinh` date DEFAULT NULL,
  `Email` varchar(100) NOT NULL,
  `SoDienThoai` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`MaKhachHang`, `HoTen`, `GioiTinh`, `NgaySinh`, `Email`, `SoDienThoai`) VALUES
(1, 'hahaha', 'Nam', '1990-05-11', 'a.@.gmail.com', '0901234545');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `MaLoai` int(11) NOT NULL,
  `TenLoai` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `loaisanpham`
--

INSERT INTO `loaisanpham` (`MaLoai`, `TenLoai`) VALUES
(1, 'Laptop văn phòng'),
(2, 'Laptop Gaming');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `mausac`
--

CREATE TABLE `mausac` (
  `MaMauSac` int(11) NOT NULL,
  `TenMauSac` varchar(20) DEFAULT NULL,
  `MaSanPham` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `mausac`
--

INSERT INTO `mausac` (`MaMauSac`, `TenMauSac`, `MaSanPham`) VALUES
(1, 'Đen', 1),
(2, 'Trắng', 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `MaSanPham` int(11) NOT NULL,
  `TenSanPham` varchar(100) NOT NULL,
  `MaLoaiSanPham` int(11) DEFAULT NULL,
  `CPU` char(50) DEFAULT NULL,
  `RAM` int(11) DEFAULT NULL,
  `CardManHinh` varchar(20) DEFAULT NULL,
  `SSD` int(11) DEFAULT NULL,
  `ManHinh` char(50) DEFAULT NULL,
  `MaMauSac` int(11) DEFAULT NULL,
  `Gia` int(11) DEFAULT NULL,
  `SoLuong` int(11) DEFAULT NULL,
  `MoTa` text DEFAULT NULL,
  `TrangThai` int(11) DEFAULT NULL,
  `HinhAnh` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`MaSanPham`, `TenSanPham`, `MaLoaiSanPham`, `CPU`, `RAM`, `CardManHinh`, `SSD`, `ManHinh`, `MaMauSac`, `Gia`, `SoLuong`, `MoTa`, `TrangThai`, `HinhAnh`) VALUES
(1, 'Laptop gaming ASUS TUF Gaming F15 FX507VV LP304W', 2, 'INTEL CORE I9 12900K', 8, 'RTX 4060', 512, '1920x1080', 2, 18000000, 2, 'ASUS Vivobook 16X K3605ZU RP296W là một chiếc laptop gaming tầm trung mới ra mắt của ASUS với thiết kế hiện đại, hiệu năng mạnh mẽ và mức giá hợp lý.', 1, 2),
(2, 'Laptop MSI Modern 14 C11M 011VN', 1, 'Intel Core i3-1115G4 (up to 4.1Ghz, 6MB)', 8, 'Intel UHD Graphics', 512, '1920x1080', 1, 8990000, 20, 'Laptop văn phòng', 1, 1),
(3, 'Laptop gaming ASUS Vivobook 16X K3605ZF RP634W', 2, 'AMD R77700M', 16, 'RTX 3050', 1024, '1920x1080', 1, 17290000, 5, 'ASUS Vivobook 16X K3605ZC RP564W là một chiếc laptop được thiết kế để đáp ứng nhu cầu giải trí và làm việc hiệu năng cao của người dùng. Với bộ vi xử lý Intel® Core™ i5 thế hệ 12 mạnh mẽ, card đồ họa NVIDIA® GeForce RTX™ 3050 4GB, màn hình 16 inch WUXGA 144Hz sắc nét và bàn phím backlit, Vivobook 16X K3605ZC RP564W là lựa chọn hoàn hảo cho game thủ và những người sáng tạo nội dung.', 1, 3),
(4, 'Laptop ASUS Vivobook', 1, 'INTEL CORE I7 12500H', 16, 'INTEL ARISXE', 1024, '1920x1080', 1, 17390000, 5, 'ASUS Vivobook 16X K3605ZC RP564W là một chiếc laptop được thiết kế để đáp ứng nhu cầu giải trí và làm việc hiệu năng cao của người dùng. Với bộ vi xử lý Intel® Core™ i5 thế hệ 12 mạnh mẽ, card đồ họa NVIDIA® GeForce RTX™ 3050 4GB, màn hình 16 inch WUXGA 144Hz sắc nét và bàn phím backlit, Vivobook 16X K3605ZC RP564W là lựa chọn hoàn hảo cho game thủ và những người sáng tạo nội dung.', 1, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `TenTaiKhoan` varchar(50) NOT NULL,
  `MaKhachHang` int(11) NOT NULL,
  `MatKhau` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `TrangThai` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`TenTaiKhoan`, `MaKhachHang`, `MatKhau`, `TrangThai`) VALUES
('nguyenvana', 1, '123', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `binhluandanhgia`
--
ALTER TABLE `binhluandanhgia`
  ADD PRIMARY KEY (`MaBinhLuan`),
  ADD KEY `FK_BinhLuanDanhGia_KhachHang` (`MaKhachHang`),
  ADD KEY `FK_BinhLuanDanhGia_SanPham` (`MaSanPham`),
  ADD KEY `FK_BinhLuanDanhGia_HoaDonBan` (`MaHoaDonBan`);

--
-- Chỉ mục cho bảng `chitiethoadonban`
--
ALTER TABLE `chitiethoadonban`
  ADD PRIMARY KEY (`MaChiTietHoaDonBan`),
  ADD KEY `FK_ChiTietHoaDonBan_HoaDonBan` (`MaHoaDonBan`),
  ADD KEY `FK_ChiTietHoaDonBan_SanPham` (`MaSanPham`);

--
-- Chỉ mục cho bảng `diachi`
--
ALTER TABLE `diachi`
  ADD PRIMARY KEY (`MaDiaChi`),
  ADD KEY `fk_diachi_khachhang` (`MaKhachHang`);

--
-- Chỉ mục cho bảng `giohang`
--
ALTER TABLE `giohang`
  ADD PRIMARY KEY (`MaGioHang`),
  ADD KEY `FK_GioHang_KhachHang` (`MaKhachHang`),
  ADD KEY `FK_GioHang_SanPham` (`MaSanPham`);

--
-- Chỉ mục cho bảng `hinhanh`
--
ALTER TABLE `hinhanh`
  ADD PRIMARY KEY (`MaHinhAnh`),
  ADD KEY `fk_ma_sanpham` (`MaSanPham`);

--
-- Chỉ mục cho bảng `hoadonban`
--
ALTER TABLE `hoadonban`
  ADD PRIMARY KEY (`MaHoaDonBan`),
  ADD KEY `FK_HoaDonBan_KhachHang` (`MaKhachHang`),
  ADD KEY `FK_HoaDonBan_DiaChi` (`MaDiaChi`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MaKhachHang`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- Chỉ mục cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`MaLoai`);

--
-- Chỉ mục cho bảng `mausac`
--
ALTER TABLE `mausac`
  ADD PRIMARY KEY (`MaMauSac`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`MaSanPham`),
  ADD KEY `FK_SanPham_LoaiSanPham` (`MaLoaiSanPham`),
  ADD KEY `FK_SanPham_MauSac` (`MaMauSac`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`TenTaiKhoan`),
  ADD KEY `FK_TaiKhoan_KhachHang` (`MaKhachHang`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `binhluandanhgia`
--
ALTER TABLE `binhluandanhgia`
  MODIFY `MaBinhLuan` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `chitiethoadonban`
--
ALTER TABLE `chitiethoadonban`
  MODIFY `MaChiTietHoaDonBan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `diachi`
--
ALTER TABLE `diachi`
  MODIFY `MaDiaChi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `giohang`
--
ALTER TABLE `giohang`
  MODIFY `MaGioHang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `hinhanh`
--
ALTER TABLE `hinhanh`
  MODIFY `MaHinhAnh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `hoadonban`
--
ALTER TABLE `hoadonban`
  MODIFY `MaHoaDonBan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `MaKhachHang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `MaLoai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `mausac`
--
ALTER TABLE `mausac`
  MODIFY `MaMauSac` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `MaSanPham` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `diachi`
--
ALTER TABLE `diachi`
  ADD CONSTRAINT `fk_diachi_khachhang` FOREIGN KEY (`MaKhachHang`) REFERENCES `khachhang` (`MaKhachHang`);

--
-- Các ràng buộc cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `FK_TaiKhoan_KhachHang` FOREIGN KEY (`MaKhachHang`) REFERENCES `khachhang` (`MaKhachHang`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
