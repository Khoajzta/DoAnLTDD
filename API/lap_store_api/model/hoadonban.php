<?php
class HoaDonBan {
    private $conn;

    // Thuộc tính liên quan đến hóa đơn bán
    public $MaHoaDonBan;
    public $MaKhachHang;
    public $NgayDatHang;
    public $MaDiaChi;
    public $TongTien;
    public $TrangThai;

    // Kết nối cơ sở dữ liệu
    public function __construct($database) {
        $this->conn = $database;
    }

    // Phương thức lấy tất cả hóa đơn bán
    public function getAllHoaDonBan() {
        try {
            $query = "SELECT * FROM hoadonban ORDER BY MaHoaDonBan DESC";
            $stmt = $this->conn->prepare($query);
            $stmt->execute();
            return $stmt;
        } catch (PDOException $e) {
            echo "Lỗi: " . $e->getMessage();
            return null;
        }
    }

    // Phương thức lấy hóa đơn bán theo ID
    public function getHoaDonBanById() {
        try {
            $query = "SELECT * FROM hoadonban WHERE MaHoaDonBan = ? LIMIT 1";
            $stmt = $this->conn->prepare($query);
            $stmt->bindParam(1, $this->MaHoaDonBan, PDO::PARAM_INT);
            $stmt->execute();

            $row = $stmt->fetch(PDO::FETCH_ASSOC);
            if ($row) {
                
                $this->MaKhachHang = $row['MaKhachHang'];
                $this->NgayDatHang = $row['NgayDatHang'];
                $this->MaDiaChi = $row['MaDiaChi'];
                $this->TongTien = $row['TongTien'];
                $this->TrangThai = $row['TrangThai'];
            }
        } catch (PDOException $e) {
            echo "Lỗi: " . $e->getMessage();
        }
    }

    // Phương thức thêm hóa đơn bán
    public function addHoaDonBan() {
        try {
            $query = "INSERT INTO hoadonban (MaKhachHang, NgayDatHang, MaDiaChi, TongTien, TrangThai) 
                      VALUES (:MaKhachHang, :NgayDatHang, :MaDiaChi, :TongTien, :TrangThai)";
            $stmt = $this->conn->prepare($query);

            // Làm sạch dữ liệu đầu vào
            $this->MaKhachHang = htmlspecialchars(strip_tags($this->MaKhachHang));
            $this->NgayDatHang = htmlspecialchars(strip_tags($this->NgayDatHang));
            $this->MaDiaChi = htmlspecialchars(strip_tags($this->MaDiaChi));
            $this->TongTien = htmlspecialchars(strip_tags($this->TongTien));
            $this->TrangThai = htmlspecialchars(strip_tags($this->TrangThai));

            // Gắn tham số
            $stmt->bindParam(':MaKhachHang', $this->MaKhachHang);
            $stmt->bindParam(':NgayDatHang', $this->NgayDatHang);
            $stmt->bindParam(':MaDiaChi', $this->MaDiaChi);
            $stmt->bindParam(':TongTien', $this->TongTien);
            $stmt->bindParam(':TrangThai', $this->TrangThai);

            return $stmt->execute();
        } catch (PDOException $e) {
            echo "Lỗi: " . $e->getMessage();
            return false;
        }
    }

    // Phương thức cập nhật hóa đơn bán
    public function updateHoaDonBan() {
        try {
            $query = "UPDATE hoadonban SET 
                      MaKhachHang = :MaKhachHang, 
                      NgayDatHang = :NgayDatHang, 
                      MaDiaChi = :MaDiaChi, 
                      TongTien = :TongTien, 
                      TrangThai = :TrangThai 
                      WHERE MaHoaDonBan = :MaHoaDonBan";

            $stmt = $this->conn->prepare($query);

            // Làm sạch dữ liệu
            $this->MaKhachHang = htmlspecialchars(strip_tags($this->MaKhachHang));
            $this->NgayDatHang = htmlspecialchars(strip_tags($this->NgayDatHang));
            $this->MaDiaChi = htmlspecialchars(strip_tags($this->MaDiaChi));
            $this->TongTien = htmlspecialchars(strip_tags($this->TongTien));
            $this->TrangThai = htmlspecialchars(strip_tags($this->TrangThai));
            $this->MaHoaDonBan = htmlspecialchars(strip_tags($this->MaHoaDonBan));

            // Gắn tham số
            $stmt->bindParam(':MaKhachHang', $this->MaKhachHang);
            $stmt->bindParam(':NgayDatHang', $this->NgayDatHang);
            $stmt->bindParam(':MaDiaChi', $this->MaDiaChi);
            $stmt->bindParam(':TongTien', $this->TongTien);
            $stmt->bindParam(':TrangThai', $this->TrangThai);
            $stmt->bindParam(':MaHoaDonBan', $this->MaHoaDonBan);

            return $stmt->execute();
        } catch (PDOException $e) {
            echo "Lỗi: " . $e->getMessage();
            return false;
        }
    }

    // Phương thức xóa hóa đơn bán
    public function deleteHoaDonBan() {
        try {
            $query = "DELETE FROM hoadonban WHERE MaHoaDonBan = :MaHoaDonBan";
            $stmt = $this->conn->prepare($query);

            $this->MaHoaDonBan = htmlspecialchars(strip_tags($this->MaHoaDonBan));

            $stmt->bindParam(':MaHoaDonBan', $this->MaHoaDonBan);

            return $stmt->execute();
        } catch (PDOException $e) {
            echo "Lỗi: " . $e->getMessage();
            return false;
        }
    }
}
?>