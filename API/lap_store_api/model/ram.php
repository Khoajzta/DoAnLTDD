<?php
class ram {
    private $conn;

    // Thuộc tính
    public $MaRAM; 
    public $DungLuong;
    public $Loai;
    public $TocDo;

    // Kết nối cơ sở dữ liệu
    public function __construct($database) {
        $this->conn = $database;
    }

    // Đọc tất cả dữ liệu RAM
    public function GetAllRAM() {
        $query = "SELECT * FROM ram"; 
        $stmt = $this->conn->prepare($query);
        $stmt->execute();
        return $stmt; // Trả về PDOStatement
    }

    // Lấy RAM theo ID
    public function GetRAMById() {
        $query = "SELECT * FROM ram WHERE MaRAM = ? LIMIT 1"; 
        $stmt = $this->conn->prepare($query);
        $stmt->bindParam(1, $this->MaRAM);
        $stmt->execute();
        $row = $stmt->fetch(PDO::FETCH_ASSOC);

        if ($row) {
            $this->MaRAM = $row['MaRAM'];
            $this->DungLuong = $row['DungLuong'];
            $this->Loai = $row['Loai'];
            $this->TocDo = $row['TocDo'];
        }
    }

    // Thêm RAM
    public function AddRAM() {
        $query = "INSERT INTO ram SET MaRAM = :MaRAM, DungLuong = :DungLuong, Loai = :Loai, TocDo = :TocDo";

        $stmt = $this->conn->prepare($query);

        // Làm sạch dữ liệu
        $this->MaRAM = htmlspecialchars(strip_tags($this->MaRAM));
        $this->DungLuong = htmlspecialchars(strip_tags($this->DungLuong));
        $this->Loai = htmlspecialchars(strip_tags($this->Loai));
        $this->TocDo = htmlspecialchars(strip_tags($this->TocDo));

        // Gán giá trị
        $stmt->bindParam(':MaRAM', $this->MaRAM);
        $stmt->bindParam(':DungLuong', $this->DungLuong);
        $stmt->bindParam(':Loai', $this->Loai);
        $stmt->bindParam(':TocDo', $this->TocDo);

        if ($stmt->execute()) {
            return true;
        }
        printf("Error %s.\n", $stmt->error);
        return false;
    }

    // Cập nhật RAM
    public function UpdateRAM() {
        $query = "UPDATE ram SET DungLuong = :DungLuong, Loai = :Loai, TocDo = :TocDo WHERE MaRAM = :MaRAM";

        $stmt = $this->conn->prepare($query);

        // Làm sạch dữ liệu
        $this->MaRAM = htmlspecialchars(strip_tags($this->MaRAM));
        $this->DungLuong = htmlspecialchars(strip_tags($this->DungLuong));
        $this->Loai = htmlspecialchars(strip_tags($this->Loai));
        $this->TocDo = htmlspecialchars(strip_tags($this->TocDo));

        // Gán giá trị
        $stmt->bindParam(':MaRAM', $this->MaRAM);
        $stmt->bindParam(':DungLuong', $this->DungLuong);
        $stmt->bindParam(':Loai', $this->Loai);
        $stmt->bindParam(':TocDo', $this->TocDo);

        if ($stmt->execute()) {
            return true;
        }
        printf("Error %s.\n", $stmt->error);
        return false;
    }

    // Xóa RAM
    public function DeleteRAM() {
        $query = "DELETE FROM ram WHERE MaRAM = :MaRAM";

        $stmt = $this->conn->prepare($query);

        // Làm sạch dữ liệu
        $this->MaRAM = htmlspecialchars(strip_tags($this->MaRAM));

        // Gán giá trị
        $stmt->bindParam(':MaRAM', $this->MaRAM);

        if ($stmt->execute()) {
            return true;
        }
        printf("Error %s.\n", $stmt->error);
        return false;
    }
}
?>
