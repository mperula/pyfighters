<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="menu.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Buscar Resultados</title>
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="css/match.css" />
    </head>
    <body>
        <div class="content">
            <h1>Buscar Resultados</h1>

            <s:form action="searchResults" method="get">
                <s:textfield name="matchId" label="ID del Combate" />
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
                                <td>
                                    <s:if test="isDraw == 1">Sí</s:if>
                                    <s:else>No</s:else>
                                    </td>
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
