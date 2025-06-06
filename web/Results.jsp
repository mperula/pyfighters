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

                            <td>
                                <s:if test="winnerName != null && winnerName.trim() != ''">
                                    <s:property value="winnerName" />
                                </s:if>
                                <s:else>
                                    Ninguno
                                </s:else>
                            </td>

                            <td>
                                <s:if test="loserName != null && loserName.trim() != ''">
                                    <s:property value="loserName" />
                                </s:if>
                                <s:else>
                                    Ninguno
                                </s:else>
                            </td>

                            <td>
                                <s:if test="isDraw == 1">
                                    Sí
                                </s:if>
                                <s:else>
                                    No
                                </s:else>
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
        </div>
    </body>
</html>

