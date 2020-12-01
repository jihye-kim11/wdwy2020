<?php
$servername = "localhost"; // 데이터베이스 호스트
$username = "hyper0616"; // 데이터베이스 ID (수정요망)
$password =  "kjh876^^"; // 데이터베이스 PW (수정요망)
$dbname =  "hyper0616"; //데이터베이스명 (수정요망)

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
$data_stream = "'".$_POST['num1']."','".$_POST['num2']."','".$_POST['text1']."','".$_POST['text2']."'";

$num1=$_POST['num1'];
$text1_ = $_POST['text1'];
$text1 =(int)$text1_;
$text2 = $_POST['text2'];


//현재 보드게시판 보드수체크 보드넘버 만들기
//$query = "SELECT * FROM question_board";
//$data = mysqli_query($conn, $query);
//$total_rows= mysqli_num_rows($data);
$query="SELECT board_no FROM question_board ORDER BY board_no desc limit 1";
$result = mysqli_query($conn, $query);
if (mysqli_num_rows($result) > 0) {
while($row = mysqli_fetch_assoc($result)) {
  $total_rows=$row["board_no"];
}}
$new_rows=(int)$total_rows +1;

$sql = "INSERT INTO question_board (board_contents,class_no,question_id,board_no)
VALUES ('$num1','$text1','$text2','$new_rows')";




if ($conn->query($sql) === TRUE) {
    echo "1" ;
} else {
  echo  "Error: " . $conn->error;
}

$conn->close();
?>
