<!-- base.jsp -->
<%@ include file="/jsp/include_tld.jsp" %>
<% /* <fmt:setBundle basename="com.vh.manchester.resources.application"/> */ %>

<html>
  <head>
    <title><fmt:message key="com.vh.title.jsp.base"/></title>
	<link rel="shortcut icon" type="image/gif" href="img/favicon.ico"/>
  </head>
  <% /* START: setup vars from request, session */ %>
  <c:set var="NO_ACCESS_TO_PROJECT_KEY"><%=com.vh.manchester.util.Constants_Scope.NO_ACCESS_TO_PROJECT_KEY%></c:set>
  <c:set var="noProjectAccess" value="${requestScope[NO_ACCESS_TO_PROJECT_KEY]}" />
  <% /* END: setup vars from request, session */ %>

  <script>
	  var noProjectAccess = '<c:out value="${noProjectAccess}"/>';
	  if(noProjectAccess) alert('<fmt:message key="errors.noprojectaccess"/>');
  </script>
  
  <frameset rows="35,15,*">
    <frame src="PopulateHeader.do"  name="header"  frameborder="0" noresize scrolling="no" />
    <frame src="PopulateSidebar.do" name="sidebar" frameborder="0" noresize scrolling="no" />
    <frame src="SetFilters.do?t=0"  name="main"    frameborder="0" />
  </frameset>
</html>