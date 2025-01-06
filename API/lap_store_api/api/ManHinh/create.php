<?php
header('Access-Control-Allow-Origin:*');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type, Access-Control-Allow-Methods, Authorization, X-Requested-With');

include_once('../../config/database.php');
include_once('../../model/manhinh.php'); // Đảm bảo bạn sử dụng đúng model cho màn hình

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp ManHinh với kết nối PDO
$manhinh = new ManHinh($conn);

// Lấy dữ liệu từ yêu cầu POST
$data = json_decode(file_get_contents("php://input"));

// Kiểm tra nếu dữ liệu đầu vào tồn tại
if (!isset($data->MaManHinh) || !isset($data->KichThuoc) || !isset($data->DoPhanGiai) || !isset($data->TanSoQuet) || !isset($data->LoaiTamNen)) {
    echo json_encode(array('message' => 'Dữ liệu không hợp lệ.'));
    die();
}

// Gán dữ liệu từ yêu cầu POST vào đối tượng
$manhinh->MaManHinh = $data->MaManHinh;
$manhinh->KichThuoc = $data->KichThuoc;
$manhinh->DoPhanGiai = $data->DoPhanGiai;
$manhinh->TanSoQuet = $data->TanSoQuet;
$manhinh->LoaiTamNen = $data->LoaiTamNen;

// Thêm màn hình vào cơ sở dữ liệu
if ($manhinh->AddManHinh()) {
    echo json_encode(array('message' => 'Thêm màn hình thành công.'));
} else {
    echo json_encode(array('message' => 'Thêm màn hình thất bại.'));
}
?>
