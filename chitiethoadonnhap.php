<?php
class CTHoaDonNhap {
    private $conn;

    // Attributes
    public $MaCTHoaDonNhap;
    public $MaHoaDonNhap;
    public $MaSanPham;
    public $SoLuong;
    public $DonGia;
    public $ThanhTien;
    public $TrangThai;

    // Connect to database
    public function __construct($database) {
        $this->conn = $database;
    }

    // Get all invoice details
    public function getAllCTHoaDonNhap() {
        $query = "SELECT * FROM CTHoaDonNhap"; 
        $stmt = $this->conn->prepare($query);
        $stmt->execute();
        return $stmt; // Return PDOStatement
    }

    // Get invoice detail by ID
    public function getCTHoaDonNhapById() {
        $query = "SELECT * FROM CTHoaDonNhap WHERE MaCTHoaDonNhap = ? LIMIT 1"; 
        $stmt = $this->conn->prepare($query);
        $stmt->bindParam(1, $this->MaCTHoaDonNhap);
        $stmt->execute();
        $row = $stmt->fetch(PDO::FETCH_ASSOC);

        // Assign data to class properties
        $this->MaCTHoaDonNhap = $row['MaCTHoaDonNhap'];
        $this->MaHoaDonNhap = $row['MaHoaDonNhap'];
        $this->MaSanPham = $row['MaSanPham'];
        $this->SoLuong = $row['SoLuong'];
        $this->DonGia = $row['DonGia'];
        $this->ThanhTien = $row['ThanhTien'];
        $this->TrangThai = $row['TrangThai'];
    }

    // Add new invoice detail
    public function addCTHoaDonNhap() {
        $query = "INSERT INTO CTHoaDonNhap SET MaHoaDonNhap=:MaHoaDonNhap, MaSanPham=:MaSanPham, SoLuong=:SoLuong, DonGia=:DonGia, ThanhTien=:ThanhTien, TrangThai=:TrangThai";

        $stmt = $this->conn->prepare($query);

        // Sanitize inputs
        $this->MaHoaDonNhap = htmlspecialchars(strip_tags($this->MaHoaDonNhap));
        $this->MaSanPham = htmlspecialchars(strip_tags($this->MaSanPham));
        $this->SoLuong = htmlspecialchars(strip_tags($this->SoLuong));
        $this->DonGia = htmlspecialchars(strip_tags($this->DonGia));
        $this->ThanhTien = htmlspecialchars(strip_tags($this->ThanhTien));
        $this->TrangThai = htmlspecialchars(strip_tags($this->TrangThai));

        // Bind parameters
        $stmt->bindParam(':MaHoaDonNhap', $this->MaHoaDonNhap);
        $stmt->bindParam(':MaSanPham', $this->MaSanPham);
        $stmt->bindParam(':SoLuong', $this->SoLuong);
        $stmt->bindParam(':DonGia', $this->DonGia);
        $stmt->bindParam(':ThanhTien', $this->ThanhTien);
        $stmt->bindParam(':TrangThai', $this->TrangThai);

        // Execute query
        if ($stmt->execute()) {
            return true;
        }
        printf("Error: %s.\n", $stmt->error);
        return false;
    }

    // Update invoice detail
    public function updateCTHoaDonNhap() {
        $query = "UPDATE CTHoaDonNhap SET MaHoaDonNhap=:MaHoaDonNhap, MaSanPham=:MaSanPham, SoLuong=:SoLuong, DonGia=:DonGia, ThanhTien=:ThanhTien, TrangThai=:TrangThai WHERE MaCTHoaDonNhap=:MaCTHoaDonNhap";

        $stmt = $this->conn->prepare($query);

        // Sanitize inputs
        $this->MaCTHoaDonNhap = htmlspecialchars(strip_tags($this->MaCTHoaDonNhap));
        $this->MaHoaDonNhap = htmlspecialchars(strip_tags($this->MaHoaDonNhap));
        $this->MaSanPham = htmlspecialchars(strip_tags($this->MaSanPham));
        $this->SoLuong = htmlspecialchars(strip_tags($this->SoLuong));
        $this->DonGia = htmlspecialchars(strip_tags($this->DonGia));
        $this->ThanhTien = htmlspecialchars(strip_tags($this->ThanhTien));
        $this->TrangThai = htmlspecialchars(strip_tags($this->TrangThai));

        // Bind parameters
        $stmt->bindParam(':MaCTHoaDonNhap', $this->MaCTHoaDonNhap);
        $stmt->bindParam(':MaHoaDonNhap', $this->MaHoaDonNhap);
        $stmt->bindParam(':MaSanPham', $this->MaSanPham);
        $stmt->bindParam(':SoLuong', $this->SoLuong);
        $stmt->bindParam(':DonGia', $this->DonGia);
        $stmt->bindParam(':ThanhTien', $this->ThanhTien);
        $stmt->bindParam(':TrangThai', $this->TrangThai);

        // Execute query
        if ($stmt->execute()) {
            return true;
        }
        printf("Error: %s.\n", $stmt->error);
        return false;
    }

    // Delete invoice detail
    public function deleteCTHoaDonNhap() {
        $query = "DELETE FROM CTHoaDonNhap WHERE MaCTHoaDonNhap=:MaCTHoaDonNhap";

        $stmt = $this->conn->prepare($query);

        $this->MaCTHoaDonNhap = htmlspecialchars(strip_tags($this->MaCTHoaDonNhap));

        // Bind parameters
        $stmt->bindParam(':MaCTHoaDonNhap', $this->MaCTHoaDonNhap);

        // Execute query
        if ($stmt->execute()) {
            return true;
        }
        printf("Error: %s.\n", $stmt->error);
        return false;
    }
}
?>
