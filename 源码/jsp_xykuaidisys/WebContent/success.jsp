<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>

{
	"statusCode":"200",
	"message":"${ActionMessage}",
	"confirmMsg":"${confirmMsg}",
	"navTabId":"${navTabId}",
	"rel":"dlg_page1",
	"callbackType":"${callbackType}",<c:if test="${not empty ForwardUrlKey}" var="urltest">
	"forwardUrl":"<%=path %>/${ForwardUrlKey}"</c:if><c:if test="${!urltest}">
	"forwardUrl":""</c:if>
	
}
