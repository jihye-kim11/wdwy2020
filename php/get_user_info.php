<?php
$servername = "localhost"; // 데이터베이스 호스트
$username = "hyper0616"; // 데이터베이스 ID (수정요망)
$password =  "kjh876^^"; // 데이터베이스 PW (수정요망)
$dbname =  "hyper0616"; //데이터베이스명 (수정요망)

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

$userId = $_POST['userId'];
$res = mysqli_query($conn, "select user.name, user.image, user.password, user.alarm_onoff from user where user.id = '$userId'");

$result = array(); 

while($row = mysqli_fetch_array($res)){

    array_push($result,
        array('name' =>$row["name"],
            'image' =>$row["image"],
            'password' =>$row["password"],
            'alarm' =>$row["alarm_onoff"]
        ));
}

echo json_encode(array("result"=>$result));
$conn->close();
?>