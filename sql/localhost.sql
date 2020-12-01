-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- 생성 시간: 20-12-01 12:09
-- 서버 버전: 5.7.32
-- PHP 버전: 7.3.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 데이터베이스: `hyper0616`
--
CREATE DATABASE IF NOT EXISTS `hyper0616` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `hyper0616`;

-- --------------------------------------------------------

--
-- 테이블 구조 `class`
--

CREATE TABLE `class` (
  `class_no2` int(11) NOT NULL COMMENT 'class_no',
  `class_no` int(11) NOT NULL,
  `user_id` varchar(45) NOT NULL COMMENT 'user_id',
  `user_name` varchar(20) NOT NULL COMMENT 'user_name',
  `class_contents` text,
  `user_phoneNumber` int(11) NOT NULL COMMENT 'user_phoneNumber',
  `class_name` varchar(100) NOT NULL COMMENT 'class_name',
  `class_price` int(11) DEFAULT NULL COMMENT 'class_price',
  `class_period` int(11) NOT NULL COMMENT 'class_period',
  `class_category` varchar(15) NOT NULL COMMENT 'class_category',
  `class_start` date NOT NULL COMMENT 'class_start',
  `class_max_student` int(11) NOT NULL COMMENT 'class_max_student',
  `class_state` char(1) NOT NULL COMMENT 'class_state',
  `class_hour` int(11) NOT NULL COMMENT 'class_hour',
  `class_ap` char(2) NOT NULL COMMENT 'class_ap',
  `class_image` text COMMENT 'class_image',
  `class_dayweek_mon` int(1) DEFAULT NULL,
  `class_dayweek_tue` int(1) DEFAULT NULL,
  `class_dayweek_wen` int(1) DEFAULT NULL,
  `class_dayweek_thu` int(1) DEFAULT NULL,
  `class_dayweek_fri` int(1) DEFAULT NULL,
  `class_dayweek_sat` int(1) DEFAULT NULL,
  `class_dayweek_sun` int(1) DEFAULT NULL,
  `class_count` int(11) NOT NULL,
  `info_image1` text,
  `info_image2` text NOT NULL,
  `class_runningtime` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- 테이블의 덤프 데이터 `class`
--

INSERT INTO `class` (`class_no2`, `class_no`, `user_id`, `user_name`, `class_contents`, `user_phoneNumber`, `class_name`, `class_price`, `class_period`, `class_category`, `class_start`, `class_max_student`, `class_state`, `class_hour`, `class_ap`, `class_image`, `class_dayweek_mon`, `class_dayweek_tue`, `class_dayweek_wen`, `class_dayweek_thu`, `class_dayweek_fri`, `class_dayweek_sat`, `class_dayweek_sun`, `class_count`, `info_image1`, `info_image2`, `class_runningtime`) VALUES
(1, 1, 'happy', '이태오', '', 1011112222, '뜨개질 클래스', 30000, 60, '수공예', '2020-10-01', 5, '0', 6, 'pm', 'http://hyper0616.dothome.co.kr/image/1.PNG', NULL, NULL, NULL, 1, NULL, NULL, NULL, 8, '', '', 1),
(2, 2, 'puppy', '여다경', '', 1033334444, '안드로이드 부수기', 25000, 120, '프로그래밍', '2020-09-25', 5, '0', 5, 'pm', 'http://hyper0616.dothome.co.kr/image/2.PNG', NULL, NULL, NULL, NULL, NULL, 1, NULL, 8, '', '', 1),
(3, 3, 'yumi', '김유미', '', 1055556666, '일러스트 기초', 30000, 90, '미술', '2020-10-03', 5, '0', 2, 'pm', 'http://hyper0616.dothome.co.kr/image/3.PNG', NULL, NULL, NULL, NULL, 1, NULL, NULL, 8, '', '', 1),
(4, 4, 'puppy', '여다경', '', 1033334444, '파이썬 특강', 180000, 60, '프로그래밍', '2020-12-09', 5, '0', 6, 'pm', 'http://hyper0616.dothome.co.kr/image/4.PNG', NULL, NULL, 1, NULL, NULL, NULL, NULL, 8, '', '', 1),
(5, 5, 'barbie', '유바비', 'http://hyper0616.dothome.co.kr/image/5.PNG\r\n', 1021324435, '통증 완화 바른 자세 필라테스', 270000, 90, '액티비티', '2020-11-19', 5, '0', 6, 'pm', 'http://hyper0616.dothome.co.kr/image/5.PNG', NULL, 1, NULL, NULL, NULL, NULL, NULL, 12, '', '', 1),
(6, 2, 'puppy', '여다경', '', 1033334444, '안드로이드 부수기', 25000, 120, '프로그래밍', '2020-11-26', 5, '0', 5, 'pm', 'http://hyper0616.dothome.co.kr/image/2.PNG', NULL, NULL, 1, NULL, NULL, NULL, NULL, 8, '', '', 1),
(7, 1, 'happy', '이태오', '', 1011112222, '뜨개질 클래스', 30000, 60, '수공예', '2020-12-03', 5, '0', 6, 'pm', 'http://hyper0616.dothome.co.kr/image/1.PNG', NULL, NULL, NULL, 1, NULL, NULL, NULL, 8, '', '', 1),
(8, 6, '1526328517', '장동재', '', 1098888888, 'Speaking English', 30000, 60, '어학', '2020-11-16', 5, '0', 6, 'pm', 'http://hyper0616.dothome.co.kr/image/6.PNG', 1, NULL, NULL, NULL, NULL, NULL, NULL, 4, NULL, '', 1),
(10, 10, '1524618196', '강유굥', 'http://hyper0616.dothome.co.kr/image/latteart.jpg', 1067371552, '라떼아트', 30000, 60, '수공예', '2020-11-13', 5, '0', 1, 'pm', 'http://hyper0616.dothome.co.kr/image/latteart.jpg', NULL, NULL, NULL, NULL, NULL, NULL, 1, 8, NULL, '', 1);

-- --------------------------------------------------------

--
-- 테이블 구조 `developer_QA`
--

CREATE TABLE `developer_QA` (
  `developerQA_no` int(11) NOT NULL COMMENT 'developerQA_no',
  `user_id` varchar(45) NOT NULL COMMENT 'user_id',
  `user_email` varchar(45) NOT NULL COMMENT 'user_email',
  `question_title` varchar(45) NOT NULL,
  `question_contents` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COMMENT='developer_QA';

--
-- 테이블의 덤프 데이터 `developer_QA`
--

INSERT INTO `developer_QA` (`developerQA_no`, `user_id`, `user_email`, `question_title`, `question_contents`) VALUES
(2, '1526328517', 'test@naver.com', 'test', 'test'),
(3, '1526328517', 'test@gmail.com', '테스트', '개발자 문의 페이지 테스트입니다.'),
(7, '1526328517', 'test@naver.com', 'ㅅ', 'ㅋ'),
(8, '', '', '', '');

-- --------------------------------------------------------

--
-- 테이블 구조 `notice`
--

CREATE TABLE `notice` (
  `notice_no` int(11) NOT NULL COMMENT 'notice_no',
  `class_no` int(11) NOT NULL COMMENT 'class_no',
  `class_no2` int(11) NOT NULL COMMENT 'class_no2',
  `user_id` varchar(45) NOT NULL COMMENT 'user_id',
  `notice_title` varchar(45) NOT NULL COMMENT 'notice_title',
  `notice_contents` text COMMENT 'notice_contents',
  `name` varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- 테이블의 덤프 데이터 `notice`
--

INSERT INTO `notice` (`notice_no`, `class_no`, `class_no2`, `user_id`, `notice_title`, `notice_contents`, `name`) VALUES
(1, 1, 1, 'happy', '뜨개질 재료 공지', '근처 문방구에서 살 수 있음', '이태오'),
(2, 1, 1, 'happy', '가방 도안', '이렇게 하면 됩니다', '이태오'),
(3, 2, 2, 'puppy', '안드로이드 설치', '저기서 하면 됩니다', '여다경'),
(4, 3, 3, 'yumi', '일러스트 설치', '여기서 하면 됩니다.', '김유미'),
(5, 2, 2, 'puppy', '공지2', '공지2입니다', '여다경'),
(6, 11, 11, 'puppy', 'title', 'content', '여다경'),
(21, 8, 8, '1526328517', '안녕하세요 여러분', '반갑습니다. 수업이 얼마 남지않았으니 모두 힘냅시다^^', '장동재'),
(20, 4, 4, 'puppy', '수업에 앞선 공지사항', '책은 파이썬파이썬을 구매해주세요.\r\n수업은 책의 예제를 통해 진행될 예정입니다.', '여다경'),
(11, 10, 10, '1524618196', '라떼아트 준비물입니다.', '우유, 스팀기, 피쳐를 준비하세요.', '강유굥'),
(19, 10, 10, '1524618196', 'notice for latteart', 'i love coffee', '강유굥'),
(17, 10, 10, '1524618196', 'ㅇㅇㅇㅇㅇㅇㅇ', 'ㅇㅇㅇㅇㅇㅇㅇ', '강유굥'),
(22, 8, 8, '1526328517', 'zz', 'zz', '장동재');

-- --------------------------------------------------------

--
-- 테이블 구조 `question_board`
--

CREATE TABLE `question_board` (
  `board_no` int(11) NOT NULL COMMENT 'board_no',
  `class_no` int(11) NOT NULL COMMENT 'class_no',
  `question_id` varchar(45) NOT NULL COMMENT 'user_id',
  `board_contents` text NOT NULL COMMENT 'board_contents',
  `comment_contents` text COMMENT 'comment_contents'
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- 테이블의 덤프 데이터 `question_board`
--

INSERT INTO `question_board` (`board_no`, `class_no`, `question_id`, `board_contents`, `comment_contents`) VALUES
(1, 1, 'doctor', '수업은 어떤 방식으로 진행되나요?', '온라인으로 진행됩니다'),
(2, 2, 'redlion', '언제시작하나요?', NULL),
(3, 5, 'yumi', '준비물을 준비해야하나요?폼 기구나 매트 등 준비물을 따로 준비해야하나요?', NULL),
(4, 1, 'yumi', '목도리도 만드나요?', NULL),
(5, 6, 'yumi', '교재가 따로 필요한가요?', '네 준비 후 수업에 임해주세요'),
(6, 6, 'yumi', '오직 스피킹 수업만 진행되는건가요? reading 이나 writing 수업의 경우는 같이 이루어지지 않는건가요?', '네 안합니다. 스피킹만 합니다.'),
(7, 6, 'yumi', '공휴일에도 진행하나요?', '네 공휴일에도 진행합니다'),
(8, 2, 'yumi', '노트북으로 가능한가요?', NULL),
(9, 2, 'yumi', '어느 프로그램을 사용하나요?', NULL),
(10, 5, 'yumi', '수업은 어떤 방식으로 진행되나요?', NULL),
(11, 1, 'yumi', '장갑도 만드나요????', NULL),
(15, 6, 'barbie', '처음 영어를 시작하는데 괜찮을까요?', NULL),
(17, 1, '1526328517', '매수업마다 준비물이 필요한가요?', NULL);

-- --------------------------------------------------------

--
-- 테이블 구조 `review_board`
--

CREATE TABLE `review_board` (
  `board_no` int(11) NOT NULL COMMENT 'board_no',
  `class_no` int(11) NOT NULL COMMENT 'class_no',
  `review_id` varchar(45) NOT NULL COMMENT 'user_id',
  `board_contents` text NOT NULL COMMENT 'board_contents',
  `comment_contents` text NOT NULL COMMENT 'comment_contents'
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- 테이블의 덤프 데이터 `review_board`
--

INSERT INTO `review_board` (`board_no`, `class_no`, `review_id`, `board_contents`, `comment_contents`) VALUES
(1, 1, 'barbie', '좋은 강의입니다', '온라인으로 진행되어 과연 잘 배울 수 있을까 걱정되었지만 강사님이 하나하나 친절히 알려주셔서 원활히 수업이 진행되었습니다'),
(2, 2, 'barbie', '좋은 강의입니다', '쉽게 따라갈 수 있는 좋은 강의입니다'),
(5, 6, 'doctor', '좋은 강의입니다', '온라인으로 진행되어 과연 잘 배울 수 있을까 걱정되었지만 강사님이 하나하나 친절히 알려주셔서 원활히 수업이 진행되었습니다'),
(6, 4, '1524618196', 'best class i ever had!', 'it was a great class.'),
(8, 2, '1526328517', '재미있네요', '선생님 안드로이드 고수세요'),
(11, 4, '1526328517', 'ggㅎ', '후기후기후기후기후기'),
(10, 10, '1526319545', '좋아요', '친절하게 알려주십니다 좋아요');

-- --------------------------------------------------------

--
-- 테이블 구조 `schedule`
--

CREATE TABLE `schedule` (
  `date_no` int(11) NOT NULL COMMENT 'date_no',
  `class_no` int(11) NOT NULL COMMENT 'class_no',
  `class_no2` int(11) NOT NULL COMMENT 'class_no2',
  `user_id` varchar(45) NOT NULL COMMENT 'user_id',
  `user_schedule_mon` varchar(6) DEFAULT '00000' COMMENT 'user_schedule_mon',
  `user_schedule_tue` varchar(6) DEFAULT '00000' COMMENT 'user_schedule_tue',
  `user_schedule_wed` varchar(6) DEFAULT '00000' COMMENT 'user_schedule_wed',
  `user_schedule_thu` varchar(6) DEFAULT '00000' COMMENT 'user_schedule_thu',
  `user_schedule_fri` varchar(6) DEFAULT '00000' COMMENT 'user_schedule_fri',
  `user_schedule_sat` varchar(6) DEFAULT '00000' COMMENT 'user_schedule_sat',
  `user_schedule_sun` varchar(6) DEFAULT '00000' COMMENT 'user_schedule_sun'
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- 테이블의 덤프 데이터 `schedule`
--

INSERT INTO `schedule` (`date_no`, `class_no`, `class_no2`, `user_id`, `user_schedule_mon`, `user_schedule_tue`, `user_schedule_wed`, `user_schedule_thu`, `user_schedule_fri`, `user_schedule_sat`, `user_schedule_sun`) VALUES
(2, 1, 0, 'doctor', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(4, 1, 0, 'barbie', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(6, 2, 0, 'barbie', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(7, 2, 0, 'redlion', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(13, 4, 4, '1526328517', '00000', '00001', '00101', '00000', '00000', '00000', '00000'),
(12, 4, 4, '1524618196', '10000', '00001', '00001', '00101', '11111', '00000', '10110');

-- --------------------------------------------------------

--
-- 테이블 구조 `somoim`
--

CREATE TABLE `somoim` (
  `somoim_no` int(11) NOT NULL COMMENT 'somoim_no',
  `user_id` varchar(45) NOT NULL COMMENT 'user_id',
  `class_no` int(11) NOT NULL COMMENT 'class_no',
  `class_no2` int(11) NOT NULL COMMENT 'class_no2',
  `somoim_link` text COMMENT 'somoim_link',
  `somoim_day` char(11) DEFAULT NULL COMMENT 'somoim_day',
  `somoim_hour` int(11) DEFAULT NULL COMMENT 'somoim_hour',
  `somoim_ap` char(2) DEFAULT 'pm' COMMENT 'somoim_ap'
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- 테이블의 덤프 데이터 `somoim`
--

INSERT INTO `somoim` (`somoim_no`, `user_id`, `class_no`, `class_no2`, `somoim_link`, `somoim_day`, `somoim_hour`, `somoim_ap`) VALUES
(1, 'doctor', 1, 1, 'link1', 'MONDAY', 7, 'pm'),
(3, 'barbie', 2, 2, 'link2', 'SATURDAY', 5, 'pm'),
(5, '1526328517', 1, 1, 'link1', 'MONDAY', 7, 'pm'),
(8, '1524618196', 4, 4, NULL, 'TUSEDAY', 9, 'pm'),
(9, '1526328517', 4, 4, NULL, 'TUSEDAY', 9, 'pm');

-- --------------------------------------------------------

--
-- 테이블 구조 `sugang_class`
--

CREATE TABLE `sugang_class` (
  `sugang_no` int(11) NOT NULL COMMENT 'sugnag_no',
  `class_no` int(11) NOT NULL COMMENT 'class_no',
  `class_no2` int(11) NOT NULL COMMENT 'class_no2',
  `user_id` varchar(45) NOT NULL COMMENT 'user_id',
  `class_link` text COMMENT 'class_link',
  `review` text
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- 테이블의 덤프 데이터 `sugang_class`
--

INSERT INTO `sugang_class` (`sugang_no`, `class_no`, `class_no2`, `user_id`, `class_link`, `review`) VALUES
(1, 1, 1, 'barbie', 'https://www.naver.com/', '1'),
(2, 2, 2, 'barbie', 'https://www.naver.com/', '1'),
(3, 2, 2, 'redlion', 'https://www.naver.com/', NULL),
(4, 1, 1, 'doctor', 'link1', NULL),
(20, 2, 2, '1524618196', 'https://www.naver.com/', NULL),
(17, 6, 8, 'doctor', 'https://www.google.com', NULL),
(22, 10, 10, 'puppy', 'https://www.cau.ac.kr', NULL),
(23, 4, 4, '1526328517', 'https://www.naver.com', '1'),
(24, 2, 2, '1526328517', NULL, '1'),
(25, 4, 4, '1524618196', 'https://www.naver.com', NULL),
(26, 10, 10, '1526319545', NULL, '1'),
(27, 1, 7, '1526328517', NULL, NULL);

-- --------------------------------------------------------

--
-- 테이블 구조 `test`
--

CREATE TABLE `test` (
  `num1` int(11) DEFAULT NULL,
  `num2` int(11) DEFAULT NULL,
  `text1` text,
  `text2` text
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- 테이블의 덤프 데이터 `test`
--

INSERT INTO `test` (`num1`, `num2`, `text1`, `text2`) VALUES
(1, 2, 'text1', 'text2'),
(7, 6, 'amy', 'kim'),
(0, 0, '', ''),
(1, 2, 'gs', 'tet'),
(4, 3, '김지혜', 'kimjihye'),
(0, 0, '', ''),
(0, 0, '5', 'yumi'),
(0, 0, '5', 'yumi'),
(0, 0, '', ''),
(0, 0, '', ''),
(0, 0, '', '');

-- --------------------------------------------------------

--
-- 테이블 구조 `user`
--

CREATE TABLE `user` (
  `no` int(11) NOT NULL COMMENT 'no',
  `id` varchar(100) NOT NULL COMMENT 'id',
  `name` varchar(100) NOT NULL COMMENT 'name',
  `phoneNumber` varchar(12) NOT NULL COMMENT 'phoneNumber',
  `birth` varchar(10) NOT NULL COMMENT 'birth',
  `gender` varchar(1) NOT NULL COMMENT 'gender',
  `type` int(11) NOT NULL COMMENT 'type',
  `password` varchar(45) NOT NULL COMMENT 'password',
  `image` text COMMENT 'image',
  `alarm_onoff` tinyint(4) DEFAULT '1' COMMENT 'alarm_onoff',
  `token` text COMMENT 'token'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 테이블의 덤프 데이터 `user`
--

INSERT INTO `user` (`no`, `id`, `name`, `phoneNumber`, `birth`, `gender`, `type`, `password`, `image`, `alarm_onoff`, `token`) VALUES
(1, 'happy', '이태오', '1011112222', '931212', 'm', 1, 'happier', NULL, 1, NULL),
(2, 'puppy', '여다경', '1091855352', '980325', 'f', 1, 'ekrud', NULL, 1, NULL),
(3, 'yumi', '김유미', '1055556666', '960501', 'f', 1, 'soonrok', NULL, 1, NULL),
(4, 'doctor', '지선우', '1077778888', '900621', 'f', 0, 'junyoung', NULL, 1, NULL),
(5, 'barbie', '유바비', '01011111111', '951004', 'f', 0, '12', NULL, 1, NULL),
(6, 'redlion', '홍설', '1023234545', '921111', 'f', 0, 'redhair', NULL, 1, NULL),
(20, '1524618196', '강유굥', '', '', '', 0, '1', 'https://k.kakaocdn.net/dn/dFY077/btqMK2SESbW/afUTx0ilNYGyVY4cRzhgK0/img_640x640.jpg', 1, NULL),
(32, '1526328517', '장동재', '', '', '', 0, '', 'https://k.kakaocdn.net/dn/cCozED/btqAKifgM3V/O319F3MKb1tcwkuOnwJ2Fk/img_640x640.jpg', 0, NULL),
(39, '1526319545', 'jihye', '', '', '', 0, '', 'https://k.kakaocdn.net/dn/c4Pk9r/btqLC1Oh8KL/LNDcsCrPTIkngrXlgKLPv0/img_640x640.jpg', 1, NULL),
(42, '1547176191', '여름', '', '', '', 0, '', 'null', 1, NULL);

--
-- 덤프된 테이블의 인덱스
--

--
-- 테이블의 인덱스 `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`class_no2`),
  ADD KEY `user_id` (`user_id`);

--
-- 테이블의 인덱스 `developer_QA`
--
ALTER TABLE `developer_QA`
  ADD PRIMARY KEY (`developerQA_no`);

--
-- 테이블의 인덱스 `notice`
--
ALTER TABLE `notice`
  ADD PRIMARY KEY (`notice_no`),
  ADD KEY `FK_notice_class_no_class_class_no` (`class_no`),
  ADD KEY `user_id` (`user_id`);

--
-- 테이블의 인덱스 `question_board`
--
ALTER TABLE `question_board`
  ADD PRIMARY KEY (`board_no`),
  ADD KEY `FK_question_board_class_no_class_class_no` (`class_no`);

--
-- 테이블의 인덱스 `review_board`
--
ALTER TABLE `review_board`
  ADD PRIMARY KEY (`board_no`),
  ADD KEY `FK_review_board_class_no_class_class_no` (`class_no`);

--
-- 테이블의 인덱스 `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`date_no`),
  ADD KEY `FK_schedule_class_no_class_class_no` (`class_no`),
  ADD KEY `user_id` (`user_id`);

--
-- 테이블의 인덱스 `somoim`
--
ALTER TABLE `somoim`
  ADD PRIMARY KEY (`somoim_no`),
  ADD KEY `FK_somoim_class_no_class_class_no` (`class_no`),
  ADD KEY `user_id` (`user_id`);

--
-- 테이블의 인덱스 `sugang_class`
--
ALTER TABLE `sugang_class`
  ADD PRIMARY KEY (`sugang_no`),
  ADD KEY `user_id` (`user_id`);

--
-- 테이블의 인덱스 `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`no`,`id`);

--
-- 덤프된 테이블의 AUTO_INCREMENT
--

--
-- 테이블의 AUTO_INCREMENT `class`
--
ALTER TABLE `class`
  MODIFY `class_no2` int(11) NOT NULL AUTO_INCREMENT COMMENT 'class_no', AUTO_INCREMENT=11;

--
-- 테이블의 AUTO_INCREMENT `developer_QA`
--
ALTER TABLE `developer_QA`
  MODIFY `developerQA_no` int(11) NOT NULL AUTO_INCREMENT COMMENT 'developerQA_no', AUTO_INCREMENT=9;

--
-- 테이블의 AUTO_INCREMENT `notice`
--
ALTER TABLE `notice`
  MODIFY `notice_no` int(11) NOT NULL AUTO_INCREMENT COMMENT 'notice_no', AUTO_INCREMENT=23;

--
-- 테이블의 AUTO_INCREMENT `question_board`
--
ALTER TABLE `question_board`
  MODIFY `board_no` int(11) NOT NULL AUTO_INCREMENT COMMENT 'board_no', AUTO_INCREMENT=18;

--
-- 테이블의 AUTO_INCREMENT `review_board`
--
ALTER TABLE `review_board`
  MODIFY `board_no` int(11) NOT NULL AUTO_INCREMENT COMMENT 'board_no', AUTO_INCREMENT=12;

--
-- 테이블의 AUTO_INCREMENT `schedule`
--
ALTER TABLE `schedule`
  MODIFY `date_no` int(11) NOT NULL AUTO_INCREMENT COMMENT 'date_no', AUTO_INCREMENT=15;

--
-- 테이블의 AUTO_INCREMENT `somoim`
--
ALTER TABLE `somoim`
  MODIFY `somoim_no` int(11) NOT NULL AUTO_INCREMENT COMMENT 'somoim_no', AUTO_INCREMENT=11;

--
-- 테이블의 AUTO_INCREMENT `sugang_class`
--
ALTER TABLE `sugang_class`
  MODIFY `sugang_no` int(11) NOT NULL AUTO_INCREMENT COMMENT 'sugnag_no', AUTO_INCREMENT=31;

--
-- 테이블의 AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT COMMENT 'no', AUTO_INCREMENT=43;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
