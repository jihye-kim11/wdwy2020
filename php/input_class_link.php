<?php
//강의 링크 저장 코드

$servername = "localhost"; // 데이터베이스 호스트
$username = "hyper0616"; // 데이터베이스 ID (수정요망)
$password =  "kjh876^^"; // 데이터베이스 PW (수정요망)
$dbname =  "hyper0616"; //데이터베이스명 (수정요망)
	
$conn = new mysqli($servername, $username, $password, $dbname);
mysqli_set_charset($conn,"utf8");
$data_stream = "'".$_POST['search']."'";
$class_link = $_POST['string1'];
$class_no2 = $_POST['string2'];

if ($conn->connect_error){
	die("connection failed: " . $conn->connect_error);
}
	
//mysql_query("set session character_set_connection=utf8;");
//mysql_query("set session character_set_results=utf8;");
//mysql_query("set session character_set_client=utf8;");


$result = "UPDATE sugang_class SET class_link = '$class_link' WHERE  class_no2='$class_no2'";


if ($conn->query($result) === TRUE) {
    echo "successfully input the lecture link.";
}
else{
    echo "fail";
}

//echo json_encode($result, JSON_UNESCAPED_UNICODE);

mysqli_close($conn); // 디비 접속 닫기
?>