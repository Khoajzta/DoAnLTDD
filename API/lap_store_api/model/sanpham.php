<?php
class SanPham
{
    private $conn;

    //Thuoc tinh
    public $MaSanPham;
    public $TenSanPham;
    public $MaLoaiSanPham;
    public $CPU;
    public $RAM;
    public $CardManHinh;
    public $SSD;
    public $ManHinh;
    public $MaMauSac;
    public $Gia;
    public $SoLuong;
    public $MoTa;
    public $HinhAnh;
    public $TrangThai;

    public $MaKhachHang;

    public$SoLuongTrongGioHang;

    //connect db

    public function __construct($database)
    {
        $this->conn = $database;
    }

    //Doc dữ liệu

    public function GetAllSanPham()
    {
        $query = "SELECT * FROM SanPham sp 
              join hinhanh ha on sp.HinhAnh = ha.MaHinhAnh";
        $stmt = $this->conn->prepare($query);
        $stmt->execute();
        return $stmt; // Trả về PDOStatement
    }

    public function GetSanPhamById()
    {
        $query = "SELECT * 
                  FROM SanPham sp 
                  join hinhanh ha on sp.HinhAnh = ha.MaHinhAnh
                  WHERE sp.MaSanPham = ? LIMIT 1";
        $stmt = $this->conn->prepare($query);
        $stmt->bindParam(1, $this->MaSanPham);
        $stmt->execute();
        // Lấy kết quả
        $row = $stmt->fetch(PDO::FETCH_ASSOC);

        // Gán giá trị từ kết quả vào các thuộc tính của đối tượng
        $this->TenSanPham = $row['TenSanPham'] ?? null;  // Sử dụng giá trị mặc định nếu không có giá trị
        $this->MaLoaiSanPham = $row['MaLoaiSanPham'] ?? null;
        $this->CPU = $row['CPU'] ?? null;
        $this->RAM = $row['RAM'] ?? null;
        $this->CardManHinh = $row['CardManHinh'] ?? null;
        $this->SSD = $row['SSD'] ?? null;
        $this->ManHinh = $row['ManHinh'] ?? null;
        $this->MaMauSac = $row['MaMauSac'] ?? null;
        $this->Gia = $row['Gia'] ?? null;
        $this->SoLuong = $row['SoLuong'] ?? null;
        $this->MoTa = $row['MoTa'] ?? null;
        $this->HinhAnh = $row['DuongDan'] ?? null;
        $this->TrangThai = $row['TrangThai'] ?? null;
    }

    public function GetSanPhamBySearch($searchTerm)
    {
        $query = "SELECT * , ro.DungLuong as DungLuongROM ,  r.DungLuong as DungLuongRAM
              FROM SanPham sp
              JOIN HinhAnh ha ON sp.HinhAnh = ha.MaHinhAnh
              JOIN CPU cpu ON sp.MaCPU = cpu.MaCPU
              JOIN RAM r ON sp.MaRAM = r.MaRAM
              JOIN ROM ro ON sp.MaROM = ro.MaROM
              JOIN CardDoHoa cdh ON sp.MaCardDoHoa = cdh.MaCardDoHoa
              JOIN ManHinh mh ON sp.MaManHinh = mh.MaManHinh
              WHERE sp.TenSanPham LIKE ? OR sp.MoTa LIKE ?";
        $stmt = $this->conn->prepare($query);
        $searchTerm = "%" . $searchTerm . "%"; // Thêm dấu '%' để tìm kiếm theo kiểu "like"
        $stmt->bindParam(1, $searchTerm);
        $stmt->bindParam(2, $searchTerm);
        $stmt->execute();
        return $stmt;
    }


    public function GetSanPhamByLoai()
    {
        $query = "SELECT * 
                  FROM SanPham sp 
                  join hinhanh ha on sp.HinhAnh = ha.MaHinhAnh
                  WHERE sp.MaLoaiSanPham = ?
                  ";
        $stmt = $this->conn->prepare($query);
        $stmt->bindParam(1, $this->MaLoaiSanPham);
        $stmt->execute();
        return $stmt;
    }

    public function GetSanPhamByGioHang()
    {
        $query = "SELECT sp.*, ha.DuongDan
                FROM sanpham sp 
                JOIN giohang gh on sp.MaSanPham = gh.MaSanPham
                JOIN hinhanh ha on sp.MaSanPham = ha.MaHinhAnh
                WHERE gh.MaKhachHang = ?";
        $stmt = $this->conn->prepare($query);
        $stmt->bindParam(1, $this->MaKhachHang);
        $stmt->execute();
        return $stmt;
    }

    public function AddSanPham()
    {
        $query = "INSERT INTO SanPham 
        (MaSanPham, TenSanPham, MaLoaiSanPham, CPU, RAM, CardManHinh, 
        SSD, ManHinh, MaMauSac, Gia, SoLuong, MoTa, HinhAnh, TrangThai) 
        VALUES 
        (:MaSanPham, :TenSanPham, :MaLoaiSanPham, :CPU, :RAM, :CardManHinh, 
        :SSD, :ManHinh, :MaMauSac, :Gia, :SoLuong, :MoTa, :HinhAnh, :TrangThai)";

        $stmt = $this->conn->prepare($query);

        $this->MaSanPham = htmlspecialchars(strip_tags($this->MaSanPham));
        $this->TenSanPham = htmlspecialchars(strip_tags($this->TenSanPham));
        $this->MaLoaiSanPham = htmlspecialchars(strip_tags($this->MaLoaiSanPham));
        $this->CPU = htmlspecialchars(strip_tags($this->CPU));
        $this->RAM = htmlspecialchars(strip_tags($this->RAM));
        $this->CardManHinh = htmlspecialchars(strip_tags($this->CardManHinh));
        $this->SSD = htmlspecialchars(strip_tags($this->SSD));
        $this->ManHinh = htmlspecialchars(strip_tags($this->ManHinh));
        $this->MaMauSac = htmlspecialchars(strip_tags($this->MaMauSac));
        $this->Gia = htmlspecialchars(strip_tags($this->Gia));
        $this->SoLuong = htmlspecialchars(strip_tags($this->SoLuong));
        $this->MoTa = htmlspecialchars(strip_tags($this->MoTa));
        $this->HinhAnh = htmlspecialchars(strip_tags($this->HinhAnh));
        $this->TrangThai = htmlspecialchars(strip_tags($this->TrangThai));


        $stmt->bindParam(':MaSanPham', $this->MaSanPham);
        $stmt->bindParam(':TenSanPham', $this->TenSanPham);
        $stmt->bindParam(':MaLoaiSanPham', $this->MaLoaiSanPham);
        $stmt->bindParam(':CPU', $this->CPU);
        $stmt->bindParam(':RAM', $this->RAM);
        $stmt->bindParam(':CardManHinh', $this->CardManHinh);
        $stmt->bindParam(':SSD', $this->SSD);
        $stmt->bindParam(':ManHinh', $this->ManHinh);
        $stmt->bindParam(':MaMauSac', $this->MaMauSac);
        $stmt->bindParam(':Gia', $this->Gia);
        $stmt->bindParam(':SoLuong', $this->SoLuong);
        $stmt->bindParam(':MoTa', $this->MoTa);
        $stmt->bindParam(':HinhAnh', $this->HinhAnh);
        $stmt->bindParam(':TrangThai', $this->TrangThai);

        if ($stmt->execute()) {
            return true;
        }
        printf("Error %s.\n", $stmt->error);
        return false;
    }

    public function UpdateSanPham()
    {
        $query = "UPDATE SanPham 
        SET TenSanPham = :TenSanPham, 
        MaLoaiSanPham = :MaLoaiSanPham, 
        CPU = :CPU, 
        RAM = :RAM, 
        CardManHinh = :CardManHinh,
        SSD = :SSD, 
        ManHinh = :ManHinh, 
        MaMauSac = :MaMauSac, 
        Gia = :Gia, 
        SoLuong = :SoLuong, 
        MoTa = :MoTa, 
        HinhAnh = :HinhAnh, 
        TrangThai = :TrangThai 
        WHERE MaSanPham = :MaSanPham";


        $stmt = $this->conn->prepare($query);

        $this->TenSanPham = htmlspecialchars(strip_tags($this->TenSanPham));
        $this->MaLoaiSanPham = htmlspecialchars(strip_tags($this->MaLoaiSanPham));
        $this->CPU = htmlspecialchars(strip_tags($this->CPU));
        $this->RAM = htmlspecialchars(strip_tags($this->RAM));
        $this->CardManHinh = htmlspecialchars(strip_tags($this->CardManHinh));
        $this->SSD = htmlspecialchars(strip_tags($this->SSD));
        $this->ManHinh = htmlspecialchars(strip_tags($this->ManHinh));
        $this->MaMauSac = htmlspecialchars(strip_tags($this->MaMauSac));
        $this->Gia = htmlspecialchars(strip_tags($this->Gia));
        $this->SoLuong = htmlspecialchars(strip_tags($this->SoLuong));
        $this->MoTa = htmlspecialchars(strip_tags($this->MoTa));
        $this->HinhAnh = htmlspecialchars(strip_tags($this->HinhAnh));
        $this->TrangThai = htmlspecialchars(strip_tags($this->TrangThai));
        $this->MaSanPham = htmlspecialchars(strip_tags($this->MaSanPham));


        $stmt->bindParam(':TenSanPham', $this->TenSanPham);
        $stmt->bindParam(':MaLoaiSanPham', $this->MaLoaiSanPham);
        $stmt->bindParam(':CPU', $this->CPU);
        $stmt->bindParam(':RAM', $this->RAM);
        $stmt->bindParam(':CardManHinh', $this->CardManHinh);
        $stmt->bindParam(':SSD', $this->SSD);
        $stmt->bindParam(':ManHinh', $this->ManHinh);
        $stmt->bindParam(':MaMauSac', $this->MaMauSac);
        $stmt->bindParam(':Gia', $this->Gia);
        $stmt->bindParam(':SoLuong', $this->SoLuong);
        $stmt->bindParam(':MoTa', $this->MoTa);
        $stmt->bindParam(':HinhAnh', $this->HinhAnh);
        $stmt->bindParam(':TrangThai', $this->TrangThai);
        $stmt->bindParam(':MaSanPham', $this->MaSanPham);

        if ($stmt->execute()) {
            return true;
        }
        printf("Error %s.\n", $stmt->error);
        return false;
    }

    public function DeleteSanPham()
    {
        $query = "DELETE FROM SanPham WHERE MaSanPham=:MaSanPham";

        $stmt = $this->conn->prepare($query);

        $this->MaSanPham = htmlspecialchars(strip_tags($this->MaSanPham));

        $stmt->bindParam(':MaSanPham', $this->MaSanPham);

        if ($stmt->execute()) {
            return true;
        }
        printf("Error %s.\n", $stmt->error);
        return false;
    }
}
