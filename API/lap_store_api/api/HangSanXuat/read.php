<?php
header('Access-Control-Allow-Origin:*');
header('Content-Type: application/json');

include_once('../../config/database.php');
include_once('../../model/hangsanxuat.php');

// Tạo đối tượng database và kết nối
$database = new database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp Khachhang với kết nối PDO
$hangsanxuat = new HangSanXuat($conn);

// Lấy tất cả khách hàng
$getAllHangSanXuat = $hangsanxuat->GetAllHangSanXuat();

$num = $getAllHangSanXuat->rowCount();

if($num>0){
    $hangsanxuat_array =[];
    $hangsanxuat_array['hangsanxuat'] =[];

    while($row = $getAllHangSanXuat->fetch(PDO::FETCH_ASSOC)){
        extract($row);

        $hangsanxuat_item = array(
            'MaHangSanXuat'=> $MaHangSanXuat,
            'TenHangSanXuat'=> $TenHangSanXuat,
            'QuocGia'=> $QuocGia,
        );
        array_push($hangsanxuat_array['hangsanxuat'],$hangsanxuat_item);
    }
    echo json_encode($hangsanxuat_array, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
}
?>