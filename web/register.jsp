<%-- 
    Document   : register
    Created on : 06-jun-2025, 23:16:44
    Author     : empichi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Registro - PyFighters</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body class="login-page">
        <div class="login-container">
            <h2>Registro de Usuario</h2>

            <s:form action="register" method="post" cssClass="login-form">
                <s:textfield name="username" placeholder="Nombre de usuario" cssClass="input-field"/>
                <s:textfield name="email" placeholder="Correo electrónico" cssClass="input-field"/>
                <s:password name="password" placeholder="Contraseña" cssClass="input-field"/>
                <s:submit value="Registrarse" cssClass="submit-button"/>
            </s:form>

            <form action="login.jsp" method="get">
                <input type="submit" value="Cancelar" class="submit-button" style="background-color: #f44336; color: #fff; margin-top: 10px;">
            </form>

            <s:if test="hasActionErrors()">
                <div class="error-message">
                    <s:actionerror/>
                </div>
            </s:if>
        </div>
    </body>
</html>
