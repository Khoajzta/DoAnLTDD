<?php
class SanPham
{
    private $conn;

    //Thuoc tinh
    public $MaSanPham;
    public $TenSanPham;
    public $MaLoaiSanPham;
    public $MaHangSanXuat;
    public $MaCPU;
    public $MaRAM;
    public $MaCardManHinh;
    public $MaROM;
    public $MaManHinh;
    public $MaMauSac;
    public $Gia;
    public $SoLuong;
    public $MoTa;
    public $HinhAnh;
    public $TrangThai;

    //connect db

    public function __construct($database)
    {
        $this->conn = $database;
    }

    //Doc dữ liệu

    public function GetAllSanPham()
{
    $query = "SELECT * , ro.DungLuong as DungLuongROM ,  r.DungLuong as DungLuongRAM, sp.MaSanPham as MSanPham
              FROM SanPham sp
              JOIN HinhAnh ha ON sp.HinhAnh = ha.MaHinhAnh
              JOIN CPU cpu ON sp.MaCPU = cpu.MaCPU
              JOIN RAM r ON sp.MaRAM = r.MaRAM
              JOIN ROM ro ON sp.MaROM = ro.MaROM
              JOIN CardManHinh cdh ON sp.MaCardManHinh = cdh.MaCardManHinh
              JOIN ManHinh mh ON sp.MaManHinh = mh.MaManHinh";
    
    $stmt = $this->conn->prepare($query);
    $stmt->execute();
    return $stmt; // Trả về PDOStatement
}

    public function GetSanPhamById()
    {
        $query = "SELECT * , ro.DungLuong as DungLuongROM ,  r.DungLuong as DungLuongRAM, sp.MaSanPham as MSanPham
                  FROM SanPham sp 
                  join HinhAnh ha on sp.HinhAnh = ha.MaHinhAnh 
                  JOIN CPU cpu ON sp.MaCPU = cpu.MaCPU
                  JOIN RAM r ON sp.MaRAM = r.MaRAM
                  JOIN ROM ro ON sp.MaROM = ro.MaROM
                  JOIN CardManHinh cdh ON sp.MaCardManHinh = cdh.MaCardManHinh
                  JOIN ManHinh mh ON sp.MaManHinh = mh.MaManHinh
                  WHERE sp.MaSanPham = ? LIMIT 1";
        $stmt = $this->conn->prepare($query);
        $stmt->bindParam(1, $this->MaSanPham);
        $stmt->execute();
        // Lấy kết quả
        $row = $stmt->fetch(PDO::FETCH_ASSOC);

        // Gán giá trị từ kết quả vào các thuộc tính của đối tượng
        $this->TenSanPham = $row['TenSanPham'] ?? null;  // Sử dụng giá trị mặc định nếu không có giá trị
        $this->MaLoaiSanPham = $row['MaLoaiSanPham'] ?? null;
        $this->MaHangSanXuat = $row['MaHangSanXuat'] ?? null;
        $this->MaCPU = $row['TenCPU'] ?? null;
        $this->MaRAM = $row['DungLuongRAM'] ?? null;
        $this->MaCardManHinh = $row['TenCard'] ?? null;
        $this->MaROM = $row['DungLuongROM'] ?? null;
        $this->MaManHinh = $row['MaManHinh'] ?? null;
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
    $searchTerm = "%".$searchTerm."%"; // Thêm dấu '%' để tìm kiếm theo kiểu "like"
    $stmt->bindParam(1, $searchTerm);
    $stmt->bindParam(2, $searchTerm);
    $stmt->execute();
    return $stmt; 
}


    public function GetSanPhamByLoai()
    {
        $query = "SELECT * , ro.DungLuong as DungLuongROM ,  r.DungLuong as DungLuongRAM, sp.MaSanPham as MSanPham
              FROM SanPham sp
              JOIN HinhAnh ha ON sp.HinhAnh = ha.MaHinhAnh
              JOIN CPU cpu ON sp.MaCPU = cpu.MaCPU
              JOIN RAM r ON sp.MaRAM = r.MaRAM
              JOIN ROM ro ON sp.MaROM = ro.MaROM
              JOIN CardManHinh cdh ON sp.MaCardManHinh = cdh.MaCardManHinh
              JOIN ManHinh mh ON sp.MaManHinh = mh.MaManHinh
              WHERE sp.MaLoaiSanPham = ?";
        $stmt = $this->conn->prepare($query);
        $stmt->bindParam(1, $this->MaLoaiSanPham);
        $stmt->execute();
        return $stmt; 
    }
    

    public function AddSanPham()
    {
        $query = "INSERT INTO SanPham 
        (MaSanPham, TenSanPham, MaLoaiSanPham, MaHangSanXuat, MaCPU, MaRAM, MaCardManHinh, 
        MaROM, MaManHinh, MaMauSac, Gia, SoLuong, MoTa, HinhAnh, TrangThai) 
        VALUES 
        (:MaSanPham, :TenSanPham, :MaLoaiSanPham, :MaHangSanXuat, :MaCPU, :MaRAM, :MaCardManHinh, 
        :MaROM, :MaManHinh, :MaMauSac, :Gia, :SoLuong, :MoTa, :HinhAnh, :TrangThai)";


        $stmt = $this->conn->prepare($query);

        $this->MaSanPham = htmlspecialchars(strip_tags($this->MaSanPham));
        $this->TenSanPham = htmlspecialchars(strip_tags($this->TenSanPham));
        $this->MaLoaiSanPham = htmlspecialchars(strip_tags($this->MaLoaiSanPham));
        $this->MaHangSanXuat = htmlspecialchars(strip_tags($this->MaHangSanXuat));
        $this->MaCPU = htmlspecialchars(strip_tags($this->MaCPU));
        $this->MaRAM = htmlspecialchars(strip_tags($this->MaRAM));
        $this->MaCardManHinh = htmlspecialchars(strip_tags($this->MaCardManHinh));
        $this->MaROM = htmlspecialchars(strip_tags($this->MaROM));
        $this->MaManHinh = htmlspecialchars(strip_tags($this->MaManHinh));
        $this->MaMauSac = htmlspecialchars(strip_tags($this->MaMauSac));
        $this->Gia = htmlspecialchars(strip_tags($this->Gia));
        $this->SoLuong = htmlspecialchars(strip_tags($this->SoLuong));
        $this->MoTa = htmlspecialchars(strip_tags($this->MoTa));
        $this->HinhAnh = htmlspecialchars(strip_tags($this->HinhAnh));
        $this->TrangThai = htmlspecialchars(strip_tags($this->TrangThai));


        $stmt->bindParam(':MaSanPham', $this->MaSanPham);
        $stmt->bindParam(':TenSanPham', $this->TenSanPham);
        $stmt->bindParam(':MaLoaiSanPham', $this->MaLoaiSanPham);
        $stmt->bindParam(':MaHangSanXuat', $this->MaHangSanXuat);
        $stmt->bindParam(':MaCPU', $this->MaCPU);
        $stmt->bindParam(':MaRAM', $this->MaRAM);
        $stmt->bindParam(':MaCardManHinh', $this->MaCardManHinh);
        $stmt->bindParam(':MaROM', $this->MaROM);
        $stmt->bindParam(':MaManHinh', $this->MaManHinh);
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
        MaHangSanXuat = :MaHangSanXuat, 
        MaCPU = :MaCPU, 
        MaRAM = :MaRAM, 
        MaCardManHinh = :MaCardManHinh,
        MaROM = :MaROM, 
        MaManHinh = :MaManHinh, 
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
        $this->MaHangSanXuat = htmlspecialchars(strip_tags($this->MaHangSanXuat));
        $this->MaCPU = htmlspecialchars(strip_tags($this->MaCPU));
        $this->MaRAM = htmlspecialchars(strip_tags($this->MaRAM));
        $this->MaCardManHinh = htmlspecialchars(strip_tags($this->MaCardManHinh));
        $this->MaROM = htmlspecialchars(strip_tags($this->MaROM));
        $this->MaManHinh = htmlspecialchars(strip_tags($this->MaManHinh));
        $this->MaMauSac = htmlspecialchars(strip_tags($this->MaMauSac));
        $this->Gia = htmlspecialchars(strip_tags($this->Gia));
        $this->SoLuong = htmlspecialchars(strip_tags($this->SoLuong));
        $this->MoTa = htmlspecialchars(strip_tags($this->MoTa));
        $this->HinhAnh = htmlspecialchars(strip_tags($this->HinhAnh));
        $this->TrangThai = htmlspecialchars(strip_tags($this->TrangThai));
        $this->MaSanPham = htmlspecialchars(strip_tags($this->MaSanPham));


        $stmt->bindParam(':TenSanPham', $this->TenSanPham);
        $stmt->bindParam(':MaLoaiSanPham', $this->MaLoaiSanPham);
        $stmt->bindParam(':MaHangSanXuat', $this->MaHangSanXuat);
        $stmt->bindParam(':MaCPU', $this->MaCPU);
        $stmt->bindParam(':MaRAM', $this->MaRAM);
        $stmt->bindParam(':MaCardDoHoa', $this->MaCardManHinh);
        $stmt->bindParam(':MaROM', $this->MaROM);
        $stmt->bindParam(':MaManHinh', $this->MaManHinh);
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

    public function deleteSanPham()
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
