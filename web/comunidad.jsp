<%-- 
    Document   : comunidad
    Created on : 07-jun-2025, 0:09:39
    Author     : empichi
--%><%-- 
    Document   : comunidad
    Created on : 07-jun-2025, 0:09:39
    Author     : empichi
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="menu.jsp" />

<html>
    <head>
        <title>Comunidad PyFighters</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body class="content">
        <h2 style="text-align:center; color: #ffd43b; margin-top: 10px; text-shadow: 1px 1px 3px #000;">🌐 Comunidad PyFighters</h2>

        <div class="community-table" style="animation: fadeInUp 0.6s ease;">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre de Usuario</th>
                        <th>Correo</th>
                        <th>Victorias</th>
                        <th>Derrotas</th>
                        <th>Empates</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="fighters" var="f" status="i">
                        <tr>
                            <td><s:property value="#f.fighter_id" /></td>
                            <td>
                                <s:if test="#i.index == 0">
                                    <span class="top-player"><s:property value="#f.username" /></span>
                                </s:if>
                                <s:else>
                                    <s:property value="#f.username" />
                                </s:else>
                            </td>
                            <td><s:property value="#f.email" /></td>
                            <td><s:property value="#f.victories" /></td>
                            <td><s:property value="#f.defeats" /></td>
                            <td><s:property value="#f.draws" /></td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </div>
    </body>
</html>
