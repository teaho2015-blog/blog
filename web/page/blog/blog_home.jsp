<%--
  create by : teaho2015@gmail.com
  Date: 2016/11/7
  Time: 17:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Tea's Blog</title>

    <link rel="icon" href="<%-- ${pageContext.request.contextPath} --%>/static/images/t.png" />

    <!-- Bootstrap Core CSS -->
    <link href="<%-- ${pageContext.request.contextPath} --%>/static/library/bootstrap/dist/css/bootstrap.css" rel="stylesheet">

    <!-- Theme CSS -->
    <link href="<%-- ${pageContext.request.contextPath} --%>/static/styles/blog/blog-theme.css" rel="stylesheet">

    <link href="<%-- ${pageContext.request.contextPath} --%>/static/styles/blog/blog-home.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<%-- ${pageContext.request.contextPath} --%>/static/library/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

    <!-- progress bar -->
    <link href='<%-- ${pageContext.request.contextPath} --%>/static/library/nprogress/nprogress.css' rel='stylesheet' type='text/css'>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<!-- Navigation -->
<nav class="navbar navbar-default navbar-custom navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header page-scroll">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                Menu <i class="fa fa-bars"></i>
            </button>
            <a class="navbar-brand" href="<%-- ${pageContext.request.contextPath} --%>/">Tea's Blog</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="javascript:;">博客</a>
                </li>
                <li>
                    <a href="javascript:;">归档</a>
                </li>
                <li>
                    <a href="<%-- ${pageContext.request.contextPath} --%>/about">关于/ 联系</a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

<!-- Page Header -->
<!-- Set your background image for this header on the line below. -->
<header class="intro-header" style="background-image: url('<%-- ${pageContext.request.contextPath} --%>/static/images/blog/blog-home-bg.jpg'); position : relative;">
    <div class="mask"></div>
    <div class="container">

        <div class="row header-caption-wrapper">
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                <div class="site-heading">
                    <h1>Tea's Blog</h1>
                    <hr class="small">
                    <span class="subheading">Curiocity is my favorite sin.</span>

                </div>
                <div class="page-scroller">
                    <a href="#main-wrapper" class="btn btn-scroll sink">
                        <span class="glyphicon glyphicon-chevron-down icon-arrow"></span>
                    </a>
                </div>
            </div>

        </div>

    </div>

</header>

<!-- Main Content -->
<div class="container" id="main-wrapper">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1" >
            <div id="article-wrapper">

            </div>
            <%--<c:forEach items="${blogList}" var="blog">
                <div class="post-preview">
                    <a href="${pageContext.request.contextPath}/blog/article/${blog.id}">
                        <h2 class="post-title">
                            ${blog.title}
                        </h2>
                        <h3 class="post-subtitle">
                                ${blog.title_secondary}
                        </h3>
                    </a>
                    <p class="post-meta" data-time="<fmt:formatDate value="${blog.date}" pattern="yyyy-MM-dd"/>">Posted on <fmt:formatDate value="${blog.date}" type="date" pattern="EEEE, MMMM d, yyyy"/></p>
                </div>
                <hr>
            </c:forEach>--%>
          <%--  <div class="post-preview">
                <a href="post.html">
                    <h2 class="post-title">
                        Man must explore, and this is exploration at its greatest
                    </h2>
                    <h3 class="post-subtitle">
                        Problems look mighty small from 150 miles up
                    </h3>
                </a>
                <p class="post-meta">Posted by <a href="#">Start Bootstrap</a> on September 24, 2014</p>
            </div>
            <hr>
            <div class="post-preview">
                <a href="post.html">
                    <h2 class="post-title">
                        I believe every human has a finite number of heartbeats. I don't intend to waste any of mine.
                    </h2>
                </a>
                <p class="post-meta">Posted by <a href="#">Start Bootstrap</a> on September 18, 2014</p>
            </div>
            <hr>
            <div class="post-preview">
                <a href="post.html">
                    <h2 class="post-title">
                        Science has not yet mastered prophecy
                    </h2>
                    <h3 class="post-subtitle">
                        We predict too much for the next year and yet far too little for the next ten.
                    </h3>
                </a>
                <p class="post-meta">Posted by <a href="#">Start Bootstrap</a> on August 24, 2014</p>
            </div>
            <hr>
            <div class="post-preview">
                <a href="post.html">
                    <h2 class="post-title">
                        Failure is not an option
                    </h2>
                    <h3 class="post-subtitle">
                        Many say exploration is part of our destiny, but it’s actually our duty to future generations.
                    </h3>
                </a>
                <p class="post-meta">Posted by <a href="#">Start Bootstrap</a> on July 8, 2014</p>
            </div>
            <hr>--%>
            <!-- Pager -->
            <nav class="pager" data-current-page-num="${currentPageNum}" data-total-page-num="${blogTotalPageNum}" data-pagesize="${pageSize}">
               <%-- <ul class="pagination">
                   &lt;%&ndash; <li class="pre-page"><a href="javascript:;">&laquo;</a></li>
                    <li><a href="#"></a></li>
                    <li><a href="#"></a></li>
                    <li><a href="#"></a></li>
                    <li><a href="#"></a></li>
                    <li><a href="#"></a></li>
                    <li><a href="#"></a></li>
                    <li class="next-page"><a href="javascript:;">&raquo;</a></li>&ndash;%&gt;
                </ul>--%>
            </nav><!-- /.Pager -->
        </div>
        <div class="template hidden">
            <div class="post-preview">
                <a href="/article/" data-context-path="<%-- ${pageContext.request.contextPath} --%>">
                    <h2 class="post-title">
                        ${blog.title}
                    </h2>
                    <h3 class="post-subtitle">
                        ${blog.title_secondary}
                    </h3>
                </a>
                <p class="post-meta" data-time="<fmt:formatDate value="${blog.date}" pattern="yyyy-MM-dd"/>">Posted on <fmt:formatDate value="${blog.date}" type="date" pattern="EEEE, MMMM d, yyyy"/></p>
            </div>
            <hr>
        </div>
    </div>
</div>

<hr>

<!-- Footer -->
<footer>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                <!--<ul class="list-inline text-center contact-list">
                    <li>
                        <a href="#">
                            <span class="fa-stack fa-lg">
                                <i class="fa fa-circle fa-stack-2x"></i>
                                <i class="fa fa-twitter fa-stack-1x fa-inverse"></i>
                            </span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <span class="fa-stack fa-lg">
                                <i class="fa fa-circle fa-stack-2x"></i>
                                <i class="fa fa-facebook fa-stack-1x fa-inverse"></i>
                            </span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <span class="fa-stack fa-lg">
                                <i class="fa fa-circle fa-stack-2x"></i>
                                <i class="fa fa-github fa-stack-1x fa-inverse"></i>
                            </span>
                        </a>
                    </li>
                </ul>-->
                <p class="copyright text-muted">Copyright &copy; Tea's Blog 2016.All rights reserved. My works are licensed under <a href='http://creativecommons.org/licenses/by-nc-sa/4.0/'>CC BY-NC-SA 4.0</a>. </p>
            </div>
        </div>
    </div>
</footer>

<!-- jQuery -->
<script type="text/javascript" src='<%-- ${pageContext.request.contextPath} --%>/static/library/jquery/jquery-1.10.2.js'></script>
<!-- Bootstrap core JavaScript  -->
<script src="<%-- ${pageContext.request.contextPath} --%>/static/library/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- Theme JavaScript -->
<script src="<%-- ${pageContext.request.contextPath} --%>/static/scripts/blog/blog-theme.min.js"></script>

<script type="text/javascript" src="<%-- ${pageContext.request.contextPath} --%>/static/library/jquery/jquery.easing.1.3.js"></script>
<!-- https://github.com/keenwon/jqPaginator -->
<script type="text/javascript" src="<%-- ${pageContext.request.contextPath} --%>/static/library/jqPaginator/jqPaginator.min.js"></script>
<script type="text/javascript" src="<%-- ${pageContext.request.contextPath} --%>/static/library/moment/moment-with-locales.js"></script>
<!-- nprogress -->
<script type="text/javascript" src="<%-- ${pageContext.request.contextPath} --%>/static/library/nprogress/nprogress.js"></script>

<script type="text/javascript" src="<%-- ${pageContext.request.contextPath} --%>/static/scripts/blog/blog-home.js"></script>



</body>

</html>
