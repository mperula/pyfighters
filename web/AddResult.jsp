<%-- 
    Author     : pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="menu.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Agregar Resultado</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
        <div class="content">
            <h1>Agregar Nuevo Resultado</h1>

            <s:if test="hasActionErrors()">
                <div class="error-message">
                    <s:actionerror />
                </div>
            </s:if>

            <s:form action="createResult" method="post">
                <s:select 
                    name="result.matchId" 
                    label="Combate"
                    list="matchOptions"
                    listKey="key"
                    listValue="value" />

                <s:select 
                    name="result.winnerId" 
                    label="Ganador"
                    list="fighterOptions"
                    listKey="key"
                    listValue="value" />

                <s:select 
                    name="result.loserId" 
                    label="Perdedor"
                    list="fighterOptions"
                    listKey="key"
                    listValue="value" />

                <s:checkbox 
                    name="result.isDraw" 
                    label="¿Empate?" />

                <s:submit value="Guardar Resultado"/>
            </s:form>

            <a href="results.action">Cancelar</a>
        </div>
    </body>
</html>
