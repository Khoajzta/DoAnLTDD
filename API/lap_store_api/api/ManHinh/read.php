<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json; charset=UTF-8');

include_once('../../config/database.php');
include_once('../../model/manhinh.php'); // Sử dụng đúng model ManHinh

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp ManHinh với kết nối PDO
$manhinh = new ManHinh($conn);

// Lấy tất cả màn hình
$getAllManHinh = $manhinh->getAllManHinh();

$num = $getAllManHinh->rowCount();

if ($num > 0) {
    $manhinh_array = [];
    $manhinh_array['manhinh'] = [];

    while ($row = $getAllManHinh->fetch(PDO::FETCH_ASSOC)) {
        $manhinh_item = array(
            'MaManHinh' => $row['MaManHinh'],
            'KichThuoc' => $row['KichThuoc'],
            'DoPhanGiai' => $row['DoPhanGiai'],
            'TanSoQuet' => $row['TanSoQuet'],
            'LoaiTamNen' => $row['LoaiTamNen'],
        );
        array_push($manhinh_array['manhinh'], $manhinh_item);
    }

    // Xuất dữ liệu dạng JSON
    echo json_encode($manhinh_array, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
} else {
    // Nếu không có dữ liệu
    echo json_encode(array('message' => 'Không có màn hình nào được tìm thấy.'), JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
}
?>
