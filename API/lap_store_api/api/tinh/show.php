<?php
    header('Access-Control-Allow-Origin:*');
    header('Content-Type: application/json');

    include_once('../../config/database.php');
    include_once('../../model/tinh.php');

    // Tạo đối tượng database và kết nối
    $database = new database();
    $conn = $database->Connect(); // Lấy kết nối PDO

    // Khởi tạo lớp Khachhang với kết nối PDO
    $tinh = new tinh($conn);

    $tinh->MaTinh = isset($_GET['id']) ? $_GET['id'] : die();

    $tinh->GetTinhById();


    $tinh_item = array(
        'MaTinh'=> $tinh->MaTinh,
        'TenTinh'=> $tinh->TenTinh,
    );

    print_r(json_encode($tinh_item,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
?>