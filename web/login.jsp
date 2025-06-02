<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login - PyFighters</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body class="login-page">
    <div class="login-container">
        <h2>Iniciar Sesión</h2>
        <s:form action="login" cssClass="login-form">
            <s:textfield name="email" label="Correo electrónico" cssClass="input-field"/>
            <s:password name="password" label="Contraseña" cssClass="input-field"/>
            <s:submit value="Entrar" cssClass="submit-button"/>
        </s:form>

        <s:if test="hasActionErrors()">
            <div class="error-message">
                <s:actionerror/>
            </div>
        </s:if>
    </div>
</body>
</html>