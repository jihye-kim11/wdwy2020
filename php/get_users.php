<?php
$servername = "localhost"; // 데이터베이스 호스트
$username = "hyper0616"; // 데이터베이스 ID (수정요망)
$password =  "kjh876^^"; // 데이터베이스 PW (수정요망)
$dbname =  "hyper0616"; //데이터베이스명 (수정요망)

$conn = new mysqli($servername, $username, $password, $dbname);
mysqli_set_charset($conn,"utf8");
$data_stream = "'".$_POST['search']."'";
$class_no2_ = $_POST['search'];

//$class_no2_ = 4;
//$user_id = 1524618196;

$res = mysqli_query($conn, "SELECT * FROM schedule
		WHERE class_no2='$class_no2_'");
		//WHERE schedule.class_no=4 AND schedule.user_id=1524618196");

if (mysqli_num_rows($res) > 0) {
    while($row = mysqli_fetch_assoc($res)) {
        echo  $row["user_id"]. " ";
    }
}
else{
    "fail to get users";
}
mysqli_close($conn); // 디비 접속 닫기

/*
$result = array();
while($row = mysqli_fetch_array($res)){
	array_push($result,
			array('id' =>$row["user_id"]));
  }
  echo json_encode(array("result"=>$result));
*/

?>