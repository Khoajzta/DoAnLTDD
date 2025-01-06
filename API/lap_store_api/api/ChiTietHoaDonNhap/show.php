<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json; charset=UTF-8');

include_once('../../config/database.php');
include_once('../../model/cthoadonnhap.php'); // Đảm bảo rằng lớp CTHoaDonNhap đã tồn tại

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp CTHoaDonNhap với kết nối PDO
$ctHDNhap = new CTHoaDonNhap($conn);

// Lấy MaCTHoaDonNhap từ URL (phương thức GET)
$ctHDNhap->MaCTHoaDonNhap = isset($_GET['id']) ? $_GET['id'] : die(json_encode(array('message' => 'Mã chi tiết hóa đơn nhập không tồn tại.')));

// Lấy thông tin chi tiết hóa đơn nhập
$ctHDNhap->getCTHoaDonNhapById();

// Kiểm tra nếu chi tiết hóa đơn nhập tồn tại
if ($ctHDNhap->MaCTHoaDonNhap) {
    // Tạo mảng phản hồi
    $ctHDNhap_item = array(
        'MaCTHoaDonNhap' => $ctHDNhap->MaCTHoaDonNhap,
        'MaHDNNhap' => $ctHDNhap->MaHDNNhap,
        'MaSanPham' => $ctHDNhap->MaSanPham,
        'SoLuong' => $ctHDNhap->SoLuong,
        'DonGia' => $ctHDNhap->DonGia,
        'ThanhTien' => $ctHDNhap->ThanhTien,
        'TrangThai' => $ctHDNhap->TrangThai
    );

    // Xuất dữ liệu dạng JSON
    echo json_encode($ctHDNhap_item, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
} else {
    // Nếu không tìm thấy
    echo json_encode(array('message' => 'Chi tiết hóa đơn nhập không tồn tại.'));
}
?>
