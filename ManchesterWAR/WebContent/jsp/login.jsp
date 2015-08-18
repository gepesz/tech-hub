<!-- login.jsp -->
<%@ include file="/jsp/include_tld.jsp" %>

<html>
<head>
  <title><fmt:message key="com.vh.title.jsp.login"/></title>
  <link rel="shortcut icon" type="image/gif" href="img/favicon.ico"/>
  <link rel="stylesheet" type="text/css" href="css/login.css">
  <script language="javascript" src="js/outside.js"></script>
</head>
<body onload="focusOnUserName();">

  <l:messagesNotPresent><c:set var="id" value="box" /></l:messagesNotPresent>
  <l:messagesPresent><c:set    var="id" value="boxLong" /></l:messagesPresent>
    
  <div id='<c:out value="${id}"/>'>
    <form name="loginform" method="post" action="Login.do">
    <p class="title"><fmt:message key="com.vh.title.jsp.login"/></p>
    <p class="text"><fmt:message key='com.vh.label.Username'/>:&nbsp;&nbsp;<input type="text" name="username"></p>
    <p class="text">&nbsp;<fmt:message key='com.vh.label.Password'/>:&nbsp;&nbsp;<input type="password" name="password"></td>
    <p class="textbutton"><input type="submit" name="submit" class="button" onMouseOver="this.className='buttonover'" onMouseOut="this.className='buttonout'" value="GO >>"></td>
    </form>
    <div style="margin-left:30px;"><h:errors/></div>
  </div>

  <span class="footer">
    <fmt:message key="com.vh.title.jsp.login"/><br/>
	<fmt:message key="com.vh.copyright"/>
  </span>
</body>
</html>