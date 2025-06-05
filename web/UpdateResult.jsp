<%-- 
    Author     : pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="menu.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Resultado</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="content">
        <h1>Editar Resultado</h1>

        <s:form action="updateResult" method="post">
            <!-- Campo oculto para mantener el ID del resultado -->
            <s:hidden name="result.resultId" />

            <s:select
                name="result.matchId"
                label="Combate"
                list="matchOptions"
                listKey="key"
                listValue="value"
                value="%{result.matchId}" />

            <s:select
                name="result.winnerId"
                label="Ganador"
                list="fighterOptions"
                listKey="key"
                listValue="value"
                value="%{result.winnerId}" />

            <s:select
                name="result.loserId"
                label="Perdedor"
                list="fighterOptions"
                listKey="key"
                listValue="value"
                value="%{result.loserId}" />

            <s:checkbox
                name="result.isDraw"
                label="¿Empate?" />

            <s:submit value="Actualizar Resultado" />
        </s:form>

        <a href="results.action">Cancelar</a>
    </div>
</body>
</html>
