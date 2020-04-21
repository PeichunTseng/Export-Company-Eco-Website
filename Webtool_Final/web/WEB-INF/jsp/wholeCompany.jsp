<%-- 
    Document   : wholeCompany
    Created on : 2019/4/21, 下午 06:00:05
    Author     : peichun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>whole company</title>
    </head>
    <body>
        <h1>Choose company type</h1><br/>
        <div>Please Make Your Selection Below</div><br/>
        
        <form action="authentication.htm" method="post">

            <select name="choice">
                <option value="shipping company">Shipping Company</option>
                <option value="manufacturing company">Manufacturing Company</option>
                <option value="export company">Export Company</option>
            </select>

            <input type="submit" name="submit" value="Send"/>
            <input type="hidden" name="option" value="home"/>
        </form>
    </body>
</html>
