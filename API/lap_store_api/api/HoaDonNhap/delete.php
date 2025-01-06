<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: DELETE');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type, Access-Control-Allow-Methods, Authorization, X-Requested-With');

include_once('../../config/database.php');
include_once('../../model/hoadonnhap.php'); // Include the correct model for "hoadonnhap"

// Create a database object and connect
$database = new Database();
$conn = $database->Connect(); // Get PDO connection

// Initialize the Hoadonnhap class with the PDO connection
$hoadonnhap = new Hoadonnhap($conn);

// Get data from the DELETE request body
$data = json_decode(file_get_contents("php://input"));

// Check if MaHDNNhap is provided
if (!isset($data->MaHDNNhap)) {
    echo json_encode(array('message' => 'Dữ liệu không hợp lệ.'));
    exit();
}

// Assign value to MaHDNNhap property
$hoadonnhap->MaHDNNhap = $data->MaHDNNhap;

// Perform deletion
if ($hoadonnhap->deleteHoadonnhap()) {
    echo json_encode(array('message' => 'Xóa hóa đơn nhập thành công.'));
} else {
    echo json_encode(array('message' => 'Xóa hóa đơn nhập thất bại.'));
}
?>
