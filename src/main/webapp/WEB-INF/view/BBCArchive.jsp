<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>

<head>
    <title>SlowNews</title>
    <meta charset="utf-8">
    <link href="css/main_pg.css" rel="stylesheet" type="text/css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script type="text/javascript" src="js/jsCounter.js"></script>
    <script type="text/javascript" src="js/scrollOnTop.js"></script>
    <link rel="shortcut icon" href="images/favicon.png" />

</head>

<body>
<div id="page_align" class="page_align">

    <div id="sidebar" class="sidebar">

        <a href="IndexPageController">

            <div id="logo_side_bar" class="logo_side_bar">
                <img src="images/logo.jpg">
                News, from the last enter:
                <div id="counter"></div>
            </div>

        </a>

        <div id="left_navigation" class="left_navigation">
            <jsp:include page="includeLeftNavigation.jsp"/>
        </div>

        <div id="left_content2" class="left_content2">
            <jsp:include page="includeWeatherForecast.jsp"/>
        </div>

    </div>

    <div id="top_menu" class="top_menu">
        <jsp:include page="includeTopMenu.jsp"/>
        <c:if test="${not empty username}">
            <div id="user_login" class="user_login">
                Welcome, ${username}! You can <a href="LogoutController" class="top_menu_logout_a">logout</a>
            </div>
        </c:if>
    </div>

    <div id="content" class="content">

        <c:forEach items="${archiveList}" var="element">
            <td><h2>${element.title}</h2></td>
            <br>
            <td>${element.description}</td>
            <br>
            <td><a href="${element.link}">More...</a></td>
            <br>
            </tr>
        </c:forEach>

    </div>

    <a href="#" id="toTop" class="toTop">TOP!</a>
</div>

<div id="clr"></div>
</div>
</body>

</html>