<?php
    header('Access-Control-Allow-Origin:*');
    header('Content-Type: application/json');
    header('Access-Control-Allow-Methods: PUT');
    header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type,Access-Control-Allow-Methods,Authorization,X-Requested-With');
    
    include_once('../../config/database.php');
    include_once('../../model/cpu.php');

    // Tạo đối tượng database và kết nối
    $database = new database();
    $conn = $database->Connect(); // Lấy kết nối PDO

    // Khởi tạo lớp diachi với kết nối PDO
    $cpu = new cpu($conn);

    $data = json_decode(file_get_contents("php://input"));

    $cpu->MaCPU = $data->MaCPU;
    $cpu->TenHangCPU = $data->TenHangCPU;


    if($cpu->UpdateCPU()){
        echo json_encode(array('message','CPU Updated'));
    }
    else{
        echo json_encode(array('message','CPU Not Updated'));
    }

?>