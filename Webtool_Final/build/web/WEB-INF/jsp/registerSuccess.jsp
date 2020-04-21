<%-- 
    Document   : registerSuccess
    Created on : 2019/4/16, 下午 03:19:02
    Author     : peichun
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add result</title>
    </head>
    
    <body>
        <h1>Register successfully!</h1>
<!--        <a href='index.htm'>Click here to go back to the home page</a>-->
        <form action="authentication.htm" method="POST">
            <input type="hidden" value="registerSuccess" name="option" />
            <input type="submit" value="Click here to go back to the home page"/>
        </form>
    </body>
    
</html>
