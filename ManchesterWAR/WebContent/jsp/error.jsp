<%-- 
Page:		error.jsp
Author:		Peter Szocs
--%>

<%@ include file="/jsp/include_tld.jsp" %>

<%@ page isErrorPage="true" %>
<%@ page import = "java.io.PrintWriter"%>

<html>
  <head>
    <%@ include file="/jsp/include_js_css.jsp" %>
    <title>500 - Error Page</title>
  </head>
  <body>

	<% java.lang.Throwable ex = com.vh.manchester.util.SessionUtils.getThrowable(request);%>

	<% /* main output to user */ %>
	<table align="center" border="0" width="100%" cellpadding="0" cellspacing="0">    
	  <tr><td align="center" height="50"></td></tr>
      <tr><td align="center"><h2>500 Error</h2></td></tr>
	  <tr><td align="center" height="30"></td></tr>
	  <tr><td align="center"><h4>A system error has occured.</h4></td></tr>
	  <tr><td align="center" height="10"></td></tr>
	  <tr><td align="center"><p>This is the error:&nbsp;&nbsp;<%=ex%></p></td></tr>
	  <tr><td align="center" height="50"></td></tr>
	  <tr><td align="center"><a href="javascript:history.go(-1)">Try Again</a></td></tr>
    </table>
    <br/><br/><br/><br/><hr width="75%"/>

	<h3>StackTrace:</h3>
	<table border="0" width="100%" cellpadding="0" cellspacing="0">    
 	  <tr><td><% if(ex!=null) ex.printStackTrace( new java.io.PrintWriter(out,false) );%></td></tr>
 	</table>
  
  </body>
</html>