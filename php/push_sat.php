<?php
//강의 링크 저장 코드

$servername = "localhost"; // 데이터베이스 호스트
$username = "hyper0616"; // 데이터베이스 ID (수정요망)
$password =  "kjh876^^"; // 데이터베이스 PW (수정요망)
$dbname =  "hyper0616"; //데이터베이스명 (수정요망)
	
$conn = new mysqli($servername, $username, $password, $dbname);
mysqli_set_charset($conn,"utf8");
$data_stream = "'".$_POST['search']."'";
$class_no2_ = $_POST['class_no2'];
$userId_ = $_POST['userId'];
$schedule_ = $_POST['schedule'];


if ($conn->connect_error){
	die("connection failed: " . $conn->connect_error);
}
	
//mysql_query("set session character_set_connection=utf8;");
//mysql_query("set session character_set_results=utf8;");
//mysql_query("set session character_set_client=utf8;");


$result = "UPDATE schedule SET user_schedule_sat='$schedule_'
			WHERE class_no2='$class_no2_' AND user_id='$userId_'";


if ($conn->query($result) === TRUE) {
    echo "successfully input the schedule.";
}
else{
    echo "fail";
}


mysqli_close($conn); // 디비 접속 닫기
?>