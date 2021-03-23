<%@ include file="../common/IncludeTop.jsp"%>
<script type="text/javascript">
	function refreshCode(){
		//切换验证码 1.获取验证码图片对象
		let vcode = document.getElementById("vcode");
		//2.设置src属性，加时间戳
		vcode.src="${pageContext.request.contextPath}/checkCodeServlet? time="+new Date().getTime();
	}

</script>

<style>
	#one{
		color: #6699ff;
	}
	* {
		padding: 0;
		margin: 0;
		font-family:'Open Sans Light';
		letter-spacing:.05em;
	}

	html {
		height: 100%;
	}

	body {
		height: 100%;
	}

	.container {
		height: 100%;
		background-image: linear-gradient(to right,#fbc2eb,#a6c1ee);
	}
	.Login-wrapper{
		width:350px;
		background-color:#ffffff;
		height:500px;
		border-radius:15px;
		padding:0 50px;
		position:relative;
		left:50%;
		top:70%;
		transform:translate(-50%,0%);
	}
	.Login-wrapper .header{
		font-size:30px;
		font-weight:bold;
		text-align:center;
		line-height:150px;
	}
	.input-item{
		display:block;
		width:100%;
		margin-bottom:20px;
		border:0;
		padding:10px;
		border-bottom:1px solid rgb(128,125,125);
		font-size:15px;
		outline:none;

	}
	.form-control
	{
		display:block;
		width:100%;
		margin-bottom:20px;
		border:0;
		padding:10px;
		border-bottom:1px solid rgb(128,125,125);
		font-size:15px;
		outline:none;
	}
	.Login-wrapper .form-wrapper .input-item::placeholder{
		text-transform:uppercase;
	}
	.form-control::placeholder
	{
		text-transform:uppercase;
	}
	.Login-wrapper .form-wrapper .btn {
		text-align:center;
		padding:5px;
		width:100%;
		margin-top:40px;
		background-image: linear-gradient(to right,#a6c1ee,#fbc2eb);
		color:#ffffff;
	}
	.Login-wrapper .msg{
		text-align:center;
		line-height:80px;
	}
	.Login-wrapper .msg a{
		text-decoration-line:none;
		color:#a6c1ee;
	}
	#vcode{
		position: absolute;
		top: 55%;
		left: 60%;
		width: 25%;
		height: 8%;
	}
</style>
<div id="Catalog">
	<%
		String error=(String)request.getAttribute("error1");
		if(error!=null){
			out.print(error);
		}
		error=(String)request.getAttribute("error2");
		if(error!=null){
			out.print(error);
		}
		error=(String)request.getAttribute("error3");
		if(error!=null){
			out.print(error);
		}
	%>

	<form action="SignonAction" method="post">

		<div class="container">
		<div class="Login-wrapper">
			<div class="header"> Login</div>
		<div class="form-wrapper">
			<input type="text" name="username" placeholder="username" class="input-item" />
			<input type="password" name="password" placeholder="password" class="input-item" />
			<div class="form-inline">
				<input type="text" name="verifycode" class="form-control" id="verifycode" placeholder="ChechCode" style="width: 120px;"/>
				<a href="javascript:refreshCode();">
					<img src="${pageContext.request.contextPath}/checkCodeServlet" title="Can't see clearly, click refresh" id="vcode"/>
				</a>
			</div>
			<input type="submit" name="signon" value="Login" class = "btn" />
		</div>


			<div class="msg">
				Don't have account? <a href="NewAccountForm">Sign up</a>
			</div>

		</div>


		</div>
	</form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>