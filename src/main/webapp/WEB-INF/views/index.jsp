<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%--<script type="application/javascript" src="/resources/js/index.js" />--%>
</head>
<body>
<h3>欢迎你，<%=request.getSession().getAttribute("userName") %></h3>
<h3><a href="${website}logout">退出登录</a></h3>
<h3><a href="${website}admin/user/users">用户管理（管理员权限）</a></h3>
<form id="uploadForm" action="/fileOperate/upload" method="post" enctype="multipart/form-data">
    文件1：<input type="file" name="file"/><br/>
    文件2：<input type="file" name="file"/>
    <input type="submit" value="提交"/>
</form>
<a href="/fileOperate/download"><p>下载文件（指定目录的一个文件）</p></a>
<a href="/admin/user/download"><p>下载用户信息文件（管理员权限）</p></a>
</body>
</html>
