<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <title>SlowNews</title>
    <meta charset="utf-8">
    <link href="css/archive.css" rel="stylesheet" type="text/css">
    <link rel="shortcut icon" href="images/favicon.png"/>
</head>

<body>
<div id="page_align" class="page_align">

    <div id="sidebar" class="sidebar">
        <a href="IndexPageController">

            <div id="logo_side_bar" class="logo_side_bar">
                <img src="images/logo.jpg">
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

        Now avaliable archives of next resources:

        <ul>
            <li><a href="/BBCArchivePageController">BBC World</a></li>
            <li><a href="/HabrahabrArchivePageController">Habrahabr</a></li>
            <li><a href="/JavaWorldArchivePageController">Java World</a></li>
        </ul>

    </div>

</div>

<div id="clr"></div>
</div>
</body>

</html>