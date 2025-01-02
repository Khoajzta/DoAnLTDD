<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: PUT');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type,Access-Control-Allow-Methods,Authorization,X-Requested-With');

include_once('../../config/database.php');
include_once('../../model/hoadonban.php');

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp HoaDonBan với kết nối PDO
$hoadon = new HoaDonBan($conn);

// Lấy dữ liệu từ yêu cầu PUT
$data = json_decode(file_get_contents("php://input"));

// Kiểm tra nếu dữ liệu đầu vào tồn tại
if (!isset($data->MaHoaDonBan)) {
    echo json_encode(array('message' => 'Mã hóa đơn không tồn tại.'));
    die();
}

// Gán dữ liệu từ yêu cầu PUT vào đối tượng
$hoadon->MaHoaDonBan = $data->MaHoaDonBan;
$hoadon->MaKhachHang = $data->MaKhachHang;
$hoadon->NgayDatHang = $data->NgayDatHang;
$hoadon->MaDiaChi = $data->MaDiaChi;
$hoadon->TongTien = $data->TongTien;
$hoadon->TrangThai = $data->TrangThai;

// Cập nhật hóa đơn bán
if ($hoadon->updateHoaDonBan()) {
    echo json_encode(array('message' => 'Hóa đơn bán đã được cập nhật.'));
} else {
    echo json_encode(array('message' => 'Không thể cập nhật hóa đơn bán.'));
}
?>
