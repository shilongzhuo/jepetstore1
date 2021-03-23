<%--
  Created by IntelliJ IDEA.
  User: 86182
  Date: 2020/11/22
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/IncludeTop.jsp"%>
<div id="BackLink">
    <a href="#">Return to Shopping Cart</a>
</div>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Recode</title>
</head>
<body>
<h2>My Recode(记录你的每一次浏览)</h2>
<form action="UpdateCartAction" method="post" >
    <table>
        <tr>
            <th><b>Item ID</b></th>
            <th><b>Date</b></th>
            <th><b>Number</b></th>
            <th>&nbsp;</th>
        </tr>

<%--        <c:if test="${Account.cart1.numberOfItems == 0}">--%>
<%--            <tr>--%>
<%--                <td colspan="8"><b>Your have ordered nothing</b></td>--%>
<%--            </tr>--%>
<%--        </c:if>--%>

     <c:forEach var="recode" items="${sessionScope.recodes}">
            <tr>
                <td>
                    <a href="ItemView?itemId=${recode.itemId}">${recode.itemId}</a>
                </td>
                <td>${recode.date}</td>
                <td>${recode.number}</td>
                <td>
                    <a class="Button" href="RecodeRemove?recodeItemid=${recode.itemId}">Remove</a>
<%--                    <a class="Button" href="RemoveAction?cartItem=${cartItem.item.itemId}">Remove</a>--%>
<%--                    ?recodeItemid=${recode.itemId}--%>
                </td>
            </tr>
<%--            RemoveAction?cartItem=${cartItem.item.itemId}--%>
        </c:forEach>
    </table>
</form>
</body>
</html>
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