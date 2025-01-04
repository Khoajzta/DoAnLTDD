<?php
header('Access-Control-Allow-Origin:*');
header('Content-Type: application/json');

include_once('../../config/database.php');
include_once('../../model/sanpham.php');

// Tạo đối tượng database và kết nối
$database = new database();
$conn = $database->Connect(); // Lấy kết nối PDO

// Khởi tạo lớp SanPham với kết nối PDO
$sanpham = new SanPham($conn);

// Kiểm tra và lấy giá trị search từ query string
$searchTerm = isset($_GET['search']) ? $_GET['search'] : die(json_encode(["message" => "Từ khóa tìm kiếm không được cung cấp."]));

// Lấy danh sách sản phẩm theo từ khóa tìm kiếm
$sanpham->TenSanPham = $searchTerm;
$sanpham->MoTa = $searchTerm;
$getSanPhamBySearch = $sanpham->GetSanPhamBySearch($searchTerm);

// Kiểm tra nếu có sản phẩm
if ($getSanPhamBySearch->rowCount() > 0) {
    $sanphamBySearch_array = [];
    $sanphamBySearch_array['sanpham'] = [];

    while ($row = $getSanPhamBySearch->fetch(PDO::FETCH_ASSOC)) {
        extract($row);

        $sanpham_item = array(
            'MaSanPham'=> $MaSanPham,
            'TenSanPham'=> $TenSanPham,
            'MaLoaiSanPham'=> $MaLoaiSanPham,
            'MaHangSanXuat'=> $MaHangSanXuat,
            'MaCPU'=> $TenHangCPU,
            'MaRAM'=> $DungLuongRAM,
            'MaCardDoHoa'=> $TenCard,
            'MaROM'=> $DungLuongROM,
            'MaManHinh'=> $DoPhanGiai,
            'MaMauSac'=> $MaMauSac,
            'Gia'=> $Gia,
            'SoLuong'=> $SoLuong,
            'MoTa'=> $MoTa,
            'HinhAnh'=> $TenHinhAnh,
            'TrangThai'=> $TrangThai,
        );
        array_push($sanphamBySearch_array['sanpham'], $sanpham_item);
    }
    echo json_encode($sanphamBySearch_array, JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES | JSON_PRETTY_PRINT);
} else {
    echo json_encode(["message" => "Không tìm thấy sản phẩm nào với từ khóa tìm kiếm: " . $searchTerm]);
}
?>
