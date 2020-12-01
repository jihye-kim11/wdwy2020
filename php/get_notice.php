<?php
$servername = "localhost"; // 데이터베이스 호스트
$username = "hyper0616"; // 데이터베이스 ID (수정요망)
$password =  "kjh876^^"; // 데이터베이스 PW (수정요망)
$dbname =  "hyper0616"; //데이터베이스명 (수정요망)

$conn = new mysqli($servername, $username, $password, $dbname);
mysqli_set_charset($conn,"utf8");
$data_stream = "'".$_POST['search']."'";
$class_no = $_POST['search'];

$res = mysqli_query($conn, "SELECT * FROM notice
	WHERE notice.class_no='$class_no'
	ORDER BY notice.notice_no");

//'$class_no'

  $result = array();
  while($row = mysqli_fetch_array($res)){
    //$title=$row["notice_title"];
    //$writer=$row["user_id"];
    //if( empty($row["notice_title"])){$title="등록된 공지가 없습니다";}
    //  if( empty($row["user_id"])){$writer="X";}
    array_push($result,
      array('title' =>$row["notice_title"],
	  'writer' =>$row["name"]
      ));

  }
   echo json_encode(array("result"=>$result));


mysqli_close($conn); // 디비 접속 닫기

?>
