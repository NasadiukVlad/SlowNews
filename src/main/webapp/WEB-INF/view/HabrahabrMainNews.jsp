<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>

<head>
    <title>SlowChat</title>
    <meta charset="utf-8">
    <link href="css/main_pg.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/jsCounter.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script type="text/javascript" src="js/scrollOnTop.js"></script>
</head>

<body>
<div id="page_align">

    <div id="sidebar">

        <a href="IndexPageController">

            <div id="logo_side_bar">
                <img src="images/logo.jpg">
                News, from the last enter:
                <div id="counter"></div>
                Latest topic:
            </div>

        </a>

        <div id="left_navigation">
            <jsp:include page="includeLeftNavigation.jsp"/>
        </div>

        <%--
                <div id="left_content">
                    <jsp:include page="includeLeftContent.jsp"/>
                </div>--%>

        <div id="left_content2">
            <jsp:include page="includeLeftContent2.jsp"/>

        </div>

    </div>

    <div id="top_menu">
        <jsp:include page="includeMenu.jsp"/>
        <c:if test="${not empty username}">
            <div id="user_login">
                Welcome, ${username}! You can <a href="LogoutController" class="top_menu_logout_a">logout</a>
            </div>
        </c:if>
    </div>

    <div id="content">

        <c:forEach items="${habrahabrNews}" var="element">
            <td><h2>${element.title}</h2></td>
            <br>
            <td>${element.description}</td>
            <br>
            <td><a href="${element.link}">More...</a></td>
            <br>
            </tr>

        </c:forEach>

        <c:if test="${not habrahabrIndexFlag}">
            <form id="archive" action="HabrahabrArchivePageController" method="post">

                <input type="text" name="habrahabrNews" hidden="true" value="${habrahabrNews}"/>
                    <%-- <input type="text" name="title" hidden="true" value="${element.title}"/>
                     <input type="text" name="description" hidden="true" value="${element.description}"/>
                     <input type="text" name="link" hidden="true" value="${element.link}"/>--%>
            </form>


            <script type="text/javascript">
                document.getElementById("archive").submit();
            </script>
        </c:if>

    </div>
    <a href="#" id="toTop">TOP!</a>

</div>


<div id="clr"></div>
</div>
</body>

</html>