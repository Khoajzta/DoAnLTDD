<?php
    header('Access-Control-Allow-Origin:*');
    header('Content-Type: application/json');
    header('Access-Control-Allow-Methods: PUT');
    header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type,Access-Control-Allow-Methods,Authorization,X-Requested-With');
    
    include_once('../../config/database.php');
    include_once('../../model/huyen.php');

    // Tạo đối tượng database và kết nối
    $database = new database();
    $conn = $database->Connect(); // Lấy kết nối PDO

    // Khởi tạo lớp Khachhang với kết nối PDO
    $huyen = new Huyen($conn);

    $data = json_decode(file_get_contents("php://input"));

    $huyen->MaHuyen = $data->MaHuyen;
    $huyen->TenHuyen = $data->TenHuyen;
    $huyen->MaTinh = $data->MaTinh;

    if($huyen->UpdateHuyen()){
        echo json_encode(array('message','Huyen Updated'));
    }
    else{
        echo json_encode(array('message','Huyen Not Updated'));
    }

?>