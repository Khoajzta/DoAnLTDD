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

// Lấy tất cả khách hàng
$getAllTinh = $tinh->GetAllTinh();

$num = $getAllTinh->rowCount();

if($num>0){
    $tinh_array =[];
    $tinh_array['tinh'] =[];

    while($row = $getAllTinh->fetch(PDO::FETCH_ASSOC)){
        extract($row);

        $tinh_item = array(
            'MaTinh'=> $MaTinh,
            'TenTinh'=> $TenTinh
        );
        array_push($tinh_array['tinh'],$tinh_item);
    }
    echo json_encode($tinh_array, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
}
?>