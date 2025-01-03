<?php
// Cấu hình header
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type, Access-Control-Allow-Methods, Authorization, X-Requested-With');

// Kết nối đến database và model
include_once('../../config/database.php');
include_once('../../model/chitiethoadonban.php');  // Điều chỉnh theo lớp ChiTietHoaDonBan

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp ChiTietHoaDonBan với kết nối PDO
$chitiet = new ChiTietHoaDonBan($conn);

// Lấy dữ liệu JSON được gửi từ client
$data = json_decode(file_get_contents("php://input"));

// Gán dữ liệu cho các thuộc tính của đối tượng ChiTietHoaDonBan
$chitiet->MaHoaDonBan = $data->MaHoaDonBan;  // Mã hóa đơn bán
$chitiet->MaSanPham = $data->MaSanPham;  // Mã sản phẩm
$chitiet->SoLuong = $data->SoLuong;  // Số lượng sản phẩm
$chitiet->DonGia = $data->DonGia;  // Đơn giá sản phẩm
$chitiet->ThanhTien = $data->ThanhTien;  // Thành tiền
$chitiet->GiamGia = $data->GiamGia;  // Giảm giá (nếu có)

// Gọi hàm thêm chi tiết hóa đơn bán
if ($chitiet->addDetail()) {
    // Nếu thêm thành công
    echo json_encode(array('message' => 'ChiTietHoaDonban has been create .'));
} else {
    // Nếu thêm thất bại
    echo json_encode(array('message' => 'ChiTietHoaDon has not been create '));
}
?>
