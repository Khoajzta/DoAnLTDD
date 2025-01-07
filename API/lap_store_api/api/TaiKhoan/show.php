<?php
    header('Access-Control-Allow-Origin:*');
    header('Content-Type: application/json');

    include_once('../../config/database.php');
    include_once('../../model/taikhoan.php');

    // Tạo đối tượng database và kết nối
    $database = new database();
    $conn = $database->Connect(); // Lấy kết nối PDO

    // Khởi tạo lớp Khachhang với kết nối PDO
    $taikhoan = new TaiKhoan($conn);

    $taikhoan->TenTaiKhoan = isset($_GET['tentaikhoan']) ? $_GET['tentaikhoan'] : die();

    $taikhoan->GetTaiKhoanByUsername();


    $taikhoan_item = array(
        'TenTaiKhoan'=> $taikhoan->TenTaiKhoan,
        'MaNguoiDung'=> $taikhoan->MaKhachHang,
    );

    print_r(json_encode($taikhoan_item,JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT));
?>