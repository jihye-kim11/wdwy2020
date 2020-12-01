<?php
$servername = "localhost"; // 데이터베이스 호스트
$username = "hyper0616"; // 데이터베이스 ID (수정요망)
$password =  "kjh876^^"; // 데이터베이스 PW (수정요망)
$dbname =  "hyper0616"; //데이터베이스명 (수정요망)
	
$conn = new mysqli($servername, $username, $password, $dbname);
mysqli_set_charset($conn,"utf8");
$data_stream = "'".$_POST['search']."'";
//$class = $_POST['search'];
$class_no2 = $_POST['search'];

if ($conn->connect_error){
	die("connection failed: " . $conn->connect_error);
}

$result = mysqli_query($conn, "SELECT * FROM sugang_class WHERE class_no2='$class_no2'");	

if (mysqli_num_rows($result) > 0) {
    while($row = mysqli_fetch_assoc($result)) {
        echo $row["class_link"] ;
		break;
    }
}
else{
    echo "no lecture link.";
}

$conn->close();
?>