<!-- sidebar.jsp -->
<%@ include file="/jsp/include_tld.jsp" %>
 
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="css/sidebar.css" />
	<script language="javascript" src="js/js_constants.jsp"></script>
	<script language="javascript" src="js/main.js"></script>
  </head>
  
  <body>
    <% /* START: setup vars from request, session */ %>
	<c:set var="USERTYPE_ADMIN"><%=com.vh.manchester.util.Constants.USERTYPE_ADMIN%></c:set>
	<c:set var="ACCESS_KEY"><%=com.vh.manchester.util.Constants_Scope.ACCESS_KEY%></c:set>
	<c:set var="accessId" value="${sessionScope[ACCESS_KEY]}" />
	<c:set var="USER_KEY"><%=com.vh.manchester.util.Constants_Scope.USER_KEY%></c:set>
	<c:set var="user" value="${sessionScope[USER_KEY]}" />
	<% /* END: setup vars from request, session */ %>

	<div class="hubAd"><fmt:message key="com.vh.title.jsp.login"/></div>

	<% /* View: */ %>
	&nbsp;<fmt:message key="com.vh.label.View"/>:
	<a href="SetFilters.do?t=0" target="main"><fmt:message key="com.vh.label.My"/> </a> |
	<a href="SetFilters.do?t=1" target="main"><fmt:message key="com.vh.label.All"/></a> |
	<a href="SetFilters.do?t=2" target="main"><fmt:message key="com.vh.label.Verify"/></a>

	<% /* Tasks: */ %>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="com.vh.label.Tasks"/>:
	<c:if test="${accessId>1}">
		<a href="ViewCreateTask.do" target="main"><fmt:message key="com.vh.label.Add"/></a> |
		<a href="#" onclick="deleteObjects(1)"><fmt:message key="com.vh.label.Delete"/></a>
	</c:if>
	
	<% /* Other: */ %>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="com.vh.label.Etc"/>:
	<a href="jsp/hasher.jsp" target="main"><fmt:message key="com.vh.label.Hasher"/></a>
	<c:if test="${user.userType.id==USERTYPE_ADMIN}">
		| <a href="jsp/adminbase.jsp" target="main"><fmt:message key="com.vh.label.Admin"/></a>
	</c:if>
	
  </body>
</html>