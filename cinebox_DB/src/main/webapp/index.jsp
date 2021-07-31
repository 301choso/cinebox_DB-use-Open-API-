<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h3>KOBIS 오픈API SAMPLE</h3>
	<dl>
		<dt>박스오피스</dt>
		<dd>
			<ol>
				<li><a href="<c:url value="/sample/boxoffice/sample1.jsp"/>">일별박스오피스 REST 예제</a></li>
				<li><a href="<c:url value="/sample/boxoffice/sample2.jsp"/>">주간/주말 박스오피스 REST(JAVASCRIPT) 예제</a></li>
				<li><a href="<c:url value="/sample/boxoffice/sample3.jsp"/>">일별 박스오피스 SOAP 예제</a></li>
			</ol>
		</dd>		
		<dt>영화정보</dt>
		<dd>
			<ol>
				<li><a href="<c:url value="/sample/movie/sample1.jsp"/>">영화정보 REST 예제</a></li>
				<li><a href="<c:url value="/sample/movie/sample2.jsp"/>">영화정보 REST(JAVASCRIPT) 예제</a></li>
				<li><a href="<c:url value="/sample/movie/sample3.jsp"/>">영화정보 SOAP 예제</a></li>
			</ol>
		</dd>
		<dt>영화사정보</dt>
		<dd>
			<ol>
				<li><a href="<c:url value="/sample/company/sample1.jsp"/>">영화사정보 REST 예제</a></li>
				<li><a href="<c:url value="/sample/company/sample2.jsp"/>">영화사정보 REST(JAVASCRIPT) 예제</a></li>
				<li><a href="<c:url value="/sample/company/sample3.jsp"/>">영화사정보 SOAP 예제</a></li>
			</ol>
		</dd>		
		<dt>영화인정보</dt>
		<dd>
			<ol>
				<li><a href="<c:url value="/sample/people/sample1.jsp"/>">영화인정보 REST 예제</a></li>
				<li><a href="<c:url value="/sample/people/sample2.jsp"/>">영화인정보 REST(JAVASCRIPT) 예제</a></li>
				<li><a href="<c:url value="/sample/people/sample3.jsp"/>">영화인정보 SOAP 예제</a></li>
			</ol>
		</dd>	
	</dl>
</body>
</html>