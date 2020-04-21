

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Username existed Page</title>
    </head>
    <body>
        <h1>User name have been used</h1>
        <form action="authentication.htm" method ="post">
            <input type="hidden" name="option" value="logout"/>
            <input type="submit" value="HOME"/>
        </form><br/>
<!--        <h2>${message}</h2>-->
    </body>
</html>
