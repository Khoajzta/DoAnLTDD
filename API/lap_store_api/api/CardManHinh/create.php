<?php
// Cấu hình header
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type, Access-Control-Allow-Methods, Authorization, X-Requested-With');

// Kết nối đến database và model
include_once('../../config/database.php');
include_once('../../model/cardmanhinh.php');

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp CardDoHoa với kết nối PDO
$carddohoa = new CardManHinh($conn);

// Lấy dữ liệu JSON được gửi từ client
$data = json_decode(file_get_contents("php://input"));

// Gán dữ liệu cho các thuộc tính của đối tượng
$carddohoa->MaCardManHinh= $data->MaCardManHinh;
$carddohoa->TenCard = $data->TenCard;
$carddohoa->DungLuongBoNho = $data->DungLuongBoNho;
$carddohoa->MaLoaiCard = $data->MaLoaiCard;

// Gọi hàm thêm mới Card Đồ Họa
if ($carddohoa->addCard()) {
    // Nếu thêm thành công
    echo json_encode(array('message' => 'card do hoa create '));
} else {
    // Nếu thêm thất bại
    echo json_encode(array('message' => 'card do not create '));
}
?>
