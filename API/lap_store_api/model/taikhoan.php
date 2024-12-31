<?php
class TaiKhoan{
    private $conn;

    //Thuoc tinh
    public $TenTaiKhoan;
    public $MaNguoiDung;
    public $MatKhau;

    //connect db

    public function __construct($database){
        $this->conn = $database;
    }

    //Doc dữ liệu

    public function GetAllTaiKhoan() {
        $query = "SELECT * FROM taikhoan"; 
        $stmt = $this->conn->prepare($query);
        $stmt->execute();
        return $stmt; // Trả về PDOStatement
    }
    public function GetTaiKhoanByUsername() {
        $query = "SELECT * FROM taikhoan WHERE TenTaiKhoan = ? LIMIT 1"; 
        $stmt = $this->conn->prepare($query);
        $stmt->bindParam(1,$this->TenTaiKhoan);
        $stmt->execute();
        $row = $stmt->fetch(PDO::FETCH_ASSOC);

        $this->TenTaiKhoan = $row['TenTaiKhoan'];
        $this->MaNguoiDung = $row['MaNguoiDung'];
        $this->MatKhau = $row['MatKhau'];
    } 

    public function AddTaiKhoan(){
        $query = "INSERT INTO taikhoan SET TenTaiKhoan =:TenTaiKhoan ,  MaNguoiDung =:MaNguoiDung, MatKhau =:MatKhau";

        $stmt = $this->conn->prepare($query);

        $this->TenTaiKhoan = htmlspecialchars(strip_tags($this->TenTaiKhoan));
        $this->MaNguoiDung = htmlspecialchars(strip_tags($this->MaNguoiDung));
        $this->MatKhau = htmlspecialchars(strip_tags($this->MatKhau));

        $stmt->bindParam(':TenTaiKhoan',$this->TenTaiKhoan);
        $stmt->bindParam(':MaNguoiDung',$this->MaNguoiDung);
        $stmt->bindParam(':MatKhau',$this->MatKhau);

        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }

    public function UpdateTaiKhoan(){
        $query = "UPDATE taikhoan SET MatKhau =:MatKhau WHERE TenTaiKhoan=:TenTaiKhoan";

        $stmt = $this->conn->prepare($query);

        $this->TenTaiKhoan = htmlspecialchars(strip_tags($this->TenTaiKhoan));
        $this->MatKhau = htmlspecialchars(strip_tags($this->MatKhau));
        

        $stmt->bindParam(':TenTaiKhoan',$this->TenTaiKhoan);
        $stmt->bindParam(':MatKhau',$this->MatKhau);

        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }

    public function deleteTaiKhoan(){
        $query = "DELETE FROM taikhoan WHERE TenTaiKhoan=:TenTaiKhoan";

        $stmt = $this->conn->prepare($query);

        $this->MaNguoiDung = htmlspecialchars(strip_tags($this->TenTaiKhoan));

        $stmt->bindParam(':TenTaiKhoan',$this->TenTaiKhoan);

        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }
}
?>