<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="menu.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>Editar Arena</title>
    <style>
        body {
            background-color: #0d1117;
            color: #f0f6fc;
            font-family: 'Segoe UI', sans-serif;
            padding: 30px;
        }

        h1 {
            color: #ffd43b;
            text-align: center;
        }

        .form-section, .challenge-section {
            background-color: #161b22;
            padding: 20px;
            margin-bottom: 20px;
            border-radius: 10px;
            box-shadow: 0 0 15px #ffd43b66;
            max-width: 800px;
            margin-left: auto;
            margin-right: auto;
        }

        .form-row {
            display: flex;
            align-items: center;
            gap: 15px;
            margin-top: 10px;
        }

        .form-row label {
            width: 150px;
            text-align: right;
            color: #c9d1d9;
            flex-shrink: 0;
        }

        .form-row input[type="text"],
        .form-row textarea {
            flex: 1;
            padding: 8px;
            background-color: #0d1117;
            border: 1px solid #30363d;
            border-radius: 5px;
            color: #f0f6fc;
            display: inline-block;
        }

        .btn-submit {
            background-color: #ffd43b;
            color: #000;
            border: none;
            padding: 10px 25px;
            font-weight: bold;
            cursor: pointer;
            border-radius: 5px;
            margin-top: 20px;
            transition: background-color 0.3s ease;
        }

        .btn-submit:hover {
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
    </style>
</head>
<body>
    <h1>Editar Arena</h1>

    <s:actionerror />
    <s:fielderror />

    <s:form action="guardarArenaEditada" method="post" theme="simple">
        <div class="form-section">
            <s:hidden name="id" theme="simple" />

            <div class="form-row">
                <label for="name">Nombre:</label>
                <s:textfield name="name" id="name" theme="simple" />
            </div>

            <div class="form-row">
                <label for="description">Descripción:</label>
                <s:textarea name="description" id="description" rows="3" theme="simple" />
            </div>

            <div class="form-row">
                <label for="date">Fecha (dd/MM/yyyy):</label>
                <s:textfield name="date" id="date" theme="simple" />
            </div>
        </div>

        <h2 style="text-align:center;">Challenges</h2>

        <s:if test="challenges == null || challenges.size() == 0">
            <div class="challenge-section">
                <div class="form-row">
                    <label for="challenges[0].name">Nombre:</label>
                    <s:textfield name="challenges[0].name" id="challenges[0].name" theme="simple" />
                </div>
                <div class="form-row">
                    <label for="challenges[0].difficulty">Dificultad:</label>
                    <s:textfield name="challenges[0].difficulty" id="challenges[0].difficulty" theme="simple" />
                </div>
                <div class="form-row">
                    <label for="challenges[0].rules">Reglas:</label>
                    <s:textarea name="challenges[0].rules" id="challenges[0].rules" rows="3" theme="simple" />
                </div>
            </div>
        </s:if>

        <s:iterator value="challenges" var="challenge" status="stat">
            <div class="challenge-section">
                <s:hidden name="challenges[%{#stat.index}].id" value="%{#challenge.id}" theme="simple" />

                <div class="form-row">
                    <label for="challenges[%{#stat.index}].name">Nombre:</label>
                    <s:textfield name="challenges[%{#stat.index}].name"
                                 id="challenges[%{#stat.index}].name"
                                 value="%{#challenge.name}" theme="simple" />
                </div>

                <div class="form-row">
                    <label for="challenges[%{#stat.index}].difficulty">Dificultad:</label>
                    <s:textfield name="challenges[%{#stat.index}].difficulty"
                                 id="challenges[%{#stat.index}].difficulty"
                                 value="%{#challenge.difficulty}" theme="simple" />
                </div>

                <div class="form-row">
                    <label for="challenges[%{#stat.index}].rules">Reglas:</label>
                    <s:textarea name="challenges[%{#stat.index}].rules"
                                id="challenges[%{#stat.index}].rules"
                                rows="3" theme="simple"><s:property value="#challenge.rules" /></s:textarea>
                </div>
            </div>
        </s:iterator>

        <div style="text-align: center;">
            <input type="submit" value="Guardar Cambios" class="btn-submit" />
        </div>
    </s:form>

    <div style="text-align: center;">
        <a href="listArenas" class="volver-menu">Volver al menú</a>
    </div>
</body>
</html>
