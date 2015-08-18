<%@ include file="/jsp/include_tld.jsp" %>

<% /* Holds the value of the current context */ %>
var CONTEXT_PREFIX = '';
var IS_MOZILLA  = !(document.all);

var LABEL_DELTASKS1    = '<fmt:message key="errors.deltasksnoneselected"/>';
var LABEL_DELTASKS2    = '<fmt:message key="errors.deltasksconfirm"/>';
var LABEL_DELTASKS3    = '<fmt:message key="errors.deltasksusage"/>';
var LABEL_DELUSERS1    = '<fmt:message key="errors.delusersnoneselected"/>';
var LABEL_DELUSERS2    = '<fmt:message key="errors.delusersconfirm"/>';
var LABEL_DELUSERS3    = '<fmt:message key="errors.delusersusage"/>';
var LABEL_DELPROJECTS1 = '<fmt:message key="errors.delprojectsnoneselected"/>';
var LABEL_DELPROJECTS2 = '<fmt:message key="errors.delprojectsconfirm"/>';
var LABEL_DELPROJECTS3 = '<fmt:message key="errors.delprojectsusage"/>';