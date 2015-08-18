<!-- adminsidebar.jsp -->
<%@ include file="/jsp/include_tld.jsp" %>
<% /* <fmt:setBundle basename="com.vh.manchester.resources.application"/> */ %>

<html>
  <head>
    <link rel="stylesheet" type="text/css" href="../css/main.css" />
  </head>  
  <body style="margin-left:10px;">
	<br/>
	<h4><fmt:message key="com.vh.label.AdminConsole"/></h4>

    <b><fmt:message key="com.vh.label.Users"/></b>
	  <ul>
		<li><a href="../RetrieveUsers.do" target="adminmain"><fmt:message key="com.vh.label.Manage"/> <fmt:message key="com.vh.label.Users"/></a></li>
		<li><a href="../Monitor.do" target="adminmain"><fmt:message key="com.vh.label.User"/> <fmt:message key="com.vh.label.Stats"/></a></li>
	  </ul>

    <b><fmt:message key="com.vh.label.Projects"/></b>
	  <ul>
		<li><a href="../RetrieveProjects.do" target="adminmain"><fmt:message key="com.vh.label.Manage"/> <fmt:message key="com.vh.label.Projects"/></a></li>
	  </ul>

  </body>
</html>