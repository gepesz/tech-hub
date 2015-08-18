<!-- viewprojects.jsp -->
<%@ include file="/jsp/include_tld.jsp" %>

<html>
  <head>
    <%@ include file="/jsp/include_js_css.jsp" %>
  </head>
  
  <body onload="initPage( document.deleteform, true )">
    <% /* START: setup vars from request, session */ %>
	<c:set var="ACCESS_KEY"><%=com.vh.manchester.util.Constants_Scope.ACCESS_KEY%></c:set>
	<c:set var="accessId" value="${sessionScope[ACCESS_KEY]}" />
	<c:set var="ALL_PROJECTS_KEY"><%=com.vh.manchester.util.Constants_Scope.ALL_PROJECTS_KEY%></c:set>
	<c:set var="allProjectsColl" value="${applicationScope[ALL_PROJECTS_KEY]}" />
	<c:set var="PROJECT_KEY"><%=com.vh.manchester.util.Constants_Scope.PROJECT_KEY%></c:set>
	<c:set var="currentProject" value="${sessionScope[PROJECT_KEY]}" />
	<c:set var="PROJECTS_CHANGED"><%=com.vh.manchester.util.Constants_Scope.PROJECTS_CHANGED%></c:set>
	<c:set var="projectsChanged" value="${requestScope[PROJECTS_CHANGED]}" />
	<% /* END: setup vars from request, session */ %>
  
  	<script>
	  var projectsChanged = '<c:out value="${projectsChanged}"/>';
	  if(projectsChanged) {
		  var oldProjectDropdown = top.frames[0].document.forms[0].elements[0];
		  oldProjectDropdown.options.length = 0;
	      var i=0;
		  <c:forEach var="proj" items="${allProjectsColl}">
			var id   = '<c:out value="${proj.id}"/>';
			var desc = '<c:out value="${proj.desc}"/>';
			oldProjectDropdown.options[i++] = new Option(desc, id);
		  </c:forEach>
	  }
	</script>

  <% /* Projects: */ %>
  <br/>
  &nbsp;<fmt:message key="com.vh.label.Projects"/>:
  <c:if test="${accessId>1}">
	<a href="ViewCreateProject.do"><fmt:message key="com.vh.label.Add"/></a> |
	<a href="#" onclick="deleteObjects(3)"><fmt:message key="com.vh.label.Delete"/></a>
  </c:if>
  <br/><br/>
  
  <form id="deleteform" name="deleteform" action="DeleteProjects.do">
  	<table border="0" cellpadding="0" cellspacing="0" class="tablefortasktableview" width="100%">
  	  <thead id="theHeader">
    	<tr>
      	  <th class="thfortasktableview" width="1%"><input type="checkbox" name="toggleAll" disabled="true" onclick="ToggleAll();"></th>
      	  
		  <th class="thfortasktableview" width="74%" onclick="TableSort(1,0);"><fmt:message key="com.vh.label.Project"/>&nbsp;&nbsp;</th>

      	  <th class="thfortasktableview" width="25%" onclick="TableSort(2,0);"><fmt:message key="com.vh.label.ID"/>&nbsp;&nbsp;</th>
    	</tr>
	  </thead>
    
	  <tbody id="theRows">
        <c:forEach var="proj" items="${allProjectsColl}" varStatus="rowCounter">
    	  <tr>
      	  	<td class="tdfortasktableview" width="10">
			
  			  <c:if test="${currentProject.id==proj.id}">
  				<input type="checkbox" name="id" disabled="true" title='<fmt:message key="errors.cannotdelcurrentproj"/>'></input>
  			  </c:if>
  			  <c:if test="${currentProject.id!=proj.id}">
  				<input type="checkbox" name="id" value='<c:out value="${proj.id}"/>' onclick="Toggle(this, <c:out value='${rowCounter.count % 2}'/>);"></input>
  			  </c:if>
			
			</td>
      	  	<td class="tdfortasktableview"><img src="img/clipboard_icon.png"/>&nbsp;&nbsp;<a href="ViewProject.do?id=<c:out value='${proj.id}'/>"><c:out value="${proj.desc}"/></a></td>
      	    <td class="tdfortasktableview" align="center"><c:out value="${proj.id}"/>&nbsp;</td>
    	  </tr>
	    </c:forEach>
   	  </tbody>
    </table>
  </form>
  </body>
</html>