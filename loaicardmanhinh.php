<?php
class loaicardmanhinh {
    private $conn;

    // Attributes
    public $MaLoaiCard;
    public $TenLoaiCard;

    // Connect to database
    public function __construct($database) {
        $this->conn = $database;
    }

    // Get all card types
    public function getAllLoaiCard() {
        $query = "SELECT * FROM loaicard"; 
        $stmt = $this->conn->prepare($query);
        $stmt->execute();
        return $stmt; // Return PDOStatement
    }

    // Get card type by ID
    public function getLoaiCardById() {
        $query = "SELECT * FROM loaicard WHERE MaLoaiCard = ? LIMIT 1"; 
        $stmt = $this->conn->prepare($query);
        $stmt->bindParam(1, $this->MaLoaiCard);
        $stmt->execute();
        $row = $stmt->fetch(PDO::FETCH_ASSOC);

        // Assign data to class properties
        $this->MaLoaiCard = $row['MaLoaiCard'];
        $this->TenLoaiCard = $row['TenLoaiCard'];
    }

    // Add new card type
    public function addLoaiCard() {
        $query = "INSERT INTO loaicard SET MaLoaiCard=:MaLoaiCard, TenLoaiCard=:TenLoaiCard";

        $stmt = $this->conn->prepare($query);

        // Sanitize inputs
        $this->MaLoaiCard = htmlspecialchars(strip_tags($this->MaLoaiCard));
        $this->TenLoaiCard = htmlspecialchars(strip_tags($this->TenLoaiCard));

        // Bind parameters
        $stmt->bindParam(':MaLoaiCard', $this->MaLoaiCard);
        $stmt->bindParam(':TenLoaiCard', $this->TenLoaiCard);

        // Execute query
        if ($stmt->execute()) {
            return true;
        }
        printf("Error: %s.\n", $stmt->error);
        return false;
    }

    // Update card type
    public function updateLoaiCard() {
        $query = "UPDATE loaicard SET TenLoaiCard=:TenLoaiCard WHERE MaLoaiCard=:MaLoaiCard";

        $stmt = $this->conn->prepare($query);

        // Sanitize inputs
        $this->MaLoaiCard = htmlspecialchars(strip_tags($this->MaLoaiCard));
        $this->TenLoaiCard = htmlspecialchars(strip_tags($this->TenLoaiCard));

        // Bind parameters
        $stmt->bindParam(':MaLoaiCard', $this->MaLoaiCard);
        $stmt->bindParam(':TenLoaiCard', $this->TenLoaiCard);

        // Execute query
        if ($stmt->execute()) {
            return true;
        }
        printf("Error: %s.\n", $stmt->error);
        return false;
    }

    // Delete card type
    public function deleteLoaiCard() {
        $query = "DELETE FROM loaicard WHERE MaLoaiCard=:MaLoaiCard";

        $stmt = $this->conn->prepare($query);

        $this->MaLoaiCard = htmlspecialchars(strip_tags($this->MaLoaiCard));

        // Bind parameters
        $stmt->bindParam(':MaLoaiCard', $this->MaLoaiCard);

        // Execute query
        if ($stmt->execute()) {
            return true;
        }
        printf("Error: %s.\n", $stmt->error);
        return false;
    }
}
?>
