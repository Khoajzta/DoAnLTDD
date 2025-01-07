<?php
class TaiKhoan{
    private $conn;

    //Thuoc tinh
    public $TenTaiKhoan;
    public $MaKhachHang;
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
        $this->MaKhachHang = $row['MaKhachHang'];
        $this->MatKhau = $row['MatKhau'];
    }

    public function KiemTraDangNhap() {
        $query = "SELECT * FROM taikhoan WHERE TenTaiKhoan = ? AND MatKhau = ?";
        $stmt = $this->conn->prepare($query);
        $stmt->bindParam(1, $this->TenTaiKhoan);
        $stmt->bindParam(2, $this->MatKhau);
    
        $stmt->execute();
        $row = $stmt->fetch(PDO::FETCH_ASSOC);
    
        if ($row) {
            // Tìm thấy tài khoản, gán thông tin
            $this->TenTaiKhoan = $row['TenTaiKhoan'];
            $this->MaKhachHang = $row['MaKhachHang'];
            $this->MatKhau = $row['MatKhau'];
            return true; // Đăng nhập thành công
        } else {
            // Không tìm thấy tài khoản
            return false; // Đăng nhập thất bại
        }
    }
    

    public function AddTaiKhoan(){
        $query = "INSERT INTO taikhoan SET TenTaiKhoan =:TenTaiKhoan ,  MaKhachHang =:MaKhachHang, MatKhau =:MatKhau";

        $stmt = $this->conn->prepare($query);

        $this->TenTaiKhoan = htmlspecialchars(strip_tags($this->TenTaiKhoan));
        $this->MaKhachHang = htmlspecialchars(strip_tags($this->MaKhachHang));
        $this->MatKhau = htmlspecialchars(strip_tags($this->MatKhau));

        $stmt->bindParam(':TenTaiKhoan',$this->TenTaiKhoan);
        $stmt->bindParam(':MaKhachHang',$this->MaKhachHang);
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

        $this->MaKhachHang = htmlspecialchars(strip_tags($this->TenTaiKhoan));

        $stmt->bindParam(':TenTaiKhoan',$this->TenTaiKhoan);

        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }
}
?>