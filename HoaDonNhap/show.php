<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');

include_once('../../config/database.php');
include_once('../../model/hoadonnhap.php'); // Include the correct model for "hoadonnhap"

// Create a database object and connect
$database = new Database();
$conn = $database->Connect(); // Get PDO connection

// Initialize the Hoadonnhap class with the PDO connection
$hoadonnhap = new Hoadonnhap($conn);

// Get MaHDNNhap from the URL (GET method)
$hoadonnhap->MaHDNNhap = isset($_GET['id']) ? $_GET['id'] : die(json_encode(array('message' => 'Mã hóa đơn nhập không tồn tại.')));

// Get detailed information of the invoice
$hoadonnhap->getHoadonnhapById();

// Check if the invoice exists
if ($hoadonnhap->MaHDNNhap) {
    // Create the response array
    $hoadonnhap_item = array(
        'MaHDNNhap' => $hoadonnhap->MaHDNNhap,
        'MaNCC' => $hoadonnhap->MaNCC,
        'NgayNhap' => $hoadonnhap->NgayNhap,
        'ThanhTien' => $hoadonnhap->ThanhTien,
        'TrangThai' => $hoadonnhap->TrangThai
    );

    // Output data as JSON
    echo json_encode($hoadonnhap_item, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
} else {
    // If not found
    echo json_encode(array('message' => 'Hóa đơn nhập không tồn tại.'));
}
?>
