<?php
$servername = "localhost"; // 데이터베이스 호스트
$username = "hyper0616"; // 데이터베이스 ID (수정요망)
$password =  "kjh876^^"; // 데이터베이스 PW (수정요망)
$dbname =  "hyper0616"; //데이터베이스명 (수정요망)

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

$userId = $_POST['userId'];

$sql1 = "delete from user where id = '$userId'";
$sql2 = "delete from class where user_id = '$userId'";
$sql3 = "delete from sugang_class where user_id = '$userId'";
$sql4 = "delete from notice where user_id = '$userId'";
$sql5 = "delete from question_board where question_id = '$userId'";
$sql6 = "delete from review_board where review_id = '$userId'";
$sql7 = "delete from somoim where user_id = '$userId'";
$sql8 = "delete from schedule where user_id = '$userId'";
$sql9 = "delete from developer_QA where user_id = '$userId'";

if ($conn->query($sql1) === TRUE && $conn->query($sql2) === TRUE && $conn->query($sql3) === TRUE &&
    $conn->query($sql4) === TRUE && $conn->query($sql5) === TRUE && $conn->query($sql6) === TRUE &&
    $conn->query($sql7) === TRUE && $conn->query($sql8) === TRUE && $conn->query($sql9) === TRUE) {
    echo "1";
} else {
    echo "Error: " . $conn->error;
}

$conn->close();
?>