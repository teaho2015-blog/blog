<%--
  create by : teaho2015@gmail.com
  Date: 2017/5/3
  Time: 23:01
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Tea's Blog - development</title>
    <link rel="icon" href="/static/images/t.png" />

    <!-- Bootstrap Core CSS -->
    <link href="/static/library/bootstrap/dist/css/bootstrap.css" rel="stylesheet">

    <!-- Theme CSS -->
    <link href="/static/styles/blog/blog-theme.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Custom Fonts -->
    <link href="/static/library/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

    <link href="/static/styles/common/development.css" rel="stylesheet">

</head>

<body>

<nav class="navbar navbar-default navbar-custom navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header page-scroll">
            <button type="button" class="navbar-toggle-custom navbar-toggle-custom-normal" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <i class="fa fa-bars"></i>
                <i class="fa fa-times" aria-hidden="true"></i>
            </button>
            <a class="navbar-brand" href="/">Tea's Blog</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="/">博客</a>
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
        <div class="navbar-collapse-menu">
            <div class="menu-item" data-direction="bt">
                <div class="menu-item-inner">
                    <a class="menu-item-span" href="/">博客</a>
                </div>
            </div>
            <div class="menu-item" data-direction="lr">
                <div class="menu-item-inner">
                    <a class="menu-item-span" href="javascript:;">归档</a>
                </div>
            </div>
            <div class="menu-item" data-direction="bfa-barst">
                <div class="menu-item-inner">
                    <a class="menu-item-span" href="/about">关于/ 联系</a>
                </div>
            </div>
        </div>
    </div>
    <!-- /.container -->
</nav>

<!-- Page Header -->
<!-- Set your background image for this header on the line below. -->
<header class="intro-header" style="background-image: url('<%-- ${pageContext.request.contextPath} --%>/static/images/common/development_bg.jpg'); position : relative; ">
    <div class="mask"></div>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                <div class="page-heading">
                    <h1>API说明</h1>
                    <hr class="small">
                    <span class="subheading"></span>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Main Content -->
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
            <br/>
            <p>暂时只有那么两三条放出来的api，也不需authorization的，比较简单易懂，直接看这里<a href="/api/v1">http://blog.teaho.net/api/v1</a>。</p>
            <br/><br/><br/>

        </div>
    </div>
</div>

<hr>

<c:import url="../util/footer.jsp"/>

<!-- jQuery -->
<script type="text/javascript" src='/static/library/jquery/jquery-1.10.2.js'></script>
<!-- Bootstrap core JavaScript===================== -->
<script src="/static/library/bootstrap/dist/js/bootstrap.min.js"></script>


<!-- Theme JavaScript -->
<script src="/static/scripts/blog/blog-theme.min.js"></script>

<script>
    $('.navbar-toggle-custom').click(function (e) {
        var _this = $(this);
        _this.toggleClass('navbar-toggle-custom-show');
        $('.navbar-collapse-menu').toggleClass('navbar-collapse-menu-show');
    });
</script>

<c:import url="../util/google-analytics.jsp"/>

</body>

</html>
