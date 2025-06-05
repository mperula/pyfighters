<%-- 
    Author     : pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="menu.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Lista de Resultados</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/match.css">

    </head>
    <body>
        <div class="content">
            <h1>Lista de Resultados</h1>

            <s:if test="results.size() == 0">
                <p>No hay resultados disponibles.</p>
            </s:if>

            <s:else>
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>Combate</th>
                        <th>Ganador</th>
                        <th>Perdedor</th>
                        <th>Empate</th>
                        <th>Acciones</th>
                    </tr>
                    <s:iterator value="results">
                        <tr>
                            <td><s:property value="resultId" /></td>
                            <td><s:property value="matchId" /></td>
                            <td><s:property value="winnerName" /></td>
                            <td><s:property value="loserName" /></td>
                            <td>
                                <s:property value="#this.isDraw == 1 ? 'Sí' : 'No'" />
                            </td>

                            <td>
                                <a href="updateResultForm.action?resultId=<s:property value='resultId'/>">Editar</a>
                                <a href="deleteResultConfirm.action?resultId=<s:property value='resultId'/>">Eliminar</a>
                            </td>
                        </tr>
                    </s:iterator>
                </table>
            </s:else>

            <br/>
            <a href="addResultForm.action">Crear nuevo resultado</a>
        </div>
    </body>
</html>
