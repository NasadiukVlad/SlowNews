<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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

        <div id="login">
            <h1>Registration form</h1>
<%--
            <p>Enter your name: </p>
            <input type="text" size="40">

            <p>Enter your password: </p>
            <input type="text" size="40">
            <br>

            <p>Enter your e-mail: </p>
            <input type="text" size="40">
            <br>
            <button>Registration</button>
            <br>
            <a href="login.html">Already have an acount? Login</a>--%>

            <form action="RegistrationController" method="post">
                <p>Enter your name: </p>
                <br>
                <input type="text" name="username">
                <br>
                <p>Enter your password: </p>
                <br>
                <input type="password" name = "password">
                <br>
                <input type="submit" value = "Register" name="button" class = "buttons">

            </form>


        </div>

    </div>


</div>


<div id="clr"></div>
</div>
</body>

</html>