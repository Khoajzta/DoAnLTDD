<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json; charset=UTF-8');

include_once('../../config/database.php');
include_once('../../model/hoadonnhap.php'); // Include the correct model for "hoadonnhap"

// Create a database object and connect
$database = new Database();
$conn = $database->Connect(); // Get PDO connection

// Initialize the Hoadonnhap class with the PDO connection
$hoadonnhap = new Hoadonnhap($conn);

// Get all invoices
$getAllHoadonnhap = $hoadonnhap->getAllHoadonnhap();

$num = $getAllHoadonnhap->rowCount();

if ($num > 0) {
    $hoadonnhap_array = [];
    $hoadonnhap_array['hoadonnhap'] = [];

    while ($row = $getAllHoadonnhap->fetch(PDO::FETCH_ASSOC)) {
        $hoadonnhap_item = array(
            'MaHDNNhap' => $row['MaHDNNhap'],
            'MaNCC' => $row['MaNCC'],
            'NgayNhap' => $row['NgayNhap'],
            'ThanhTien' => $row['ThanhTien'],
            'TrangThai' => $row['TrangThai']
        );
        array_push($hoadonnhap_array['hoadonnhap'], $hoadonnhap_item);
    }

    // Output data as JSON
    echo json_encode($hoadonnhap_array, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
} else {
    // If no data is found
    echo json_encode(array('message' => 'Không có hóa đơn nhập nào được tìm thấy.'), JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
}
?>
