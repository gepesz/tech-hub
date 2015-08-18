<!-- viewtasks.jsp -->
<%@ include file="/jsp/include_tld.jsp" %>

<html>
  <head>
    <%@ include file="/jsp/include_js_css.jsp" %>
  </head>  
  <body onload="initPage( document.deleteform, false ); fixURL( document.filterform );">
    <% /* START: setup vars from request, session */ %>
    <c:set var="taskCollSize"><z:size collection="<%=com.vh.manchester.util.Constants_Scope.TASKCOLL_KEY%>" scope="request"/></c:set>
	<c:set var="TASKCOLL_KEY"><%=com.vh.manchester.util.Constants_Scope.TASKCOLL_KEY%></c:set>
	<c:set var="taskColl" value="${requestScope[TASKCOLL_KEY]}" />
	<c:set var="LOCALE_CHANGED_KEY"><%=com.vh.manchester.util.Constants_Scope.LOCALE_CHANGED_KEY%></c:set>
	<c:set var="localeChanged" value="${requestScope[LOCALE_CHANGED_KEY]}" />
	<% /* END: setup vars from request, session */ %>

	<script>
	  var localeChanged = '<c:out value="${localeChanged}"/>';
	  if(localeChanged) top.location.href = 'jsp/base.jsp';
	</script>

  <h:form action="/SetFilters">
  <table cellpadding="0" cellspacing="0" bgcolor="#CCCCCC"  width="100%" >
    <tr>
      <td align="left" valign="middle" height="35">
	    &nbsp;&nbsp;<big><b><c:out value="${taskCollSize}"/></b></big>&nbsp;
		<c:if test="${taskCollSize==1}">
          <fmt:message key="com.vh.label.result"/>
		</c:if>
		<c:if test="${taskCollSize!=1}">
          <fmt:message key="com.vh.label.results"/>
		</c:if>
	  </td>    
      <td align="right" valign="middle" height="35">
	    <h:text property="fsearch" styleClass="dropdownclass" style="line-height:14px;"/>&nbsp;&nbsp;<h:submit styleClass="button" onmouseover="style.background='#CCCCCC'; style.color='#000000';" onmouseout="style.background='#003366'; style.color='#FFFFFF';"><fmt:message key="com.vh.label.Search"/></h:submit>&nbsp;&nbsp;
	  </td>
    </tr>
	<tr>
      <td colspan="2">	
        <table border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
          <tr class="textstyleforh">
		    <td align="left"  valign="middle"><b>&nbsp;&nbsp;<fmt:message key="com.vh.label.Filters"/>: </b></td>
			<td align="left"  valign="bottom">          		
          		<fmt:message key="com.vh.label.Status"/>&nbsp;
          		<h:select property="fstatus" onchange="submit();" styleClass="dropdownclass">
          			<option value="-1"></option>
					<h:options collection="<%=com.vh.manchester.util.Constants_Scope.ALL_TASKSTATUSES_KEY%>"
				               labelProperty="desc"
							   property="id"/>
				</h:select>
			</td>
			<td align="left"  valign="bottom">          		          
          		<fmt:message key="com.vh.label.Who"/>&nbsp;
          		<h:select property="fassignedTo" onchange="submit();" styleClass="dropdownclass">
          			<option value="-1"></option>
					<h:options collection="<%=com.vh.manchester.util.Constants_Scope.ALL_DEVELOPERS_IN_PROJECT_KEY%>"
					           labelProperty="username"
							   property="id"/>
          		</h:select>
            </td>
			<td align="left"  valign="bottom">          		          
          		<fmt:message key="com.vh.label.Reportedby"/>&nbsp;
          		<h:select property="freportedBy" onchange="submit();" styleClass="dropdownclass">
          			<option value="-1"></option>
					<h:options collection="<%=com.vh.manchester.util.Constants_Scope.ALL_DEVELOPERS_IN_PROJECT_KEY%>"
					           labelProperty="username"
							   property="id"/>
          		</h:select>
            </td>
			<td align="left"  valign="bottom">
          		<fmt:message key="com.vh.label.When"/>&nbsp;
          		<h:select property="fwhen" onchange="submit();" styleClass="dropdownclass">
          			<option value="-1"></option>
					<h:options collection="<%=com.vh.manchester.util.Constants_Scope.ALL_DATES_KEY%>"
				               labelProperty="desc"
							   property="value"/>
				</h:select>
			</td>
          </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td height="5" colspan="2"></td>
    </tr>
  </h:form>

    <tr>
  <form id="deleteform" name="deleteform" action="DeleteTasks.do">
  <table border="0" cellpadding="0" cellspacing="0" class="tablefortasktableview">
  	<thead id="theHeader">
    <tr>
      <th class="thfortasktableview" width="1%"><input type="checkbox" name="toggleAll" title='<fmt:message key="com.vh.label.Selectall"/>' onclick="ToggleAll();"></th>

      <th class="thfortasktableview" width="7%" onclick="TableSort(1,1);" title='<fmt:message key="com.vh.label.Sortby"/> <fmt:message key="com.vh.label.ID"/>'><fmt:message key="com.vh.label.ID"/>&nbsp;&nbsp;</th>
      
	  <th class="thfortasktableview" width="39%" onclick="TableSort(2,0);" title='<fmt:message key="com.vh.label.Sortby"/> <fmt:message key="com.vh.label.Title"/>'><fmt:message key="com.vh.label.Title"/>&nbsp;&nbsp;</th>
      
	  <th class="thfortasktableview" width="9%" onclick="TableSort(3,4);" title='<fmt:message key="com.vh.label.Sortby"/> <fmt:message key="com.vh.label.Date"/>'><fmt:message key="com.vh.label.Date"/>&nbsp;&nbsp;</th>

	  <th class="thfortasktableview" width="9%" onclick="TableSort(4,0);" title='<fmt:message key="com.vh.label.Sortby"/> <fmt:message key="com.vh.label.Status"/>'><fmt:message key="com.vh.label.Status"/>&nbsp;&nbsp;</th>

	  <th class="thfortasktableview" width="9%" onclick="TableSort(5,0);" title='<fmt:message key="com.vh.label.Sortby"/> <fmt:message key="com.vh.label.Priority"/>'><fmt:message key="com.vh.label.Priority"/>&nbsp;&nbsp;</th>
      
	  <th class="thfortasktableview" width="9%" onclick="TableSort(6,0);" title='<fmt:message key="com.vh.label.Sortby"/> <fmt:message key="com.vh.label.Difficulty"/>'><fmt:message key="com.vh.label.Difficulty"/>&nbsp;&nbsp;</th>

      <th class="thfortasktableview" width="9%" onclick="TableSort(7,0);" title='<fmt:message key="com.vh.label.Sortby"/> <fmt:message key="com.vh.label.Who"/>'><fmt:message key="com.vh.label.Who"/>&nbsp;&nbsp;</th>

      <th class="thfortasktableview" width="8%" onclick="TableSort(8,1);" title='<fmt:message key="com.vh.label.Sortby"/> <fmt:message key="com.vh.label.Progress"/>'><fmt:message key="com.vh.label.Progress"/>&nbsp;&nbsp;</th>
    </tr>
	</thead>

    
	<tbody id="theRows">
      <c:forEach var="task" items="${taskColl}" varStatus="rowCounter">
        <tr>
    	  <td class="tdfortasktableview" width="10"><input type="checkbox" name="id" value='<c:out value="${task.id}"/>' onclick="Toggle(this, <c:out value='${rowCounter.count % 2}'/>);"></input></td>
          <td class="tdfortasktableview" align="center"><img src="img/bug_icon.gif"/>&nbsp;&nbsp;<a title='<fmt:message key="com.vh.label.Edittask"/>' href="ViewTask.do?id=<c:out value='${task.id}'/>"><c:out value="${task.id}"/></a>
		  </td>
          <td class="tdfortasktableview"><a title="<c:out value='${task.summary}'/>" style="color:black;"><string:truncateNicely lower="40" upper="40"><c:out value="${task.title}"/></string:truncateNicely></a></td>
          <td class="tdfortasktableview" align="center"><c:out value="${task.plannedDate}"/>&nbsp;</td>
          <td class="tdfortasktableview" align="center"><c:out value="${task.status}"/>&nbsp;</td>
          <td class="tdfortasktableview" align="center"><c:out value="${task.priority}"/>&nbsp;</td>
          <td class="tdfortasktableview"><c:out value="${task.difficulty}"/>&nbsp;</td>
          <td class="tdfortasktableview"><string:truncateNicely lower="17" upper="17"><c:out value="${task.who}"/></string:truncateNicely></td>
          <td class="tdfortasktableview">
				  <table border="0" cellpadding="0" cellspacing="0">
				    <tr>
					    <c:if test="${task.progress>0}">
					  	  <c:if test="${task.progress==100}">
				    	    <td width="99%" class="noprogress"><fmt:message key="com.vh.label.done"/></td>
				    	  </c:if>
					  	  <c:if test="${task.progress!=100}">
				    	    <td width='<c:out value="${task.progress}"/>%' class="progress" title='<c:out value="${task.progress}"/>%'></td>
				    	  </c:if>
					  	</c:if>
				    	<td width="1" class="noprogress">&nbsp;</td>
				    </tr>
				  </table>
          </td>
        </tr>
	  </c:forEach>
   	</tbody>
  </table>

  </form>
  </tr>  
  </table>  
  </body>
</html>