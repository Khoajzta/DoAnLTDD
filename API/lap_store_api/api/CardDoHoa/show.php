<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');

include_once('../../config/database.php');
include_once('../../model/carddohoa.php');

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp CardDoHoa với kết nối PDO
$carddohoa = new CardDoHoa($conn);

// Lấy MaCardDoHoa từ URL (phương thức GET)
$carddohoa->MaCardDoHoa = isset($_GET['id']) ? $_GET['id'] : die(json_encode(array('message' => 'macarddohoa not exist.')));

// Lấy thông tin chi tiết Card Đồ Họa
$carddohoa->getCardById();

// Kiểm tra nếu Card Đồ Họa tồn tại
if ($carddohoa->MaCardDoHoa) {
    // Tạo mảng phản hồi
    $carddohoa_item = array(
        'MaCardDoHoa' => $carddohoa->MaCardDoHoa,
        'TenCard' => $carddohoa->TenCard,
        'DungLuongBoNho' => $carddohoa->DungLuongBoNho,
        'MaLoaiCard' => $carddohoa->MaLoaiCard
    );

    // Xuất dữ liệu dạng JSON
    echo json_encode($carddohoa_item, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
} else {
    // Nếu không tìm thấy
    echo json_encode(array('message' => 'card not exist '));
}
?>
