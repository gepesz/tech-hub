<!-- header.jsp --> 
<%@ include file="/jsp/include_tld.jsp" %>
 
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="css/header.css" />
	<script language="javascript" src="js/main.js"></script>
  </head>  
  <body onload="fixURL( document.projectform )">
    <% /* START: setup vars from request, session */ %>
	<c:set var="ALL_PROJECTS_KEY"><%=com.vh.manchester.util.Constants_Scope.ALL_PROJECTS_KEY%></c:set>
	<c:set var="allProjectsColl" value="${applicationScope[ALL_PROJECTS_KEY]}" />
	<c:set var="USER_KEY"><%=com.vh.manchester.util.Constants_Scope.USER_KEY%></c:set>
	<c:set var="user" value="${sessionScope[USER_KEY]}" />
	<c:set var="SSOENABLED_KEY"><%=com.vh.manchester.util.Constants_Scope.SSOENABLED_KEY%></c:set>
	<c:set var="ssoenabled" value="${applicationScope[SSOENABLED_KEY]}" />
    <% /* END: setup vars from request, session */ %>

    <h:form action="/ChangeProject" target="_top">
      <table cellspacing="0" cellpadding="0" border="0">
        <tr valign="top">
          <td class="tdlabel"><font color="#999999"><fmt:message key="com.vh.label.Project"/>:</font></td>
          <td align="left"><h:select property="project" onchange="submit();" styleClass="projectdropdown">
								<h:options collection="allProjectsColl" labelProperty="desc" property="id"/>
        	  			   </h:select></td>        	  
          <td align="right">
        	<font color="#999999"><fmt:message key="com.vh.label.Welcome"/>, <c:out value="${user.username}"/>&nbsp;|&nbsp;</font>
        	<a href="ViewUser.do?id=<c:out value='${user.id}'/>&my=1" target="main"><fmt:message key="com.vh.label.Settings"/></a><font color="#999999">&nbsp;|&nbsp;</font>			
		    <c:choose>
              <c:when test="${ssoenabled}">
                <a href="josso_logout" target="_top"><fmt:message key="com.vh.label.Logout"/></a>
			  </c:when>
              <c:otherwise>
                <a href="Logout.do" target="_top"><fmt:message key="com.vh.label.Logout"/></a>
			  </c:otherwise>
            </c:choose>
		  </td>
        </tr>
	  </table>
    </h:form>

  </body>
</html>