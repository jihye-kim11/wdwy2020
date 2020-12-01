<?php
$servername = "localhost"; // 데이터베이스 호스트
$username = "hyper0616"; // 데이터베이스 ID (수정요망)
$password =  "kjh876^^"; // 데이터베이스 PW (수정요망)
$dbname =  "hyper0616"; //데이터베이스명 (수정요망)

$conn = new mysqli($servername, $username, $password, $dbname);
mysqli_set_charset($conn,"utf8");
$data_stream = "'".$_POST['search']."'";
$class_no2_ = $_POST['class_no2'];
$userId_ = $_POST['userId'];

//$class_no2 = 4;
//$user_id = 1524618196;

$res = mysqli_query($conn, "SELECT * FROM schedule
		WHERE class_no2='$class_no2_' AND user_id='$userId_'");
		//WHERE schedule.class_no=4 AND schedule.user_id=1524618196");

$result = array();
while($row = mysqli_fetch_array($res)){
	array_push($result,
			array('mon_' =>$row["user_schedule_mon"],
				'tue_' =>$row["user_schedule_tue"],
				'wed_' =>$row["user_schedule_wed"],
				'thu_' =>$row["user_schedule_thu"],
				'fri_' =>$row["user_schedule_fri"],
				'sat_' =>$row["user_schedule_sat"],
				'sun_' =>$row["user_schedule_sun"]
      ));
  }
  echo json_encode(array("result"=>$result));


mysqli_close($conn); // 디비 접속 닫기

?>