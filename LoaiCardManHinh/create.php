<?php
header('Access-Control-Allow-Origin:*');
header('Content-Type: application/json');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type, Access-Control-Allow-Methods, Authorization, X-Requested-With');

include_once('../../config/database.php');
include_once('../../model/manhinh.php'); // Include the correct model for "manhinh"

// Create a database object and connect
$database = new Database();
$conn = $database->Connect(); // Get PDO connection

// Initialize the CardManHinh class with the PDO connection
$cardmanhinh = new CardManHinh($conn);

// Get data from the POST request
$data = json_decode(file_get_contents("php://input"));

// Check if the required data is present
if (isset($data->MaLoaiCard) && isset($data->TenCard) && isset($data->MoTa)) {
    // Assign data from POST request to the object
    $cardmanhinh->MaLoaiCard = $data->MaLoaiCard;
    $cardmanhinh->TenCard = $data->TenCard;
    $cardmanhinh->MoTa = $data->MoTa;

    // Add screen card to the database
    if ($cardmanhinh->AddManHinh()) {
        echo json_encode(array('message' => 'Thêm card màn hình thành công.'));
    } else {
        echo json_encode(array('message' => 'Thêm card màn hình thất bại.'));
    }
} else {
    echo json_encode(array('message' => 'Dữ liệu không hợp lệ. Thiếu trường yêu cầu.'));
}
?>
