<?php
$servername = "localhost"; // 데이터베이스 호스트
$username = "hyper0616"; // 데이터베이스 ID (수정요망)
$password =  "kjh876^^"; // 데이터베이스 PW (수정요망)
$dbname =  "hyper0616"; //데이터베이스명 (수정요망)

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
$data_stream = "'".$_POST['num1']."','".$_POST['num2']."','".$_POST['text1']."','".$_POST['text2']."'";

$num1_=$_POST['num1'];
$num1 =(int)$num1_;
$num2_=$_POST['num2'];
$num2 =(int)$num2_;
$text2 = $_POST['text2'];


$query="SELECT sugang_no FROM sugang_class ORDER BY sugang_no desc limit 1";
$result = mysqli_query($conn, $query);
if (mysqli_num_rows($result) > 0) {
while($row = mysqli_fetch_assoc($result)) {
  $total_rows=$row["sugang_no"];
}}
$new_rows=(int)$total_rows +1;

$sql = "INSERT INTO sugang_class (sugang_no,class_no,class_no2,user_id)
VALUES ('$new_rows','$num1','$num2','$text2')";




if ($conn->query($sql) === TRUE) {
    echo "1" ;
} else {
  echo  "Error: " . $conn->error;
}

$conn->close();
?>
