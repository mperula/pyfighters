<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="menu.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Detalles del Resultado</title>
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="css/match.css" />

    </head>
    <body>
        <div class="content">
            <h1>Detalles del Resultado</h1>

            <p><strong>ID del resultado:</strong> <s:property value="result.resultId" /></p>
            <p><strong>Combate ID:</strong> <s:property value="result.matchId" /></p>
            <p><strong>Ganador:</strong> <s:property value="result.winnerName" /></p>
            <p><strong>Perdedor:</strong> <s:property value="result.loserName" /></p>
            <p><strong>¿Empate?:</strong> <s:property value="result.isDraw" /></p>
            <p><strong>Fecha creación:</strong> <s:property value="result.createdAt" /></p>
            <p><strong>Última modificación:</strong> <s:property value="result.updatedAt" /></p>

            <a href="updateResultForm.action?resultId=<s:property value='result.resultId'/>">Editar</a> |
            <a href="deleteResultConfirm.action?resultId=<s:property value='result.resultId'/>">Eliminar</a> |
            <a href="results.action">Volver</a>
        </div>
    </body>
</html>
