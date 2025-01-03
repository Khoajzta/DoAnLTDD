<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: PUT');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type, Access-Control-Allow-Methods, Authorization, X-Requested-With');

include_once('../../config/database.php');
include_once('../../model/manhinh.php'); // Use the correct model for "manhinh"

// Create a database object and connect
$database = new Database();
$conn = $database->Connect(); // Get PDO connection

// Initialize the CardManHinh class with the PDO connection
$cardmanhinh = new CardManHinh($conn);

// Get data from the PUT request
$data = json_decode(file_get_contents("php://input"));

// Check if the required data is present
if (!isset($data->MaLoaiCard)) {
    echo json_encode(array('message' => 'Mã card màn hình không tồn tại.'));
    die();
}

// Assign data from the PUT request to the object
$cardmanhinh->MaLoaiCard = $data->MaLoaiCard;
$cardmanhinh->TenCard = $data->TenCard;
$cardmanhinh->MoTa = $data->MoTa;

// Update screen card information
if ($cardmanhinh->updateCardManHinh()) {
    echo json_encode(array('message' => 'Card màn hình cập nhật thành công.'));
} else {
    echo json_encode(array('message' => 'Cập nhật card màn hình thất bại.'));
}
?>

