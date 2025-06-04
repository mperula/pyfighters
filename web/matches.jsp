<%-- 
    Author     : pablo
--%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Lista de Matches</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
        <h2>Lista de Combates</h2>

        <s:iterator value="matches">
            <div class="match-item">
                <strong><s:property value="fighter1"/></strong>
                vs
                <strong><s:property value="fighter2"/></strong><br>
                Arena: <s:property value="arena"/> <br>
                Resultado: <s:property value="result"/> <br>
                Fecha: <s:property value="date"/> <br><br>
            </div>
        </s:iterator>

    </body>
</html>
