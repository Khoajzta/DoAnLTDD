<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json; charset=UTF-8');

include_once('../../config/database.php');
include_once('../../model/diachi.php');

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp Rom với kết nối PDO
$rom = new Rom($conn);

// Lấy tất cả ROM
$getAllRom = $rom->getAllRom();

$num = $getAllRom->rowCount();

if ($num > 0) {
    $rom_array = [];
    $rom_array['rom'] = [];

    while ($row = $getAllRom->fetch(PDO::FETCH_ASSOC)) {
        $rom_item = array(
            'MaRom' => $row['MaRom'],
            'DungLuong' => $row['DungLuong'],
        );
        array_push($rom_array['rom'], $rom_item);
    }

    // Xuất dữ liệu dạng JSON
    echo json_encode($rom_array, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
} else {
    // Nếu không có dữ liệu
    echo json_encode(array('message' => 'Không có ROM nào được tìm thấy.'), JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
}
?>
