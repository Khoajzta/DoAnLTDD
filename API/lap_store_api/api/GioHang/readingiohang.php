<?php 
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');

include_once('../../config/database.php');
include_once('../../model/giohang.php');

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp GioHang với kết nối PDO
$giohang = new GioHang($conn);

// Kiểm tra xem `MaGioHang` có được gửi qua API không
if (isset($_GET['MaGioHang'])) {
    $giohang->MaGioHang = $_GET['MaGioHang']; // Gán giá trị MaGioHang từ request

    // Gọi phương thức GetGioHangById
    $giohang->GetGioHangById();

    // Kiểm tra xem dữ liệu có tồn tại hay không
    if ($giohang->MaGioHang) {
        // Chuẩn bị dữ liệu trả về
        $giohang_item = array(
            'MaGioHang' => $giohang->MaGioHang,
            'MaKhachHang' => $giohang->MaKhachHang,
            'MaSanPham' => $giohang->MaSanPham,
            'SoLuong' => $giohang->SoLuong,
            'TrangThai' => $giohang->TrangThai,
            'TenSanPham' => $giohang->TenSanPham,
            'MoTa' => $giohang->MoTa,
            'Gia' => $giohang->Gia,
            'SanPhamSoLuong' => $giohang->SanPhamSoLuong,
            'HinhAnh' => $giohang->HinhAnh
        );

        // Trả về kết quả dưới dạng JSON
        echo json_encode($giohang_item, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
    } else {
        // Nếu không tìm thấy giỏ hàng
        echo json_encode(array(
            "message" => "Không tìm thấy giỏ hàng với MaGioHang này."
        ));
    }
} else {
    // Nếu không có MaGioHang trong request
    echo json_encode(array(
        "message" => "Vui lòng cung cấp MaGioHang."
    ));
}
