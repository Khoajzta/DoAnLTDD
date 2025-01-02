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

// Lấy tất cả khách hàng
$getAllHuyen = $huyen->GetAllHuyen();

$num = $getAllHuyen->rowCount();

if($num>0){
    $huyen_array =[];
    $huyen_array['huyen'] =[];

    while($row = $getAllHuyen->fetch(PDO::FETCH_ASSOC)){
        extract($row);

        $huyen_item = array(
            'MaHuyen'=> $MaHuyen,
            'TenHuyen'=> $TenHuyen,
            'MaTinh'=> $MaTinh,
        );
        array_push($huyen_array['huyen'],$huyen_item);
    }
    echo json_encode($huyen_array, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
}
?>