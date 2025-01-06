<?php
header('Access-Control-Allow-Origin:*');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type, Access-Control-Allow-Methods, Authorization, X-Requested-With');

include_once('../../config/database.php');
include_once('../../model/hoadonnhap.php'); // Include the correct model for "hoadonnhap"

// Create a database object and connect
$database = new Database();
$conn = $database->Connect(); // Get PDO connection

// Initialize the Hoadonnhap class with the PDO connection
$hoadonnhap = new Hoadonnhap($conn);

// Get data from the POST request
$data = json_decode(file_get_contents("php://input"));

// Check if the required data is present
if (isset($data->MaHDNNhap) && isset($data->MaNCC) && isset($data->NgayNhap) && isset($data->ThanhTien) && isset($data->TrangThai)) {
    // Assign data from POST request to the object
    $hoadonnhap->MaHDNNhap = $data->MaHDNNhap;
    $hoadonnhap->MaNCC = $data->MaNCC;
    $hoadonnhap->NgayNhap = $data->NgayNhap;
    $hoadonnhap->ThanhTien = $data->ThanhTien;
    $hoadonnhap->TrangThai = $data->TrangThai;

    // Add invoice to the database
    if ($hoadonnhap->AddHoadonnhap()) {
        echo json_encode(array('message' => 'Thêm hóa đơn nhập thành công.'));
    } else {
        echo json_encode(array('message' => 'Thêm hóa đơn nhập thất bại.'));
    }
} else {
    echo json_encode(array('message' => 'Dữ liệu không hợp lệ. Thiếu trường yêu cầu.'));
}
?>

