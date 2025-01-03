<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json; charset=UTF-8');

include_once('../../config/database.php');
include_once('../../model/chitiethoadonban.php');  // Sử dụng lớp ChiTietHoaDonBan

// Tạo đối tượng database và kết nối
$database = new Database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp ChiTietHoaDonBan với kết nối PDO
$chitiet = new ChiTietHoaDonBan($conn);

// Lấy tất cả dữ liệu hóa đơn bán
$getAllHoaDon = $chitiet->getAllDetails(); // Sử dụng phương thức lấy tất cả hóa đơn
$num = $getAllHoaDon->rowCount();

// Kiểm tra nếu có dữ liệu
if ($num > 0) {
    $hoadon_array = [];
    $hoadon_array['hoadonban'] = [];

    while ($row = $getAllHoaDon->fetch(PDO::FETCH_ASSOC)) {
        extract($row);

        // Lấy thông tin chi tiết của từng hóa đơn
        $chitiet->MaHoaDonBan = $MaHoaDonBan;
        $getAllDetails = $chitiet->getDetailById(); // Lấy chi tiết của hóa đơn

        // Tạo mảng chi tiết hóa đơn
        $chitiet_array = [];
        while ($detail = $getAllDetail->fetch(PDO::FETCH_ASSOC)) {
            $chitiet_item = array(
                'MaChiTietHoaDonBan' => $detail['MaChiTietHoaDonBan'],
                'MaSanPham' => $detail['MaSanPham'],
                'SoLuong' => $detail['SoLuong'],
                'DonGia' => $detail['DonGia'],
                'ThanhTien' => $detail['ThanhTien'],
                'GiamGia' => $detail['GiamGia']
            );
            array_push($chitiet_array, $chitiet_item);
        }

        // Tạo mảng phản hồi cho hóa đơn bán
        $hoadon_item = array(
            'MaHoaDonBan' => $MaHoaDonBan,
            'MaKhachHang' => $MaKhachHang,
            'NgayDatHang' => $NgayDatHang,
            'MaDiaChi' => $MaDiaChi,
            'TongTien' => $TongTien,
            'TrangThai' => $TrangThai,
            'ChiTietHoaDon' => $chitiet_array // Thêm chi tiết hóa đơn vào mảng
        );

        array_push($hoadon_array['hoadonban'], $hoadon_item);
    }

    // Xuất dữ liệu dạng JSON
    echo json_encode($hoadon_array, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
} else {
    // Trường hợp không có dữ liệu
    echo json_encode(
        array('message' => 'Không có hóa đơn bán nào.')
    );
}
?>
