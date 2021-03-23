<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
<form action="#" method="post">
	<table>
		<tr>
			<th colspan=2>Shipping Address</th>
		</tr>

		<tr>
			<td>First name:</td>
			<td><input type="text" name="order.shipToFirstName" value="${order.shipToFirstName}"/></td>
		</tr>
		<tr>
			<td>Last name:</td>
			<td><input type="text" name="order.shipToLastName" value="${order.shipToLastName}"/></td>
		</tr>
		<tr>
			<td>Address 1:</td>
			<td><input type="text" size="40" name="order.shipAddress1" value="${order.shipAddress1}"/></td>
		</tr>
		<tr>
			<td>Address 2:</td>
			<td><input type="text" size="40" name="order.shipAddress2" value="${order.shipAddress2}"/></td>
		</tr>
		<tr>
			<td>City:</td>
			<td><input type="text" name="order.shipCity" value="${order.shipCity}"/></td>
		</tr>
		<tr>
			<td>State:</td>
			<td><input type="text" size="4" name="order.shipState" value="${order.shipState}"/></td>
		</tr>
		<tr>
			<td>Zip:</td>
			<td><input type="text" size="10" name="order.shipZip" value="${order.shipZip}"/></td>
		</tr>
		<tr>
			<td>Country:</td>
			<td><input type="text" size="15" name="order.shipCountry" value="${order.shipCountry}"/></td>
		</tr>


	</table>

	<input type="submit" name="newOrder" value="Continue" />

</form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>