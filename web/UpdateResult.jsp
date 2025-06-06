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
                <!-- Campo oculto para el ID del resultado -->
                <s:hidden name="resultId" />

                <!-- Combate (solo lectura) -->
                <label>Combate:</label>
                <input type="text" value="Combate #<s:property value='matchId' />" readonly />
                <s:hidden name="matchId" />

                <!-- Luchador ganador -->
                <s:select 
                    name="winnerId" 
                    label="Ganador"
                    list="fighterOptions" 
                    listKey="key" 
                    listValue="value" 
                    headerKey="0" 
                    headerValue="-- Ninguno --" />

                <!-- Luchador perdedor -->
                <s:select 
                    name="loserId" 
                    label="Perdedor"
                    list="fighterOptions" 
                    listKey="key" 
                    listValue="value" 
                    headerKey="0" 
                    headerValue="-- Ninguno --" />

                <!-- Empate -->
                <label>¿Empate?</label><br>
                <input type="radio" name="isDraw" value="1" <s:if test="isDraw == 1">checked</s:if> /> Sí<br>
                <input type="radio" name="isDraw" value="0" <s:if test="isDraw == 0">checked</s:if> /> No<br><br>

                <s:submit value="Guardar cambios" />
            </s:form>

            <a href="results.action">Volver a la lista</a>
        </div>
    </body>
</html>

