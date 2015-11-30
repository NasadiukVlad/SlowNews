<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>

<head>
    <title>SlowChat</title>
    <meta charset="utf-8">
    <link href="css/main_pg.css" rel="stylesheet" type="text/css">
    <script type="text/javascript"  src="js/jsCounter.js"></script>
</head>

<body>
<div id="page_align">

    <div id="sidebar">

        <a href="IndexPageController">

            <div id="logo_side_bar">
                <img src="images/logo.jpg">
                News, from the last enter: <div id="counter"></div>
            </div>

        </a>

        <div id="left_navigation">
            <jsp:include page="includeLeftNavigation.jsp"/>
        </div>


        <div id="left_content">
            <jsp:include page="includeLeftContent.jsp"/>
        </div>

        <div id="left_content2">
            <jsp:include page="includeLeftContent2.jsp"/>

        </div>

    </div>

    <div id="top_menu">
        <jsp:include page="includeMenu.jsp"/>
        <div id="user_login">
            Welcome, ${username}!
        </div>
    </div>

    <div id="content">


        <!--<h1>Investigate world with us!</h1>-->

        12.10.15 <a href="news1">Developers weigh JSON, security proposals for Java EE 8</a>
        <br>
        05.05.15 <a href=".html">Oracle lays out plans for the next Java generation</a>
        <br>
        14.12.15 <a href=".html">Java developers carry hopes and fears to JavaOne</a>
        <br>
        27.01.15 <a href=".html">Oracle fixes critical flaws in Database Server, MySQL, Java</a>
        <br>
        30.06.15 <a href=".html">Word to API providers: Make it simple for developers</a>
        <br>
        08.11.15 <a href=".html">Oracle considers a new effort to develop mobile Java apps</a>
        <br>
        12.10.15 <a href=".html">Developers weigh JSON, security proposals for Java EE 8</a>
        <br>
        05.05.15 <a href=".html">Oracle lays out plans for the next Java generation</a>
        <br>
        14.12.15 <a href=".html">Java developers carry hopes and fears to JavaOne</a>
        <br>
        27.01.15 <a href=".html">Oracle fixes critical flaws in Database Server, MySQL, Java</a>
        <br>
        30.06.15 <a href=".html">Word to API providers: Make it simple for developers</a>
        <br>
        08.11.15 <a href=".html">Oracle considers a new effort to develop mobile Java apps</a>
        <br>


    </div>


</div>


<div id="clr"></div>
</div>
</body>

</html>