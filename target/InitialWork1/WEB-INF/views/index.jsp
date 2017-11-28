<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<body>
<h2>欢迎你，<%=request.getSession().getAttribute("userName") %></h2>
<h2><a href="${website}logout">退出登录</a></h2>
</body>
</html>
