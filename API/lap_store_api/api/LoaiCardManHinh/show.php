<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');

include_once('../../config/database.php');
include_once('../../model/manhinh.php'); // Include the correct model for "manhinh"

// Create a database object and connect
$database = new Database();
$conn = $database->Connect(); // Get PDO connection

// Initialize the CardManHinh class with the PDO connection
$cardmanhinh = new CardManHinh($conn);

// Get MaLoaiCard from the URL (GET method)
$cardmanhinh->MaLoaiCard = isset($_GET['id']) ? $_GET['id'] : die(json_encode(array('message' => 'Mã card màn hình không tồn tại.')));

// Get detailed information of the screen card
$cardmanhinh->getCardManHinhById();

// Check if the screen card exists
if ($cardmanhinh->MaLoaiCard) {
    // Create the response array
    $cardmanhinh_item = array(
        'MaLoaiCard' => $cardmanhinh->MaLoaiCard,
        'TenCard' => $cardmanhinh->TenCard,
        'MoTa' => $cardmanhinh->MoTa
    );

    // Output data as JSON
    echo json_encode($cardmanhinh_item, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
} else {
    // If not found
    echo json_encode(array('message' => 'Card màn hình không tồn tại.'));
}
?>
