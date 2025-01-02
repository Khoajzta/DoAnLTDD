<?php
// Cấu hình header
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: DELETE');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type, Access-Control-Allow-Methods, Authorization, X-Requested-With');

// Kết nối đến database và model
include_once('../../config/database.php');
include_once('../../model/carddohoa.php');

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp CardDoHoa với kết nối PDO
$carddohoa = new CardDoHoa($conn);

// Lấy dữ liệu JSON từ yêu cầu
$data = json_decode(file_get_contents("php://input"));

// Gán giá trị cho thuộc tính MaCardDoHoa
$carddohoa->MaCardDoHoa = $data->MaCardDoHoa;

// Gọi hàm deleteCard() để xóa Card Đồ Họa
if ($carddohoa->deleteCard()) {
    // Nếu xóa thành công
    echo json_encode(array('message' => 'Card Đồ Họa đã được xóa.'));
} else {
    // Nếu xóa thất bại
    echo json_encode(array('message' => 'Không thể xóa Card Đồ Họa.'));
}
?>
