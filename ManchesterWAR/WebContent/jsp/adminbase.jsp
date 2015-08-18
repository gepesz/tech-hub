<!-- adminbase.jsp -->
<%@ include file="/jsp/include_tld.jsp" %>

<html>
  <head>
    <title><fmt:message key="com.vh.title.jsp.base"/></title>
  </head>
  
  <frameset cols="30%,*">
    <frame src="adminsidebar.jsp" name="adminsidebar" frameborder="1" />
    <frame src="include_tld.jsp"  name="adminmain"    frameborder="1" />
  </frameset>
</html>