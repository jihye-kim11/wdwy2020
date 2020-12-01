<?php
$servername = "localhost"; // 데이터베이스 호스트
$username = "hyper0616"; // 데이터베이스 ID (수정요망)
$password =  "kjh876^^"; // 데이터베이스 PW (수정요망)
$dbname =  "hyper0616"; //데이터베이스명 (수정요망)

$conn = new mysqli($servername, $username, $password, $dbname);
mysqli_set_charset($conn,"utf8");
$data_stream = "'".$_POST['search']."'";
$login = $_POST['search'];
//user_id가 로그인한 아이디의 수강중인 클래스 목록
$res = mysqli_query($conn,
 "SELECT * FROM class
  INNER JOIN sugang_class ON class.class_no2=sugang_class.class_no2
  WHERE sugang_class.user_id='$login'
  ");
  $result = array();

  while($row = mysqli_fetch_array($res)){
      if( empty($row["review"])){
      array_push($result,
      array(
      'class_no' =>$row["class_no"],
      'class_image' =>$row["class_image"],
      'class_name' =>$row["class_name"]
      ));

    }
  }
   echo json_encode(array("result"=>$result));


mysqli_close($conn); // 디비 접속 닫기

?>
