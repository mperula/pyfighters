<%-- 
    Document   : createTournament
    Created on : 07-jun-2025, 17:39:27
    Author     : pablouceda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creacion de Torneos</title>
    </head>
    <body>
        <s:form action="saveTournament">
        <s:textfield name="tournament.name" label="Nombre"/>
        <s:textfield name="tournament.startDate" label="Fecha inicio"/>
        <s:submit value="Crear"/>
        </s:form>

    </body>
</html>
