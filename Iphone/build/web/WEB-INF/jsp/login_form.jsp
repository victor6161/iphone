
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <link href="<c:url value = "/resources/css/login_button.css"/>" rel="stylesheet">
        
        
    </head>
    
    <body>
        <form id="login">
        <h1>Форма входа</h1>
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
