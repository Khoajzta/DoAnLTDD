<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');

// Kết nối đến database và model
include_once('../../config/database.php');
include_once('../../model/chitiethoadonban.php');  // Sử dụng lớp ChiTietHoaDonBan

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp ChiTietHoaDonBan với kết nối PDO
$chitiet = new ChiTietHoaDonBan($conn);

// Lấy MaHoaDonBan từ URL (phương thức GET)
$chitiet->MaHoaDonBan = isset($_GET['id']) ? $_GET['id'] : die(json_encode(array('message' => 'Mã hóa đơn không tồn tại.')));

// Lấy thông tin chi tiết hóa đơn bán
$getAllDetails = $chitiet->getDetailById(); // Phương thức lấy chi tiết hóa đơn

// Kiểm tra nếu có dữ liệu
$num = $getAllDetailById->rowCount();
if ($num > 0) {
    $chitiet_array = [];
    $chitiet_array['chitiethoadonban'] = [];

    while ($row = $getAllDetailById->fetch(PDO::FETCH_ASSOC)) {
        extract($row);

        $chitiet_item = array(
            'MaChiTietHoaDonBan' => $MaChiTietHoaDonBan,
            'MaHoaDonBan' => $MaHoaDonBan,
            'MaSanPham' => $MaSanPham,
            'SoLuong' => $SoLuong,
            'DonGia' => $DonGia,
            'ThanhTien' => $ThanhTien,
            'GiamGia' => $GiamGia
        );

        array_push($chitiet_array['chitiethoadonban'], $chitiet_item);
    }

    // Xuất dữ liệu dạng JSON
    echo json_encode($chitiet_array, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
} else {
    // Nếu không có chi tiết hóa đơn bán
    echo json_encode(array('message' => 'Không có ChiTietHoaDon cho hóa đơn này.'));
}
?>
