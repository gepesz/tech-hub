<!-- viewusers.jsp -->
<%@ include file="/jsp/include_tld.jsp" %>

<html>
  <head>
    <%@ include file="/jsp/include_js_css.jsp" %>
  </head>
  
  <body onload="initPage( document.deleteform, true )">
  <% /* START: setup vars from request, session */ %>
  <c:set var="ACCESS_KEY"><%=com.vh.manchester.util.Constants_Scope.ACCESS_KEY%></c:set>
  <c:set var="accessId" value="${sessionScope[ACCESS_KEY]}" />
  <c:set var="ALL_USERS_KEY"><%=com.vh.manchester.util.Constants_Scope.ALL_USERS_KEY%></c:set>
  <c:set var="allUsersColl" value="${applicationScope[ALL_USERS_KEY]}" />
  <c:set var="USER_KEY"><%=com.vh.manchester.util.Constants_Scope.USER_KEY%></c:set>
  <c:set var="currentUser" value="${sessionScope[USER_KEY]}" />
  <% /* END: setup vars from request, session */ %>
  
  <% /* Users: */ %>
  <br/>
  &nbsp;<fmt:message key="com.vh.label.Users"/>:
  <c:if test="${accessId>1}">
	<a href="ViewCreateUser.do"><fmt:message key="com.vh.label.Add"/></a> |
	<a href="#" onclick="deleteObjects(2)"><fmt:message key="com.vh.label.Delete"/></a>
  </c:if>
  <br/><br/>

  <form id="deleteform" name="deleteform" action="DeleteUsers.do">
  	<table border="0" cellpadding="0" cellspacing="0" class="tablefortasktableview" width="100%">
  	  <thead id="theHeader">
    	<tr>
      	  <th class="thfortasktableview" width="1%"><input type="checkbox" name="toggleAll" disabled="true" onclick="ToggleAll();"></th>
      	  
		  <th class="thfortasktableview" width="34%" onclick="TableSort(1,0);"><fmt:message key="com.vh.label.Who"/>&nbsp;&nbsp;</th>

      	  <th class="thfortasktableview" width="20%" onclick="TableSort(2,0);"><fmt:message key="com.vh.label.Type"/>&nbsp;&nbsp;</th>

      	  <th class="thfortasktableview" width="25%" onclick="TableSort(3,0);"><fmt:message key="com.vh.label.Project"/>&nbsp;&nbsp;</th>

		  <th class="thfortasktableview" width="20%" onclick="TableSort(4,0);"><fmt:message key="com.vh.label.Language"/>&nbsp;&nbsp;</th>
    	</tr>
	  </thead>
    
	  <tbody id="theRows">
        <c:forEach var="user" items="${allUsersColl}" varStatus="rowCounter">
    	  <tr>
      	  	<td class="tdfortasktableview" width="10">
  			  <c:if test="${currentUser.id==user.id}">
  				<input type="checkbox" name="id" disabled="true" title='<fmt:message key="errors.cannotdelyourself"/>'></input>
  			  </c:if>
  			  <c:if test="${currentUser.id!=user.id}">
  				<input type="checkbox" name="id" value='<c:out value="${user.id}"/>' onclick="Toggle(this, <c:out value='${rowCounter.count % 2}'/>);"></input>
  			  </c:if>
			</td>
      	  	<td class="tdfortasktableview"><img src="img/user_icon.png"/>&nbsp;&nbsp;<a href="ViewUser.do?id=<c:out value='${user.id}'/>"><c:out value="${user.username}"/></a></td>
      	    <td class="tdfortasktableview" align="center"><c:out value="${user.userType.desc}"/>&nbsp;</td>
      	    <td class="tdfortasktableview" align="center"><c:out value="${user.lastProject.desc}"/>&nbsp;</td>
      	    <td class="tdfortasktableview" align="center"><c:out value="${user.locale.desc}"/>&nbsp;</td>
    	  </tr>
	    </c:forEach>
   	  </tbody>
    </table>
  </form>
  </body>
</html>