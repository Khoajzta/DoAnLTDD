<?php
    header('Access-Control-Allow-Origin:*');
    header('Content-Type: application/json');

    include_once('../../config/database.php');
    include_once('../../model/cpu.php');

    // Tạo đối tượng database và kết nối
    $database = new database();
    $conn = $database->Connect(); // Lấy kết nối PDO

    // Khởi tạo lớp diachi với kết nối PDO
    $cpu = new cpu($conn);

    $cpu->MaCPU = isset($_GET['id']) ? $_GET['id'] : die();

    $cpu->GetCPUById();


    $cpu_item = array(
        'MaCPU'=> $cpu->MaCPU,
        'TenHangCPU'=> $cpu->TenHangCPU
    );

    print_r(json_encode($cpu_item,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
?>