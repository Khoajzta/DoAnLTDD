<?php
class tinh{
    private $conn;

    //Thuoc tinh
    public $MaTinh;
    public $TenTinh;
    //connect db

    public function __construct($database){
        $this->conn = $database;
    }

    //Doc dữ liệu

    public function GetAllTinh() {
        $query = "SELECT * FROM tinh"; 
        $stmt = $this->conn->prepare($query);
        $stmt->execute();
        return $stmt; // Trả về PDOStatement
    }
    public function GetTinhById() {
        $query = "SELECT * FROM tinh WHERE MaTinh = ? LIMIT 1"; 
        $stmt = $this->conn->prepare($query);
        $stmt->bindParam(1,$this->MaTinh);
        $stmt->execute();
        $row = $stmt->fetch(PDO::FETCH_ASSOC);

        $this->MaTinh = $row['MaTinh'];
        $this->TenTinh = $row['TenTinh'];
    } 

    public function AddTinh(){
        $query = "INSERT INTO tinh SET TenTinh =:TenTinh ,  MaTinh =:MaTinh";

        $stmt = $this->conn->prepare($query);

        $this->MaTinh = htmlspecialchars(strip_tags($this->MaTinh));
        $this->TenTinh = htmlspecialchars(strip_tags($this->TenTinh));


        $stmt->bindParam(':MaTinh',$this->MaTinh);
        $stmt->bindParam(':TenTinh',$this->TenTinh);
        
        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }

    public function UpdateTinh(){
        $query = "UPDATE tinh SET TenTinh =:TenTinh  WHERE MaTinh=:MaTinh";

        $stmt = $this->conn->prepare($query);

        $this->MaTinh = htmlspecialchars(strip_tags($this->MaTinh));
        $this->TenTinh = htmlspecialchars(strip_tags($this->TenTinh));

        $stmt->bindParam(':MaTinh',$this->MaTinh);
        $stmt->bindParam(':TenTinh',$this->TenTinh);

        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }

    public function deleteTinh(){
        $query = "DELETE FROM tinh WHERE MaTinh=:MaTinh";

        $stmt = $this->conn->prepare($query);

        $this->MaTinh = htmlspecialchars(strip_tags($this->MaTinh));

        $stmt->bindParam(':MaTinh',$this->MaTinh);

        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }
}
?>