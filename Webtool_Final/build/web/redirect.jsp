<%--
Views should be stored under the WEB-INF folder so that
they are not accessible except through controller process.

This JSP is here to provide a redirect to the dispatcher
servlet but should be the only JSP outside of WEB-INF.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Whole company</title>
    </head>
    <body>
        <jsp:forward page="WEB-INF/jsp/wholeCompany.jsp"/>
    </body>
</html>

<!--<% response.sendRedirect("index.htm"); %>-->
