
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="menu.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Lista de Combates</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="css/match.css">
    </head>
    <body>
        <div class="content">
            <h1>Ver Todos los Combates</h1>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Luchador 1</th>
                    <th>Luchador 2</th>
                    <th>Arena</th>
                    <th>Resultado</th>
                    <th>Fecha</th>
                    <th>Acciones</th>
                </tr>
                <s:iterator value="matches">
                    <tr>
                        <td><s:property value="matchId"/></td>
                        <td><s:property value="fighter1Name"/></td>
                        <td><s:property value="fighter2Name"/></td>
                        <td><s:property value="arenaName"/></td>
                        <td><s:property value="result"/></td>
                        <td><s:property value="date"/></td>
                        <td>
                            <a href="getMatch.action?matchId=<s:property value='matchId'/>">🔍 Ver</a>
                            <a href="updateMatchForm.action?matchId=<s:property value='matchId'/>">✏️ Editar</a>
                            <a href="deleteMatchConfirm.action?matchId=<s:property value='matchId'/>">🗑️ Borrar</a>
                        </td>
                    </tr>
                </s:iterator>
            </table>
        </div>
    </body>
</html>
