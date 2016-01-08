<%@ page import="java.util.Random" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>SlowChat</title>
    <meta charset="utf-8">
    <link href="css/login.css" rel="stylesheet" type="text/css">
</head>

<body>

<div id="page_align">

    <div id="sidebar">

        <a href="IndexPageController">

            <div id="logo_side_bar">
                <img src="images/logo.jpg">
            </div>

        </a>

        <div id="left_navigation">
            <jsp:include page="includeLeftNavigation.jsp"/>
        </div>

        <div id="left_content2">
            <jsp:include page="includeWeatherForecast.jsp"/>
        </div>

    </div>

    <div id="top_menu">

        <jsp:include page="includeTopMenu.jsp"/>

        <c:if test="${not empty username}">
            <div id="user_login">
                Welcome, ${username}! You can <a href="LogoutController" class="top_menu_logout_a">logout</a>
            </div>
        </c:if>

    </div>

    <div id="content">

        <div id="login">

            <h1 class="h1">Login form</h1>


            <form action="LoginController" method="post" class="login_form">
                <br>

                <p class="login_p">Enter your login: </p>
                <br>
                <input type="text" name="username" class="login_form_input">
                <br>

                <p class="login_p">Enter your password: </p>
                <br>
                <input type="password" name="password" class="login_form_input">
                <br>
                <input type="submit" value="Login" name="button" class="button">

            </form>

        </div>

    </div>

</div>

<div id="clr"></div>
</div>
</body>

</html>