<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>

<h2 class="contentTitle">发送好友申请</h2>
<div class="pageContent">
	<form action="<%=path %>/Control?act=applyAdd" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone)">
		<div class="pageFormContent" layoutH="97">
			<table>
					<tr>
						<td colspan="2"><dl>
							<dt>好友电话：</dt>
							<dd><input name="phone" maxlength="11" type="text" class="phone required" /></dd>
							</dl></td>
					</tr>
					 
					<tr>
						<td colspan="2"><dl class="nowrap">
							<dt>说点啥：</dt>
							<dd><input name="content" size="80" type="text" class="required" /></dd>
							</dl></td>
					</tr>
				</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">发送好友申请</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="reset" class="reset">重置</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>
