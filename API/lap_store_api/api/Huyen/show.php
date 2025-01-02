<?php
    header('Access-Control-Allow-Origin:*');
    header('Content-Type: application/json');

    include_once('../../config/database.php');
    include_once('../../model/huyen.php');

    // Tạo đối tượng database và kết nối
    $database = new database();
    $conn = $database->Connect(); // Lấy kết nối PDO

    // Khởi tạo lớp Khachhang với kết nối PDO
    $huyen = new Huyen($conn);

    $huyen->MaHuyen = isset($_GET['id']) ? $_GET['id'] : die();

    $huyen->GetHuyenById();


    $huyen_item = array(
        'MaHuyen'=> $huyen->MaHuyen,
        'TenHuyen'=> $huyen->TenHuyen,
        'MaTinh'=> $huyen->MaTinh,
    );

    print_r(json_encode($huyen_item,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
?>