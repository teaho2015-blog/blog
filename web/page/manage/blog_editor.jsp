<%--
  create by : teaho2015@gmail.com
  Date: 2016/11/12
  Time: 10:49
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%--<base href="<%=basePath%>">--%>
    <title>My JSP 'index.jsp' starting page</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <!-- Bootstrap core CSS -->
    <link href="<%-- ${pageContext.request.contextPath} --%>/static/library/bootstrap/dist/css/bootstrap.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <%--<link href="${pageContext.request.contextPath}/static/library/bootstrap/dist/css/bootstrap-theme.css" rel="stylesheet">--%>
    <link rel="stylesheet" href="<%-- ${pageContext.request.contextPath} --%>/static/styles/manage/blog_editor.css"/>
    <!-- 配置文件 -->
    <script type="text/javascript" src="<%-- ${pageContext.request.contextPath} --%>/static/library/ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="<%-- ${pageContext.request.contextPath} --%>/static/library/ueditor/ueditor.all.js"></script>
    <!-- 语言包文件(建议手动加载语言包，避免在ie下，因为加载语言失败导致编辑器加载失败) -->
    <script type="text/javascript" src="<%-- ${pageContext.request.contextPath} --%>/static/library/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
<section class="container">
    <form action="/manage/blog" METHOD="post" <%--onsubmit="return beforeSubmit();"--%> novalidate enctype="multipart/form-data" >
        <div class="blog-editor-header">
            <div class="form-group">
                <label for="postTitle" class="sr-only">title</label>
                <input type="text" id="postTitle" name="title" class="form-control blogDialog" placeholder="Title" required/>
            </div>
            <div class="form-group">
                <label for="postTitleSecondary" class="sr-only">title_secondary</label>
                <input type="text" id="postTitleSecondary" class="form-control blogDialog"
                        placeholder="Title_secondary" name="title_secondary" required/>
            </div>

           <%-- <div class="form-group">
                        <textarea name="content" id="content" class="form-control input-group-lg blogDialog"
                                placeholder="Your text here" required style="resize: none;" rows="10"></textarea>
            </div>--%>
            <div class="form-group">
                <label for="photo" class="sr-only">photo</label>

                <p>head_image：<input type="file" name="image" id="photo" class="form-control blogDialog" placeholder="photo"
                        required autofocus/></p>
            </div>

          <%--  <div class="form-group">
                <input type="text" id="blog-body" class="form-control blogDialog hide" placeholder=""
                        required/>
            </div>--%>
        </div>
        <hr/>
        <script id="blog-ueditor" name="content" type="text/plain">这里写你的初始化内容</script>

        <div class="blog-editor-controller">
            <input type="submit" class="btn btn-primary publisher" value="发布"/>
            <button class="btn btn-default clear">清空</button>
        </div>
    </form>
</section>
</body>
<script type="text/javascript">
    var editor = UE.getEditor('blog-ueditor');
</script>
<!-- Bootstrap core JavaScript
================================================== -->
<script type="text/javascript" src='<%-- ${pageContext.request.contextPath} --%>/static/library/jquery/jquery-1.10.2.js'></script>
<script type="text/javascript" src="<%-- ${pageContext.request.contextPath} --%>/static/library/bootstrap/dist/js/bootstrap.js"></script>
<script type="text/javascript" src="<%-- ${pageContext.request.contextPath} --%>/static/scripts/manage/blog_editor.js"></script>

<script type="text/javascript">
//    function beforeSubmit() {
//        $("#blog-body").val(editor.getContent());
//    }
</script>
<c:import url="../util/google-analytics.jsp"/>
</html>
