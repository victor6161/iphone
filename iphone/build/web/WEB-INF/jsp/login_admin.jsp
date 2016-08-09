<%@page contentType="text/html" pageEncoding="UTF-8" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> </title>
        <link href="<c:url value = "/resources/css/login_button.css"/>" rel="stylesheet">
    </head>
    <body>
        <form id="login">
    
            <fieldset id="inputs">
                <input id="username" type="text" placeholder="Логин" autofocus required>   
                <input id="password" type="password" placeholder="Пароль" required>
            </fieldset>
            
            <fieldset id="actions">
                <input type="submit" id="submit" value="ВОЙТИ">
                <a href="">Забыли пароль?</a><a href="">Регистрация</a>
            </fieldset>
        </form>
        
    </body>
</html>
