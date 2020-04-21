
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Manage Company</title>
        </head>

        <body>
<!--            <h2> Welcome  </h2>-->
        <h4>Search</h4>
        <form action="search.htm" method="post">
            <label>Product Name</label><input type="radio" name="choice" value="productName" /><br/>
            <label>Destination</label><input type="radio" name="choice" value="destination" /><br/>
            <input type="text" name="searchBar" />
            <input type="submit" name="search" value="Search" />
        </form>
        
        <form action="logout.htm" method ="post">
            <input type="hidden" name="option" value="logout"/>
            <input type="submit" value="Logout"/>
        </form><br/>
        
        <hr>
        
        <h1>Product Information</h1>
<!--        <form action="user.htm" method="post">
            Search User: <input type="text" name="search" />
            <input type="hidden" name="option" value="search"/>
            <input type="submit" value="Search"/>
        </form>-->
       
        <table>
      <c:if test="${not empty model.get('wholeproduct')}">
            <thead>
            <th>Company Name</th>
            <th>Product Name</th>
            <th>Price</th>
        </thead>
      </c:if>
        <tbody>
            <!--Implement Code here-->
            <c:forEach var="pro" items="${model.get('wholeproduct')}"> 
                <tr>
                    <td><c:out value="${pro.getCompanyName()}" /></td>
                    <td><c:out value="${pro.getProductName()}" /></td>
                    <td><c:out value="${pro.getPrice()}" /></td>
                    <td>
                        <form action="/Webtool_Final/exportHome.htm" method="POST">
                            <input type='hidden' name='proId' value='${pro.getId()}'/>
                            <input type='hidden' name='option' value='delete'/>
                            <input type="submit" value="Delete"/>
                        </form>
                    <td>
                </tr>
            </c:forEach>

        </tbody>
    </table>

<hr>

<h1>Shipping Information</h1>
<!--        <form action="user.htm" method="post">
            Search User: <input type="text" name="search" />
            <input type="hidden" name="option" value="search"/>
            <input type="submit" value="Search"/>
        </form>-->

<table>
    <c:if test="${not empty model.get('wholeshipping')}">
        <thead>
        <th>Company Name</th>
        <th>Destination</th>
        <th>Price/kg</th>
    </thead>
</c:if>
<tbody>
    <!--Implement Code here-->
    <c:forEach var="shi" items="${model.get('wholeshipping')}"> 
        <tr>
            <td><c:out value="${shi.getCompanyName()}" /></td>
            <td><c:out value="${shi.getDestination()}" /></td>
            <td><c:out value="${shi.getPrice()}" /></td>
            <td>
                <form action="/Webtool_Final/exportHome.htm" method="POST">
                    <input type='hidden' name='shipId' value='${shi.getId()}'/>
                    <input type='hidden' name='option' value='delete'/>
                    <input type="submit" value="Delete"/>
                </form>
            <td>
        </tr>
    </c:forEach>

</tbody>
        </table>
</body>
</html>