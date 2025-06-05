<%-- 
    Author     : pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="menu.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Buscar Resultados</title>
        <link rel="stylesheet" href="css/style.css" />
    </head>
    <body>
        <div class="content">
            <h1>Buscar Resultados</h1>

            <s:form action="searchResults" method="get">
                <s:select 
                    name="fighterId" 
                    label="Luchador"
                    list="fighterOptions"
                    listKey="key"
                    listValue="value"
                    headerKey=""
                    headerValue="Todos los luchadores" />

                <s:select 
                    name="arenaId" 
                    label="Arena"
                    list="arenaOptions"
                    listKey="key"
                    listValue="value"
                    headerKey=""
                    headerValue="Todas las arenas" />

                <s:submit value="Buscar"/>
            </s:form>

            <s:if test="results != null && !results.isEmpty()">
                <h2>Resultados Encontrados</h2>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Combate</th>
                            <th>Ganador</th>
                            <th>Perdedor</th>
                            <th>Empate</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="results">
                            <tr>
                                <td><s:property value="resultId"/></td>
                                <td><s:property value="matchId"/></td>
                                <td><s:property value="winnerName"/></td>
                                <td><s:property value="loserName"/></td>
                                <td><s:property value="isDraw"/></td>
                                <td>
                                    <a href="getResult.action?resultId=<s:property value='resultId'/>">Ver</a>
                                </td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </s:if>
        </div>
    </body>
</html>
