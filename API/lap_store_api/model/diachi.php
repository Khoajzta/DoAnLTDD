<?php
class diachi{
    private $conn;

    //Thuoc tinh
    public $MaDiaChi;
    public $MaTinh;
    public $MaHuyen;
    public $MaXa;
    public $SoNha;
    //connect db

    public function __construct($database){
        $this->conn = $database;
    }

    //Doc dữ liệu

    public function GetAllDiaChi() {
        $query = "SELECT * FROM diachi"; 
        $stmt = $this->conn->prepare($query);
        $stmt->execute();
        return $stmt; // Trả về PDOStatement
    }
    public function GetDiaChiById() {
        $query = "SELECT * FROM diachi WHERE MaDiaChi = ? LIMIT 1"; 
        $stmt = $this->conn->prepare($query);
        $stmt->bindParam(1,$this->MaDiaChi);
        $stmt->execute();
        $row = $stmt->fetch(PDO::FETCH_ASSOC);

        $this->MaDiaChi = $row['MaDiaChi'];
        $this->MaTinh = $row['MaTinh'];
        $this->MaHuyen = $row['MaHuyen'];
        $this->MaXa = $row['MaXa'];
        $this->SoNha = $row['SoNha'];
    } 

    public function AddDiaChi(){
        $query = "INSERT INTO diachi SET MaDiaChi =: MaDiaChi, MaTinh =:MaTinh, MaHuyen=: MaHuyen, MaXa=: MaXa, SoNha=:SoNha";

        $stmt = $this->conn->prepare($query);

        $this->MaDiaChi = htmlspecialchars(strip_tags($this->MaDiaChi));
        $this->MaTinh = htmlspecialchars(strip_tags($this->MaTinh));
        $this->MaHuyen = htmlspecialchars(strip_tags($this->MaHuyen));
        $this->MaXa = htmlspecialchars(strip_tags($this->MaXa));
        $this->SoNha = htmlspecialchars(strip_tags($this->SoNha));


        $stmt->bindParam(':MaDiaChi',$this->MaDiaChi);
        $stmt->bindParam(':MaTinh',$this->MaTinh);
        $stmt->bindParam(':MaHuyen',$this->MaHuyen);
        $stmt->bindParam(':MaXa',$this->MaXa);
        $stmt->bindParam(':SoNha',$this->SoNha);

        
        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }

    public function UpdateDiaChi(){
        $query = "UPDATE diachi SET MaTinh =:MaTinh, MaHuyen=: MaHuyen, MaXa=: MaXa, SoNha=:SoNha  WHERE MaDiaChi =: MaDiaChi";

        $stmt = $this->conn->prepare($query);

        $this->MaDiaChi = htmlspecialchars(strip_tags($this->MaDiaChi));
        $this->MaTinh = htmlspecialchars(strip_tags($this->MaTinh));
        $this->MaHuyen = htmlspecialchars(strip_tags($this->MaHuyen));
        $this->MaXa = htmlspecialchars(strip_tags($this->MaXa));
        $this->SoNha = htmlspecialchars(strip_tags($this->SoNha));


        $stmt->bindParam(':MaDiaChi',$this->MaDiaChi);
        $stmt->bindParam(':MaTinh',$this->MaTinh);
        $stmt->bindParam(':MaHuyen',$this->MaHuyen);
        $stmt->bindParam(':MaXa',$this->MaXa);
        $stmt->bindParam(':SoNha',$this->SoNha);

        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }

    public function deleteDiaChi(){
        $query = "DELETE FROM diachi WHERE MaDiaChi =: MaDiaChi";

        $stmt = $this->conn->prepare($query);

        $this->MaDiaChi = htmlspecialchars(strip_tags($this->MaDiaChi));

        $stmt->bindParam(':MaDiaChi',$this->MaDiaChi);

        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }
}
?>