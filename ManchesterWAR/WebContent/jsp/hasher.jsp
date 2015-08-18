<!-- hasher.jsp -->
<%@ include file="/jsp/include_tld.jsp" %>

<html>
  <head>
    <script language="javascript" src="../js/js_constants.jsp"></script>
    <script language="javascript" src="../js/utils.js"></script>
    <script language="javascript" src="../js/main.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/main.css"/>
  </head>  
  <body>
  <% /* START: setup vars from request */ %>  
  <c:set var="CLEAR_VALUE"><%=request.getParameter("uv")%></c:set>
  <c:choose>
    <c:when test="${CLEAR_VALUE=='null'}">
      <c:set var="HASHED_VALUE"></c:set>
	</c:when>
    <c:otherwise>
      <c:set var="HASHED_VALUE"><%=org.apache.commons.codec.digest.DigestUtils.md5Hex(request.getParameter("uv"))%></c:set>
	</c:otherwise>
  </c:choose>
  <% /* END: setup vars from request */ %>

  <br/><br/>
  <center><h3><fmt:message key="com.vh.label.Hasher"/></h3></center>
  <br/>

  <form action="hasher.jsp">
  	<table border="0" cellpadding="0" cellspacing="0" class="tablefortasktableview" width="100%">
    	<tr height="25">
		  <td class="thfortasktableview" width="20%" align="right"><fmt:message key="com.vh.label.Unhashed_value"/>:&nbsp;&nbsp;</td>
      	  <td class="thfortasktableview" width="80%">&nbsp;
		    <c:if test="${CLEAR_VALUE=='null'}">
			  <input type="text" name="uv" value="" size="50">
			</c:if>
		    <c:if test="${CLEAR_VALUE!='null'}">
			  <input type="text" name="uv" value="<c:out value='${CLEAR_VALUE}'/>" size="50">
			</c:if>
		  </td>
        </tr>
		<tr height="25">
		  <td class="thfortasktableview" width="20%" align="right"><fmt:message key="com.vh.label.Hashed_value"/>:&nbsp;&nbsp;</td>
      	  <td class="thfortasktableview" width="80%">&nbsp;&nbsp;<input type="text" name="hv" value="<c:out value='${HASHED_VALUE}'/>" size="50" readonly></td>
        </tr>
		<tr height="25">
		  <td class="thfortasktableview" width="20%">&nbsp;</td>
		  <td class="thfortasktableview">&nbsp;&nbsp;<input type="submit" name="submit" class="button" onMouseOver="style.background='#CCCCCC'; style.color='#000000';" onMouseOut="style.background='#003366'; style.color='#FFFFFF';" value="<fmt:message key='com.vh.label.Submit'/>"></td>
    	</tr>
    </table>
  </form>
  </body>
</html>