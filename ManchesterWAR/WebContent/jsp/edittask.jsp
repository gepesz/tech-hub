<!-- edittask.jsp -->
<%@ include file="/jsp/include_tld.jsp" %>
 
<html>
  <head>
    <%@ include file="/jsp/include_js_css.jsp" %>
	<link rel="stylesheet" type="text/css" href="js/popcalendar/popcalendar.css" />  
	<script language="javascript" src="js/popcalendar/popcalendar.js"></script>
  </head>
  <body  onload="fixURL( document.taskform )">

  <% /* START: setup vars from request, session */ %>
  <c:set var="USERTYPE_ADMIN"><%=com.vh.manchester.util.Constants.USERTYPE_ADMIN%></c:set>
  <c:set var="USER_KEY"><%=com.vh.manchester.util.Constants_Scope.USER_KEY%></c:set>
  <c:set var="user" value="${sessionScope[USER_KEY]}" />
  <c:set var="TASK_KEY"><%=com.vh.manchester.util.Constants_Scope.TASK_KEY%></c:set>
  <c:set var="task" value="${requestScope[TASK_KEY]}" />
  <% /* END: setup vars from request, session */ %>
  
  <br/>
  <h:form action="/SaveTask">
	<h:hidden property="id"/>

    <fieldset>
    <legend><fmt:message key="com.vh.label.Edittask"/>: #<c:out value="${task.id}"/></legend>
	<p class="requiredFieldExplanator"><span class="requiredFieldStar">*</span> <fmt:message key="com.vh.label.Indicatesreqfields"/></p>

	<table>
	  <tr><% /* The Task, The Workflow */ %>
	  	<td class="tdheader"><fmt:message key="com.vh.label.Thetask"/></td><td class="tdheader"><fmt:message key="com.vh.label.Theworkflow"/></td>
	  </tr>
	  <tr>
	    <td valign="top">
	  	  <!-- col 1 -->
	  	  <table border="0" cellpadding="0" cellspacing="0">
			<tr>
		  	  <td class="tdlabel"><fmt:message key="com.vh.label.Title"/>:<span class="requiredFieldStar">*</span>&nbsp;</td>
		  	  <td><h:text property="title" styleClass="formdropdown"/></td>
	    	</tr>
	    	<tr>
	      	  <td class="tdlabel"><fmt:message key="com.vh.label.Summary"/>:<span class="requiredFieldStar">*</span>&nbsp;</td>
	      	  <td><h:textarea property="summary" rows="15" styleClass="formdropdown"/></td>
	    	</tr>
	    	<tr>
	      	  <td class="tdlabel"><fmt:message key="com.vh.label.Type"/>:<span class="requiredFieldStar">*</span>&nbsp;</td>
          	  <td><h:select property="type" styleClass="formdropdown">
					<h:options collection="<%=com.vh.manchester.util.Constants_Scope.ALL_TASKTYPES_KEY%>" 
					           labelProperty="desc" 
					           property="id"/>
        	      </h:select></td>
	    	</tr>
	    	<tr>
	      	  <td class="tdlabel"><fmt:message key="com.vh.label.Priority"/>:<span class="requiredFieldStar">*</span>&nbsp;</td>
          	  <td><h:select property="priority" styleClass="formdropdown">
					<h:options collection="<%=com.vh.manchester.util.Constants_Scope.ALL_TASKPRIORITIES_KEY%>" 
					           labelProperty="desc" 
							   property="id"/>
	    	  	  </h:select></td>
	    	</tr>
	    	<tr>
	      	  <td class="tdlabel"><fmt:message key="com.vh.label.Difficulty"/>:<span class="requiredFieldStar">*</span>&nbsp;</td>
          	  <td><h:select property="difficulty" styleClass="formdropdown">
					<h:options collection="<%=com.vh.manchester.util.Constants_Scope.ALL_TASKDIFFICULTIES_KEY%>" 
					           labelProperty="desc" 
							   property="id"/>
	    	  	  </h:select></td>
	    	</tr>
	      </table>
	    </td>
	    
	    <td valign="top">
	  	  <!-- col 2 -->
	  	  <table border="0" cellpadding="0" cellspacing="0">
	    	<tr>
	      	  <td class="tdlabel"><fmt:message key="com.vh.label.Status"/>:<span class="requiredFieldStar">*</span>&nbsp;</td>
          	  <td><h:select property="status" styleClass="formdropdown">
					<h:options collection="<%=com.vh.manchester.util.Constants_Scope.ALL_TASKSTATUSES_KEY%>" 
					           labelProperty="desc" 
							   property="id"/>
	    	  	  </h:select></td>
	    	</tr>
	    	<tr>
	      	  <td class="tdlabel"><fmt:message key="com.vh.label.Project"/>:<span class="requiredFieldStar">*</span>&nbsp;</td>
          	  <td><h:select property="projectId" styleClass="formdropdown">
					<h:options collection="<%=com.vh.manchester.util.Constants_Scope.ALL_PROJECTS_KEY%>" 
					           labelProperty="desc"
							   property="id"/>
	    	  	  </h:select></td>
	    	</tr>
	    	<tr>
	      	  <td class="tdlabel"><fmt:message key="com.vh.label.Assignedto"/>:<span class="requiredFieldStar">*</span>&nbsp;</td>
          	  <td><h:select property="assignedTo" styleClass="formdropdown">
					<h:options collection="<%=com.vh.manchester.util.Constants_Scope.ALL_DEVELOPERS_IN_PROJECT_KEY%>" 
					           labelProperty="username"
							   property="id"/>
	    	  	  </h:select></td>
	    	</tr>
	    	<tr style="line-height:2">
	      	  <td class="tdlabel"><fmt:message key="com.vh.label.Reportedby"/>:&nbsp;</td>
          	  <td><a href="mailto:<c:out value='${task.createdBy.email}'/>?subject=Regarding task #<c:out value='${task.id}'/>"><c:out value="${task.createdBy.username}"/></a></td>
	    	</tr>
	    	<tr>
	      	  <td class="tdlabel"><fmt:message key="com.vh.label.PlannedDate"/>:<span class="requiredFieldStar">*</span>&nbsp;</td>
          	  <td><h:text property="plannedDate" onclick="popUpCalendar(taskform.plannedDate, taskform.plannedDate, 'yyyy-mm-dd')" styleClass="formdropdown" readonly="true" style="cursor:default"/><td>
          	</tr>
	  		<tr><td height="10"/></tr>
			<tr><% /* The Solution */ %>
	  		  <td class="tdheader" colspan="2"><fmt:message key="com.vh.label.Thesolution"/></td>
	  		</tr>
	  		<tr><td height="5"/></tr>	    	
	    	<tr>
	      	  <td class="tdlabel"><fmt:message key="com.vh.label.Comment"/>:&nbsp;</td>
	      	  <td><h:textarea property="comment" rows="8" styleClass="formdropdown"/></td>
	    	</tr>
	    	<tr>
	      	  <td class="tdlabel"><fmt:message key="com.vh.label.Progress"/>:&nbsp;</td>
	      	  <td><h:select property="progress" styleClass="formdropdown">
				<c:forEach var="i" begin="0" end="100" step="5">
  				    <c:if test="${task.progress==i}">
  						<option value='<c:out value="${i}"/>' selected="true"><c:out value="${i}" /></option>
  				    </c:if>
  				    <c:if test="${task.progress!=i}">
  						<option value='<c:out value="${i}"/>'><c:out value="${i}" /></option>
  				    </c:if>
				</c:forEach>
        	  </h:select></td>
	    	</tr>
	  	  </table>	  
	    </td>	  
	  </tr>
	    	
   	  <tr>
	    <td>&nbsp;</td>
	  </tr>
	  <tr>
	    <td colspan="2" align="center">
			<% /* if Admin, it can edit task properties */ %>
	        <c:if test="${user.userType.id==USERTYPE_ADMIN}">
	    	  &nbsp;<h:submit styleClass="button" onmouseover="style.background='#CCCCCC'; style.color='#000000';" onmouseout="style.background='#003366'; style.color='#FFFFFF';"><fmt:message key="com.vh.label.Save"/></h:submit>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    	  <h:cancel styleClass="button" onmouseover="style.background='#CCCCCC'; style.color='#000000';" onmouseout="style.background='#003366'; style.color='#FFFFFF';"><fmt:message key="com.vh.label.Cancel"/></h:cancel>
			</c:if>
			
			<% /* else it can only edit task properties if this user is either the creator or assignedTo of this task */ %>
			<c:if test="${user.userType.id!=USERTYPE_ADMIN}">
      
  			  <c:if test="${user.id==task.createdBy.id || user.id==task.who.id}">
	    	    &nbsp;<h:submit styleClass="button" onmouseover="style.background='#CCCCCC'; style.color='#000000';" onmouseout="style.background='#003366'; style.color='#FFFFFF';"><fmt:message key="com.vh.label.Save"/></h:submit>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    	    <h:cancel styleClass="button" onmouseover="style.background='#CCCCCC'; style.color='#000000';" onmouseout="style.background='#003366'; style.color='#FFFFFF';"><fmt:message key="com.vh.label.Cancel"/></h:cancel>
  			  </c:if>
			</c:if>
	    </td>
	  </tr>
	  <tr>
	    <td>&nbsp;</td>
	  </tr>
	</table>
    </fieldset>

    <br/><!-- error messages -->
	<table align="left">
	  <tr valign="top">
	  	<td width="120"></td>
	  	<td><h:errors/></td>
	  </tr>
	</table>

  </h:form>
  </body>
</html>