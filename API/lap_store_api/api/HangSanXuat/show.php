<?php
    header('Access-Control-Allow-Origin:*');
    header('Content-Type: application/json');

    include_once('../../config/database.php');
    include_once('../../model/hangsanxuat.php');

    // Tạo đối tượng database và kết nối
    $database = new database();
    $conn = $database->Connect(); // Lấy kết nối PDO

    // Khởi tạo lớp Khachhang với kết nối PDO
    $hangsanxuat = new HangSanXuat($conn);

    $hangsanxuat->MaHangSanXuat = isset($_GET['id']) ? $_GET['id'] : die();

    $hangsanxuat->GetHangSanXuatById();


    $hangsanxuat_item = array(
        'MaHangSanXuat'=> $hangsanxuat->MaHangSanXuat,
        'TenHangSanXuat'=> $hangsanxuat->TenHangSanXuat,
        'QuocGia'=> $hangsanxuat->QuocGia,
    );

    print_r(json_encode($hangsanxuat_item,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
?>