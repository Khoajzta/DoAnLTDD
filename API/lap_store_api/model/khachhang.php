<?php
class Khachhang{
    private $conn;

    //Thuoc tinh
    public $MaKhachHang;
    public $HoTen;
    public $GioiTinh;
    public $NgaySinh;
    public $Email;
    public $SoDienThoai;
    public $MaDiaChi;

    //connect db

    public function __construct($database){
        $this->conn = $database;
    }

    //Doc dữ liệu

    public function GetAllKhachHang() {
        $query = "SELECT * FROM khachhang ORDER BY MaKhachHang DESC"; 
        $stmt = $this->conn->prepare($query);
        $stmt->execute();
        return $stmt; // Trả về PDOStatement
    }
    public function GetKhachHangById() {
        $query = "SELECT * FROM khachhang WHERE MaKhachHang = ? LIMIT 1"; 
        $stmt = $this->conn->prepare($query);
        $stmt->bindParam(1,$this->MaKhachHang);
        $stmt->execute();
        $row = $stmt->fetch(PDO::FETCH_ASSOC);

        $this->HoTen = $row['HoTen'];
        $this->GioiTinh = $row['GioiTinh'];
        $this->NgaySinh = $row['NgaySinh'];
        $this->Email = $row['Email'];
        $this->SoDienThoai = $row['SoDienThoai'];
        $this->MaDiaChi = $row['MaDiaChi'];
    } 

    public function AddKhachHang(){
        $query = "INSERT INTO khachhang SET HoTen =:HoTen, GioiTinh =:GioiTinh, NgaySinh =:NgaySinh,  Email =:Email, SoDienThoai =:SoDienThoai, MaDiaChi =:MaDiaChi";

        $stmt = $this->conn->prepare($query);

        $this->HoTen = htmlspecialchars(strip_tags($this->HoTen));
        $this->GioiTinh = htmlspecialchars(strip_tags($this->GioiTinh));
        $this->NgaySinh = htmlspecialchars(strip_tags($this->NgaySinh));
        $this->Email = htmlspecialchars(strip_tags($this->Email));
        $this->SoDienThoai = htmlspecialchars(strip_tags($this->SoDienThoai));
        $this->MaDiaChi = htmlspecialchars(strip_tags($this->MaDiaChi));


        $stmt->bindParam(':HoTen',$this->HoTen);
        $stmt->bindParam(':GioiTinh',$this->GioiTinh);
        $stmt->bindParam(':NgaySinh',$this->NgaySinh);
        $stmt->bindParam(':Email',$this->Email);
        $stmt->bindParam(':SoDienThoai',$this->SoDienThoai);
        $stmt->bindParam(':MaDiaChi',$this->MaDiaChi);

        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }

    public function UpdateKhachHang(){
        $query = "UPDATE khachhang SET HoTen =:HoTen , GioiTinh =:GioiTinh, NgaySinh =:NgaySinh,  Email =:Email, SoDienThoai =:SoDienThoai, MaDiaChi =:MaDiaChi WHERE MaKhachHang=:MaKhachHang";

        $stmt = $this->conn->prepare($query);

        $this->HoTen = htmlspecialchars(strip_tags($this->HoTen));
        $this->GioiTinh = htmlspecialchars(strip_tags($this->GioiTinh));
        $this->NgaySinh = htmlspecialchars(strip_tags($this->NgaySinh));
        $this->Email = htmlspecialchars(strip_tags($this->Email));
        $this->SoDienThoai = htmlspecialchars(strip_tags($this->SoDienThoai));
        $this->MaDiaChi = htmlspecialchars(strip_tags($this->MaDiaChi));
        $this->MaKhachHang = htmlspecialchars(strip_tags($this->MaKhachHang));

        $stmt->bindParam(':HoTen',$this->HoTen);
        $stmt->bindParam(':GioiTinh',$this->GioiTinh);
        $stmt->bindParam(':NgaySinh',$this->NgaySinh);
        $stmt->bindParam(':Email',$this->Email);
        $stmt->bindParam(':SoDienThoai',$this->SoDienThoai);
        $stmt->bindParam(':MaDiaChi',$this->MaDiaChi);
        $stmt->bindParam(':MaKhachHang',$this->MaKhachHang);

        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }

    public function deleteKhachHang(){
        $query = "DELETE FROM khachhang WHERE MaKhachHang=:MaKhachHang";

        $stmt = $this->conn->prepare($query);

        $this->MaKhachHang = htmlspecialchars(strip_tags($this->MaKhachHang));

        $stmt->bindParam(':MaKhachHang',$this->MaKhachHang);

        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }
}
?>