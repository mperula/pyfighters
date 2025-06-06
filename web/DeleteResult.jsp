<%-- 
    Author     : pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="menu.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Eliminar Resultado</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/match.css">
    </head>
    <body>
        <div class="content">
            <h1>Eliminar Resultado</h1>

            <p>¿Estás seguro de que quieres eliminar este resultado?</p>

            <ul>
                <li><strong>ID del Resultado:</strong> <s:property value="resultId" /></li>
                <li><strong>ID del Combate:</strong> <s:property value="matchId" /></li>
                <li><strong>Ganador:</strong> <s:property value="winnerName" default="Ninguno" /></li>
                <li><strong>Perdedor:</strong> <s:property value="loserName" default="Ninguno" /></li>
                <li><strong>Empate:</strong> <s:property value="isDraw == 1 ? 'Sí' : 'No'" /></li>
            </ul>

            <s:form action="deleteResult" method="post">
                <s:hidden name="resultId" />
                <s:submit value="Confirmar eliminación" />
            </s:form>

            <a href="results.action">Cancelar</a>
        </div>
    </body>
</html>

