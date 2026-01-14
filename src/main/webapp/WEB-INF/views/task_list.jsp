<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>タスク一覧</title>
</head>
<body>
    <h2>マイタスク一覧</h2>
    <p>ようこそ、<c:out value="${sessionScope.loginUser.username}" /> さん</p>
    
    <a href="${pageContext.request.contextPath}/app/logout">ログアウト</a>
    <hr>
    
    <ul>
        <c:forEach var="task" items="${tasks}">
            <li>
                <strong><c:out value="${task.title}" /></strong>
                (状態: <c:out value="${task.status}" />)
            </li>
        </c:forEach>
    </ul>
    
    <c:if test="${empty tasks}">
        <p>タスクはまだありません。</p>
    </c:if>
</body>
</html>