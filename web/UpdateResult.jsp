
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="menu.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Editar Resultado</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="css/match.css">

    </head>
    <body>
        <div class="content">
            <h1>Editar Resultado</h1>

            <s:form action="updateResult" method="post">
                <s:hidden name="resultId" />

                <label>Combate:</label>
                <input type="text" value="Combate #<s:property value='matchId' />" readonly />
                <s:hidden name="matchId" />

                <s:select 
                    name="winnerId" 
                    label="Ganador"
                    list="fighterOptions" 
                    listKey="key" 
                    listValue="value" 
                    headerKey="0" 
                    headerValue="-- Ninguno --" />

                <s:select 
                    name="loserId" 
                    label="Perdedor"
                    list="fighterOptions" 
                    listKey="key" 
                    listValue="value" 
                    headerKey="0" 
                    headerValue="-- Ninguno --" />

                <label>¿Empate?</label><br>
                <input type="radio" name="isDraw" value="1" <s:if test="isDraw == 1">checked</s:if> /> Sí<br>
                <input type="radio" name="isDraw" value="0" <s:if test="isDraw == 0">checked</s:if> /> No<br><br>

                <s:submit value="Guardar cambios" />
            </s:form>

            <a href="results.action">Volver a la lista</a>
        </div>
    </body>
</html>

