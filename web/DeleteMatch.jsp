<%-- 
    Document   : DeleteMatch
    Created on : 04-jun-2025, 20:17:55
    Author     : pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="menu.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Eliminar Combate</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="css/match.css">
    </head>
    <body>
        <div class="content">
            <h1>Eliminar Combate</h1>
            <p>¿Estás seguro de que deseas eliminar el siguiente combate?</p>
            <p><strong>ID:</strong> <s:property value="matchId"/></p>
            <p><strong>Luchador 1:</strong> <s:property value="fighter1Id"/></p>
            <p><strong>Luchador 2:</strong> <s:property value="fighter2Id"/></p>
            <p><strong>Arena:</strong> <s:property value="arenaId"/></p>
            <p><strong>Resultado:</strong> <s:property value="result"/></p>
            <p><strong>Fecha:</strong> <s:property value="date"/></p>

            <s:form action="deleteMatch" method="post">
                <s:hidden name="matchId" value="%{matchId}" />
                <s:submit value="Confirmar Eliminación"/>
            </s:form>
            <a href="matches.action">Cancelar</a>
        </div>
    </body>
</html>