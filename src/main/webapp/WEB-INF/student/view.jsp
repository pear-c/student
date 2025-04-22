<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<%
    com.nhnacademy.student.entity.Student studentObj = (com.nhnacademy.student.entity.Student) request.getAttribute("student");
    String formattedDate = "";
    if (studentObj != null) {
        formattedDate = studentObj.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
%>

<html>
<head>
    <title>학생-조회</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<table>
    <tbody>
    <!-- todo view 구현 -->
        <tr>
            <th>아이디</th> <td>${student.id}</td>
        </tr>
        <tr>
            <th>이름</th> <td>${student.name}</td>
        </tr>
        <tr>
            <th>성별</th> <td>${student.gender}</td>
        </tr>
        <tr>
            <th>나이</th> <td>${student.age}</td>
        </tr>
        <tr>
            <th>등록일</th> <td><%= formattedDate %></td>
        </tr>
    </tbody>
</table>

<br>

<div class="button-group">
    <a href="/student/list">리스트</a>
    <a href="/student/update?id=${student.id}">수정</a>
    <form action="/student/delete" method="post" style="display: inline">
        <input type="hidden" name="id" value="${student.id}" />
        <button type="submit">삭제</button>
    </form>
</div>

</body>
</html>