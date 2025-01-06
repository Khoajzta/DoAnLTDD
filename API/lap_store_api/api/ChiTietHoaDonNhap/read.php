<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json; charset=UTF-8');

include_once('../../config/database.php');
include_once('../../model/cthoadonnhap.php'); // Đường dẫn đến model CTHoaDonNhap

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp CTHoaDonNhap với kết nối PDO
$ctHDNhap = new CTHoaDonNhap($conn);

// Lấy tất cả chi tiết hóa đơn nhập
$getAllCTHoaDonNhap = $ctHDNhap->getAllCTHoaDonNhap();

$num = $getAllCTHoaDonNhap->rowCount();

if ($num > 0) {
    $ctHDNhap_array = [];
    $ctHDNhap_array['cthoadonnhap'] = [];

    while ($row = $getAllCTHoaDonNhap->fetch(PDO::FETCH_ASSOC)) {
        $ctHDNhap_item = array(
            'MaCTHoaDonNhap' => $row['MaCTHoaDonNhap'],
            'MaHDNNhap' => $row['MaHDNNhap'],
            'MaSanPham' => $row['MaSanPham'],
            'SoLuong' => $row['SoLuong'],
            'DonGia' => $row['DonGia'],
            'ThanhTien' => $row['ThanhTien'],
            'TrangThai' => $row['TrangThai']
        );
        array_push($ctHDNhap_array['cthoadonnhap'], $ctHDNhap_item);
    }

    // Xuất dữ liệu dạng JSON
    echo json_encode($ctHDNhap_array, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
} else {
    // Nếu không có dữ liệu
    echo json_encode(array('message' => 'Không có chi tiết hóa đơn nhập nào được tìm thấy.'), JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
}
?>
