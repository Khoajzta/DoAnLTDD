<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: DELETE');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type, Access-Control-Allow-Methods, Authorization, X-Requested-With');

include_once('../../config/database.php');
include_once('../../model/manhinh.php'); // Include the correct model for "manhinh"

// Create a database object and connect
$database = new Database();
$conn = $database->Connect(); // Get PDO connection

// Initialize the CardManHinh class with the PDO connection
$cardmanhinh = new CardManHinh($conn);

// Get data from the DELETE request body
$data = json_decode(file_get_contents("php://input"));

// Check if MaLoaiCard is provided
if (!isset($data->MaLoaiCard)) {
    echo json_encode(array('message' => 'Dữ liệu không hợp lệ.'));
    exit();
}

// Assign value to MaLoaiCard property
$cardmanhinh->MaLoaiCard = $data->MaLoaiCard;

// Perform deletion
if ($cardmanhinh->deleteCardManHinh()) {
    echo json_encode(array('message' => 'Xóa card màn hình thành công.'));
} else {
    echo json_encode(array('message' => 'Xóa card màn hình thất bại.'));
}
?>
