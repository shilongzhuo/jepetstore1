<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
	<a href="ProductView?productId=${sessionScope.product.productId}">
	Return to ${sessionScope.product.productId}</a>
</div>

<div id="Catalog">

	<table>
		<tr>
			<td>${sessionScope.product.description}</td>
		</tr>
		<tr>
			<td><b> ${sessionScope.item.itemId} </b></td>
		</tr>
		<tr>
			<td><b><font size="4"> 
			${sessionScope.item.attribute1}
			${sessionScope.item.attribute2} 
			${sessionScope.item.attribute3}
			${sessionScope.item.attribute4} 
			${sessionScope.item.attribute5}
			${sessionScope.product.name} 
			</font></b></td>
		</tr>
		<tr>
			<td>${sessionScope.product.name}</td>
		</tr>
		<tr>
			<td><c:if test="${sessionScope.item.quantity <= 0}">
	        Back ordered.
	      </c:if> <c:if test="${sessionScope.item.quantity > 0}">
	      	${sessionScope.item.quantity} in stock.
		  </c:if></td>
		</tr>
		<tr>
			<td>${sessionScope.item.listPrice}</td>
		</tr>

		<tr>
			<td><a class="Button" href="CartView?workingItemId=${sessionScope.item.itemId}">Add to Cart</a></td>
		</tr>
	</table>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>



