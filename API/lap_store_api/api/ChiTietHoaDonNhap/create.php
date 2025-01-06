<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type,Access-Control-Allow-Methods,Authorization,X-Requested-With');

include_once('../../config/database.php');
include_once('../../model/cthoadonnhap.php'); // Thay đổi đường dẫn đến model đúng với đối tượng sử dụng

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp CTHoaDonNhap với kết nối PDO
$ctHDNhap = new CTHoaDonNhap($conn); // Đặt đúng tên lớp từ model

// Lấy dữ liệu từ yêu cầu POST
$data = json_decode(file_get_contents("php://input"));

// Gán dữ liệu từ yêu cầu POST vào đối tượng
if (isset($data->MaCTHoaDonNhap)) {
    $ctHDNhap->MaCTHoaDonNhap = $data->MaCTHoaDonNhap;
    $ctHDNhap->MaHDNNhap = $data->MaHDNNhap;
    $ctHDNhap->MaSanPham = $data->MaSanPham;
    $ctHDNhap->SoLuong = $data->SoLuong;
    $ctHDNhap->DonGia = $data->DonGia;
    $ctHDNhap->ThanhTien = $data->ThanhTien;
    $ctHDNhap->TrangThai = $data->TrangThai;

    // Gọi phương thức thêm chi tiết hóa đơn nhập
    if ($ctHDNhap->addCTHoaDonNhap()) {
        echo json_encode(array('message' => 'Thêm chi tiết hóa đơn nhập thành công.'));
    } else {
        echo json_encode(array('message' => 'Thêm chi tiết hóa đơn nhập thất bại.'));
    }
} else {
    echo json_encode(array('message' => 'Dữ liệu không hợp lệ.'));
}
?>
