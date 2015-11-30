<!DOCTYPE html>
<html>

<head>
    <title>SlowChat</title>
    <meta charset="utf-8">
    <link href="css/news.css" rel="stylesheet" type="text/css">
</head>

<body>
<div id="page_align">

    <div id="sidebar">

        <a href="index.jsp">

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
    </div>

    <div id="content">


        <h1>Sockets</h1>

        <p>If you want to create socket, you must know <strong>5 parameters:</strong>
        <ul>
            <li>Protocol</li>
            <li>Local adress</li>
            <li>Local port</li>
            <li>Remote adress</li>
            <li>Remote port</li>
        </ul>
        </p>
        <p>
            For creating connecting by using <strong>TCP/IP</strong> you need next parameters:
        <ul>
            <li>IP</li>
            <li>Port</li>
            <li>Protocol: TCP or UDP</li>
        </ul>
        </p>
        <a href="https://www.dropbox.com/s/4ec4ggiqgfrv14f/packet-switched-networking.pdf?raw=1">Download
            presentation</a>


    </div>


</div>


<div id="clr"></div>
</div>
</body>

</html>