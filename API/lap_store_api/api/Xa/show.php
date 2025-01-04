<?php
    header('Access-Control-Allow-Origin:*');
    header('Content-Type: application/json');

    include_once('../../config/database.php');
    include_once('../../model/xa.php');

    // Tạo đối tượng database và kết nối
    $database = new database();
    $conn = $database->Connect(); // Lấy kết nối PDO

    // Khởi tạo lớp Khachhang với kết nối PDO
    $xa = new Xa($conn);

    $xa->MaXa = isset($_GET['id']) ? $_GET['id'] : die();

    $xa->GetXaById();


    $xa_item = array(
        'MaXa'=> $xa->MaXa,
        'TenXa'=> $xa->TenXa,
        'MaHuyen'=> $xa->MaHuyen,
    );

    print_r(json_encode($xa_item,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
?>