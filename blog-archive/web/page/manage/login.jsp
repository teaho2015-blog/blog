<%--
  Created by IntelliJ IDEA.
  User: 庭亮
  Date: 15-2-5
  Time: 下午2:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<% String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="<%-- ${pageContext.request.contextPath} --%>/static/images/title.jpg">
    <title>Blog Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="<%-- ${pageContext.request.contextPath} --%>/static/library/bootstrap/dist/css/bootstrap.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="<%-- ${pageContext.request.contextPath} --%>/static/library/bootstrap/dist/css/bootstrap-theme.css" rel="stylesheet">


    <!-- Custom styles for this template -->
    <link href='<%-- ${pageContext.request.contextPath} --%>/static/styles/manage/login.css' rel='stylesheet' type='text/css'>

    <%--<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->--%>
    <%--<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->--%>
    <%--<script src="../../assets/js/ie-emulation-modes-warning.js"></script>--%>

    <%--<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->--%>
    <%--<!--[if lt IE 9]>--%>
    <%--<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>--%>
    <%--<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>--%>
    <%--<![endif]-->--%>
</head>

<body>

<div class="form-container">

    <form class="form-login" autocomplete="on" method="post" action="<c:url value='/manage/login'/>" onsubmit="setReadOnly();">
        <div class="alert alert-warning alert-dismissible fade in ${param.error=='true'? "" : "hidden"}" role="alert">
            <button type="button" class="close" data-dismiss="alert">
                <span aria-hidden="true">&times;</span>
                <span class="sr-only">Close</span>
            </button>
            <p>登录失败</p>
        </div>
        <h2 class="form-login-heading">Please log-in</h2>
        <label for="username" class="sr-only">User Name</label>
        <input type="text" name="username" id="username" class="form-control" placeholder="User Name" required autofocus>
        <%--<label for="inputPassword2" class="sr-only">Password</label>--%>
        <%--<input type="password" name="password2" id="inputPassword2" class="form-control" placeholder="Password" required>--%>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <div class="checkbox">
            <label>
                <input type="checkbox" class="remember" data-toggle="tooltip" data-placement="top" title="一段时间内记住我" value="remember-me" /> 一段时间内记住我
            </label>
        </div>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
      <%--  <div class="form-group">
            <p>Switch to <a class="btn-link"  href="${pageContext.request.contextPath}/register/signup">Sign up</a></p>
        </div>--%>
        <sec:csrfInput />

    </form>

</div> <!-- /container -->



</body>
<!-- Bootstrap core JavaScript
================================================== -->
<script type="text/javascript" src='<%-- ${pageContext.request.contextPath} --%>/static/library/jquery/jquery-1.10.2.js'></script>
<script type="text/javascript" src="<%-- ${pageContext.request.contextPath} --%>/static/library/bootstrap/dist/js/bootstrap.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<%--<script src="${pageContext.request.contextPath}/static/library/bootstrap/assets/js/ie10-viewport-bug-workaround.js"></script>--%>

<script type="text/javascript" src="<%-- ${pageContext.request.contextPath} --%>/static/library/security/md5.js"></script>
<script type="text/javascript" src="<%-- ${pageContext.request.contextPath} --%>/static/scripts/manage/login.js"></script>

<script type="text/javascript">
//    $("#inputPassword2").bind("propertychange input",function(){
//        $("#inputPassword").val(hex_md5($("#inputPassword2").val()));
//    });
</script>

<c:import url="../util/google-analytics.jsp"/>
</html>
