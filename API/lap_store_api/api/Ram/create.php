<?php
// Cấu hình header
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type, Access-Control-Allow-Methods, Authorization, X-Requested-With');

// Kết nối đến database và model
include_once('../../config/database.php');
include_once('../../model/ram.php');

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp RAM với kết nối PDO
$ram = new ram($conn);

// Lấy dữ liệu JSON được gửi từ client
$data = json_decode(file_get_contents("php://input"));

// Gán dữ liệu cho các thuộc tính của đối tượng RAM
$ram->MaRAM = $data->MaRAM;
$ram->DungLuong = $data->DungLuong;
$ram->Loai = $data->Loai;
$ram->TocDo = $data->TocDo;

// Gọi hàm thêm mới RAM
if ($ram->AddRAM()) {
    // Nếu thêm thành công
    echo json_encode(array('message' => 'RAM created.'));
} else {
    // Nếu thêm thất bại
    echo json_encode(array('message' => 'RAM not created.'));
}
?>
