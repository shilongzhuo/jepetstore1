<%@ include file="../common/IncludeTop.jsp"%>
<script type="text/javascript">
	function refreshCode(){
		//切换验证码 1.获取验证码图片对象
		let vcode = document.getElementById("vcode");
		//2.设置src属性，加时间戳
		vcode.src="${pageContext.request.contextPath}/checkCodeServlet? time="+new Date().getTime();
	}

</script>

<div id="Catalog">

	<%
		String error=(String)request.getAttribute("error4");
		if(error!=null){
			out.print(error);
		}
		error=(String)request.getAttribute("error5");
		if(error!=null){
			out.print(error);
		}
	%>

	<form action="NewAccountActionServlet" method="post">
			<h3>User Information</h3>
			<table>
				<tr>
					<td>User ID:</td>
					<td>
						<input type="text" name="username" id="username"  onblur="usernameIsExist();"/>
						<div id="usernameMsg"></div>
						<script type="text/javascript" src="${pageContext.request.contextPath }/js/register.js"></script>
					</td>
				</tr>
				<tr>
					<td>New password:</td>
					<td><input type="text" name="password" /></td>
				</tr>
				<tr>
					<td>Repeat password:</td>
					<td><input type="text" name="repeatedPassword" /></td>
				</tr>
			</table>

			<%@ include file="IncludeAccountFields.jsp"%>

		<div class="form-inline">
			<label for="vcode">ChechCode: </label>
			<input type="text" name="verifycode" class="form-control" id="verifycode" placeholder="Please enter the verification code" style="width: 120px;"/>
			<a href="javascript:refreshCode();">
				<img src="${pageContext.request.contextPath}/checkCodeServlet" title="Can't see clearly, click refresh" id="vcode"/>
			</a>
		</div>

			<input type="submit" name="newAccount" value="Save Account Information" />
	</form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>