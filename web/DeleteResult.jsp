<%-- 
    Author     : pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="menu.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Eliminar Resultado</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <div class="content">
        <h1>Confirmar Eliminación</h1>

        <p>¿Estás seguro de que deseas eliminar el resultado del combate <strong><s:property value="result.matchId" /></strong>?</p>

        <s:form action="deleteResult" method="post">
            <s:hidden name="result.resultId" />
            <s:submit value="Sí, eliminar" />
        </s:form>

        <a href="results.action">Cancelar</a>
    </div>
</body>
</html>
