
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="menu.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Agregar Combate</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="css/match.css">
    </head>
    <body>
        <div class="content">
            <h1>Agregar Nuevo Combate</h1>

            <s:if test="hasActionErrors()">
                <div class="error-message">
                    <s:actionerror />
                </div>
            </s:if>

            <s:form action="createMatch" method="post">

                <s:select 
                    name="fighter1Id" 
                    label="Luchador 1"
                    list="fighterOptions"
                    listKey="key"
                    listValue="value" />

                <s:select 
                    name="fighter2Id"
                    label="Luchador 2"
                    list="fighterOptions"
                    listKey="key"
                    listValue="value" />

                <s:select 
                    name="arenaId"
                    label="Arena"
                    list="arenaOptions"
                    listKey="key"
                    listValue="value" />

                <s:textfield name="result" label="Resultado"/>
                <s:textfield name="date" label="Fecha (YYYY-MM-DD)"/>
                <s:submit value="Agregar Combate"/>

            </s:form>


            <a href="matches.action">Cancelar</a>
        </div>
    </body>
</html>

