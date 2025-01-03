<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json; charset=UTF-8');

include_once('../../config/database.php');
include_once('../../model/manhinh.php'); // Include the correct model for "manhinh"

// Create a database object and connect
$database = new Database();
$conn = $database->Connect(); // Get PDO connection

// Initialize the CardManHinh class with the PDO connection
$cardmanhinh = new CardManHinh($conn);

// Get all screen cards
$getAllCardManHinh = $cardmanhinh->getAllCardManHinh();

$num = $getAllCardManHinh->rowCount();

if ($num > 0) {
    $cardmanhinh_array = [];
    $cardmanhinh_array['cardmanhinh'] = [];

    while ($row = $getAllCardManHinh->fetch(PDO::FETCH_ASSOC)) {
        $cardmanhinh_item = array(
            'MaLoaiCard' => $row['MaLoaiCard'],
            'TenCard' => $row['TenCard'],
            'MoTa' => $row['MoTa']
        );
        array_push($cardmanhinh_array['cardmanhinh'], $cardmanhinh_item);
    }

    // Output data as JSON
    echo json_encode($cardmanhinh_array, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
} else {
    // If no data is found
    echo json_encode(array('message' => 'Không có card màn hình nào được tìm thấy.'), JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
}
?>
