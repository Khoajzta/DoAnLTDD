<?php
header('Access-Control-Allow-Origin:*');
header('Content-Type: application/json');

include_once('../../config/database.php');
include_once('../../model/diachi.php');

// Tạo đối tượng database và kết nối
$database = new database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp Khachhang với kết nối PDO
$diachi = new DiaChi($conn);

// Lấy tất cả khách hàng
$getAllDiaChi = $diachi->GetAllDiaChi();

$num = $getAllDiaChi->rowCount();

if($num>0){
    $diachi_array =[];
    $diachi_array['diachi'] =[];

    while($row = $getAllDiaChi->fetch(PDO::FETCH_ASSOC)){
        extract($row);

        $diachi_item = array(
            'MaDiaChi'=> $MaDiaChi,
            'MaTinh'=> $MaTinh,
            'Email'=> $Email,
            'SoDienThoai'=> $SoDienThoai,
            'MaDiaChi'=> $MaDiaChi
        );
        array_push($diachi_array['khachhang'],$diachi_item);
    }
    echo json_encode($diachi_array, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
}
?>