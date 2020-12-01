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
$user_id = $_POST['userId'];
$nickname_ = $_POST['nickname'];
$user_image = $_POST['userImage'];


$res = mysqli_query($conn, "SELECT * FROM user where id = '$user_id'");
if (mysqli_num_rows($res) > 0) {
    while($row = mysqli_fetch_assoc($res)) {
        echo $row["user_id"]."//" ;
    }
}
else {
	$sql = "INSERT INTO user (id, name, image, phoneNumber, birth, gender, type, password, token) 
		VALUES ('$user_id','$nickname_','$user_image','','','',0,'',NULL)";
		
	if ($conn->query($sql) === TRUE) {
		echo "1";
	}
	else{
		echo "FAIL: ". $sql . $conn->error;;
	}
}

//echo json_encode($result, JSON_UNESCAPED_UNICODE);

$conn->close(); // 디비 접속 닫기
?>