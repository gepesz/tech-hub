<!-- createuser.jsp -->
<%@ include file="/jsp/include_tld.jsp" %>
 
<html>
  <head>
    <%@ include file="/jsp/include_js_css.jsp" %>
  </head>
  <body onload="fixURL( document.userform )">

  <% /* START: setup vars from request, session */ %>
  <c:set var="ALL_LOCALES_KEY"><%=com.vh.manchester.util.Constants_Scope.ALL_LOCALES_KEY%></c:set>
  <c:set var="allLocaleColl" value="${applicationScope[ALL_LOCALES_KEY]}" />
  <c:set var="ALL_USERTYPES_KEY"><%=com.vh.manchester.util.Constants_Scope.ALL_USERTYPES_KEY%></c:set>
  <c:set var="allUserTypeColl" value="${applicationScope[ALL_USERTYPES_KEY]}" />
  <% /* END: setup vars from request, session */ %>
  
  <br/>
  <h:form action="/SaveCreateUser">
    <fieldset>
    <legend><fmt:message key="com.vh.label.Createuser"/></legend>
	<p class="requiredFieldExplanator"><span class="requiredFieldStar">*</span> <fmt:message key="com.vh.label.Indicatesreqfields"/></p>
	<br/>

	  <table border="0" cellpadding="0" cellspacing="0">
	    <tr>
	      <td class="tdlabel"><fmt:message key="com.vh.label.Username"/>:<span class="requiredFieldStar">*</span>&nbsp;</td>
	      <td><h:text property="username" styleClass="formdropdown"/></td>
	    </tr>
	    <tr>
	      <td class="tdlabel"><fmt:message key="com.vh.label.Password"/>:<span class="requiredFieldStar">*</span>&nbsp;</td>
	      <td><h:password property="password" styleClass="formdropdown"/></td>
	    </tr>
	    <tr>
	      <td class="tdlabel"><fmt:message key="com.vh.label.Email"/>:<span class="requiredFieldStar">*</span>&nbsp;</td>
	      <td><h:text property="email" styleClass="formdropdown"/></td>
	    </tr>
	    <tr>
	      <td class="tdlabel"><fmt:message key="com.vh.label.Type"/>:<span class="requiredFieldStar">*</span>&nbsp;</td>
          <td><h:select property="type" styleClass="formdropdown">
					<h:options collection="allUserTypeColl" labelProperty="desc" property="id"/>				
	    	  </h:select></td>	  
	    </tr>
	    <tr>
	      <td class="tdlabel"><fmt:message key="com.vh.label.Language"/>:<span class="requiredFieldStar">*</span>&nbsp;</td>
          <td><h:select property="locale" styleClass="formdropdown">
				<h:options collection="allLocaleColl" labelProperty="desc" property="id"/>				
	    	  </h:select></td>	  
	    </tr>
	    <tr>
	      <td>&nbsp;</td>
	    </tr>
	    <tr>
	      <td></td>
	      <td>
	    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<h:submit styleClass="button" onmouseover="style.background='#CCCCCC'; style.color='#000000';" onmouseout="style.background='#003366'; style.color='#FFFFFF';"><fmt:message key="com.vh.label.Save"/></h:submit>
	    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<h:cancel styleClass="button" onmouseover="style.background='#CCCCCC'; style.color='#000000';" onmouseout="style.background='#003366'; style.color='#FFFFFF';"><fmt:message key="com.vh.label.Cancel"/></h:cancel>
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
	  	<td width="210"></td>
	  	<td><h:errors/></td>
	  </tr>
	</table>

  </h:form>
  </body>
</html>