<?php
$servername = "localhost"; // 데이터베이스 호스트
$username = "hyper0616"; // 데이터베이스 ID (수정요망)
$password =  "kjh876^^"; // 데이터베이스 PW (수정요망)
$dbname =  "hyper0616"; //데이터베이스명 (수정요망)

$conn = new mysqli($servername, $username, $password, $dbname);
mysqli_set_charset($conn,"utf8");
$data_stream = "'".$_POST['search']."'";
$login = $_POST['search'];

$res = mysqli_query($conn, "SELECT * FROM class
  INNER JOIN question_board ON class.class_no=question_board.class_no
    WHERE user_id='$login' order by user_id desc");//user_id가 로그인한 아이디인 클래스들에 대한 질문 목


  $result = array();     // 'class_image' =>$image,
  while($row = mysqli_fetch_array($res)){
      if( empty($row["comment_contents"])){
      array_push($result,
      array( 'class_qa'=>$row["board_contents"],
      'user_name' =>$row["question_id"],
      'board_no' =>$row["board_no"],
      'class_image' =>$row["class_image"],
      'class_name' =>$row["class_name"]
      ));

    }
  }
   echo json_encode(array("result"=>$result));


mysqli_close($conn); // 디비 접속 닫기

?>
