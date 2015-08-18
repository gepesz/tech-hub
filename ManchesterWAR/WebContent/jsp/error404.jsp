<%-- 
Page:		error404.jsp
Author:		Peter Szocs
--%>

<%@ include file="/jsp/include_tld.jsp" %>

<html>
  <head>
    <%@ include file="/jsp/include_js_css.jsp" %>
    <title>404 - File Not Found</title>
  </head>
  <body>

	<% /* main output to user */ %>
	<table align="center" border="0" width="100%" cellpadding="0" cellspacing="0">    
	  <tr><td align="center" height="50"></td></tr>
      <tr><td align="center"><h2>404 Error</h2></td></tr>
	  <tr><td align="center" height="30"></td></tr>
	  <tr><td align="center"><p>The page you have requested does not exist.</p></td></tr>
	  <tr><td align="center" height="10"></td></tr>
    </table>
    <br/><br/><br/><br/><hr width="75%"/>

	<p align="center"><dt:format pattern="yyyy-MM-d HH:mm:ss.S"><dt:currentTime/></dt:format></p>
	<br/>

<%
    System.out.println("User got a 404 for " +  request.getAttribute("javax.servlet.forward.request_uri"));
%>

  </body>
</html>