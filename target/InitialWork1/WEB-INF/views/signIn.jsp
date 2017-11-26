<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript">
    //注册失败提示
    var signIn_err = '<%=request.getAttribute("signIn_err") == null ? "" : request.getAttribute("signIn_err")%>'

    if(signIn_err != null && signIn_err != '') {
        alert(signIn_err);
    }
</script>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Login</title>
</head>
<body>
<h4>${signIn_fail}</h4>
<form action="${website}signIn" method="post">
    用户名: <input type="text" name="userName" /> <br />
    密码： <input type="password" name="password" /> <br />
    <input type="submit" value="注册" />
    <input type="reset" value="重置" />
</form>
</body>
</html>