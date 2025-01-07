<?php
    header('Access-Control-Allow-Origin:*');
    header('Content-Type: application/json');

    include_once('../../config/database.php');
    include_once('../../model/diachi.php');

    // Tạo đối tượng database và kết nối
    $database = new database();
    $conn = $database->Connect(); // Lấy kết nối PDO

    // Khởi tạo lớp diachi với kết nối PDO
    $diachi = new DiaChi($conn);

    $diachi->MaDiaChi = isset($_GET['id']) ? $_GET['id'] : die();

    $diachi->GetDiaChiById();


    $diachi_item = array(
        'MaDiaChi'=> $diachi->MaDiaChi,
        'ThongTinDiaChi'=> $diachi->ThongTinDiaChi
    );

    print_r(json_encode($diachi_item,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
?>