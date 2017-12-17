<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>用户修改</title>
    <h3><a href="/admin/user/users">返回</a></h3>
</head>
<body>
    <form action="${website}update" method="post">
        <input type="hidden" name="id" value="${user.id}">
        用户名: <input type="text" name="userName" value="${user.userName}" /> <br />
        密码： <input type="text" name="password" value="${user.password}" /> <br />
        <input type="submit" value="保存修改" />
        <input type="reset" value="重置" />
    </form>
</body>
</html>