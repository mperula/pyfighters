<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="menu.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>Crear Arena</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        .arena-form {
            max-width: 700px;
            margin: 50px auto;
            background-color: #161b22;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 15px #ffd43b66;
            color: #f0f6fc;
            display: flex;
            flex-direction: column;
            gap: 20px;
        }
        .form-row {
            display: flex;
            align-items: center;
            gap: 20px;
        }
        .form-row label {
            width: 160px;
            text-align: right;
            font-weight: bold;
            flex-shrink: 0;
        }
        .form-row input[type="text"],
        .form-row textarea {
            flex: 1;
            padding: 10px;
            background-color: #0d1117;
            color: #f0f6fc;
            border: 1px solid #30363d;
            border-radius: 5px;
            font-size: 1em;
        }
        .form-row textarea {
            resize: vertical;
            height: 100px;
        }
        .submit-button {
            background-color: #ffd43b;
            color: #000;
            border: none;
            padding: 10px 25px;
            font-weight: bold;
            cursor: pointer;
            border-radius: 5px;
            align-self: center;
            font-size: 1.1em;
        }
        .submit-button:hover {
            background-color: #ffdd55;
        }
        body.content {
            margin-left: 230px;
            padding: 30px;
            background-color: #0d1117;
            font-family: 'Segoe UI', sans-serif;
        }
        /* Estilo para mensajes */
        .messages {
            max-width: 700px;
            margin: 0 auto 20px auto;
            padding: 15px;
            border-radius: 5px;
        }
        .error-messages {
            background-color: #ff4c4c;
            color: white;
        }
        .info-messages {
            background-color: #4caf50;
            color: white;
        }

        
    </style>
</head>
<body class="content">
    <h1>Crear Arena</h1>

    <!-- Mostrar mensajes de error de acción -->
    <s:if test="hasActionErrors()">
        <div class="messages error-messages">
            <s:actionerror />
        </div>
    </s:if>

    <!-- Mostrar errores de campo -->
    <s:if test="hasFieldErrors()">
        <div class="messages error-messages">
            <s:fielderror />
        </div>
    </s:if>

    <!-- Mostrar mensajes de éxito -->
    <s:if test="hasActionMessages()">
        <div class="messages info-messages">
            <s:actionmessage />
        </div>
    </s:if>

    <s:form action="createArena" method="post" cssClass="arena-form">
        <div class="form-row">
            <label for="name">Nombre:</label>
            <input type="text" name="name" id="name" value="<s:property value='name'/>"/>
        </div>

        <div class="form-row">
            <label for="description">Descripción:</label>
            <textarea name="description" id="description"><s:property value='description'/></textarea>
        </div>

        <div class="form-row">
            <label for="date">Fecha (YYYY-MM-DD):</label>
            <input type="text" name="date" id="date" value="<s:property value='date'/>"/>
        </div>

        <input type="submit" value="Crear Arena" class="submit-button" />
    </s:form>
    <s:if test="hasActionMessages()">
        <script type="text/javascript">
            alert('<s:property value="actionMessages[0]" escape="true" />');
            window.location.href = 'home.jsp';
        </script>
    </s:if>

</body>
</html>
