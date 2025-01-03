<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: PUT');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type,Access-Control-Allow-Methods,Authorization,X-Requested-With');

include_once('../../config/database.php');
include_once('../../model/rom.php');

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp Rom với kết nối PDO
$rom = new Rom($conn);

// Lấy dữ liệu từ yêu cầu PUT
$data = json_decode(file_get_contents("php://input"));

// Kiểm tra nếu dữ liệu đầu vào tồn tại
if (!isset($data->MaRom)) {
    echo json_encode(array('message' => 'MaRom không tồn tại'));
    die();
}

// Gán dữ liệu từ yêu cầu PUT vào đối tượng
$rom->MaRom = $data->MaRom;
$rom->DungLuong = $data->DungLuong;

// Cập nhật thông tin ROM
if ($rom->updateRom()) {
    echo json_encode(array('message' => 'ROM cập nhật thành công.'));
} else {
    echo json_encode(array('message' => 'ROM thất bại.'));
}
?>
