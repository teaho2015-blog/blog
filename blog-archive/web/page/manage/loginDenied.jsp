<%--
  create by : teaho2015@gmail.com
  Date: 2016/10/11
  Time: 3:41
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h1>你的权限不够!</h1>
<p>只有拥有Admin权限才能访问!</p>
<a href="<c:url value="/manage/login" />">返回</a>
<c:import url="../util/google-analytics.jsp"/>
</body>
</html>
