<div id="user_login">
  <%
    String name = request.getParameter("Username");
//
    if ( name != null && !name.isEmpty() || session.getAttribute("Username") == null || session.getAttribute("Username").equals("")) {
      names.add(name);
      session.setAttribute("Username", name); %>
  Loged as, <%= name  %>
  <%
  } else {
    //  name = names.get(0);
    name = (String) session.getAttribute(name);

  %>

  Loged as, <%= name  %>
  <%

    }

  %>

</div>