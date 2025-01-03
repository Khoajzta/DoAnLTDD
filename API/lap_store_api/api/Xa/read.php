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

// Lấy tất cả khách hàng
$getAllXa = $xa->GetAllXa();

$num = $getAllXa->rowCount();

if($num>0){
    $xa_array =[];
    $xa_array['xa'] =[];

    while($row = $getAllXa->fetch(PDO::FETCH_ASSOC)){
        extract($row);

        $xa_item = array(
            'MaXa'=> $MaXa,
            'TenXa'=> $TenXa,
            'MaHuyen'=> $MaHuyen,
        );
        array_push($xa_array['xa'],$xa_item);
    }
    echo json_encode($xa_array, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
}
?>