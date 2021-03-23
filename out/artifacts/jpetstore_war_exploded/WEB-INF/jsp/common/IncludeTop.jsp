<%--<%@ page contentType="text/html;charset=UTF-8" language="java"%>--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<link rel="StyleSheet" href="css/jpetstore.css" type="text/css" media="screen" />
	<link rel="StyleSheet" href="css/searchProduct.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="css/mouseEventInform.css" type="text/css" media="screen" />
	<style type="text/css">
		#birds{
			position: absolute;
			top: 20px;
			width: 100px;
			max-height: 250px;           /* 设置最大高度，当高度达到此值时出现滚动条 */
			z-index: 10;
			background-color: #E0E5E5;
			overflow: auto;              /* 自动添加滚动条 */
			box-shadow:0px 0px 10px #000;   /* 外阴影 */
			display: none;   /* 默认隐藏 */
		}

		#inform{
			position: fixed;
			left:900px;
			width: 350px;
			max-height: 350px;           /* 设置最大高度，当高度达到此值时出现滚动条 */
			z-index: 10;
			background-color: #E0E5E5;
			word-wrap: break-word;
			box-shadow:0px 0px 10px #000;   /* 外阴影 */
			display: none;   /* 默认隐藏 */
		}
		#inform hr{
			border:1px;
			width: 325px;
			margin-bottom: 0px;
		}
	</style>
	<meta name="generator" content="HTML Tidy for Linux/x86 (vers 1st November 2002), see www.w3.org" />
	<title>MyPetStore</title>
	<meta content="text/html; charset=windows-1252" http-equiv="Content-Type" />
	<meta http-equiv="Cache-Control" content="max-age=0" />
	<meta http-equiv="Cache-Control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
	<meta http-equiv="Pragma" content="no-cache" />

	<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
</head>

<body>

<div id="Header">

	<div id="Logo">
		<div id="LogoContent">
			<a href="main"><img src="images/logo-topbar.gif" /></a>
		</div>
	</div>

	<div id="Menu">
		<div id="MenuContent">
			<a href="CartIcon"><img align="middle" name="img_cart" src="images/cart.gif" /></a>
			<img align="middle" src="images/separator.gif" />

			<c:if test="${sessionScope.accountBean == null}">
				<a href="SignonForm"> Sign In </a>
			</c:if>

			<c:if test="${sessionScope.accountBean != null}">
				<c:if test="${!sessionScope.authenticated}">
				    <a href="SignonForm"> Sign In </a>
				</c:if>
			</c:if>

			<c:if test="${sessionScope.accountBean != null}">
				<c:if test="${sessionScope.authenticated}">
				    <a href="SignoutAction"> Sign Out </a>
					<img align="middle" src="images/separator.gif" />
				    <a href="EditAccountForm"> My Account </a>
					<img align="middle" src="images/separator.gif" />
					<a href="Recode"> Recode  </a>

				</c:if>
			</c:if>

			<img align="middle" src="images/separator.gif" />
			<a href="help.html">?</a>
		</div>
	</div>

	<div id="Search">
		<div id="SearchContent">
			<form action="SearchProduct" method="post">
				<input type="text" id="keyword" name="keyword" size="14"  />
				<div class="auto hidden" id="auto">
					<div class="auto_out">1</div>
					<div class="auto_out">2</div>
				</div>
				<input type="submit" name="searchProducts" value="Search"/>
				<script src="js/searchProduct.js"></script>
			</form>
		</div>
	</div>

	<div id="QuickLinks">
		<a href="CategoryView?categoryId=FISH"><img src="images/sm_fish.gif" /></a>
		<img src="images/separator.gif" />

		<a href="CategoryView?categoryId=DOGS"><img src="images/sm_dogs.gif" /></a>
		<img src="images/separator.gif" />

		<a href="CategoryView?categoryId=REPTILES"><img src="images/sm_reptiles.gif" /></a>
		<img src="images/separator.gif" />

		<a href="CategoryView?categoryId=CATS"><img src="images/sm_cats.gif" /></a>
		<img src="images/separator.gif" />

		<a href="CategoryView?categoryId=BIRDS"><img src="images/sm_birds.gif" /></a>
	</div>

</div>

<div id="Content"></div>