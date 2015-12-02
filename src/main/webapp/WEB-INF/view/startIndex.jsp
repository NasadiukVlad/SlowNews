<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>

<head>
    <title>SlowChat</title>
    <meta charset="utf-8">
    <link href="css/main_pg.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/jsCounter.js"></script>
</head>

<body>
<div id="page_align">

    <div id="sidebar">

        <a href="IndexPageController">

            <div id="logo_side_bar">
                <img src="images/logo.jpg">
                News, from the last enter:
                <div id="counter"></div>
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

        <c:forEach items="${news}" var="element">
            <td><h2>${element.title}</h2></td>
            <br>
            <td>${element.description}</td>
            <br>
            <td><a href="${element.link}">More...</a></td>
            <br>
            </tr>
        </c:forEach>


    </div>


</div>


<div id="clr"></div>
</div>
</body>

</html>