<?php
class Huyen{
    private $conn;

    //Thuoc tinh
    public $MaHuyen;
    public $TenHuyen;
    public $MaTinh;
    //connect db

    public function __construct($database){
        $this->conn = $database;
    }

    //Doc dữ liệu

    public function GetAllHuyen() {
        $query = "SELECT * FROM huyen"; 
        $stmt = $this->conn->prepare($query);
        $stmt->execute();
        return $stmt; // Trả về PDOStatement
    }
    public function GetHuyenById() {
        $query = "SELECT * FROM huyen WHERE MaHuyen = ? LIMIT 1"; 
        $stmt = $this->conn->prepare($query);
        $stmt->bindParam(1,$this->MaHuyen);
        $stmt->execute();
        $row = $stmt->fetch(PDO::FETCH_ASSOC);

        $this->MaHuyen = $row['MaHuyen'];
        $this->TenHuyen = $row['TenHuyen'];
        $this->MaTinh = $row['MaTinh'];
    } 

    public function AddHuyen(){
        $query = "INSERT INTO huyen SET MaHuyen =:MaHuyen,  TenHuyen =:TenHuyen ,  MaTinh =:MaTinh";

        $stmt = $this->conn->prepare($query);

        $this->MaHuyen = htmlspecialchars(strip_tags($this->MaHuyen));
        $this->TenHuyen = htmlspecialchars(strip_tags($this->TenHuyen));
        $this->MaTinh = htmlspecialchars(strip_tags($this->MaTinh));


        $stmt->bindParam(':MaHuyen',$this->MaHuyen);
        $stmt->bindParam(':TenHuyen',$this->TenHuyen);
        $stmt->bindParam(':MaTinh',$this->MaTinh);
        
        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }

    public function UpdateHuyen(){
        $query = "UPDATE huyen SET TenHuyen =:TenHuyen, MaTinh =:MaTinh  WHERE MaHuyen=:MaHuyen";

        $stmt = $this->conn->prepare($query);

        $this->MaTinh = htmlspecialchars(strip_tags($this->MaTinh));
        $this->TenHuyen = htmlspecialchars(strip_tags($this->TenHuyen));
        $this->MaHuyen = htmlspecialchars(strip_tags($this->MaHuyen));

        $stmt->bindParam(':MaTinh',$this->MaTinh);
        $stmt->bindParam(':TenHuyen',$this->TenHuyen);
        $stmt->bindParam(':MaHuyen',$this->MaHuyen);

        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }

    public function DeleteHuyen(){
        $query = "DELETE FROM huyen WHERE MaHuyen=:MaHuyen";

        $stmt = $this->conn->prepare($query);

        $this->MaHuyen = htmlspecialchars(strip_tags($this->MaHuyen));

        $stmt->bindParam(':MaHuyen',$this->MaHuyen);

        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }
}
?>