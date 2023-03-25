<%@ page import="membership.MemberDTO" %>
<%@ page import="membership.MemberDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
  //로그인 폼으로부터 받은 아이디와 패스워드
  String userId=request.getParameter("user_id");
  String userPwd=request.getParameter("user_pwd");
  
  //web.xml에서 가져온 데이터 베이스 연결정보
 String oracleDriver=application.getInitParameter("OracleDriver");
  String oracleURL=application.getInitParameter("OracleURL");
  String oracleId=application.getInitParameter("OracleId");
  String oraclePwd=application.getInitParameter("OraclePwd");
  
  //회원 테이블 DAO를 통해 회워 정보 DTO 획득
  MemberDAO dao= new MemberDAO(oracleDriver,oracleURL,oracleId,oraclePwd);
  MemberDTO memberDTO=dao.getMemberDTO(userId,userPwd);
  dao.close();
  
  //로그인 성송 여부에 따른 처리
  if(memberDTO.getId() != null){
	  //로그인 성공
	  session.setAttribute("UserId",memberDTO.getId());
	  session.setAttribute("UserName",memberDTO.getName());
	  response.sendRedirect("loginForm.jsp");
  }
  else{
	  //로그인 실패
	  request.setAttribute("LoginErrMsg","로그인 오류입니다.");
	  request.getRequestDispatcher("loginForm.jsp").forward(request,response);
  }
  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>