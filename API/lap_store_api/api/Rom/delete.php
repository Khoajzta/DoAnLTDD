<?php
    header('Access-Control-Allow-Origin: *');
    header('Content-Type: application/json');
    header('Access-Control-Allow-Methods: DELETE');
    header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type, Access-Control-Allow-Methods, Authorization, X-Requested-With');

    include_once('../../config/database.php');
    include_once('../../model/diachi.php');

    // Tạo đối tượng database và kết nối
    $database = new Database();
    $conn = $database->Connect(); // Lấy kết nối PDO

    // Khởi tạo lớp Rom với kết nối PDO
    $rom = new Rom($conn);

    // Lấy dữ liệu từ body request
    $data = json_decode(file_get_contents("php://input"));

    if (!isset($data->MaROM)) {
        echo json_encode(array('message' => 'Dữ liệu không hợp lệ.'));
        exit();
    }

    // Gán giá trị cho thuộc tính MaROM
    $rom->MaROM = $data->MaROM;

    // Thực hiện xóa
    if ($rom->deleteRom()) {
        echo json_encode(array('message' => 'Xóa thành công.'));
    } else {
        echo json_encode(array('message' => 'Xóa thất bại.'));
    }
?>
