<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="menu.jsp" %>

<div class="content">
    <h1>¡Bienvenido, 👾<s:property value="#session.usuario.username" />👾, a PhyFighter!</h1>

    <pre style="color: #58a6ff; font-size: 14px;">

        .-------------------------.
        |         .:::::.         |
        |        :: ::::::        |
        |        ````:::::        |
        |  .:::::::::::::: iiii.  |
        | :::::::::::::::: iiiiii |
        | :::::: ..........iiiiii |
        |  ':::: iiiiiiiiiiiiii'  |
        |        iiiii....        |
        |        iiiiii ii        |
        |         'iiiii'         |
        '-------------------------'

        ¡Prepárate para programar... y luchar!
    </pre>

    <p>Ataca una sección del menú para comenzar tu camino como campeón de PyFighters 🐍⚔️</p>
</div>