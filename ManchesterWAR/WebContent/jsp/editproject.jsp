<!-- editproject.jsp -->
<%@ include file="/jsp/include_tld.jsp" %>
 
<html>
  <head>
    <%@ include file="/jsp/include_js_css.jsp" %>
  </head>
  <body onload="initPage( document.projectform, true );">
  
  <% /* START: setup vars from request, session */ %>
  <c:set var="PROJECT_BEING_UPDATED_KEY"><%=com.vh.manchester.util.Constants_Scope.PROJECT_BEING_UPDATED_KEY%></c:set>
  <c:set var="proj" value="${requestScope[PROJECT_BEING_UPDATED_KEY]}" />
  <c:set var="ALL_USERS_KEY"><%=com.vh.manchester.util.Constants_Scope.ALL_USERS_KEY%></c:set>
  <c:set var="allUserColl" value="${applicationScope[ALL_USERS_KEY]}" />
  <c:set var="ALL_ACCESSES_KEY"><%=com.vh.manchester.util.Constants_Scope.ALL_ACCESSES_KEY%></c:set>
  <c:set var="allAccessColl" value="${applicationScope[ALL_ACCESSES_KEY]}" />
  <c:set var="PROJECTACCESSCOLL_KEY"><%=com.vh.manchester.util.Constants_Scope.PROJECTACCESSCOLL_KEY%></c:set>
  <c:set var="projAccessColl" value="${requestScope[PROJECTACCESSCOLL_KEY]}" />
  <% /* END: setup vars from request, session */ %>

  <br/>
  <h:form action="/SaveProject">
  	<h:hidden property="project"/>
	
    <fieldset>
    <legend><fmt:message key="com.vh.label.Editproject"/>: #<c:out value="${proj.id}"/></legend>
	<p class="requiredFieldExplanator"><span class="requiredFieldStar">*</span> <fmt:message key="com.vh.label.Indicatesreqfields"/></p>
	<br/>

	  <table border="0" cellpadding="0" cellspacing="0">
		<tr>
      	  <td class="tdlabel"><fmt:message key="com.vh.label.Projectname"/>:<span class="requiredFieldStar">*</span>&nbsp;</td>
      	  <td><h:text property="desc" styleClass="formdropdown"/></td>
	    </tr>
	  </table>
    
	  <table border="0" cellpadding="0" cellspacing="0" class="tableforcreateproject">
  		<thead>  	
    	  <tr>
      		<th class="thfortasktableview" width="1%"><input type="checkbox" name="toggleAll" title='<fmt:message key="com.vh.label.Selectall"/>' onclick="ToggleAll();"></th>
      		<th class="thfortasktableview" width="69%"><fmt:message key="com.vh.label.Users"/> &nbsp;&nbsp;</th>
      		<th class="thfortasktableview" width="30%"><fmt:message key="com.vh.label.Access"/>&nbsp;&nbsp;</th>
    	  </tr>
		</thead>
		<tbody id="theRows">
      	  <c:forEach var="abean" items="${projAccessColl}" varStatus="rowCounter">
        	<tr>
    	  	  <td class="tdfortasktableview" width="10">
    	  	    <c:choose> 
  				  <c:when test="${abean.hasAccess}">
  				    <input type="checkbox" name="userId" value='<c:out value="${abean.userId}"/>' checked="true" onclick="Toggle(this, <c:out value='${rowCounter.count % 2}'/>); changeDropDown(this);"></input>
				  </c:when> 
				  <c:otherwise> 
    	  	  	    <input type="checkbox" name="userId" value='<c:out value="${abean.userId}"/>' onclick="Toggle(this, <c:out value='${rowCounter.count % 2}'/>); changeDropDown(this);"></input>
  				  </c:otherwise> 
			    </c:choose>     	  	  
    	  	  </td>
      	  	  <td class="tdfortasktableview">&nbsp;<a href='mailto:<c:out value="${abean.email}"/>'><c:out value="${abean.userName}"/></a>&nbsp;</td>
      	  	  <td class="tdfortasktableview" align="center">
      	  		<h:select property="access" styleClass="dropdownclass" onchange="changeCheckbox(this);">
    	  	      <c:choose> 
  				    <c:when test="${abean.accessId==1}">
					  <option value="-1"        ></option>
					  <option value="1" selected="true"><fmt:message key="com.vh.label.Read"/></option>
					  <option value="2"         ><fmt:message key="com.vh.label.Write"/></option>
				    </c:when> 
  				    <c:when test="${abean.accessId==2}">
					  <option value="-1"        ></option>
					  <option value="1"         ><fmt:message key="com.vh.label.Read"/></option>
					  <option value="2" selected="true"><fmt:message key="com.vh.label.Write"/></option>
				    </c:when> 
				    <c:otherwise> <!-- default option -->
					  <option value="-1" selected="true"></option>
					  <option value="1"          ><fmt:message key="com.vh.label.Read"/></option>
					  <option value="2"          ><fmt:message key="com.vh.label.Write"/></option>
  				    </c:otherwise> 					
				  </c:choose>	
				</h:select>
		  	  </td>
        	</tr>
	  	  </c:forEach>
   		</tbody>
  	  </table>
	  
	  <table border="0" cellpadding="0" cellspacing="0">
	    <tr>
	      <td class="tdlabel"></td>
	      <td>
	    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<h:submit styleClass="button" onmouseover="style.background='#CCCCCC'; style.color='#000000';" onmouseout="style.background='#003366'; style.color='#FFFFFF';"><fmt:message key="com.vh.label.Save"/></h:submit>
	    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<h:cancel styleClass="button" onmouseover="style.background='#CCCCCC'; style.color='#000000';" onmouseout="style.background='#003366'; style.color='#FFFFFF';"><fmt:message key="com.vh.label.Cancel"/></h:cancel>
	      </td>
	    </tr>
	    <br/>
	    <tr>
	      <td>&nbsp;</td>
	    </tr>
	  </table>
	</fieldset>

	<br/><!-- error messages -->
	<table align="left">
	  <tr valign="top">
	  	<td width="210"></td>
	  	<td><h:errors/></td>
	  </tr>
	</table>

  </h:form>    
  </body>
</html>