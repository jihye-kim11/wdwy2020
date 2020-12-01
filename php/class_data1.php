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
//class_name,user_name,class_categey,class_price,클래스 인원, 클랫 시간, 클래스 횟수, 시작일,시작요일
$result = array();
  while($row = mysqli_fetch_array($res)){
    $day1="";
    $day2="";
    $day3="";
    $day4="";
    $day5="";
    $day6="";
    $day7="";
    $mon=(int)$row["class_dayweek_mon"];
    if( $mon==1){$day1="월요일 ";}
    $tue=(int)$row["class_dayweek_tue"];
    if( $tue==1){$day2="화요일 ";}
    $wen=(int)$row["class_dayweek_wen"];
    if( $wen==1){$day3="수요일 ";}
    $thu=(int)$row["class_dayweek_thu"];
    if( $thu==1){$day4="목요일 ";}
    $fri=(int)$row["class_dayweek_fri"];
    if( $fri==1){$day5="금요일 ";}
    $sat=(int)$row["class_dayweek_sat"];
    if( $sat==1){$day6="토요일 ";}
    $sun=(int)$row["class_dayweek_sun"];
    if( $sun==1){$day7="일요일 ";}


    array_push($result,
      array('class_name' =>$row["class_name"],
      'class_no' =>$row["class_no"],
      'class_no2' =>$row["class_no2"],
      'class_image' =>$row["class_image"],
      'user_name' =>$row["user_name"],
      'class_time' =>"매주 ".$day1.$day2.$day3.$day4.$day5.$day6.$day7. $row["class_ap"]." ". $row["class_hour"]."시",
      'class_price' => $row["class_price"],
      'class_start' => "개강일: ".$row["class_start"],
      'class_category' => $row["class_category"],
      'class_count'=>$row["class_count"]."주 진행",
      'class_runningtime'=>"시간  ".$row["class_runningtime"]."시간",
      'class_max_student'=>"인원  ".$row["class_max_student"].":1"
      ));


  }
   echo json_encode(array("result"=>$result));

mysqli_close($conn); // 디비 접속 닫기

?>
