<%-- 
    Author     : pablo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="menu.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Agregar Resultado</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
        <div class="content">
            <h1>Agregar Nuevo Resultado</h1>

            <s:if test="hasActionErrors()">
                <div class="error-message">
                    <s:actionerror />
                </div>
            </s:if>

            <s:form action="createResult" method="post">

                <s:select 
                    name="matchId" 
                    label="Combate"
                    list="matchOptions"
                    listKey="key"
                    listValue="value"
                    required="true" />

                <s:select 
                    name="resultado" 
                    label="Resultado"
                    list="resultOptions"
                    listKey="key"
                    listValue="value"
                    required="true" />

                <s:submit value="Guardar Resultado" />

            </s:form>

            <a href="results.action">Cancelar</a>
        </div>
    </body>
</html>

