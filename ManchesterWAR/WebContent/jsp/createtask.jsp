<!-- createtask.jsp -->
<%@ include file="/jsp/include_tld.jsp" %>
 
<html>
  <head>
    <%@ include file="/jsp/include_js_css.jsp" %>
	<link rel="stylesheet" type="text/css" href="js/popcalendar/popcalendar.css" />
	<script language="javascript" src="js/popcalendar/popcalendar.js"></script>
  </head>
  <body onload="fixURL( document.taskform )">

  <br/>
  <h:form action="/SaveCreateTask">
	<h:hidden property="projectId" value="-1" />

    <fieldset>
    <legend><fmt:message key="com.vh.label.Createtask"/></legend>
	<p class="requiredFieldExplanator"><span class="requiredFieldStar">*</span> <fmt:message key="com.vh.label.Indicatesreqfields"/></p>

	<table>
	  <tr>
	  	<td class="tdheader"><fmt:message key="com.vh.label.Thetask"/></td><td class="tdheader"><fmt:message key="com.vh.label.Theworkflow"/></td>
	  </tr>
	  <tr>
	    <td valign="top">
	  	  <!-- col 1 -->
	  	  <table border="0" cellpadding="0" cellspacing="0">
			<tr>
      	  	  <td class="tdlabel"><fmt:message key="com.vh.label.Title"/>:<span class="requiredFieldStar">*</span>&nbsp;</td>
      	  	  <td><h:text property="title" styleClass="formdropdown" value=""/></td>
	    	</tr>
	    	<tr>
	      	  <td class="tdlabel"><fmt:message key="com.vh.label.Summary"/>:<span class="requiredFieldStar">*</span>&nbsp;</td>
	      	  <td><h:textarea property="summary" rows="15" styleClass="formdropdown" value=""/></td>
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
	      	  <td class="tdlabel"><fmt:message key="com.vh.label.Assignedto"/>:<span class="requiredFieldStar">*</span>&nbsp;</td>
          	  <td><h:select property="assignedTo" styleClass="formdropdown">
					<h:options collection="<%=com.vh.manchester.util.Constants_Scope.ALL_DEVELOPERS_IN_PROJECT_KEY%>" 
					           labelProperty="username" 
							   property="id"/>
	    	  	  </h:select></td>
	    	</tr>
	    	<tr>
	      	  <td class="tdlabel"><fmt:message key="com.vh.label.PlannedDate"/>:<span class="requiredFieldStar">*</span>&nbsp;</td>
          	  <td><h:text property="plannedDate" onclick="popUpCalendar(taskform.plannedDate, taskform.plannedDate, 'yyyy-mm-dd')" styleClass="formdropdown" readonly="true" style="cursor:default"/><td>
          	</tr>
	  	  </table>	  
	    </td>	  
	  </tr>
	  
	  <tr>
	    <td>&nbsp;</td>
	  </tr>
	  <tr>
	    <td colspan="2" align="center">
	    	&nbsp;<h:submit styleClass="button" onmouseover="style.background='#CCCCCC'; style.color='#000000';" onmouseout="style.background='#003366'; style.color='#FFFFFF';"><fmt:message key="com.vh.label.Save"/></h:submit>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    	<h:cancel styleClass="button" onmouseover="style.background='#CCCCCC'; style.color='#000000';" onmouseout="style.background='#003366'; style.color='#FFFFFF';"><fmt:message key="com.vh.label.Cancel"/></h:cancel>
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