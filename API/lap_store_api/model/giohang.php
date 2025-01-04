<?php
class giohang{
    private $conn;

    //Thuoc tinh
    public $MaGioHang;
    public $MaKhachHang;
    public $MaSanPham;
    public $SoLuong;
    public $TrangThai;
    //connect db

    public function __construct($database){
        $this->conn = $database;
    }

    //Doc dữ liệu

    public function GetAllGioHang() {
        $query = "SELECT * FROM giohang"; 
        $stmt = $this->conn->prepare($query);
        $stmt->execute();
        return $stmt; // Trả về PDOStatement
    }
    public function GetGioHangById() {
        $query = "SELECT * FROM giohang WHERE MaGioHang = ? LIMIT 1"; 
        $stmt = $this->conn->prepare($query);
        $stmt->bindParam(1,$this->MaGioHang);
        $stmt->execute();
        $row = $stmt->fetch(PDO::FETCH_ASSOC);

        $this->MaGioHang = $row['MaGioHang'];
        $this->MaKhachHang = $row['MaKhachHang'];
        $this->MaSanPham = $row['MaSanPham'];
        $this->SoLuong = $row['SoLuong'];
        $this->TrangThai = $row['TrangThai'];
    } 

    public function AddGioHang() {
    $query = "INSERT INTO giohang SET MaGioHang =:MaGioHang, MaKhachHang=:MaKhachHang, MaSanPham=:MaSanPham, SoLuong=:SoLuong, TrangThai=:TrangThai";

    $stmt = $this->conn->prepare($query);

    $this->MaGioHang = htmlspecialchars(strip_tags($this->MaGioHang));
    $this->MaKhachHang = htmlspecialchars(strip_tags($this->MaKhachHang));
    $this->MaSanPham = htmlspecialchars(strip_tags($this->MaSanPham));
    $this->SoLuong = htmlspecialchars(strip_tags($this->SoLuong));
    $this->TrangThai = htmlspecialchars(strip_tags($this->TrangThai));

    $stmt->bindParam(':MaGioHang', $this->MaGioHang);
    $stmt->bindParam(':MaKhachHang', $this->MaKhachHang);
    $stmt->bindParam(':MaSanPham', $this->MaSanPham);
    $stmt->bindParam(':SoLuong', $this->SoLuong);
    $stmt->bindParam(':TrangThai', $this->TrangThai);

    if ($stmt->execute()) {
        return true;
    }
    printf("Error: %s.\n", $stmt->error);
    return false;
}


    public function UpdateGioHang(){
        $query = "UPDATE giohang SET MaKhachHang=:MaKhachHang, MaSanPham=:MaSanPham, SoLuong=:SoLuong, TrangThai=:TrangThai  WHERE MaGioHang =:MaGioHang";

        $stmt = $this->conn->prepare($query);

        $this->MaGioHang = htmlspecialchars(strip_tags($this->MaGioHang));
        $this->MaKhachHang = htmlspecialchars(strip_tags($this->MaKhachHang));
        $this->MaSanPham = htmlspecialchars(strip_tags($this->MaSanPham));
        $this->SoLuong = htmlspecialchars(strip_tags($this->SoLuong));
        $this->TrangThai = htmlspecialchars(strip_tags($this->TrangThai));

        $stmt->bindParam(':MaGioHang', $this->MaGioHang);
        $stmt->bindParam(':MaKhachHang', $this->MaKhachHang);
        $stmt->bindParam(':MaSanPham', $this->MaSanPham);
        $stmt->bindParam(':SoLuong', $this->SoLuong);
        $stmt->bindParam(':TrangThai', $this->TrangThai);

        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }

    public function deleteGioHang(){
        $query = "DELETE FROM giohang WHERE MaGioHang =:MaGioHang";

        $stmt = $this->conn->prepare($query);

        $this->MaGioHang = htmlspecialchars(strip_tags($this->MaGioHang));

        $stmt->bindParam(':MaGioHang',$this->MaGioHang);

        if($stmt->execute()){
            return true;
        }
        printf("Error %s.\n",$stmt->error);
        return false;
    }
}
?>