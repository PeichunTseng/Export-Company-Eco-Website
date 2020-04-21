<%-- 
    Document   : manufacturinginfo
    Created on : 2019/4/21, 下午 09:46:38
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
        <h1>Add New Product</h1>
        <form action="addcompany.htm" method="post">
            Company Name<input type="text" name="companyname" /><br/>
            Product Name<input type="text" name="productname" /><br/>
            Price<input type="text" name="price" /><br/><br/>
            <input type="hidden" value="submitproduct" name="formtype" />
            <input type="submit" value="Add Product" name="addproduct" />
        </form>
        <form action="addcompany.htm" method ="post">
            <input type="hidden" name="formtype" value="logout"/>
            <input type="submit" value="Logout"/>
        </form><br/>
    </body>
</html>