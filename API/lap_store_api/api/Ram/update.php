<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: PUT');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type,Access-Control-Allow-Methods,Authorization,X-Requested-With');

include_once('../../config/database.php');
include_once('../../model/ram.php');

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp RAM với kết nối PDO
$ram = new ram($conn);

// Lấy dữ liệu từ yêu cầu PUT
$data = json_decode(file_get_contents("php://input"));

// Kiểm tra nếu dữ liệu đầu vào tồn tại
if (!isset($data->MaRAM)) {
    echo json_encode(array('message' => 'MaRAM không tồn tại.'));
    die();
}

// Gán dữ liệu từ yêu cầu PUT vào đối tượng
$ram->MaRAM = $data->MaRAM;
$ram->DungLuong = $data->DungLuong;
$ram->Loai = $data->Loai;
$ram->TocDo = $data->TocDo;

// Cập nhật RAM
if ($ram->UpdateRAM()) {
    echo json_encode(array('message' => 'RAM update.'));
} else {
    echo json_encode(array('message' => 'RAM can not update.'));
}
?>
