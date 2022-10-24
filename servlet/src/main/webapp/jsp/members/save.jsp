<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%--
  Created by IntelliJ IDEA.
  User: junhee
  Date: 2022/09/28
  Time: 6:37 오후
  To change this template use File | Settings | File Templates.
--%>

<%
    //request, response 사용 가능 단, req x, resp x, request o, response o
    MemberRepository memberRepository = MemberRepository.getInstance();

    System.out.println("MemberSaveServlet.service");
    String username = request.getParameter("username"); //등록된 회원 가져오기
    int age = Integer.parseInt(request.getParameter("age")); //등록된 나이 가져오기

    //MemberRepository  저장하기
    Member member = new Member(username, age);
    memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
        <li>id=<%=member.getId()%></li>
        <li>username=<%=member.getUsername()%></li>
        <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>

</body>
</html>
