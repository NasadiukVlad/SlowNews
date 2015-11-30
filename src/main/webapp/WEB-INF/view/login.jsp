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

        <div id="left_content">
            <jsp:include page="includeLeftContent.jsp"/>
        </div>

        <div id="left_content2">
            <jsp:include page="includeLeftContent2.jsp"/>
        </div>

    </div>

    <div id="top_menu">
        <jsp:include page="includeMenu.jsp"/>
        <c:if test="${not empty username}">
            <div id="user_login">
                Welcome, ${username}!
            </div>
        </c:if>
    </div>


    <div id="content">

        <div id="login">
            <%-- <h1>Login form</h1>
             <p>Enter your login: </p>
             <input type="text" size="40">
             <p>Enter your password: </p>
             <input type="text" size="40">
             <br>
             <button>Login</button>
             <br>
             <a href=".html">Registration</a>
             <br>
             <a href=".html">Forgot your password ?</a>
 --%>
            <h1>Login form</h1>



                <form action="LoginController" method="post">

                        <p>Enter your login: </p>
                        <br>
                        <input type="text" name="username">
                        <br>

                        <p>Enter your password: </p>
                        <br>
                        <input type="password" name="password">
                        <br>
                        <input type="submit" value="Login" name="button">

                </form>


        </div>

    </div>


</div>


<div id="clr"></div>
</div>
</body>

</html>