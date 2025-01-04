<?php
class cpu{
    private $conn;

    //Thuoc tinh
    public $MaCPU;
    public $TenHangCPU;
    //connect db

    public function __construct($database){
        $this->conn = $database;
    }

    //Doc dữ liệu

    public function GetAllCPU() {
        $query = "SELECT * FROM cpu"; 
        $stmt = $this->conn->prepare($query);
        $stmt->execute();
        return $stmt; // Trả về PDOStatement
    }
    public function GetCPUById() {
        $query = "SELECT * FROM cpu WHERE MaCPU = ? LIMIT 1"; 
        $stmt = $this->conn->prepare($query);
        $stmt->bindParam(1,$this->MaCPU);
        $stmt->execute();
        $row = $stmt->fetch(PDO::FETCH_ASSOC);

        $this->MaCPU = $row['MaCPU'];
        $this->TenHangCPU = $row['TenHangCPU'];
    } 

    public function AddCPU(){
        $query = "INSERT INTO cpu SET TenHangCPU =:TenHangCPU ,  MaCPU =:MaCPU";

        $stmt = $this->conn->prepare($query);

        $this->MaCPU = htmlspecialchars(strip_tags($this->MaCPU));
        $this->TenHangCPU = htmlspecialchars(strip_tags($this->TenHangCPU));


        $stmt->bindParam(':MaCPU',$this->MaCPU);
        $stmt->bindParam(':TenHangCPU',$this->TenHangCPU);
        
        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }

    public function UpdateCPU(){
        $query = "UPDATE cpu SET TenHangCPU =:TenHangCPU  WHERE MaCPU=:MaCPU";

        $stmt = $this->conn->prepare($query);

        $this->MaCPU = htmlspecialchars(strip_tags($this->MaCPU));
        $this->TenHangCPU = htmlspecialchars(strip_tags($this->TenHangCPU));

        $stmt->bindParam(':MaCPU',$this->MaCPU);
        $stmt->bindParam(':TenHangCPU',$this->TenHangCPU);

        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }

    public function deleteCPU(){
        $query = "DELETE FROM cpu WHERE MaCPU=:MaCPU";

        $stmt = $this->conn->prepare($query);

        $this->MaCPU = htmlspecialchars(strip_tags($this->MaCPU));

        $stmt->bindParam(':MaCPU',$this->MaCPU);

        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }
}
?>