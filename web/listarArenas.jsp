<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Listar Arenas</title>
    <style>
        body.content {
            margin-left: 230px;
            padding: 30px;
            background-color: #0d1117;
            font-family: 'Segoe UI', sans-serif;
            color: #f0f6fc;
        }
        h1 {
            color: #ffd43b;
            text-align: center;
        }
        .arena-card {
            background-color: #161b22;
            padding: 20px;
            margin-bottom: 20px;
            border-radius: 10px;
            box-shadow: 0 0 15px #ffd43b66;
        }
        .arena-card strong {
            color: #ffd43b;
        }
        ul.challenges {
            list-style-type: disc;
            margin-left: 20px;
            color: #c9d1d9;
        }
        ul.challenges li {
            margin-bottom: 15px;
        }
        ul.challenges li em {
            color: #8b949e;
        }
        .btn-eliminar {
            background-color: #ffd43b;
            color: #000;
            border: none;
            padding: 8px 20px;
            font-weight: bold;
            cursor: pointer;
            border-radius: 5px;
            margin-top: 10px;
            transition: background-color 0.3s ease;
        }
        .btn-eliminar:hover {
            background-color: #ffdd55;
        }
        a.volver-menu {
            display: inline-block;
            margin-top: 30px;
            padding: 10px 25px;
            background-color: #ffd43b;
            color: #000;
            font-weight: bold;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        a.volver-menu:hover {
            background-color: #ffdd55;
        }
        .btn-modificar {
            background-color: #4caf50;
            color: #fff;
            border: none;
            padding: 8px 20px;
            font-weight: bold;
            cursor: pointer;
            border-radius: 5px;
            margin-top: 10px;
            transition: background-color 0.3s ease;
        }
        .btn-modificar:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body class="content">
    <h1>Arenas Registradas</h1>

    <s:iterator value="arenas" var="arena">
        <div class="arena-card">
            <strong>Nombre:</strong> <s:property value="#arena.name" /><br/>
            <strong>Descripción:</strong> <s:property value="#arena.description" /><br/>
            <strong>Fecha:</strong> <s:property value="#arena.date" /><br/>

            <strong>Challenges:</strong>
            <ul class="challenges">
                <s:if test="#arena.challenges == null || #arena.challenges.isEmpty()">
                    <li><em>No hay challenges</em></li>
                </s:if>
                <s:iterator value="#arena.challenges" var="ch">
                    <li>
                        <strong>Nombre:</strong> <s:property value="#ch.name" /><br/>
                        <strong>Dificultad:</strong> <s:property value="#ch.difficulty" /><br/>
                        <strong>Reglas:</strong> <s:property value="#ch.rules" />
                    </li>
                </s:iterator>
            </ul>

            <form action="eliminarArena" method="post">
                <input type="hidden" name="arenaId" value="<s:property value='#arena.id' />" />
                <input type="submit" value="Eliminar" class="btn-eliminar" onclick="return confirm('¿Eliminar esta arena?');" />
                
            </form>
            <form action="mostrarEditarArena" method="get" style="display:inline; margin-left: 10px;">
                <input type="hidden" name="id" value="<s:property value='#arena.id' />" />
                <input type="submit" value="Modificar" class="btn-modificar" />
            </form>
        </div>
    </s:iterator>

    <a href="home.jsp" class="volver-menu">Volver al menú</a>
</body>
</html>
