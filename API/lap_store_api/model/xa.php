<?php
class Xa{
    private $conn;

    //Thuoc tinh
    public $MaXa;
    public $TenXa;
    public $MaHuyen;
    //connect db

    public function __construct($database){
        $this->conn = $database;
    }

    //Doc dữ liệu

    public function GetAllXa() {
        $query = "SELECT * FROM xa"; 
        $stmt = $this->conn->prepare($query);
        $stmt->execute();
        return $stmt; // Trả về PDOStatement
    }
    public function GetXaById() {
        $query = "SELECT * FROM xa WHERE MaXa = ? LIMIT 1"; 
        $stmt = $this->conn->prepare($query);
        $stmt->bindParam(1,$this->MaXa);
        $stmt->execute();
        $row = $stmt->fetch(PDO::FETCH_ASSOC);

        $this->MaXa = $row['MaXa'];
        $this->TenXa = $row['TenXa'];
        $this->MaHuyen = $row['MaHuyen'];
    } 

    public function AddXa(){
        $query = "INSERT INTO xa SET MaXa =:MaXa,  TenXa =:TenXa ,  MaHuyen =:MaHuyen";

        $stmt = $this->conn->prepare($query);

        $this->MaXa = htmlspecialchars(strip_tags($this->MaXa));
        $this->TenXa = htmlspecialchars(strip_tags($this->TenXa));
        $this->MaHuyen = htmlspecialchars(strip_tags($this->MaHuyen));


        $stmt->bindParam(':MaXa',$this->MaXa);
        $stmt->bindParam(':TenXa',$this->TenXa);
        $stmt->bindParam(':MaHuyen',$this->MaHuyen);
        
        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }

    public function UpdateXa(){
        $query = "UPDATE xa SET TenXa =:TenXa, MaHuyen =:MaHuyen  WHERE MaXa=:MaXa";

        $stmt = $this->conn->prepare($query);

        $this->TenXa = htmlspecialchars(strip_tags($this->TenXa));
        $this->MaHuyen = htmlspecialchars(strip_tags($this->MaHuyen));
        $this->MaXa = htmlspecialchars(strip_tags($this->MaXa));

        $stmt->bindParam(':TenXa',$this->TenXa);
        $stmt->bindParam(':MaHuyen',$this->MaHuyen);
        $stmt->bindParam(':MaXa',$this->MaXa);

        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }

    public function deleteXa(){
        $query = "DELETE FROM xa WHERE MaXa=:MaXa";

        $stmt = $this->conn->prepare($query);

        $this->MaXa = htmlspecialchars(strip_tags($this->MaXa));

        $stmt->bindParam(':MaXa',$this->MaXa);

        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }
}
?>