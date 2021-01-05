<%--
  create by : teaho2015@gmail.com
  Date: 2016/11/30
  Time: 17:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:import url="../util/config.jsp"/>
<!DOCTYPE html>
<html>
<head>
    <title>Tea's Article</title>
    <meta name="viewport" content="initial-scale=1.0, width=device-width, minimum-scale=1.0, maximum-scale=2.0">
    <link rel="icon" href="<%-- ${pageContext.request.contextPath} --%>${sourceHost}/static/images/t.png"/>
    <link href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700,400italic,700italic|PT+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <!-- Bootstrap core CSS -->
    <link href="<%-- ${pageContext.request.contextPath} --%>${sourceHost}/static/library/bootstrap/dist/css/bootstrap.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <%--<link href="${pageContext.request.contextPath}/static/library/bootstrap/dist/css/bootstrap-theme.css" rel="stylesheet">--%>
    <link href='<%-- ${pageContext.request.contextPath} --%>${sourceHost}/static/styles/blog/article.css' rel='stylesheet' type='text/css'>
</head>
<body>
<!-- Page. -->
<article class='page hidden'>
    <div class='big-image'>

        <div class='inner'>
            <div class='fader'>
                <div class='text'>
                    <a class='goto-next'>Read Next</a>

                    <h1 class='title'></h1>

                    <h2 class='description'></h2>
                </div>
            </div>
        </div>
    </div>
    <div class='content'>
        <h3 class='byline'>
            <time></time>
            发布
            <!--by <span class='author'></span>-->
        </h3>
        <h1 class='title'></h1>
        <h2 class='description'></h2>
        <div class='text'></div>
        <a class='show_comment'>查看评论</a>
    </div>
</article>
</body>
<script type="text/javascript" src='<%-- ${pageContext.request.contextPath} --%>${sourceHost}/static/library/jquery/jquery-1.10.2.js'></script>
<script type="text/javascript" src="<%-- ${pageContext.request.contextPath} --%>${sourceHost}/static/library/bootstrap/dist/js/bootstrap.js"></script>
<script type="text/javascript" src='<%-- ${pageContext.request.contextPath} --%>${sourceHost}/static/library/moment/moment-with-locales.js'></script>
<script type="text/javascript" src='<%-- ${pageContext.request.contextPath} --%>${sourceHost}/static/library/jquery/lazyload.js'></script>

<script type="text/javascript" src='${sourceHost}/static/scripts/blog/disqus.js'></script>
<script type="text/javascript" src='<%-- ${pageContext.request.contextPath} --%>${sourceHost}/static/scripts/blog/article.js'></script>

<c:import url="../util/google-analytics.jsp"/>

</html>

