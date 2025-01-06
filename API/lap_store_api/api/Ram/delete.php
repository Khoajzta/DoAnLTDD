<?php
// Cấu hình header
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: DELETE');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type, Access-Control-Allow-Methods, Authorization, X-Requested-With');

// Kết nối đến database và model
include_once('../../config/database.php');
include_once('../../model/ram.php');

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp RAM với kết nối PDO
$ram = new ram($conn);

// Lấy dữ liệu JSON từ yêu cầu
$data = json_decode(file_get_contents("php://input"));

// Gán giá trị cho thuộc tính MaRAM
$ram->MaRAM = $data->MaRAM;

// Gọi hàm deleteRAM() để xóa RAM
if ($ram->DeleteRAM()) {
    // Nếu xóa thành công
    echo json_encode(array('message' => 'RAM delete.'));
} else {
    // Nếu xóa thất bại
    echo json_encode(array('message' => 'can not delete'));
}
?>