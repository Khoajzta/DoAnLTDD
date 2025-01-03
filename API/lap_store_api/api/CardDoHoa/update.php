<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: PUT');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type, Access-Control-Allow-Methods, Authorization, X-Requested-With');

include_once('../../config/database.php');
include_once('../../model/carddohoa.php');

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp CardDoHoa với kết nối PDO
$carddohoa = new CardDoHoa($conn);

// Lấy dữ liệu từ yêu cầu PUT
$data = json_decode(file_get_contents("php://input"));

// Kiểm tra nếu dữ liệu đầu vào tồn tại
if (!isset($data->MaCardDoHoa)) {
    echo json_encode(array('message' => 'Mã Card Đồ Họa không tồn tại.'));
    die();
}

// Gán dữ liệu từ yêu cầu PUT vào đối tượng
$carddohoa->MaCardDoHoa = $data->MaCardDoHoa;
$carddohoa->TenCard = $data->TenCard;
$carddohoa->DungLuongBoNho = $data->DungLuongBoNho;
$carddohoa->MaLoaiCard = $data->MaLoaiCard;

// Cập nhật Card Đồ Họa
if ($carddohoa->updateCard()) {
    echo json_encode(array('message' => 'Card been update.'));
} else {
    echo json_encode(array('message' => 'card can not update.'));
}
?>
