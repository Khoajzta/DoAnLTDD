<?php
    header('Access-Control-Allow-Origin: *');
    header('Content-Type: application/json');
    header('Access-Control-Allow-Methods: DELETE');
    header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type, Access-Control-Allow-Methods, Authorization, X-Requested-With');

    include_once('../../config/database.php');
    include_once('../../model/nhacungcap.php');  // Đảm bảo bạn sử dụng model đúng

    // Tạo đối tượng database và kết nối
    $database = new Database();
    $conn = $database->Connect(); // Lấy kết nối PDO

    // Khởi tạo lớp NhaCungCap với kết nối PDO
    $nhacungcap = new NhaCungCap($conn);

    // Lấy dữ liệu từ body request
    $data = json_decode(file_get_contents("php://input"));

    // Kiểm tra nếu Mã nhà cung cấp không tồn tại trong dữ liệu
    if (!isset($data->MaNCC)) {
        echo json_encode(array('message' => 'Dữ liệu không hợp lệ.'));
        exit();
    }

    // Gán giá trị cho thuộc tính MaNCC
    $nhacungcap->MaNCC = $data->MaNCC;

    // Thực hiện xóa nhà cung cấp
    if ($nhacungcap->deleteNhaCungCap()) {
        echo json_encode(array('message' => 'Xóa nhà cung cấp thành công.'));
    } else {
        echo json_encode(array('message' => 'Xóa nhà cung cấp thất bại.'));
    }
?>
