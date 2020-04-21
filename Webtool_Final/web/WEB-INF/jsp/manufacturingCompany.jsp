<%-- 
    Document   : manufacturingCompany
    Created on : 2019/4/21, 下午 06:55:37
    Author     : peichun
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Manufacturing Company employee interface</h1>
        <h3>Setting Your Information</h3>
        <form action="authentication.htm" method="POST">
            <label> Username :  </label><input type="text" name="userName" />
            <label> Password :  </label><input type="password" name="password" />            
            <input type="hidden" value="manufacturingregister" name="option" />
            <input type="submit" value="Set"/>
        </form>
        <br/><br/><br/><br/><br/><br/><br/>
        <h3>Login To System</h3>
        <form action="authentication.htm" method="POST">
            <label> Username :  </label><input type="text" name="userName" />
            <label> Password :  </label><input type="password" name="password" />
            <input type="hidden" value="manufacturinglogin" name="option" />
            <input type="submit" value="Login"/>
        </form>
        <br/><br/><br/><br/><br/><br/><br/>

    </body>
</html>

