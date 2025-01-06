<?php
class hoadonnhap{
    private $conn;

    // Attributes
    public $MaHoaDon;
    public $MaNhaCungCap;
    public $NgayNhap;
    public $TongTien;
    public $TrangThai;

    // Connect to database
    public function __construct($database) {
        $this->conn = $database;
    }

    // Get all invoices
    public function getAllHoaDonNhap() {
        $query = "SELECT * FROM hoadonnhap"; 
        $stmt = $this->conn->prepare($query);
        $stmt->execute();
        return $stmt; // Return PDOStatement
    }

    // Get invoice by ID
    public function getHoaDonNhapById() {
        $query = "SELECT * FROM hoadonnhap WHERE MaHoaDon = ? LIMIT 1"; 
        $stmt = $this->conn->prepare($query);
        $stmt->bindParam(1, $this->MaHoaDon);
        $stmt->execute();
        $row = $stmt->fetch(PDO::FETCH_ASSOC);

        // Assign data to class properties
        $this->MaHoaDon = $row['MaHoaDon'];
        $this->MaNhaCungCap = $row['MaNhaCungCap'];
        $this->NgayNhap = $row['NgayNhap'];
        $this->TongTien = $row['TongTien'];
        $this->TrangThai = $row['TrangThai'];
    }

    // Add new invoice
    public function addHoaDonNhap() {
        $query = "INSERT INTO hoadonnhap SET MaHoaDon=:MaHoaDon, MaNhaCungCap=:MaNhaCungCap, NgayNhap=:NgayNhap, TongTien=:TongTien, TrangThai=:TrangThai";

        $stmt = $this->conn->prepare($query);

        // Sanitize inputs
        $this->MaHoaDon = htmlspecialchars(strip_tags($this->MaHoaDon));
        $this->MaNhaCungCap = htmlspecialchars(strip_tags($this->MaNhaCungCap));
        $this->NgayNhap = htmlspecialchars(strip_tags($this->NgayNhap));
        $this->TongTien = htmlspecialchars(strip_tags($this->TongTien));
        $this->TrangThai = htmlspecialchars(strip_tags($this->TrangThai));

        // Bind parameters
        $stmt->bindParam(':MaHoaDon', $this->MaHoaDon);
        $stmt->bindParam(':MaNhaCungCap', $this->MaNhaCungCap);
        $stmt->bindParam(':NgayNhap', $this->NgayNhap);
        $stmt->bindParam(':TongTien', $this->TongTien);
        $stmt->bindParam(':TrangThai', $this->TrangThai);

        // Execute query
        if ($stmt->execute()) {
            return true;
        }
        printf("Error: %s.\n", $stmt->error);
        return false;
    }

    // Update invoice
    public function updateHoaDonNhap() {
        $query = "UPDATE hoadonnhap SET MaNhaCungCap=:MaNhaCungCap, NgayNhap=:NgayNhap, TongTien=:TongTien, TrangThai=:TrangThai WHERE MaHoaDon=:MaHoaDon";

        $stmt = $this->conn->prepare($query);

        // Sanitize inputs
        $this->MaHoaDon = htmlspecialchars(strip_tags($this->MaHoaDon));
        $this->MaNhaCungCap = htmlspecialchars(strip_tags($this->MaNhaCungCap));
        $this->NgayNhap = htmlspecialchars(strip_tags($this->NgayNhap));
        $this->TongTien = htmlspecialchars(strip_tags($this->TongTien));
        $this->TrangThai = htmlspecialchars(strip_tags($this->TrangThai));

        // Bind parameters
        $stmt->bindParam(':MaHoaDon', $this->MaHoaDon);
        $stmt->bindParam(':MaNhaCungCap', $this->MaNhaCungCap);
        $stmt->bindParam(':NgayNhap', $this->NgayNhap);
        $stmt->bindParam(':TongTien', $this->TongTien);
        $stmt->bindParam(':TrangThai', $this->TrangThai);

        // Execute query
        if ($stmt->execute()) {
            return true;
        }
        printf("Error: %s.\n", $stmt->error);
        return false;
    }

    // Delete invoice
    public function deleteHoaDonNhap() {
        $query = "DELETE FROM hoadonnhap WHERE MaHoaDon=:MaHoaDon";

        $stmt = $this->conn->prepare($query);

        $this->MaHoaDon = htmlspecialchars(strip_tags($this->MaHoaDon));

        // Bind parameters
        $stmt->bindParam(':MaHoaDon', $this->MaHoaDon);

        // Execute query
        if ($stmt->execute()) {
            return true;
        }
        printf("Error: %s.\n", $stmt->error);
        return false;
    }
}
?>
