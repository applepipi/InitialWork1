<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>用户管理</title>
    <h3><a href="/index">返回首页</a></h3>
</head>
<body>
    <table border="1">
        <tr>
            <td>用户名</td>
            <td>密码</td>
            <td>修改</td>
            <td>删除</td>
        </tr>
        <c:forEach var="u" items="${userList}">
            <tr>
                <td>${u.userName}</td>
                <td>${u.password}</td>
                <td><a href="/admin/user/${u.id}/update">修改</a></td>
                <form action="/admin/user/${u.id}/del" method="post">
                    <input type="hidden" value="${u.id}">
                <td><input type="submit" value="删除"></td></form>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
