<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');

include_once('../../config/database.php');
include_once('../../model/manhinh.php'); // Thay Rom thành ManHinh

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp ManHinh với kết nối PDO
$manhinh = new ManHinh($conn);

// Lấy MaManHinh từ URL (phương thức GET)
$manhinh->MaManHinh = isset($_GET['id']) ? $_GET['id'] : die(json_encode(array('message' => 'Mã màn hình không tồn tại.')));

// Lấy thông tin chi tiết màn hình
$manhinh->getManHinhById();

// Kiểm tra nếu màn hình tồn tại
if ($manhinh->MaManHinh) {
    // Tạo mảng phản hồi
    $manhinh_item = array(
        'MaManHinh' => $manhinh->MaManHinh,
        'KichThuoc' => $manhinh->KichThuoc,
        'DoPhanGiai' => $manhinh->DoPhanGiai,
        'TanSoQuet' => $manhinh->TanSoQuet,
        'LoaiTamNen' => $manhinh->LoaiTamNen,
    );

    // Xuất dữ liệu dạng JSON
    echo json_encode($manhinh_item, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
} else {
    // Nếu không tìm thấy
    echo json_encode(array('message' => 'Màn hình không tồn tại.'));
}
?>
