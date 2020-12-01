<?php
$servername = "localhost"; // 데이터베이스 호스트
$username = "hyper0616"; // 데이터베이스 ID (수정요망)
$password =  "kjh876^^"; // 데이터베이스 PW (수정요망)
$dbname =  "hyper0616"; //데이터베이스명 (수정요망)

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

$user_id_ = $_POST['userId'];
$alarm_ = $_POST['alarm'];

if ($alarm_ == 1) {
    $sql = "update user set alarm_onoff = 0 where id = '$user_id_'";

    if ($conn->query($sql) === TRUE) {
        echo "1";
    } else {
        echo "Error: " . $sql . $conn->error;
    }
} else if ($alarm_ == 0) {
    $sql = "update user set alarm_onoff = 1 where id = '$user_id_'";

    if ($conn->query($sql) === TRUE) {
        echo "1";
    } else {
        echo "Error: " . $sql . $conn->error;
    }
}

$conn->close();
?>