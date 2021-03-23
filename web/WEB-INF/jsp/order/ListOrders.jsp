<%@ include file="../common/IncludeTop.jsp"%>

<h2>My Orders</h2>

<table>
	<tr>
		<th>Order ID</th>
		<th>Date</th>
		<th>Total Price</th>
	</tr>

	<c:forEach var="order" items="${sessionScope.orderList}">
		<tr>
			<td>
			  <a href="viewOrder?orderId=${order.orderId}" name="orderId" value="${order.orderId}">${order.orderId}</a>
		  	</td>
			<td>${order.orderDate }</td>
			<td>${order.totalPrice}</td>
		</tr>
	</c:forEach>
</table>
<style>
	div{
		text-align: center;
	}
	body{
		font-family: Arial,Verdana,sans-serif;
		color: #111111;
		text-align: left;
	}
	table{
		width:600px;
		/*margin: auto;*/
	}
	th,td{
		padding:7px 10px 10px 10px;
		text-align: center;
	}
	th{
		text-transform: uppercase;
		letter-spacing: 0.1em;
		font-size: 90%;
		border-bottom: 2px solid #111111;
		border-top: 1px solid #999;
		text-align: center;
	}
	tr.even
	{
		background-color: #efefef;
	}
	tr:hover{
		background-color: #c3e6e5;
	}
</style>
<%@ include file="../common/IncludeBottom.jsp"%>


