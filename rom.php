<?php
class Rom {
    private $conn;

    // Attributes
    public $MaHoaDonNhap ;
    public $MaNhaCungCap ;
    public $NgayNhap;
    public $ThanhTien;
    public $TrangThai;


    // Connect to database
    public function __construct($database) {
        $this->conn = $database;
    }

    // Get all ROM records
    public function getAllRom() {
        $query = "SELECT * FROM rom"; 
        $stmt = $this->conn->prepare($query);
        $stmt->execute();
        return $stmt; // Return PDOStatement
    }

    // Get ROM by ID
    public function getRomById() {
        $query = "SELECT * FROM rom WHERE MaROM = ? LIMIT 1"; 
        $stmt = $this->conn->prepare($query);
        $stmt->bindParam(1, $this->MaROM);
        $stmt->execute();
        $row = $stmt->fetch(PDO::FETCH_ASSOC);

        // Assign data to class properties
        $this->MaROM = $row['MaROM'];
        $this->DungLuong = $row['DungLuong'];
    }

    // Add new ROM
    public function addRom() {
        $query = "INSERT INTO rom SET MaROM=:MaROM, DungLuong=:DungLuong";

        $stmt = $this->conn->prepare($query);

        // Sanitize inputs
        $this->MaROM = htmlspecialchars(strip_tags($this->MaROM));
        $this->DungLuong = htmlspecialchars(strip_tags($this->DungLuong));

        // Bind parameters
        $stmt->bindParam(':MaROM', $this->MaROM);
        $stmt->bindParam(':DungLuong', $this->DungLuong);

        // Execute query
        if ($stmt->execute()) {
            return true;
        }
        printf("Error: %s.\n", $stmt->error);
        return false;
    }

    // Update ROM
    public function updateRom() {
        $query = "UPDATE rom SET DungLuong=:DungLuong WHERE MaROM=:MaROM";

        $stmt = $this->conn->prepare($query);

        // Sanitize inputs
        $this->MaROM = htmlspecialchars(strip_tags($this->MaROM));
        $this->DungLuong = htmlspecialchars(strip_tags($this->DungLuong));

        // Bind parameters
        $stmt->bindParam(':MaROM', $this->MaROM);
        $stmt->bindParam(':DungLuong', $this->DungLuong);

        // Execute query
        if ($stmt->execute()) {
            return true;
        }
        printf("Error: %s.\n", $stmt->error);
        return false;
    }

    // Delete ROM
    public function deleteRom() {
        $query = "DELETE FROM rom WHERE MaROM=:MaROM";

        $stmt = $this->conn->prepare($query);

        $this->MaROM = htmlspecialchars(strip_tags($this->MaROM));

        // Bind parameters
        $stmt->bindParam(':MaROM', $this->MaROM);

        // Execute query
        if ($stmt->execute()) {
            return true;
        }
        printf("Error: %s.\n", $stmt->error);
        return false;
    }
}
?>
