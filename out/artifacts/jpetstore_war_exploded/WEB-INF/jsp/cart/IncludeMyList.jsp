<c:if test="${!empty sesssionScope.myList}">
	<p>Pet Favorites <br />
	Shop for more of your favorite pets here.</p>
	<ul>
		<c:forEach var="product" items="${accountBean.myList}">
		<li>
		<a href="#" name="productId" value="${product.productId}">${product.name}</a> 
		(${product.productId})
		</li>
		</c:forEach>
	</ul>
</c:if>