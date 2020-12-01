<?php
$servername = "localhost"; // 데이터베이스 호스트
$username = "hyper0616"; // 데이터베이스 ID (수정요망)
$password =  "kjh876^^"; // 데이터베이스 PW (수정요망)
$dbname =  "hyper0616"; //데이터베이스명 (수정요망)

$conn = new mysqli($servername, $username, $password, $dbname);
mysqli_set_charset($conn,"utf8");
$class_no = $_POST['string1'];
$title = $_POST['string2'];

$res = mysqli_query($conn, "SELECT * FROM notice
	WHERE notice_title = '$title'");
	
	// AND class_no2='$class_no'

if (mysqli_num_rows($res) > 0) {
    while($row = mysqli_fetch_assoc($res)) {
        echo  $row["notice_contents"];
    }
}
else{
    "fail to get notice content";
}
mysqli_close($conn); // 디비 접속 닫기

?>
