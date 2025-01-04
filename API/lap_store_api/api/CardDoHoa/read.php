<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json; charset=UTF-8');

include_once('../../config/database.php');
include_once('../../model/carddohoa.php');

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp CardDoHoa với kết nối PDO
$carddohoa = new CardDoHoa($conn);

// Lấy tất cả dữ liệu Card Đồ Họa
$getAllCardDoHoa = $carddohoa->getAllCards();
$num = $getAllCardDoHoa->rowCount();

// Kiểm tra nếu có dữ liệu
if ($num > 0) {
    $carddohoa_array = [];
    $carddohoa_array['carddohoa'] = [];

    while ($row = $getAllCardDoHoa->fetch(PDO::FETCH_ASSOC)) {
        extract($row);

        $carddohoa_item = array(
            'MaCardDoHoa' => $MaCardDoHoa,
            'TenCard' => $TenCard,
            'DungLuongBoNho' => $DungLuongBoNho,
            'MaLoaiCard' => $MaLoaiCard
        );

        array_push($carddohoa_array['carddohoa'], $carddohoa_item);
    }

    // Xuất dữ liệu dạng JSON
    echo json_encode($carddohoa_array, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
} else {
    // Trường hợp không có dữ liệu
    echo json_encode(
        array('message' => 'card do hoa not have .')
    );
}
?>
