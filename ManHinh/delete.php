<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: DELETE');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type, Access-Control-Allow-Methods, Authorization, X-Requested-With');

include_once('../../config/database.php');
include_once('../../model/manhinh.php'); // Đảm bảo bạn sử dụng đúng model cho màn hình

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp ManHinh với kết nối PDO
$manhinh = new ManHinh($conn);

// Lấy dữ liệu từ body request
$data = json_decode(file_get_contents("php://input"));

if (!isset($data->MaManHinh)) {
    echo json_encode(array('message' => 'Dữ liệu không hợp lệ.'));
    exit();
}

// Gán giá trị cho thuộc tính MaManHinh
$manhinh->MaManHinh = $data->MaManHinh;

// Thực hiện xóa màn hình
if ($manhinh->deleteManHinh()) {
    echo json_encode(array('message' => 'Xóa màn hình thành công.'));
} else {
    echo json_encode(array('message' => 'Xóa màn hình thất bại.'));
}
?>
