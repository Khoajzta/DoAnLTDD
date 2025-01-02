<?php
class CardDoHoa {
    private $conn;

    // Thuộc tính liên quan đến Card Đồ Họa
    public $MaCardDoHoa;
    public $TenCard;
    public $DuongLuongBoNho;
    public $MaLoaiCard;

    // Kết nối cơ sở dữ liệu
    public function __construct($database) {
        $this->conn = $database;
    }

    // Phương thức lấy tất cả Card Đồ Họa
    public function getAllCards() {
        try {
            $query = "SELECT * FROM carddohoa ORDER BY MaCardDoHoa DESC";
            $stmt = $this->conn->prepare($query);
            $stmt->execute();
            return $stmt;
        } catch (PDOException $e) {
            echo "Lỗi: " . $e->getMessage();
            return null;
        }
    }

    // Phương thức lấy Card Đồ Họa theo ID
    public function getCardById() {
        try {
            $query = "SELECT * FROM carddohoa WHERE MaCardDoHoa = ? LIMIT 1";
            $stmt = $this->conn->prepare($query);
            $stmt->bindParam(1, $this->MaCardDoHoa, PDO::PARAM_INT);
            $stmt->execute();

            $row = $stmt->fetch(PDO::FETCH_ASSOC);
            if ($row) {
                $this->TenCard = $row['TenCard'];
                $this->DuongLuongBoNho = $row['DuongLuongBoNho'];
                $this->MaLoaiCard = $row['MaLoaiCard'];
            }
        } catch (PDOException $e) {
            echo "Lỗi: " . $e->getMessage();
        }
    }

    // Phương thức thêm Card Đồ Họa
    public function addCard() {
        try {
            $query = "INSERT INTO carddohoa (TenCard, DuongLuongBoNho, MaLoaiCard) 
                      VALUES (:TenCard, :DuongLuongBoNho, :MaLoaiCard)";
            $stmt = $this->conn->prepare($query);

            // Làm sạch dữ liệu đầu vào
            $this->TenCard = htmlspecialchars(strip_tags($this->TenCard));
            $this->DuongLuongBoNho = htmlspecialchars(strip_tags($this->DuongLuongBoNho));
            $this->MaLoaiCard = htmlspecialchars(strip_tags($this->MaLoaiCard));

            // Gắn tham số
            $stmt->bindParam(':TenCard', $this->TenCard);
            $stmt->bindParam(':DuongLuongBoNho', $this->DuongLuongBoNho);
            $stmt->bindParam(':MaLoaiCard', $this->MaLoaiCard);

            return $stmt->execute();
        } catch (PDOException $e) {
            echo "Lỗi: " . $e->getMessage();
            return false;
        }
    }

    // Phương thức cập nhật Card Đồ Họa
    public function updateCard() {
        try {
            $query = "UPDATE carddohoa SET 
                      TenCard = :TenCard, 
                      DuongLuongBoNho = :DuongLuongBoNho, 
                      MaLoaiCard = :MaLoaiCard 
                      WHERE MaCardDoHoa = :MaCardDoHoa";

            $stmt = $this->conn->prepare($query);

            // Làm sạch dữ liệu
            $this->TenCard = htmlspecialchars(strip_tags($this->TenCard));
            $this->DuongLuongBoNho = htmlspecialchars(strip_tags($this->DuongLuongBoNho));
            $this->MaLoaiCard = htmlspecialchars(strip_tags($this->MaLoaiCard));
            $this->MaCardDoHoa = htmlspecialchars(strip_tags($this->MaCardDoHoa));

            // Gắn tham số
            $stmt->bindParam(':TenCard', $this->TenCard);
            $stmt->bindParam(':DuongLuongBoNho', $this->DuongLuongBoNho);
            $stmt->bindParam(':MaLoaiCard', $this->MaLoaiCard);
            $stmt->bindParam(':MaCardDoHoa', $this->MaCardDoHoa);

            return $stmt->execute();
        } catch (PDOException $e) {
            echo "Lỗi: " . $e->getMessage();
            return false;
        }
    }

    // Phương thức xóa Card Đồ Họa
    public function deleteCard() {
        try {
            $query = "DELETE FROM carddohoa WHERE MaCardDoHoa = :MaCardDoHoa";
            $stmt = $this->conn->prepare($query);

            $this->MaCardDoHoa = htmlspecialchars(strip_tags($this->MaCardDoHoa));

            $stmt->bindParam(':MaCardDoHoa', $this->MaCardDoHoa);

            return $stmt->execute();
        } catch (PDOException $e) {
            echo "Lỗi: " . $e->getMessage();
            return false;
        }
    }
}
?>
