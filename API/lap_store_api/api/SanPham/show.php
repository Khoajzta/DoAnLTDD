<?php
    header('Access-Control-Allow-Origin:*');
    header('Content-Type: application/json');

    include_once('../../config/database.php');
    include_once('../../model/sanpham.php');

    // Tạo đối tượng database và kết nối
    $database = new database();
    $conn = $database->Connect(); // Lấy kết nối PDO

    // Khởi tạo lớp Khachhang với kết nối PDO
    $sanpham = new SanPham($conn);

    $sanpham->MaSanPham = isset($_GET['id']) ? $_GET['id'] : die();

    $sanpham->GetSanPhamById();


    $sanpham_item = array(
        'MaSanPham' => $sanpham->MaSanPham,
        'TenSanPham' => $sanpham->TenSanPham,
        'MaLoaiSanPham' => $sanpham->MaLoaiSanPham,
        'CPU' => $sanpham->CPU,
        'RAM' => $sanpham->RAM,
        'CardManHinh' => $sanpham->CardManHinh,
        'SSD' => $sanpham->SSD,
        'ManHinh' => $sanpham->ManHinh,
        'MaMauSac' => $sanpham->MaMauSac,
        'Gia' => $sanpham->Gia,
        'SoLuong' => $sanpham->SoLuong,
        'MoTa' => $sanpham->MoTa,
        'HinhAnh' => $sanpham->HinhAnh,
        'TrangThai' => $sanpham->TrangThai
    );
    print_r(json_encode($sanpham_item, JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES | JSON_PRETTY_PRINT));


?>