<?php
header('Access-Control-Allow-Origin:*');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type,Access-Control-Allow-Methods,Authorization,X-Requested-With');

include_once('../../config/database.php');
include_once('../../model/nhacungcap.php');  // Đảm bảo bạn có mô hình NhaCungCap đúng

// Tạo đối tượng database và kết nối
$database = new database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp NhaCungCap với kết nối PDO
$NhaCungCap = new NhaCungCap($conn);  // Đảm bảo bạn sử dụng lớp đúng

// Lấy dữ liệu từ yêu cầu POST
$data = json_decode(file_get_contents("php://input"));

// Gán dữ liệu từ yêu cầu POST vào đối tượng NhaCungCap
$NhaCungCap->MaNCC = $data->MaNCC;
$NhaCungCap->TenNCC = $data->TenNCC;
$NhaCungCap->DiaChi = $data->DiaChi;
$NhaCungCap->SDT = $data->SDT;
$NhaCungCap->TrangThai = $data->TrangThai;

// Thêm nhà cung cấp vào cơ sở dữ liệu
if($NhaCungCap->AddNhaCungCap()){  // Gọi phương thức AddNhaCungCap() của lớp NhaCungCap
    echo json_encode(array('message' => 'Thêm nhà cung cấp thành công.'));
}
else{
    echo json_encode(array('message' => 'Thêm nhà cung cấp thất bại.'));
}
?>
