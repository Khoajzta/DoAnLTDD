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

// Lấy tất cả diachi
$getAllCPU = $cpu->GetAllCPU();

$num = $getAllCPU->rowCount();

if($num>0){
    $cpu_array =[];
    $cpu_array['cpu'] =[];

    while($row = $getAllCPU->fetch(PDO::FETCH_ASSOC)){
        extract($row);

        $cpu_item = array(
            'MaCPU'=> $MaCPU,
            'TenHangCPU'=> $TenHangCPU
        );
        array_push($cpu_array['cpu'],$cpu_item);
    }
    echo json_encode($cpu_array, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
}
?>