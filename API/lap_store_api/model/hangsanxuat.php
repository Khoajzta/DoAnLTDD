<?php
class HangSanXuat{
    private $conn;

    //Thuoc tinh
    public $MaHangSanXuat;
    public $TenHangSanXuat;
    public $QuocGia;
    //connect db

    public function __construct($database){
        $this->conn = $database;
    }

    //Doc dữ liệu

    public function GetAllHangSanXuat() {
        $query = "SELECT * FROM hangsanxuat"; 
        $stmt = $this->conn->prepare($query);
        $stmt->execute();
        return $stmt; // Trả về PDOStatement
    }
    public function GetHangSanXuatById() {
        $query = "SELECT * FROM hangsanxuat WHERE MaHangSanXuat = ? LIMIT 1"; 
        $stmt = $this->conn->prepare($query);
        $stmt->bindParam(1,$this->MaHangSanXuat);
        $stmt->execute();
        $row = $stmt->fetch(PDO::FETCH_ASSOC);

        $this->MaHangSanXuat = $row['MaHangSanXuat'];
        $this->TenHangSanXuat = $row['TenHangSanXuat'];
        $this->QuocGia = $row['QuocGia'];
    } 

    public function AddHangSanXuat(){
        $query = "INSERT INTO hangsanxuat SET MaHangSanXuat =:MaHangSanXuat,  TenHangSanXuat =:TenHangSanXuat ,  QuocGia =:QuocGia";

        $stmt = $this->conn->prepare($query);

        $this->MaHangSanXuat = htmlspecialchars(strip_tags($this->MaHangSanXuat));
        $this->TenHangSanXuat = htmlspecialchars(strip_tags($this->TenHangSanXuat));
        $this->QuocGia = htmlspecialchars(strip_tags($this->QuocGia));


        $stmt->bindParam(':MaHangSanXuat',$this->MaHangSanXuat);
        $stmt->bindParam(':TenHangSanXuat',$this->TenHangSanXuat);
        $stmt->bindParam(':QuocGia',$this->QuocGia);
        
        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }

    public function UpdateHangSanXuat(){
        $query = "UPDATE hangsanxuat SET TenHangSanXuat =:TenHangSanXuat, QuocGia =:QuocGia  WHERE MaHangSanXuat=:MaHangSanXuat";

        $stmt = $this->conn->prepare($query);

        $this->TenHangSanXuat = htmlspecialchars(strip_tags($this->TenHangSanXuat));
        $this->QuocGia = htmlspecialchars(strip_tags($this->QuocGia));
        $this->MaHangSanXuat = htmlspecialchars(strip_tags($this->MaHangSanXuat));

        $stmt->bindParam(':TenHangSanXuat',$this->TenHangSanXuat);
        $stmt->bindParam(':QuocGia',$this->QuocGia);
        $stmt->bindParam(':MaHangSanXuat',$this->MaHangSanXuat);

        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }

    public function deleteHangSanXuat(){
        $query = "DELETE FROM hangsanxuat WHERE MaHangSanXuat=:MaHangSanXuat";

        $stmt = $this->conn->prepare($query);

        $this->MaHangSanXuat = htmlspecialchars(strip_tags($this->MaHangSanXuat));

        $stmt->bindParam(':MaHangSanXuat',$this->MaHangSanXuat);

        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }
}
?>