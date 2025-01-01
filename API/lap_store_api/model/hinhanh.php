<?php
class HinhAnh{
    private $conn;

    //Thuoc tinh
    public $MaHinhAnh;
    public $TenHinhAnh;
    //connect db

    public function __construct($database){
        $this->conn = $database;
    }

    //Doc dữ liệu

    public function GetAllHinhAnh() {
        $query = "SELECT * FROM hinhanh"; 
        $stmt = $this->conn->prepare($query);
        $stmt->execute();
        return $stmt; // Trả về PDOStatement
    }
    public function GetHinhAnhById() {
        $query = "SELECT * FROM hinhanh WHERE MaHinhAnh = ? LIMIT 1"; 
        $stmt = $this->conn->prepare($query);
        $stmt->bindParam(1,$this->MaHinhAnh);
        $stmt->execute();
        $row = $stmt->fetch(PDO::FETCH_ASSOC);

        $this->MaHinhAnh = $row['MaHinhAnh'];
        $this->TenHinhAnh = $row['TenHinhAnh'];
    } 

    public function AddHinhAnh(){
        $query = "INSERT INTO hinhanh SET TenHinhAnh =:TenHinhAnh ,  MaHinhAnh =:MaHinhAnh";

        $stmt = $this->conn->prepare($query);

        $this->MaHinhAnh = htmlspecialchars(strip_tags($this->MaHinhAnh));
        $this->TenHinhAnh = htmlspecialchars(strip_tags($this->TenHinhAnh));


        $stmt->bindParam(':MaHinhAnh',$this->MaHinhAnh);
        $stmt->bindParam(':TenHinhAnh',$this->TenHinhAnh);
        
        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }

    public function UpdateHinhAnh(){
        $query = "UPDATE hinhanh SET TenHinhAnh =:TenHinhAnh  WHERE MaHinhAnh=:MaHinhAnh";

        $stmt = $this->conn->prepare($query);

        $this->MaHinhAnh = htmlspecialchars(strip_tags($this->MaHinhAnh));
        $this->TenHinhAnh = htmlspecialchars(strip_tags($this->TenHinhAnh));

        $stmt->bindParam(':MaHinhAnh',$this->MaHinhAnh);
        $stmt->bindParam(':TenHinhAnh',$this->TenHinhAnh);

        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }

    public function deleteHinhAnh(){
        $query = "DELETE FROM hinhanh WHERE MaHinhAnh=:MaHinhAnh";

        $stmt = $this->conn->prepare($query);

        $this->MaHinhAnh = htmlspecialchars(strip_tags($this->MaHinhAnh));

        $stmt->bindParam(':MaHinhAnh',$this->MaHinhAnh);

        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }
}
?>