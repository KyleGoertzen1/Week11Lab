<%-- 
    Document   : forgot
    Created on : Nov 21, 2017, 1:17:01 PM
    Author     : 729347
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
    </head>
    <body>
        <form action="forgot" method="POST">
            <h1>Forgot Password</h1>
            <p>Please enter your email address to retrieve your password.</p>
            Email Address: <input type="text" name="email"><br>
            <input type="hidden" name="action" value="emailed"><br>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
