<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>1.쿠키(Cookie)설정</h2>
	<%
		Cookie cookie = new Cookie("myCookie", "쿠키있나요"); //쿠키 생성
		cookie.setPath(request.getContextPath()); //경로를 컨테스트 루트로 설정
		cookie.setMaxAge(3600); //유지기간을 1시간으로 설정
		response.addCookie(cookie); //응답 헤더에 쿠키 추기
	%>

	<h2>2.쿠키 설정 직후 쿠키값 확인하기</h2>
	<%
		Cookie[] cookies = request.getCookies();//요청 헤더의 모든 쿠키 열기
		if (cookies != null) {
			for (Cookie c : cookies) {
				String cookieName = c.getName();
				String cookieValue = c.getValue();
				//화면에 출력
				out.println(String.format("%s : %s<br/>", cookieName, cookieValue));
			}
		}
	%>
	
	<h2>3.페이지 이동후 쿠키값 확인하기</h2>
	<a href="CookieResult.jsp">
		다음 페이지에서 쿠키값 확인하기
	</a>
</body>
</html>