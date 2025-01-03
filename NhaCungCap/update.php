<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: PUT');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type, Access-Control-Allow-Methods, Authorization, X-Requested-With');

include_once('../../config/database.php');
include_once('../../model/nhacungcap.php'); // Đảm bảo bạn sử dụng đúng model cho nhà cung cấp

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp NhaCungCap với kết nối PDO
$nhacungcap = new NhaCungCap($conn);

// Lấy dữ liệu từ yêu cầu PUT
$data = json_decode(file_get_contents("php://input"));

// Kiểm tra nếu dữ liệu đầu vào tồn tại
if (!isset($data->MaNCC)) {
    echo json_encode(array('message' => 'MaNCC không tồn tại'));
    die();
}

// Gán dữ liệu từ yêu cầu PUT vào đối tượng
$nhacungcap->MaNCC = $data->MaNCC;
$nhacungcap->TenNCC = $data->TenNCC;
$nhacungcap->DiaChi = $data->DiaChi;
$nhacungcap->SDT = $data->SDT;
$nhacungcap->TrangThai = $data->TrangThai;

// Cập nhật thông tin nhà cung cấp
if ($nhacungcap->updateNhaCungCap()) {
    echo json_encode(array('message' => 'Nhà cung cấp cập nhật thành công.'));
} else {
    echo json_encode(array('message' => 'Nhà cung cấp cập nhật thất bại.'));
}
?>
