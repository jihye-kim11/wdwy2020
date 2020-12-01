<?php
//강의명 가져오는 코드 : class page 제목

$servername = "localhost"; // 데이터베이스 호스트
$username = "hyper0616"; // 데이터베이스 ID (수정요망)
$password =  "kjh876^^"; // 데이터베이스 PW (수정요망)
$dbname =  "hyper0616"; //데이터베이스명 (수정요망)

$conn = new mysqli($servername, $username, $password, $dbname);
mysqli_set_charset($conn,"utf8");
$data_stream = "'".$_POST['search']."'";
//$class = $_POST['search'];
$userId = $_POST['search'];

//if ($conn->connect_error){
   //die("connection failed: " . $conn->connect_error);
//}

//mysql_query("set session character_set_connection=utf8;");
//mysql_query("set session character_set_results=utf8;");
//mysql_query("set session character_set_client=utf8;");

$result = mysqli_query($conn, "SELECT * FROM user WHERE id='$userId'");

if (mysqli_num_rows($result) > 0) {
    while($row = mysqli_fetch_assoc($result)) {
        echo  $row["name"];
    }
}
else{
    "fail to get user name";
}
mysqli_close($conn); // 디비 접속 닫기

?>