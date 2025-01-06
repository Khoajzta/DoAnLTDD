<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');

include_once('../../config/database.php');
include_once('../../model/rom.php');

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp Rom với kết nối PDO
$rom = new Rom($conn);

// Lấy MaRom từ URL (phương thức GET)
$rom->MaRom = isset($_GET['id']) ? $_GET['id'] : die(json_encode(array('message' => 'Mã ROM không tồn tại.')));

// Lấy thông tin chi tiết ROM
$rom->getRomById();

// Kiểm tra nếu ROM tồn tại
if ($rom->MaRom) {
    // Tạo mảng phản hồi
    $rom_item = array(
        'MaRom' => $rom->MaRom,
        'DungLuong' => $rom->DungLuong,
    );

    // Xuất dữ liệu dạng JSON
    echo json_encode($rom_item, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
} else {
    // Nếu không tìm thấy
    echo json_encode(array('message' => 'ROM không tồn tại.'));
}
?>
