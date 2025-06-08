<%-- 
    Document   : ListTournaments
    Created on : 07-jun-2025, 17:36:05
    Author     : pablouceda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Torneos</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="css/match.css">
    </head>
    <body>
        <s:iterator value="tournamentList">
            <div class="tournament">
                <h3><s:property value="name"/></h3>
            <p>Fecha: <s:property value="startDate"/> a <s:property value="endDate"/></p>
            </div>
        </s:iterator>
    </body>
</html>
