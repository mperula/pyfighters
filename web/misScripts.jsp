<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="menu.jsp" />

<html>
    <head>
        <title>Mis Scripts</title>
        <link rel="stylesheet" href="css/style.css">

        <script>
            function validarFormulario() {
                const titulo = document.querySelector('input[name="title"]').value;
                const archivo = document.querySelector('input[name="scriptFile"]').value;

                if (!titulo || titulo.length < 3) {
                    alert("El título debe tener al menos 3 caracteres.");
                    return false;
                }
                if (!archivo) {
                    alert("Debes seleccionar un archivo para subir.");
                    return false;
                }
                return true;
            }
        </script>
    </head>

    <body class="content">
        <h2 style="text-align:center; color: #ffd43b; margin-top: 10px; text-shadow: 1px 1px 3px #000;">📜 Mis Scripts</h2>

        <div class="community-table">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Título</th>
                        <th>Creado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="scripts" var="s">
                        <tr>
                            <!-- ID -->
                            <td><s:property value="#s.script_id" /></td>

                            <!-- TÍTULO -->
                            <td>
                                <form action="actualizarScript.action" method="post" style="display:flex; align-items:center; justify-content:center; gap:8px;">
                                    <input type="hidden" name="script_id" value="<s:property value='#s.script_id'/>"/>
                                    <input type="text" name="new_title" value="<s:property value='#s.title'/>"
                                           style="padding:6px 10px; background:#0d1117; color:#f0f6fc; border:1px solid #30363d; border-radius:5px; width:100%; text-align:center;"/>
                            </td>

                            <!-- CREADO -->
                            <td style="text-align:center;">
                                <s:property value="#s.created_at" />
                            </td>

                            <!-- ACCIONES -->
                            <td style="text-align:center;">
                                <input type="submit" value="💾" class="submit-button" style="padding:6px 14px;"/>
                                </form>

                                <form action="descargarScript.action" method="post" style="display:inline;">
                                    <input type="hidden" name="script_id" value="<s:property value='#s.script_id'/>"/>
                                    <input type="submit" value="⬇️" class="submit-button" style="padding:6px 14px; background-color:#3da9fc; color:#fff; margin-top:6px;"/>
                                </form>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>

            <!-- Exportar PDF -->
            <form action="exportarScriptsPDF.action" method="post" style="text-align:center; margin-top:20px;">
                <input type="submit" class="submit-button" value="📄 Exportar PDF" />
            </form>
        </div>

        <br/><br/>

        <!-- Subida de scripts -->
        <div class="upload-script-form">
            <h3 style="text-align:center;">📤 Subir nuevo script</h3>
            <div class="form-group-centered">
                <s:form action="subirScript" method="post" enctype="multipart/form-data" cssClass="script-upload-form" onsubmit="return validarFormulario();">
                    <s:textfield name="title" cssClass="input-field" placeholder="Título del script"/>
                    <s:file name="scriptFile" cssClass="input-field"/>
                    <s:submit value="Subir Script" cssClass="submit-button"/>
                </s:form>
            </div>
        </div>

    </body>
</html>