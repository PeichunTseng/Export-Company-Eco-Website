
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Page</title>
    </head>
    <body>
        <h2>Product for ${sessionScope.USER.username} Export Company</h2>
        <table>
            <tr>
                <th></th>
                <th>Company</th>
                <th>Product</th>
                <th>Price</th>
            </tr>
            <form action="exportHome.htm" method ="post">
            <c:forEach var="product" items="${products}">
                <tr>
                    <td><input type="radio" name="companyname" value="${product.getCompanyName()}"/></td>
                    <td><c:out value="${product.companyName}" /></td>
                    <td><c:out value="${product.productName}" /></td>
                    <td><c:out value="${product.price}" /></td>
<!--                    <td>
                        <form action="/Webtool_Final/exportHome.htm" method="POST">
                            <input type='hidden' name='proId' value='${product.getId()}'/>
                            <input type='hidden' name='option' value='deleteStoreProduct'/>
                            <input type="submit" value="Delete"/>
                        </form>
                    <td>-->
                    <td><input type="hidden" name="productname" value="${product.productName}"/></td>
                </tr>
            </c:forEach> 
                
        </table><br/>
           
        <p><input type="submit" name="option" value="add"></p>
        </form>
        <a href="exportHome.htm">Go Back</a>
    </body>
</body>
</html>
