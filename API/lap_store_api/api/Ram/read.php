<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json; charset=UTF-8');

include_once('../../config/database.php');
include_once('../../model/ram.php');

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp RAM với kết nối PDO
$ram = new ram($conn);

// Lấy tất cả dữ liệu RAM
$getAllRAM = $ram->GetAllRAM();
$num = $getAllRAM->rowCount();

// Kiểm tra nếu có dữ liệu
if ($num > 0) {
    $ram_array = [];
    $ram_array['ram'] = [];

    while ($row = $getAllRAM->fetch(PDO::FETCH_ASSOC)) {
        extract($row);

        $ram_item = array(
            'MaRAM' => $MaRAM,
            'DungLuong' => $DungLuong,
            'Loai' => $Loai,
            'TocDo' => $TocDo
        );

        array_push($ram_array['ram'], $ram_item);
    }

    // Xuất dữ liệu dạng JSON
    echo json_encode($ram_array, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
} else {
    // Trường hợp không có dữ liệu
    echo json_encode(
        array('message' => 'Không có bản ghi RAM nào.')
    );
}
?>
