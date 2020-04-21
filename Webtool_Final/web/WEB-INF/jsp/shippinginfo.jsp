<%-- 
    Document   : shippinginfo
    Created on : 2019/4/21, 下午 09:44:50
    Author     : peichun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Add New Shipping Company</h1>
        <form action="addcompany.htm" method="post">
            Company Name<input type="text" name="companyname" /><br/>
            Destination<input type="text" name="destination" /><br/>
            Price/kg<input type="text" name="price" /><br/><br/>
            <input type="hidden" value="submitshipping" name="formtype" />
            <input type="submit" value="Add Shipping Company" name="addshipping" />
        </form>
        <form action="addcompany.htm" method ="post">
            <input type="hidden" name="formtype" value="logout"/>
            <input type="submit" value="Logout"/>
        </form><br/>
    </body>
</html>
