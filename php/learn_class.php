<?php
$servername = "localhost"; // 데이터베이스 호스트
$username = "hyper0616"; // 데이터베이스 ID (수정요망)
$password =  "kjh876^^"; // 데이터베이스 PW (수정요망)
$dbname =  "hyper0616"; //데이터베이스명 (수정요망)

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

$userId = $_POST['userId'];
$res = mysqli_query($conn, "select * from sugang_class join class on class.class_no2 = sugang_class.class_no2 where sugang_class.user_id = '$userId'");

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

    $class_count = (int)$row["class_count"];
    $total_day = $class_count * 7;
    $class_start = $row["class_start"];
    $today = date("Y-m-d");
    $temp_day = '';

    // 시작일로부터 경과된 일 수
    $past_day = (strtotime($today) - strtotime($class_start)) / ( 60 * 60 * 24);

    if ($past_day < 0) {
        $temp_day = $class_start;
        $past_day = 0;
    } else if ($past_day > 0) {
        $temp_day = $today;
    }

    // 종료까지 남은 일 수
    $leave_day = $total_day - $past_day;
    // class_count 빼줄 주 --> 수업한 주차 수
    $cha_q = floor($past_day / 7);
    // 남은 기간 주차 수
    $q = floor(($total_day - $past_day) / 7);
    // 남은 기간 일 수
    $r = ($total_day - $past_day) % 7;
    // class_count를 수업한 주차만큼 빼줌
    $class_count -= $cha_q;

    if ($class_count > 0) {
        array_push($result,
            array(
                'class_name' => $row["class_name"],
                'class_no2' => $row["class_no2"],
                'class_time' => "매주 " . $day1 . $day2 . $day3 . $day4 . $day5 . $day6 . $day7 . $row["class_ap"] . " " . $row["class_hour"] . "시"
            ));
    }
}

echo json_encode(array("result"=>$result));
$conn->close();
?>