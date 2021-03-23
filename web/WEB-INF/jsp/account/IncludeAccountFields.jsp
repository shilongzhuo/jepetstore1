<h3>Account Information</h3>
<table>
	<tr>
	<td>First name:</td>
		<td><input type="text" name="account.firstName"  value="${sessionScope.accountBean.firstName}"/></td>
	</tr>
	<tr>
		<td>Last name:</td>
		<td><input type="text"  name="account.lastName" value="${sessionScope.accountBean.lastName}" /></td>
	</tr>
	<tr>
		<td>Email:</td>
		<td><input type="text"  size="40" name="account.email" value="${sessionScope.accountBean.email}"/></td>
	</tr>
	<tr>
		<td>Phone:</td>
		<td><input type="text"  name="account.phone" value="${sessionScope.accountBean.phone}"/></td>
	</tr></td>
	</tr>
	<tr>
		<td>Address 1:</td>
		<td><input type="text"  size="40" name="account.address1" value="${sessionScope.accountBean.address1}"/></td>
	</tr></td>
	</tr>
	<tr>
		<td>Address 2:</td>
		<td><input type="text"  size="40" name="account.address2" value="${sessionScope.accountBean.address2}"/></td>
	</tr></td>
	</tr>
	<tr>
		<td>City:</td>
		<td><input type="text"  name="account.city" value="${sessionScope.accountBean.city}"/></td>
	</tr></td>
	</tr>
	<tr>
		<td>State:</td>
		<td><input type="text"  size="4" name="account.state" value="${sessionScope.accountBean.state}"/></td>
	</tr></td>
	</tr>
	<tr>
		<td>Zip:</td>
		<td><input type="text"  size="10" name="account.zip" value="${sessionScope.accountBean.zip}"/></td>
	</tr></td>
	</tr>
	<tr>
		<td>Country:</td>
		<td><input type="text"  size="15" name="account.country" value="${sessionScope.accountBean.country}"/></td>
	</tr></td>
	</tr>
</table>

<h3>Profile Information</h3>

<table>
	<tr>
		<td>Language Preference:</td>
		<td>
			<select name="account.languagePreference">
				<option>english</option>
				<option>japanese</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>Favourite Category:</td>
		<td>
			<select name="account.favouriteCategoryId">
				<option>FISH</option>
				<option>DOGS</option>
				<option>REPTILES</option>
				<option>CATS</option>
				<option>BIRDS</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>Enable MyList</td>
		<td><input type="checkbox" name="account.listOption" checked="true"/></td>
	</tr>
	<tr>
		<td>Enable MyBanner</td>
		<td><input type="checkbox" name="account.bannerOption" checked="true"/></td>
	</tr>

</table>
