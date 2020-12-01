<?php
$servername = "localhost"; // 데이터베이스 호스트
$username = "hyper0616"; // 데이터베이스 ID (수정요망)
$password =  "kjh876^^"; // 데이터베이스 PW (수정요망)
$dbname =  "hyper0616"; //데이터베이스명 (수정요망)

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

$user_id_ = $_POST['userId'];
$password_ = $_POST['password'];
$password_2_ = $_POST['password_2'];
$ph_ = $_POST['ph'];

if ($password_ == $password_2_) {
    $sql = "update user set password = '$password_', phoneNumber = '$ph_' where id = '$user_id_'";

    if ($conn->query($sql) === TRUE) {
        echo "1";
    } else {
        echo "Error: " . $sql . $conn->error;
    }
}

$conn->close();
?>