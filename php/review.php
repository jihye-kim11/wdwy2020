<?php
$servername = "localhost"; // 데이터베이스 호스트
$username = "hyper0616"; // 데이터베이스 ID (수정요망)
$password =  "kjh876^^"; // 데이터베이스 PW (수정요망)
$dbname =  "hyper0616"; //데이터베이스명 (수정요망)

$conn = new mysqli($servername, $username, $password, $dbname);
mysqli_set_charset($conn,"utf8");
$data_stream = "'".$_POST['search']."'";
$class_no = $_POST['search'];

$res = mysqli_query($conn, "SELECT * FROM review_board
  INNER JOIN user ON review_board.review_id=user.id
  WHERE class_no='$class_no' order by board_no desc");

  $result = array();
  while($row = mysqli_fetch_array($res)){
  $image=$row["image"];
  if( empty($row["image"])){$image="http://hyper0616.dothome.co.kr/image/no_image.PNG";}
    array_push($result,
      array('user_id' =>$row["review_id"],
      'board_contents' =>$row["board_contents"],
      'comment_contents' =>$row["comment_contents"],
      'user_image' =>$image
      ));


  }
   echo json_encode(array("result"=>$result));


mysqli_close($conn); // 디비 접속 닫기

?>
