<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json; charset=UTF-8');

include_once('../../config/database.php');
include_once('../../model/nhacungcap.php'); // Đảm bảo bạn sử dụng đúng model

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp NhaCungCap với kết nối PDO
$nhacungcap = new NhaCungCap($conn);

// Lấy tất cả nhà cung cấp
$getAllNhaCungCap = $nhacungcap->getAllNhaCungCap();

$num = $getAllNhaCungCap->rowCount();

if ($num > 0) {
    $nhacungcap_array = [];
    $nhacungcap_array['nhacungcap'] = [];

    while ($row = $getAllNhaCungCap->fetch(PDO::FETCH_ASSOC)) {
        $nhacungcap_item = array(
            'MaNCC' => $row['MaNCC'],
            'TenNCC' => $row['TenNCC'],
            'DiaChi' => $row['DiaChi'],
            'SDT' => $row['SDT'],
            'TrangThai' => $row['TrangThai']
        );
        array_push($nhacungcap_array['nhacungcap'], $nhacungcap_item);
    }

    // Xuất dữ liệu dạng JSON
    echo json_encode($nhacungcap_array, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
} else {
    // Nếu không có dữ liệu
    echo json_encode(array('message' => 'Không có nhà cung cấp nào được tìm thấy.'), JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
}
?>
