<?php
header('Access-Control-Allow-Origin:*');
header('Content-Type: application/json');

include_once('../../config/database.php');
include_once('../../model/sanpham.php');

// Tạo đối tượng database và kết nối
$database = new database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp SanPham với kết nối PDO
$sanpham = new SanPham($conn);

// Kiểm tra và lấy giá trị MaLoaiSanPham từ query string
$sanpham->MaLoaiSanPham = isset($_GET['MaLoaiSanPham']) ? $_GET['MaLoaiSanPham'] : die(json_encode(["message" => "MaLoaiSanPham không được cung cấp."]));

// Lấy danh sách sản phẩm theo MaLoaiSanPham
$getSanPhamByLoai = $sanpham->GetSanPhamByLoai();
$numSanPhamByLoai = $getSanPhamByLoai->rowCount();

if ($numSanPhamByLoai > 0) {
    $sanphamByLoai_array = [];
    $sanphamByLoai_array['sanpham'] = [];

    while ($row = $getSanPhamByLoai->fetch(PDO::FETCH_ASSOC)) {
        extract($row);

        $sanpham_item = array(
            'MaSanPham'=> $MSanPham,
            'TenSanPham'=> $TenSanPham,
            'MaLoaiSanPham'=> $MaLoaiSanPham,
            'MaHangSanXuat'=> $MaHangSanXuat,
            'MaCPU'=> $TenCPU,
            'MaRAM'=> $DungLuongRAM,
            'MaCardManHinh'=> $TenCard,
            'MaROM'=> $DungLuongROM,
            'MaManHinh'=> $DoPhanGiai,
            'MaMauSac'=> $MaMauSac,
            'Gia'=> $Gia,
            'SoLuong'=> $SoLuong,
            'MoTa'=> $MoTa,
            'HinhAnh'=> $DuongDan,
            'TrangThai'=> $TrangThai,
        );
        array_push($sanphamByLoai_array['sanpham'], $sanpham_item);
    }
    echo json_encode($sanphamByLoai_array, JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES | JSON_PRETTY_PRINT);
} else {
    echo json_encode(["message" => "Không tìm thấy sản phẩm nào với MaLoaiSanPham = " . $sanpham->MaLoaiSanPham]);
}
?>
