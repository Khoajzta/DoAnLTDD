<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');

include_once('../../config/database.php');
include_once('../../model/ram.php');

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp RAM với kết nối PDO
$ram = new ram($conn);

// Lấy MaRAM từ URL (phương thức GET)
$ram->MaRAM = isset($_GET['id']) ? $_GET['id'] : die(json_encode(array('message' => 'Mã RAM không tồn tại.')));

// Lấy thông tin chi tiết RAM
$ram->GetRAMById();

// Kiểm tra nếu RAM tồn tại
if ($ram->MaRAM) {
    // Tạo mảng phản hồi
    $ram_item = array(
        'MaRAM' => $ram->MaRAM,
        'DungLuong' => $ram->DungLuong,
        'Loai' => $ram->Loai,
        'TocDo' => $ram->TocDo
    );

    // Xuất dữ liệu dạng JSON
    echo json_encode($ram_item, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
} else {
    // Nếu không tìm thấy
    echo json_encode(array('message' => 'RAM không tồn tại.'));
}
?>