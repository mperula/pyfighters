<%-- 
    Document   : SearchMatch
    Created on : 04-jun-2025, 20:20:28
    Author     : pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ include file="menu.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Buscar Combate</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="css/match.css">
    </head>
    <body>
        <h2>Buscar Combate por ID</h2>
        <s:form action="getMatchById">
            <s:textfield name="matchId" label="ID del combate"/>
            <s:submit value="Buscar"/>
        </s:form>

        <s:if test="match != null">
            <h3>Datos del Combate:</h3>
            <p>ID: <s:property value="match.matchId"/></p>
            <p>Fecha: <s:property value="match.date"/></p>
            <p>Luchador 1: <s:property value="match.fighter1Name"/></p>
            <p>Luchador 2: <s:property value="match.fighter2Name"/></p>
            <p>Arena: <s:property value="match.arenaName"/></p>
            <p>Resultado: <s:property value="match.result"/></p>
        </s:if>

        <s:if test="match == null">
            <p>No se encontró ningún combate con ese ID.</p>
        </s:if>
    </body>
</html>
