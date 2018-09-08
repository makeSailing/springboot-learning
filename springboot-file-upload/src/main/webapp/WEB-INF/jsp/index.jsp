<%--
  Created by IntelliJ IDEA.
  User: jamie.li
  Date: 2018/9/7
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>单文件上传</title>
</head>
<body>
  单文件上传 <br/>
  <form action="/upload" method="post" enctype="multipart/form-data">
      <input type="file" name="file"/>
      <input type="submit" value="提交上传"/>
  </form>
</body>
</html>
