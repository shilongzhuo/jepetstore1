<%@ include file="../common/IncludeTop.jsp"%>
<%@   page   import= "org.csu.myjpetstore.domain.Account "%>
<%@ page import="org.csu.myjpetstore.domain.Cart" %>
<%@ page import="org.csu.myjpetstore.service.OrderService" %>
<%@ page import="org.csu.myjpetstore.domain.Order" %>

<div id="BackLink">
	<a href="main">Return to Main Menu</a>
</div>
<script>
	function updateCart(Id)
	{
		var x = document.getElementById(Id).value;
		sendRequest("UpdateCart?quantity="+ x +"&ID="+Id);
		location.reload();
		// var num = new Array(x.length);
		// var len = parseInt(x.length);
		// for(var i = 0;i < x.length;i++)
		// {
		// 	num[i] = parseInt(x[i].value);
		// }
		// location.href = "UpdateCart?num = 11";
	}
</script>

<style>
    #first{
        display: none;
        position: absolute;
        top: 10%;
        left: 25%;
        width: 30%;
        height: 70%;
        padding: 20px;
        border: 30px solid rgba(241, 161, 10, 0.2);
        background-color: white;
        z-index:1002;
        overflow: auto;

    }
    #second
    {
        display: none;
        position: absolute;
        top: 10%;
        left: 25%;
        width: 30%;
        height: 70%;
        padding: 20px;
        border: 30px solid rgba(241, 161, 10, 0.2);
        background-color: white;
        z-index:1002;
        overflow: auto;
    }
    #third
    {
        display: none;
        position: absolute;
        top: 30%;
        left: 30%;
        width: 15%;
        height: 20%;
        padding: 20px;
        border: 30px solid rgba(241, 161, 10, 0.2);
        background-color: white;
        z-index:1002;
        overflow: auto;
    }
    .black_overlay{
        display: none;
        position: absolute;
        top: 0%;
        left: 0%;
        width: 100%;
        height: 100%;
        background-color: black;
        z-index:1001;
        -moz-opacity: 0.8;
        opacity:.80;
        filter: alpha(opacity=88);
    }
    .information{
        width:80%;
    }
    #tijiaoxinxi{
        position: fixed;
        top: 550px;
        left:550px;
    }
    #quxiao{
        position: fixed;
        top: 550px;
        left:700px;
    }
    /* #table1{
        position: fixed;
        left:500px;
    } */
    .button1
    {
        width:25%;
        text-align: center;
    }
    #Su{
        position: fixed;
        top: 380px;
        left:520px;
    }
    #Ca{
        position: fixed;
        top: 380px;
        left:640px;
    }
    .tishi{
        color:rgb(58, 10, 235);
        text-align: left;
    }
    #password{
        width:85%;
    }
</style>
	<div id="Cart">

		<%
			session.setAttribute("cart", Account.cart1);
		%>
		<h2>Shopping Cart</h2>

		<form action="UpdateCart" method="post">
			<table>
				<tr>
					<th><b>Item ID</b></th>
					<th><b>Product ID</b></th>
					<th><b>Description</b></th>
					<th><b>In Stock?</b></th>
					<th><b>Quantity</b></th>
					<th><b>List Price</b></th>
					<th><b>Total Cost</b></th>
					<th>&nbsp;</th>
				</tr>

				<c:if test="${Account.cart1.numberOfItems == 0}">
					<tr>
						<td colspan="8"><b>Your cart is empty.</b></td>
					</tr>
				</c:if>

				<c:forEach var="cartItem" items="${sessionScope.cart.cartItems}">
					<tr>
						<td>
							<a href="ItemView?itemId=${cartItem.item.itemId}">${cartItem.item.itemId}</a>
						</td>
						<td>${cartItem.item.productId}</td>
						<td>${cartItem.item.attribute1} ${cartItem.item.attribute2}
								${cartItem.item.attribute3} ${cartItem.item.attribute4}
								${cartItem.item.attribute5} ${cartItem.item.product.name}</td>
						<td>${cartItem.inStock}</td>
						<td>
							<input type="text" size="3" id = "${cartItem.item.itemId}" onblur="updateCart(this.id);" name="${cartItem.item.itemId}"
								   value="${cartItem.quantity}" class = "num" />
						</td>
						<td>${cartItem.item.listPrice}</td>
						<td>${cartItem.total}</td>
						<td>
							<a class="Button" href="RemoveAction?cartItem=${cartItem.item.itemId}">Remove</a>
						</td>
					</tr>
				</c:forEach>
<%--				<tr>--%>
<%--					<td colspan="7">Sub Total: ${sessionScope.cart.subTotal}--%>
<%--						<input type="submit" name="updateCartQuantities" value="Update Cart" />--%>
<%--					</td>--%>
<%--					<td>&nbsp;</td>--%>
<%--				</tr>--%>
			</table>
		</form>
<%
    Account account = new Account();
    OrderService orderService = new OrderService();
    Cart localCart = new Cart();
    Order order = new Order();
    account = (Account)session.getAttribute("accountBean");
    //System.out.print(account.getEmail());
    //System.out.print(account.getFirstName());
    localCart = (Cart) session.getAttribute("cart");
    order.initOrder(account, localCart);
    order.getBillToFirstName();
    session.setAttribute("order", order);
%>

		<c:if test="${sessionScope.cart.numberOfItems > 0}">
            <a class="Button" href = "JavaScript:void(0)" onclick = "tijiao()">Proceed to Checkout</a>
		</c:if>
	</div>
    <div id = "first" class = "submitBill">
        <div id="Catalog1"></div>
        <form action="ContitueAction" method="post">
            <table>
                <tr>
                    <th colspan=2>Payment Details</th>
                </tr>
                <tr>
                    <td>Card Type:</td>
                    <td>
                        <select name="order.cardType">
                            <option>Visa</option>
                            <option>Mastercard</option>
                            <option>Visa</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Card Number:</td>
                    <td><input type="text" name="order.creditCard" value="${order.creditCard}"class = "information"/> </td>
                </tr>
                <tr>
                    <td>Expiry Date (MM/YYYY):</td>
                    <td><input type="text" name="order.expiryDate" value="${order.expiryDate}"class = "information"/></td>
                </tr>
                <tr>
                    <th colspan=2>Billing Address</th>
                </tr>

                <tr>
                    <td>First name:</td>
                    <td><input type="text" name="order.billToFirstName" value="${order.billToFirstName}"class = "information"/></td>
                </tr>
                <tr>
                    <td>Last name:</td>
                    <td><input type="text" name="order.billToLastName" value="${order.billToLastName}"class = "information"/></td>
                </tr>
                <tr>
                    <td>Address 1:</td>
                    <td><input type="text" size="40" name="order.billAddress1" value="${order.billAddress1}"class = "information"/></td>
                </tr>
                <tr>
                    <td>Address 2:</td>
                    <td><input type="text" size="40" name="order.billAddress2" value="${order.billAddress2}"class = "information"/></td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td><input type="text" name="order.billCity" value="${order.billCity}"class = "information"/></td>
                </tr>
                <tr>
                    <td>State:</td>
                    <td><input type="text" size="4" name="order.billState" value="${order.billState}"class = "information"/></td>
                </tr>
                <tr>
                    <td>Zip:</td>
                    <td><input type="text" size="10" name="order.billZip" value="${order.billZip}"class = "information"/></td>
                </tr>
                <tr>
                    <td>Country:</td>
                    <td><input type="text" size="15" name="order.billCountry" value="${order.billCountry}"class = "information"/></td>
                </tr>

                <tr>
                    <td colspan=2><input type="checkbox" name="shippingAddressRequired">Ship to different address...</td>

                </tr>

            </table>

            <input type="button" name="newOrder" onclick="xinxiqueren()"id = "tijiaoxinxi" value="Continue" />
            <input type="button" name="newOrder" onclick="closeOne()" id = "quxiao"value="Cancle" />
        </form>
    </div>
    <div id="fade" class="black_overlay"></div>
    <div id = "second" class = "heduixinxi">
        <div id="BackLink1">
            <a href="main">Return to Main Menu</a>
        </div>

        <div id="Catalog2">Please confirm the information below and then
            press continue...

            <table>
                <tr>
                    <th align="center" colspan="2">
                        <font size="4">
                            <b>Order</b>
                        </font>
                        <br />
                        <font size="3">
                            <b>${sessionScope.order.orderDate}</b>
                        </font>
                    </th>
                </tr>

                <tr>
                    <th colspan="2">Billing Address</th>
                </tr>
                <tr>
                    <td>First name:</td>
                    <td><c:out value="${sessionScope.order.billToFirstName}" /></td>
                </tr>
                <tr>
                    <td>Last name:</td>
                    <td><c:out value="${sessionScope.order.billToLastName}" /></td>
                </tr>
                <tr>
                    <td>Address 1:</td>
                    <td><c:out value="${sessionScope.order.billAddress1}" /></td>
                </tr>
                <tr>
                    <td>Address 2:</td>
                    <td><c:out value="${sessionScope.order.billAddress2}" /></td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td><c:out value="${sessionScope.order.billCity}" /></td>
                </tr>
                <tr>
                    <td>State:</td>
                    <td><c:out value="${sessionScope.order.billState}" /></td>
                </tr>
                <tr>
                    <td>Zip:</td>
                    <td><c:out value="${sessionScope.order.billZip}" /></td>
                </tr>
                <tr>
                    <td>Country:</td>
                    <td><c:out value="${sessionScope.order.billCountry}" /></td>
                </tr>
                <tr>
                    <th colspan="2">Shipping Address</th>
                </tr>
                <tr>
                    <td>First name:</td>
                    <td><c:out value="${sessionScope.order.shipToFirstName}" /></td>
                </tr>
                <tr>
                    <td>Last name:</td>
                    <td><c:out value="${sessionScope.order.shipToLastName}" /></td>
                </tr>
                <tr>
                    <td>Address 1:</td>
                    <td><c:out value="${sessionScope.order.shipAddress1}" /></td>
                </tr>
                <tr>
                    <td>Address 2:</td>
                    <td><c:out value="${sessionScope.order.shipAddress2}" /></td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td><c:out value="${sessionScope.order.shipCity}" /></td>
                </tr>
                <tr>
                    <td>State:</td>
                    <td><c:out value="${sessionScope.order.shipState}" /></td>
                </tr>
                <tr>
                    <td>Zip:</td>
                    <td><c:out value="${sessionScope.order.shipZip}" /></td>
                </tr>
                <tr>
                    <td>Country:</td>
                    <td><c:out value="${sessionScope.order.shipCountry}" /></td>
                </tr>

            </table>
<%--            <a href="ConfirmOrderAction" class="Button" type = "button"name="confirmed" value="true"><input text="button" value = "Confirm" class = "button1"/></a>--%>

            <input text="button" value = "Confirm" onclick = "zhifumima()" class = "button1"/>
            <input text = "button" class = "button1" value = "return" onclick = "closeTwo()"/>
            <input text = "button" class = "button1" value = "cancel" onclick = "closeThree()"/>

        </div>
    </div>
    <div id = "third">
        <p class = "tishi">Please Enter Your Password For Payment</p>
        <input type="text" placeholder = "Password" id = "password">
        <input type="button" value="Submit" id = "Su" class = "zhifu" onclick = "closeFour()"/>
        <input type="button" value="Cancel" id = 'Ca' class = "zhifu" onclick = "closeFive()"/>
    </div>

    <script>
        function tijiao()
        {
            document.getElementById('first').style.display='block';
            document.getElementById('fade').style.display='block';
        }
        function xinxiqueren()
        {
            <%
            Order localOrder = new Order();
            localOrder = (Order) session.getAttribute("order");
            localOrder.setCardType(request.getParameter("order.cardType"));
            //System.out.print(localOrder.getCardType());
            localOrder.setCreditCard(request.getParameter("order.creditCard"));
            localOrder.setExpiryDate(request.getParameter("order.expiryDate"));
            localOrder.setBillToFirstName(request.getParameter("order.billToFirstName"));
            localOrder.setBillToLastName(request.getParameter("order.billToLastName"));
            localOrder.setBillAddress1(request.getParameter("order.billAddress1"));
            localOrder.setBillAddress2(request.getParameter("order.billAddress2"));
            localOrder.setBillCity(request.getParameter("order.billCity"));
            localOrder.setBillState(request.getParameter("order.billState"));
            localOrder.setBillZip(request.getParameter("order.billZip"));
            localOrder.setBillCountry(request.getParameter("order.billCountry"));
            session.setAttribute("order", localOrder);

        %>
            document.getElementById('first').style.display='none';
            document.getElementById('second').style.display='block';
        }
        function closeOne()
        {
            document.getElementById('first').style.display='none';
            document.getElementById('fade').style.display='none';
        }
        function closeTwo()
        {
            document.getElementById('first').style.display='block';
            document.getElementById('second').style.display='none';
        }
        function closeThree()
        {
            document.getElementById('fade').style.display='none';
            document.getElementById('second').style.display='none';
        }
        function zhifumima()
        {
            document.getElementById('second').style.display='none';
            document.getElementById('third').style.display='block';
        }
        function closeFour()
        {

            var x = document.getElementById("password").value;

            var y = "<%=Account.accountPassword%>";

            <%
                System.out.println(Account.accountPassword);
                System.out.println(Account.accountUsername);
            %>

            if(x === y)
            {
                location.href = "ConfirmOrderAction";
                <%
                    System.out.println("password11221");
                %>
            }
            else
            {
                confirm("Password Error");
            }
        }
        function closeFive()
        {
            document.getElementById('second').style.display='block';
            document.getElementById('third').style.display='none';
            <%
                System.out.println("password2222");
            %>
        }

    </script>
	<div id="MyList">
		<c:if test="${sessionScope.accountBean != null}">
			<c:if test="${!sessionScope.authenticated}">
				<c:if test="${!empty sessionScope.accountBean.account.listOption}">
					<%@ include file="IncludeMyList.jsp"%>
				</c:if>
			</c:if>
		</c:if>
	</div>

	<div id="Separator">&nbsp;</div>
</div>

<%--<style>--%>
<%--	div{--%>
<%--	text-align: center;--%>
<%--	}--%>
<%--	body{--%>
<%--	font-family: Arial,Verdana,sans-serif;--%>
<%--	color: #111111;--%>
<%--	text-align: left;--%>
<%--	}--%>
<%--	table{--%>
<%--	width:600px;--%>
<%--	/*margin: auto;*/--%>
<%--	}--%>
<%--	th,td{--%>
<%--	padding:7px 10px 10px 10px;--%>
<%--	text-align: center;--%>
<%--	}--%>
<%--	th{--%>
<%--	text-transform: uppercase;--%>
<%--	letter-spacing: 0.1em;--%>
<%--	font-size: 90%;--%>
<%--	border-bottom: 2px solid #111111;--%>
<%--	border-top: 1px solid #999;--%>
<%--	text-align: center;--%>
<%--	}--%>
<%--	tr.even--%>
<%--	{--%>
<%--	background-color: #efefef;--%>
<%--	}--%>
<%--	tr:hover{--%>
<%--	background-color: #c3e6e5;--%>
<%--	}--%>
<%--</style>--%>
<%@ include file="../common/IncludeBottom.jsp"%>