<?php
$servername = "localhost"; // 데이터베이스 호스트
$username = "hyper0616"; // 데이터베이스 ID (수정요망)
$password =  "kjh876^^"; // 데이터베이스 PW (수정요망)
$dbname =  "hyper0616"; //데이터베이스명 (수정요망)

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

$userId = $_POST['userId'];
// class 일정
$res = mysqli_query($conn, "select * from sugang_class join class on class.class_no2 = sugang_class.class_no2 where sugang_class.user_id = '$userId'");
// 소모임 일정
//$res_ = mysqli_query($conn, "select * from somoim join class on class.class_no2 = somoim.class_no2 where somoim.user_id = '$userId'");
$res_ = mysqli_query($conn, "select * from somoim join class on class.class_no2 = somoim.class_no2 where somoim.user_id = '$userId'");

$result = array();

// class 일정
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
        // 남은 일 수가 있을 때
        if ($r != 0) {
            $temp_day = date("Y-m-d", strtotime("+" . $r . " days", strtotime($today)));
            $class_count -= 1;

            array_push($result,
                array(
                    'class_name' => $row["class_name"] . " 강의",
                    'class_no2' => $row["class_no2"],
                    'class_time' => $temp_day . " " . $day1 . $day2 . $day3 . $day4 . $day5 . $day6 . $day7 . " " . $row["class_ap"] . " " . $row["class_hour"] . "시"
                ));


            while ($class_count > 0) {
                $temp_day = date("Y-m-d", strtotime("+7 days", strtotime($temp_day)));
                $class_count -= 1;

                array_push($result,
                    array(
                        'class_name' => $row["class_name"] . " 강의",
                        'class_no2' => $row["class_no2"],
                        'class_time' => $temp_day . " " . $day1 . $day2 . $day3 . $day4 . $day5 . $day6 . $day7 . " " . $row["class_ap"] . " " . $row["class_hour"] . "시"
                    ));
            }
        } // 남은 날짜 없을 때
        else if ($r == 0) {
            array_push($result,
                array(
                    'class_name' => $row["class_name"] . " 강의",
                    'class_no2' => $row["class_no2"],
                    'class_time' => $temp_day . " " . $day1 . $day2 . $day3 . $day4 . $day5 . $day6 . $day7 . " " . $row["class_ap"] . " " . $row["class_hour"] . "시"
                ));
            $class_count -= 1;

            while ($class_count > 0) {
                $temp_day = date("Y-m-d", strtotime("+7 days", strtotime($temp_day)));
                $class_count -= 1;

                array_push($result,
                    array(
                        'class_name' => $row["class_name"] . " 강의",
                        'class_no2' => $row["class_no2"],
                        'class_time' => $temp_day . " " . $day1 . $day2 . $day3 . $day4 . $day5 . $day6 . $day7 . " " . $row["class_ap"] . " " . $row["class_hour"] . "시"
                    ));
            }
        }
    }
}

// 소모임 일정
//while ($row = mysqli_fetch_array($res_)) {
//    $temp_y = $row["somoim_year"];
//    $temp_m = $row["somoim_month"];
//    $temp_d = $row["somoim_day"];
//
//    $y = (string)$temp_y;
//    $m = (string)$temp_m;
//    $d = (string)$temp_d;
//
//    if ($temp_m < 10) $m = '0'+$temp_m;
//    if ($temp_d < 10) $m = '0'+$temp_d;
//
//    $temp_date = $temp_y."-".$temp_m."-".$temp_d;
//    $today = date("Y-m-d");
//
//    // 데이터베이스 내 이미 지난 시간일 수 있으므로
//    $interval = strtotime($temp_date) - strtotime($today);
//
//    if ($interval >= 0) {
//        $yoil = array("일","월","화","수","목","금","토");
//        $day = $yoil[date('w', strtotime($temp_date))];
//
//        array_push($result,
//            array(
//                'class_name' => $row["class_name"]. " 소모임",
//                'class_no2' => $row["class_no2"],
//                'class_time' => $temp_date . " ". $day ."요일  " . $row["somoim_ap"]. " ". $row["somoim_hour"]. "시"
//            ));
//    }
//
//}

// 정렬 함수
function arr_sort($array, $key, $sort='asc') //정렬대상 array, 정렬 기준 key, 오름/내림차순
{
    $keys = array();
    $vals = array();
    foreach ($array as $k=>$v)
    {
        $i = $v[$key].'.'.$k;
        $vals[$i] = $v;
        array_push($keys, $k);
    }
    unset($array);

    if ($sort=='asc') {
        ksort($vals);
    } else {
        krsort($vals);
    }

    $ret = array_combine($keys, $vals);
    unset($keys);
    unset($vals);

    return $ret;

}

// 날짜 순으로 정렬
$result = arr_sort($result, 'class_time');


echo json_encode(array("result"=>$result));
$conn->close();
?>