<?php
$servername = "localhost"; // 데이터베이스 호스트
$username = "hyper0616"; // 데이터베이스 ID (수정요망)
$password =  "kjh876^^"; // 데이터베이스 PW (수정요망)
$dbname =  "hyper0616"; //데이터베이스명 (수정요망)

$conn = new mysqli($servername, $username, $password, $dbname);
mysqli_set_charset($conn,"utf8");
//board_no, class_no, review_id, board_contents,comment_contents
$data_stream = "'".$_POST['string1']."','".$_POST['string2']."',
              '".$_POST['string3']."','".$_POST['string4']."',
              '".$_POST['string5']."'";

$class_no_=$_POST['string2']; //sugang_no
$class_no=(int)$class_no_;
$review_id = $_POST['string3'];
$board_contents = $_POST['string4'];
$comment_contents = $_POST['string5'];


//현재 리뷰게시판 리뷰수 체크 보드넘버 만들기
//$query = "SELECT * FROM review_board";
$query="SELECT board_no FROM review_board ORDER BY board_no desc limit 1";
$result = mysqli_query($conn, $query);
if (mysqli_num_rows($result) > 0) {
while($row = mysqli_fetch_assoc($result)) {
  $total_rows=$row["board_no"];
}}
$new_rows=(int)$total_rows +1;

$sql = "INSERT INTO review_board
  (board_no, class_no, review_id, board_contents,comment_contents)
  VALUES ('$new_rows','$class_no','$review_id','$board_contents','$comment_contents')";

if ($conn->query($sql) === TRUE) {
  $sql2="UPDATE sugang_class SET review='1' WHERE class_no='$class_no' and user_id='$review_id'";
  if($conn->query($sql2) === TRUE)
    {echo "1";}
} else {
    echo "Error: " . $sql . ";
" . $conn->error;
}

$conn->close();

?>
