<%--
  create by : teaho2015@gmail.com
  Date: 2016/11/30
  Time: 17:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tea's Article</title>
    <meta name="viewport" content="initial-scale=1.0, width=device-width, minimum-scale=1.0, maximum-scale=2.0">
    <link rel="icon" href="<%-- ${pageContext.request.contextPath} --%>/static/images/t.png" />

     <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700,400italic,700italic|PT+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <!-- Bootstrap core CSS -->
    <link href="<%-- ${pageContext.request.contextPath} --%>/static/library/bootstrap/dist/css/bootstrap.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <%--<link href="${pageContext.request.contextPath}/static/library/bootstrap/dist/css/bootstrap-theme.css" rel="stylesheet">--%>

    <link href='<%-- ${pageContext.request.contextPath} --%>/static/styles/blog/article.css' rel='stylesheet' type='text/css'>


</head>
<body>
<%--<div class="fixed-svg si-icons">
    <span class="si-icon si-icon-hamburger-cross" data-icon-name="hamburgerCross"></span>
    <ol class="navBar">
        <li><a href="javascript:void(0);"><img src="images/home.png" /></a></li>
        <li><a href="javascript:void(0);"><img src="images/write.png" /></a></li>
        <li><a href="javascript:void(0);"><img src="images/setting.png" /></a></li>

        <!--<li><a href="#">子</a></li>-->

    </ol>
</div>--%>



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
            <time></time> 发布
            <!--by <span class='author'></span>-->
        </h3>
        <h1 class='title'></h1>

        <h2 class='description'></h2>

        <div class='text'></div>
    </div>
</article>

</body>
<script type="text/javascript" src='<%-- ${pageContext.request.contextPath} --%>/static/library/jquery/jquery-1.10.2.js'></script>
<script type="text/javascript" src="<%-- ${pageContext.request.contextPath} --%>/static/library/bootstrap/dist/js/bootstrap.js"></script>
<script type="text/javascript" src='<%-- ${pageContext.request.contextPath} --%>/static/library/moment/moment-with-locales.js'></script>


<script type="text/javascript" src='<%-- ${pageContext.request.contextPath} --%>/static/scripts/blog/article.js'></script>
<!-- svg file -->
<!--<link rel="stylesheet" type="text/css" href="css/demo.css" />-->
<%--<link rel="stylesheet" type="text/css" href="css/component.css" />--%>
<%--<script src="js/snap.svg-min.js"></script>--%>
<!-- /svg file -->

<%--<script src="js/svgicons-config.js"></script>--%>
<%--<script src="js/svgicons.js"></script>--%>
<%--
<script>
    (function() {
        // initialize all

        [].slice.call( document.querySelectorAll( '.si-icon' ) ).forEach( function( el ) {
            var svgicon = new svgIcon( el, svgIconConfig );
//            alert("1");
        } );


//        new svgIcon( document.querySelector( '.si-icons-easing .si-icon-hamburger-cross' ), svgIconConfig, { easing : mina.elastic, speed: 600 } );

    })();
    $(function(){
        $(".fixed-svg .si-icon-hamburger-cross").click(function(){
            var navBar = $(".fixed-svg .navBar");
            if(navBar.is(":visible")){
                $(".fixed-svg .navBar").hide();
            }else{
                $(".fixed-svg .navBar").slideDown();
            }
        });
    });
</script>--%>
</html>

