<!-- monitor.jsp -->
<%@ include file="/jsp/include_tld.jsp" %>

<html>
  <head>
    <%@ include file="/jsp/include_js_css.jsp" %>
  </head>
  
  <body onload="initPage( document.editUser, true )">
  <% /* START: setup vars from request, session */ %>
  <c:set var="MONITORCOLL_KEY"><%=com.vh.manchester.util.Constants_Scope.MONITORCOLL_KEY%></c:set>
  <c:set var="monitorColl" value="${requestScope[MONITORCOLL_KEY]}" />  
  <% /* END: setup vars from request, session */ %>
  
  <br/>
  <form name="editUser" action="SetFilters.do">
  	<table border="0" cellpadding="0" cellspacing="0" class="tablefortasktableview" width="100%">
  	  <thead id="theHeader">
    	<tr>
      	  <th class="thfortasktableview" width="1%"><input type="checkbox" name="toggleAll" disabled="true" onclick="ToggleAll();"></th>
      	  
		  <th class="thfortasktableview" width="24%" onclick="TableSort(1,0);"><fmt:message key="com.vh.label.Who"/>&nbsp;&nbsp;</th>

      	  <th class="thfortasktableview" width="15%" onclick="TableSort(2,4);"><fmt:message key="com.vh.label.Lastlogin"/>&nbsp;&nbsp;</th>

      	  <th class="thfortasktableview" width="10%" onclick="TableSort(3,1);"><fmt:message key="com.vh.label.#login"/>&nbsp;&nbsp;</th>

      	  <th class="thfortasktableview" width="10%" onclick="TableSort(4,1);"><fmt:message key="com.vh.label.Avgprog"/>&nbsp;&nbsp;</th>

      	  <th class="thfortasktableview" width="10%" onclick="TableSort(5,1);"><fmt:message key="com.vh.label.#open"/>&nbsp;&nbsp;</th>

	      <th class="thfortasktableview" width="10%" onclick="TableSort(6,1);"><fmt:message key="com.vh.label.#resolved"/>&nbsp;&nbsp;</th>

    	  <th class="thfortasktableview" width="10%" onclick="TableSort(7,1);"><fmt:message key="com.vh.label.#other"/>&nbsp;&nbsp;</th>

      	  <th class="thfortasktableview" width="10%" onclick="TableSort(8,1);"><fmt:message key="com.vh.label.Total"/>&nbsp;&nbsp;</th>
    	</tr>
	  </thead>
    
	  <tbody id="theRows">
        <c:forEach var="mbean" items="${monitorColl}" varStatus="rowCounter">
    	  <tr>
      	  	<td class="tdfortasktableview" width="10"><input type="checkbox" name="userId" disabled="true" value='<c:out value="${mbean.userId}"/>' onclick="Toggle(this, <c:out value='${rowCounter.count % 2}'/>);"></input></td>
      	  	<td class="tdfortasktableview"><img src="img/user_icon.png"/>&nbsp;&nbsp;<a href="ViewUser.do?id=<c:out value='${mbean.userId}'/>"><c:out value="${mbean.userName}"/></a></td>
      	    <td class="tdfortasktableview" align="center"><c:out value="${mbean.lastLogin}"/>&nbsp;</td>
      	    <td class="tdfortasktableview" align="center"><c:out value="${mbean.nofLogins}"/>&nbsp;</td>
      	    <td class="tdfortasktableview" align="center"><c:out value="${mbean.avgOpenProgress}"/>&nbsp;</td>
      	    <td class="tdfortasktableview" align="center"><c:out value="${mbean.nofOpen}"/>&nbsp;</td>
      	    <td class="tdfortasktableview" align="center"><c:out value="${mbean.nofRslvd}"/>&nbsp;</td>
      	    <td class="tdfortasktableview" align="center"><c:out value="${mbean.nofOther}"/>&nbsp;</td>
      	    <td class="tdfortasktableview" align="center"><c:out value="${mbean.nofTotal}"/>&nbsp;</td>
    	  </tr>
	    </c:forEach>
   	  </tbody>
    </table>
  </form>
  </body>
</html>