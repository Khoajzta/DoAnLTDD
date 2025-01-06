<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: PUT');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type,Access-Control-Allow-Methods,Authorization,X-Requested-With');

include_once('../../config/database.php');
include_once('../../model/manhinh.php'); // Thay Rom thành ManHinh

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp ManHinh với kết nối PDO
$manhinh = new ManHinh($conn);

// Lấy dữ liệu từ yêu cầu PUT
$data = json_decode(file_get_contents("php://input"));

// Kiểm tra nếu dữ liệu đầu vào tồn tại
if (!isset($data->MaManHinh)) {
    echo json_encode(array('message' => 'Mã màn hình không tồn tại.'));
    die();
}

// Gán dữ liệu từ yêu cầu PUT vào đối tượng
$manhinh->MaManHinh = $data->MaManHinh;
$manhinh->KichThuoc = $data->KichThuoc;
$manhinh->DoPhanGiai = $data->DoPhanGiai;
$manhinh->TanSoQuet = $data->TanSoQuet;
$manhinh->LoaiTamNen = $data->LoaiTamNen;

// Cập nhật thông tin màn hình
if ($manhinh->updateManHinh()) {
    echo json_encode(array('message' => 'Màn hình cập nhật thành công.'));
} else {
    echo json_encode(array('message' => 'Cập nhật màn hình thất bại.'));
}
?>
