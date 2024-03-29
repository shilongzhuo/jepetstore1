<%@ include file="/WEB-INF/jsp/common/IncludeTop.jsp"%>
<div id="Welcome">
	<div id="WelcomeContent">
		<c:if test="${sessionScope.accountBean != null }">
			<c:if test="${sessionScope.authenticated}">Welcome ${sessionScope.accountBean.firstName}!
	    	</c:if>
		</c:if>
	</div>
</div>

<div id="Main">
	<div id="Sidebar">
		<div id="SidebarContent">
		<a href="CategoryView?categoryId=FISH"><img src="images/fish_icon.gif" /></a><br />
		Saltwater, Freshwater <br />

		<a href="CategoryView?categoryId=DOGS"><img src="images/dogs_icon.gif" /></a> <br />
		Various Breeds <br />

		<a href="CategoryView?categoryId=CATS"><img src="images/cats_icon.gif" /></a><br />
		Various Breeds, Exotic Varieties <br />
	
		<a href="CategoryView?categoryId=REPTILES"><img src="images/reptiles_icon.gif" /></a><br />
		Lizards, Turtles, Snakes <br />
		
		<a href="CategoryView?categoryId=BIRDS"><img src="images/birds_icon.gif" /></a><br />
		Exotic Varieties
		</div>
	</div>

	<div id="inform" style="display: none">sssssssssss</div>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/mouseEvent.js"></script>
	<div id="MainImage">

		<div id="MainImageContent">
		  <map name="estoremap">
			  <area alt="BIRDS" coords="72,2,280,250" href="CategoryView?categoryId=BIRDS" shape="rect"
					onmouseover="showInform(alt);" onmouseout="hiddenInform(event)"/>
			  <area alt="FISH" coords="2,180,72,250" href="CategoryView?categoryId=FISH" shape="rect"
					onmouseover="showInform(alt);" onmouseout="hiddenInform(event)"/>
			  <area alt="DOGS" coords="60,250,130,320" href="CategoryView?categoryId=DOGS" shape="rect"
					onmouseover="showInform(alt);" onmouseout="hiddenInform(event)"/>
			  <area alt="REPTILES" coords="140,270,210,340" href="CategoryView?categoryId=REPTILES" shape="rect"
					onmouseover="showInform(alt);" onmouseout="hiddenInform(event)"/>
			  <area alt="CATS" coords="225,240,295,310" href="CategoryView?categoryId=CATS" shape="rect"
					onmouseover="showInform(alt);" onmouseout="hiddenInform(event)"/>
			  <area alt="BIRDS" coords="280,180,350,250" href="CategoryView?categoryId=BIRDS" shape="rect"
					onmouseover="showInform(alt);" onmouseout="hiddenInform(event)"/>

			  <%--<area alt="Birds" coords="72,2,280,250" href="CategoryView?categoryId=BIRDS" shape="RECT" />
			  <area alt="Fish" coords="2,180,72,250" href="CategoryView?categoryId=FISH" shape="RECT" />
			  <area alt="Dogs" coords="60,250,130,320" href="CategoryView?categoryId=DOGS" shape="RECT" />
			  <area alt="Reptiles" coords="140,270,210,340" href="CategoryView?categoryId=REPTILES" shape="RECT" />
			  <area alt="Cats" coords="225,240,295,310" href="CategoryView?categoryId=CATS" shape="RECT" />
			  <area alt="Birds" coords="280,180,350,250" href="CategoryView?categoryId=BIRDS" shape="RECT" />--%>
		  </map> 
		<img height="355" src="images/splash.gif" align="middle" usemap="#estoremap" width="350" />
		</div>
	</div>

	<div id="Separator">&nbsp;</div>
</div>

<%@ include file="/WEB-INF/jsp/common/IncludeBottom.jsp"%>

