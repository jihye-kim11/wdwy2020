<?php
$servername = "localhost"; // 데이터베이스 호스트
$username = "hyper0616"; // 데이터베이스 ID (수정요망)
$password =  "kjh876^^"; // 데이터베이스 PW (수정요망)
$dbname =  "hyper0616"; //데이터베이스명 (수정요망)

$conn = new mysqli($servername, $username, $password, $dbname);
mysqli_set_charset($conn,"utf8");
//board_no과 답변 저장
$data_stream = "'".$_POST['string1']."','".$_POST['string2']."'";
$no_=$_POST['string1'];
$no=(int)$no_;
$answer = $_POST['string2'];


$sql = "UPDATE question_board SET comment_contents='$answer' WHERE board_no='$no'";
if ($conn->query($sql) === TRUE) {
    echo "1";
} else {
    echo "Error: " . $sql . ";
" . $conn->error;
}

$conn->close();

?>
