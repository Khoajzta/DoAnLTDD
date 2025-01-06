<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');

// Bao gồm các file cấu hình và lớp
include_once('../../config/database.php');
include_once('../../model/nhacungcap.php'); // Đảm bảo bạn sử dụng đúng model cho nhà cung cấp

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp NhaCungCap với kết nối PDO
$nhacungcap = new NhaCungCap($conn);

// Lấy MaNCC từ URL (phương thức GET)
$nhacungcap->MaNCC = isset($_GET['id']) ? $_GET['id'] : die(json_encode(array('message' => 'Mã nhà cung cấp không tồn tại.')));

// Lấy thông tin chi tiết nhà cung cấp
$nhacungcap->getNhaCungCapById();

// Kiểm tra nếu nhà cung cấp tồn tại
if ($nhacungcap->MaNCC) {
    // Tạo mảng phản hồi
    $nhacungcap_item = array(
        'MaNCC' => $nhacungcap->MaNCC,
        'TenNCC' => $nhacungcap->TenNCC,
        'DiaChi' => $nhacungcap->DiaChi,
        'SDT' => $nhacungcap->SDT,
        'TrangThai' => $nhacungcap->TrangThai
    );

    // Xuất dữ liệu dạng JSON
    echo json_encode($nhacungcap_item, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
} else {
    // Nếu không tìm thấy nhà cung cấp
    echo json_encode(array('message' => 'Nhà cung cấp không tồn tại.'));
}
?>
