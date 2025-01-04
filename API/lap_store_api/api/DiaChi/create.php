<?php
    header('Access-Control-Allow-Origin:*');
    header('Content-Type: application/json');
    header('Access-Control-Allow-Methods: POST');
    header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type,Access-Control-Allow-Methods,Authorization,X-Requested-With');
    
    include_once('../../config/database.php');
    include_once('../../model/diachi.php');

    // Tạo đối tượng database và kết nối
    $database = new database();
    $conn = $database->Connect(); // Lấy kết nối PDO

    // Khởi tạo lớp diachi với kết nối PDO
    $diachi = new DiaChi($conn);

    $data = json_decode(file_get_contents("php://input"));
    $diachi->MaDiaChi = $data->MaDiaChi;
    $diachi->MaTinh = $data->MaTinh;
    $diachi->MaHuyen = $data->MaHuyen;
    $diachi->MaXa = $data->MaXa;
    $diachi->SoNha = $data->SoNha;


    if($diachi->AddDiaChi()){
        echo json_encode(array('message','Dia Chi Created'));
    }
    else{
        echo json_encode(array('message','Dia Chi Not Created'));
    }

?>