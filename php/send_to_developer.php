<?php
$servername = "localhost"; // 데이터베이스 호스트
$username = "hyper0616"; // 데이터베이스 ID (수정요망)
$password =  "kjh876^^"; // 데이터베이스 PW (수정요망)
$dbname =  "hyper0616"; //데이터베이스명 (수정요망)

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

$user_id_ = $_POST['userId'];
$title_ = $_POST['title'];
$email_ = $_POST['email'];
$content_ = $_POST['content'];

//$sql = "update user set password = '$password_', phoneNumber = '$ph_' where id = '$user_id_'";
$sql = "insert into developer_QA (user_id, user_email, question_title, question_contents) values ('$user_id_', '$email_', '$title_', '$content_')";

if ($conn->query($sql) === TRUE) {
    echo "1";
} else {
    echo "Error: " . $sql . $conn->error;
}
$conn->close();
?>