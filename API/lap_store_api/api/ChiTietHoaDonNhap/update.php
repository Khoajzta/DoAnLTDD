<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: PUT');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type,Access-Control-Allow-Methods,Authorization,X-Requested-With');

include_once('../../config/database.php');
include_once('../../model/cthoadonnhap.php'); // Đảm bảo rằng lớp CTHoaDonNhap đã tồn tại

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp CTHoaDonNhap với kết nối PDO
$ctHDNhap = new CTHoaDonNhap($conn);

// Lấy dữ liệu từ yêu cầu PUT
$data = json_decode(file_get_contents("php://input"));

// Kiểm tra nếu dữ liệu đầu vào tồn tại
if (!isset($data->MaCTHoaDonNhap)) {
    echo json_encode(array('message' => 'MaCTHoaDonNhap không tồn tại.'));
    die();
}

// Gán dữ liệu từ yêu cầu PUT vào đối tượng
$ctHDNhap->MaCTHoaDonNhap = $data->MaCTHoaDonNhap;
$ctHDNhap->MaHDNNhap = $data->MaHDNNhap;
$ctHDNhap->MaSanPham = $data->MaSanPham;
$ctHDNhap->SoLuong = $data->SoLuong;
$ctHDNhap->DonGia = $data->DonGia;
$ctHDNhap->ThanhTien = $data->ThanhTien;
$ctHDNhap->TrangThai = $data->TrangThai;

// Cập nhật thông tin chi tiết hóa đơn nhập
if ($ctHDNhap->updateCTHoaDonNhap()) {
    echo json_encode(array('message' => 'Chi tiết hóa đơn nhập đã được cập nhật.'));
} else {
    echo json_encode(array('message' => 'Cập nhật chi tiết hóa đơn nhập thất bại.'));
}
?>
