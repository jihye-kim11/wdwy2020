<?php
$servername = "localhost"; // 데이터베이스 호스트
$username = "hyper0616"; // 데이터베이스 ID (수정요망)
$password =  "kjh876^^"; // 데이터베이스 PW (수정요망)
$dbname =  "hyper0616"; //데이터베이스명 (수정요망)

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
mysqli_set_charset($conn,"utf8");
if ($conn->connect_error){
	die("connect error: " . $conn->connect_error);
}

//전달 받은 data
$class_no2 = $_POST['string1'];
$time = $_POST['string2'];
$yoil = $_POST['string3'];

$sql = "UPDATE somoim SET somoim_day = '$yoil', somoim_hour = '$time'
		WHERE class_no2 = '$class_no2'";

if ($conn->query($sql) === TRUE) {
    echo "success";
} else {
    echo "Error: " . $sql . ";
" . $conn->error;
}

$conn->close(); // 디비 접속 닫기
?>