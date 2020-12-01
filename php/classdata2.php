<?php
$servername = "localhost"; // 데이터베이스 호스트
$username = "hyper0616"; // 데이터베이스 ID (수정요망)
$password =  "kjh876^^"; // 데이터베이스 PW (수정요망)
$dbname =  "hyper0616"; //데이터베이스명 (수정요망)

$conn = new mysqli($servername, $username, $password, $dbname);
mysqli_set_charset($conn,"utf8");
$data_stream = "'".$_POST['search']."'";
$class_no2 = $_POST['search'];

$res = mysqli_query($conn, "SELECT * FROM class
  WHERE  class_no2='$class_no2'");

  $result = array();
  while($row = mysqli_fetch_array($res)){
    $image=$row["info_image1"];
    if( empty($row["info_image1"])){$image="http://hyper0616.dothome.co.kr/image/commingsoon.PNG";}
    array_push($result,
      array('info_image1' =>$image
      ));
  }
   echo json_encode(array("result"=>$result));


mysqli_close($conn); // 디비 접속 닫기

?>
