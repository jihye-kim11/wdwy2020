<?php
$servername = "localhost"; // 데이터베이스 호스트
$username = "hyper0616"; // 데이터베이스 ID (수정요망)
$password =  "kjh876^^"; // 데이터베이스 PW (수정요망)
$dbname =  "hyper0616"; //데이터베이스명 (수정요망)

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

$userId = $_POST['userId'];
// class 일정
$res = mysqli_query($conn, "select * from class where class.user_id = '$userId'");

$result = array();

// class 일정
while($row = mysqli_fetch_array($res)){

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

            while ($class_count > 0) {
                $temp_day = date("Y-m-d", strtotime("+7 days", strtotime($temp_day)));
                $class_count -= 1;

                if ($class_count == 0) {
                    $p = (strtotime($temp_day) - strtotime($today)) / ( 60 * 60 * 24);
                    if ($p > 0) {
                        array_push($result,
                            array(
                                'class_name' => $row["class_name"] . " 강의",
                                'class_no2' => $row["class_no2"],
                                'class_time' => $temp_day
                            ));
                    }
                }
            }
        } // 남은 날짜 없을 때
        else if ($r == 0) {
            $class_count -= 1;

            while ($class_count > 0) {
                $temp_day = date("Y-m-d", strtotime("+7 days", strtotime($temp_day)));
                $class_count -= 1;

                if ($class_count == 0) {
                    $p = (strtotime($temp_day) - strtotime($today)) / ( 60 * 60 * 24);
                    if ($p > 0) {
                        array_push($result,
                            array(
                                'class_name' => $row["class_name"] . " 강의",
                                'class_no2' => $row["class_no2"],
                                'class_time' => $temp_day
                            ));
                    }
                }
            }
        }
    }
}
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

if (empty($result)) {
    echo "1";
} else {
    echo "0";
}


$conn->close();
?>