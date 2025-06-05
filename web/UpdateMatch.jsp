<%-- 
    Author     : pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="menu.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Actualizar Combate</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="css/match.css">
    </head>
    <body>
        <div class="content">
            <h1>Actualizar Combate</h1>
            <s:form action="updateMatch" method="post">
                <s:hidden name="match.matchId"/>
                <s:textfield name="match.fighter1Name" label="Luchador 1"/>
                <s:textfield name="match.fighter2Name" label="Luchador 2"/>
                <s:textfield name="match.arenaName" label="Arena"/>
                <s:textfield name="match.result" label="Resultado"/>
                <s:textfield name="match.date" label="Fecha (YYYY-MM-DD)"/>
                <s:submit value="Actualizar Combate"/>
            </s:form>
            <a href="matches.action">Cancelar</a>
        </div>
    </body>
</html>
