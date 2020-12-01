<?php
//강의 링크 저장 코드

$servername = "localhost"; // 데이터베이스 호스트
$username = "hyper0616"; // 데이터베이스 ID (수정요망)
$password =  "kjh876^^"; // 데이터베이스 PW (수정요망)
$dbname =  "hyper0616"; //데이터베이스명 (수정요망)
	
$conn = new mysqli($servername, $username, $password, $dbname);
mysqli_set_charset($conn,"utf8");
if ($conn->connect_error){
	die("connection failed: " . $conn->connect_error);
}
	
$class_no2_ = $_POST['class_no2'];
$notice_title_ = $_POST['notice_title'];
$notice_writer_ = $_POST['notice_writer'];
$notice_contents_ = $_POST['notice_contents'];
$user_name_ = $_POST['user_name'];


//mysql_query("set session character_set_connection=utf8;");
//mysql_query("set session character_set_results=utf8;");
//mysql_query("set session character_set_client=utf8;");


	//VALUES ('$notice_title_','$notice_writer_','$notice_contents_','','') 
$result = mysqli_query($conn, "INSERT INTO notice (notice_title, user_id, notice_contents, class_no2, class_no, name) 
	VALUES ('$notice_title_','$notice_writer_','$notice_contents_', '$class_no2_ ', '$class_no2_ ', '$user_name_')");

if ($result === TRUE) {
    echo "successfully input notice.";
}
else{
    echo "FAIL: ". $sql . $conn->error;
}

//echo json_encode($result, JSON_UNESCAPED_UNICODE);

mysqli_close($conn); // 디비 접속 닫기
?>