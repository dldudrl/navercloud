<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Forty by HTML5 UP</title>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet" href="assets/css/board.css" />
<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->

</head>
<body>
	<div id="board">
		<h1>게시판 메인 페이지</h1>
		<table id="list">
			<!-- thead와 tbody로 나눈다. 
			thead와 tbody는 데이터를 구조적으로 나누고 관리하기 위해 사용하는 요소입니다. 특히 테이블 데이터가 많아지고 복잡해질수록, thead와 tbody를 활용하면 유지보수와 관리 측면에서 큰 장점이 있습니다. 
			데이터가 많아도 특정 부분만 수정하거나 접근할 수 있어 관리가 편리함.
			-->
			<thead>
				<tr>
					<td colspan="3">
						<input id="text" type="text"></td>
					<td>
						<button id="searchBtn">검색하기</button>
					</td>
				</tr>



				<!--thead : 테이블에서 변하면 안되는  테이블의 "헤더" 역할만 집중적으로 관리.-->
				<tr>
					<td>번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>시간</td>
				</tr>
			</thead>
			<tbody>
				<!-- tbody:  테이블의 "본문 데이터"만 관리 -->
				<%--Ex10.게시글 목록을 출력해봅시다. --%>
				<%--예시) --%>
				<%--
					begin : 시작하는 숫자
					end : 끝나는 숫자
					step : 증감
					>> for(int i = begin; i<=end; i+=step)
					
					var : 어떤 이름으로 저장할지
					items: 배열
					>> for( var :items)
				--%>
				<c:forEach var="board" items="${list}">
					<tr>
						<td>${board.idx}</td>
						<!--  
							query string
							{urlMapping}?name=value&...
							
						-->
						<!-- 
							path variable
							{urlMapping}/{data}/{data2}... 
							view?idx=${board.idx} 이게 다 데이터값이다.
						-->
						<td><a href="view?idx=${board.idx}">${board.title}</a></td>
						<td>${board.writer}</td>
						<td>${board.indate}</td>
						<td><a href="delete/${board.idx}"> X </a></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>

		<a href="writerBoard"><button id="writer">작성하러가기</button></a>
	</div>
	
	<div>
  		<canvas id="myChart"></canvas>
	</div>

	<!-- Scripts -->
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.scrolly.min.js"></script>
	<script src="assets/js/jquery.scrollex.min.js"></script>
	<script src="assets/js/skel.min.js"></script>
	<script src="assets/js/util.js"></script>
	<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
	<script src="assets/js/main.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script src="assets/js/search.js"></script>



</body>
</html>