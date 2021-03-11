<%--
  create by : teaho2015@gmail.com
  Date: 2016/11/7
  Time: 17:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="../util/config.jsp"/>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Tea's Blog - About</title>
    <link rel="icon" href="<%-- ${pageContext.request.contextPath} --%>${sourceHost}/static/images/t.png" />

    <!-- Bootstrap Core CSS -->
    <link href="<%-- ${pageContext.request.contextPath} --%>${sourceHost}/static/library/bootstrap/dist/css/bootstrap.css" rel="stylesheet">

    <!-- Theme CSS -->
    <link href="<%-- ${pageContext.request.contextPath} --%>${sourceHost}/static/styles/blog/blog-theme.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Custom Fonts -->
    <link href="<%-- ${pageContext.request.contextPath} --%>${sourceHost}/static/library/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

    <link href="<%-- ${pageContext.request.contextPath} --%>${sourceHost}/static/styles/blog/about.css" rel="stylesheet">

</head>

<body>

<!-- Navigation -->
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
                    <a href="<%-- ${pageContext.request.contextPath} --%>/">博客</a>
                </li>
                <li>
                    <a href="javascript:;">归档</a>
                </li>
                <li>
                    <a href="javascript:;">关于/ 联系</a>
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
                    <a class="menu-item-span" href="javascript:;">关于/ 联系</a>
                </div>
            </div>
        </div>
    </div>
    <!-- /.container -->
</nav>

<!-- Page Header -->
<!-- Set your background image for this header on the line below. -->
<header class="intro-header" style="background-image: url('<%-- ${pageContext.request.contextPath} --%>${sourceHost}/static/images/blog/about-bg.jpg');background-position: 30% center;">
    <div class="mask"></div>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                <div class="page-heading">
                    <h1>About Me</h1>
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

            <p>"I know that I know nothing."</p>
            <p>-- from 2020</p>

            <br/>
            <hr/>
            <br/>

            <p>代码渣，伪文青。希望做到知行合一。</p>
            <p>专注于填坑。由于擅长于挖坑，所以经常在填坑的同时又挖了许多坑。</p>
            <p>业余大概逃不开读书，看电影，听音乐，看NBA，拍照这几件事。</p>
            <p>从现在到未来很长一段时间里，应该会是一名后台工程师。</p>
            <p>-- from 2016</p>
            <br/>
            <hr/>
            <br/>

            <p>联系方式：</p>
            <ul class="list-inline contact-list">
                <li>
                    <a href="javascript:;" target="_blank" data-toggle="tooltip" data-placement="bottom" title="暂未公开。">
                                <span class="fa-stack fa-lg custom-fa-mid">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-twitter fa-stack-1x fa-inverse"></i>
                                </span>
                    </a>
                </li>
                <!-- <li>
                     <a href="javascript:;">
                             <span class="fa-stack fa-lg">
                                 <i class="fa fa-circle fa-stack-2x"></i>
                                 <i class="fa fa-facebook fa-stack-1x fa-inverse"></i>
                             </span>
                     </a>

                 </li>-->
                <li>
                    <a href="https://github.com/teaho2015/" target="_blank"  data-toggle="tooltip" data-placement="top" title="https://github.com/teaho2015/">
                                <span class="fa-stack fa-lg custom-fa-mid">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-github fa-stack-1x fa-inverse"></i>
                                </span>
                    </a>
                </li>
                <li>
                    <a href="javascript:;" target="_blank" data-toggle="tooltip" data-placement="bottom" title="敬请期待">
                                <span class="fa-stack fa-lg custom-fa-mid">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-stack-1x fa-inverse">豆</i>
                                </span>

                    </a>
                </li>
                <li>
                    <a href="https://www.instagram.com/_tea_ho/" target="_blank" data-toggle="tooltip" data-placement="top" title="Instagram:_tea_ho">
                                <span class="fa-stack fa-lg custom-fa-mid">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-instagram fa-stack-1x fa-inverse"></i>
                                </span>

                    </a>
                </li>
                <li <%--data-rotate-y="270deg" &lt;%&ndash;data-move-x="150%"&ndash;%&gt; style="transition: all 1s ease, opacity 0s ease; /*opacity: 0;*/ transform: translateX(150%) rotateY(270deg);"--%>>
                    <a href="mailto:teaho2015#gmail.com(replace '#' with '@')" target="_blank" data-toggle="tooltip" data-placement="bottom" title="E-mail:teaho2015#gmail.com(replace '#' with '@')">
                                <span class="fa-stack fa-lg custom-fa-mid">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-envelope-o fa-stack-1x fa-inverse"></i>
                                </span>

                    </a>
                </li>
                <li>
                    <a href="http://wordpress.teaho.net" target="_blank" data-toggle="tooltip" data-placement="top" title="我的wordpress">
                                <span class="fa-stack fa-lg custom-fa-mid">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-wordpress fa-stack-1x fa-inverse"></i>
                                </span>
                    </a>

                </li>

                <li>
                    <a href="javascript:;" target="_blank" data-toggle="tooltip" data-placement="top" title="我的微信：hotingleong">
                                <span class="fa-stack fa-lg custom-fa-mid">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-weixin fa-stack-1x fa-inverse"></i>
                                </span>
                    </a>

                </li>

            </ul>

            <br/>
            <br/>
            <br/>
            <p>经反复比对，本站遗憾采用Disqus评论系统，如若您怀揣任何建议或意见，烦清翻下墙。</p>

            <p>本站内容除特殊说明外皆为原创(包括内容配图)，并受 <a href='http://creativecommons.org/licenses/by-nc-sa/4.0/'>Creative Commons BY-NC-SA 4.0</a> 开源协议保护。</p>
            <p> <a href="/development/">API说明</a></p>

            <br/><br/>

            <div id="disqus_thread"></div>
            <script>
                /**
                 *  RECOMMENDED CONFIGURATION VARIABLES: EDIT AND UNCOMMENT THE SECTION BELOW TO INSERT DYNAMIC VALUES FROM YOUR PLATFORM OR CMS.
                 *  LEARN WHY DEFINING THESE VARIABLES IS IMPORTANT: https://disqus.com/admin/universalcode/#configuration-variables    */
                var disqus_config = function () {
                this.page.url = "https://blog.teaho.net/about";  // Replace PAGE_URL with your page's canonical URL variable
                this.page.identifier = "about"; // Replace PAGE_IDENTIFIER with your page's unique identifier variable
                };
                (function() { // DON'T EDIT BELOW THIS LINE
                    var d = document, s = d.createElement('script');
                    s.src = 'https://teas-blog.disqus.com/embed.js';
                    s.setAttribute('data-timestamp', +new Date());
                    (d.head || d.body).appendChild(s);
                })();
            </script>
            <noscript>Please enable JavaScript to view the <a href="https://disqus.com/?ref_noscript">comments powered by Disqus.</a></noscript>
        </div>
    </div>
</div>

<hr>

<c:import url="../util/footer.jsp"/>

<!-- jQuery -->
<script type="text/javascript" src='<%-- ${pageContext.request.contextPath} --%>${sourceHost}/static/library/jquery/jquery-1.10.2.js'></script>
<!-- Bootstrap core JavaScript===================== -->
<script src="<%-- ${pageContext.request.contextPath} --%>${sourceHost}/static/library/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Theme JavaScript -->
<script src="<%-- ${pageContext.request.contextPath} --%>${sourceHost}/static/scripts/blog/blog-theme.min.js"></script>
<script src="${sourceHost}/static/scripts/blog/about.js"></script>
<c:import url="../util/google-analytics.jsp"/>
</body>

</html>
