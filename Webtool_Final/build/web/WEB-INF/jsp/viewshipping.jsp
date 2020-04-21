
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Destination Page</title>
    </head>
    <body>
        <h2>Destination for ${sessionScope.USER.username} Shipping Company</h2>
        <table>
            <tr>
                <th></th>
                <th>Company</th>
                <th>Destination</th>
                <th>Price/kg</th>
            </tr>
            <form action="exportHome.htm" method ="post">
            <c:forEach var="shipping" items="${shippinges}">
                <tr>
                    <td><input type="radio" name="companyname" value="${shipping.getCompanyName()}"/></td>
                    <td><c:out value="${shipping.companyName}" /></td>
                    <td><c:out value="${shipping.destination}" /></td>
                    <td><c:out value="${shipping.price}" /></td>
                    <td><input type="hidden" name="destination" value="${shipping.destination}"/></td>
                </tr>
            </c:forEach> 
        </table><br/>
        <p><input type="submit" name="option" value="addship"></p>
        </form>
        <a href="exportHome.htm">Go Back</a>
    </body>
</body>
</html>
