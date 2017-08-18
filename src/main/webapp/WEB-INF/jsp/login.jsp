<%--
  Created by IntelliJ IDEA.
  User: JOKER
  Date: 2017/8/18
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>login page</title>
</head>
<body>
<c:if test="${!empty error}">
    <font color="red"><c:out value="${error}"></c:out></font>
    <form>
        用户名：<input type="text" name="userName" /><br/>
        密码：  <input type="text" name="password" />
        <br/>
        <input type="button" value="登录"/>&nbsp;&nbsp;<input type="button" value="重置"/>
    </form>
</c:if>
</body>
</html>
