
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="menu.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Detalles del Combate</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="css/match.css">
    </head>
    <body>
        <div class="content">
            <h1>Detalles del Combate</h1>
            <p><strong>ID:</strong> <s:property value="match.matchId"/></p>
            <p><strong>Luchador 1:</strong> <s:property value="match.fighter1Name"/></p>
            <p><strong>Luchador 2:</strong> <s:property value="match.fighter2Name"/></p>
            <p><strong>Arena:</strong> <s:property value="match.arenaName"/></p>
            <p><strong>Resultado:</strong> <s:property value="match.result"/></p>
            <p><strong>Fecha:</strong> <s:property value="match.date"/></p>
            <a href="matches.action">Volver a la lista</a>
        </div>
    </body>
</html>