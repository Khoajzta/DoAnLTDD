<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: PUT');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type, Access-Control-Allow-Methods, Authorization, X-Requested-With');

include_once('../../config/database.php');
include_once('../../model/hoadonnhap.php'); // Use the correct model for "hoadonnhap"

// Create a database object and connect
$database = new Database();
$conn = $database->Connect(); // Get PDO connection

// Initialize the Hoadonnhap class with the PDO connection
$hoadonnhap = new Hoadonnhap($conn);

// Get data from the PUT request
$data = json_decode(file_get_contents("php://input"));

// Check if the required data is present
if (!isset($data->MaHDNNhap)) {
    echo json_encode(array('message' => 'Mã hóa đơn nhập không tồn tại.'));
    die();
}

// Assign data from the PUT request to the object
$hoadonnhap->MaHDNNhap = $data->MaHDNNhap;
$hoadonnhap->MaNCC = $data->MaNCC;
$hoadonnhap->NgayNhap = $data->NgayNhap;
$hoadonnhap->ThanhTien = $data->ThanhTien;
$hoadonnhap->TrangThai = $data->TrangThai;

// Update the invoice information
if ($hoadonnhap->updateHoadonnhap()) {
    echo json_encode(array('message' => 'Cập nhật hóa đơn nhập thành công.'));
} else {
    echo json_encode(array('message' => 'Cập nhật hóa đơn nhập thất bại.'));
}
?>
