
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="menu.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Actualizar Combate</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="css/match.css">
    </head>
    <body>
        <div class="content">
            <h1>Actualizar Combate</h1>

            <s:form action="updateMatch" method="post">
                <s:hidden name="matchId" />

                <s:select 
                    name="fighter1Id" 
                    label="Luchador 1" 
                    list="fighterOptions" 
                    listKey="key" 
                    listValue="value"
                    />

                <s:select 
                    name="fighter2Id" 
                    label="Luchador 2" 
                    list="fighterOptions" 
                    listKey="key" 
                    listValue="value"
                    />

                <s:select 
                    name="arenaId" 
                    label="Arena" 
                    list="arenaOptions" 
                    listKey="key" 
                    listValue="value"
                    />

                <label for="result">Resultado:</label><br>
                <input type="radio" name="result" value="1" <s:if test="result == '1'">checked</s:if> /> Gana Luchador 1<br>
                <input type="radio" name="result" value="X" <s:if test="result == 'X'">checked</s:if> /> Empate<br>
                <input type="radio" name="result" value="2" <s:if test="result == '2'">checked</s:if> /> Gana Luchador 2<br>

                    <label for="date">Fecha:</label><br>
                    <input type="date" name="date" value="<s:property value='date'/>" /><br><br>

                <s:submit value="Actualizar Combate" />
            </s:form>

            <a href="matches.action">Cancelar</a>
        </div>
    </body>
</html>
