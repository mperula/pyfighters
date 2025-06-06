<%-- 
    Document   : profile
    Created on : 06-jun-2025, 23:26:25
    Author     : empichi
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="menu.jsp" />

<html>
    <head>
        <title>Mi Perfil</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body class="content">
        <h2>Mi Perfil</h2>

        <s:form action="actualizarPerfil" method="post" cssClass="arena-form">
            <div class="form-row">
                <label>ID:</label>
                <s:property value="fighter.fighter_id" />
                <s:hidden name="fighter.fighter_id" value="%{fighter.fighter_id}" />
            </div>

            <div class="form-row">
                <label>Nombre de Usuario:</label>
                <s:property value="fighter.username" />
                <s:textfield name="username" value="%{fighter.username}" cssClass="input-field"/>
            </div>

            <div class="form-row">
                <label>Correo:</label>
                <s:property value="fighter.email" />
                <s:textfield name="email" value="%{fighter.email}" cssClass="input-field"/>
            </div>

            <div class="form-row">
                <label>Contraseña:</label>
                <s:property value="new String(new char[fighter.password.length()]).replace('\0', '*')" />
                <s:password name="password" value="%{fighter.password}" cssClass="input-field"/>
            </div>

            <input type="submit" value="Actualizar" class="submit-button"/>
        </s:form>

        <s:if test="hasActionErrors()">
            <div class="error-message">
                <s:actionerror />
            </div>
        </s:if>
    </body>
</html>