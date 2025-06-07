<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="menu.jsp" />

<html>
    <head>
        <title>Scripts Globales</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body class="content">
        <h2 style="text-align:center; color: #ffd43b; margin-top: 10px; text-shadow: 1px 1px 3px #000;">🌍 Scripts Globales</h2>

        <form action="scriptsGlobales" method="get" style="text-align:center; margin-bottom: 20px;">
            <input type="text" name="query" placeholder="Buscar por título o autor..." class="input-field" style="width: 250px;" />
            <input type="submit" value="🔍 Buscar" class="submit-button" />
        </form>

        <form action="limpiarLikes.action" method="post" style="margin-bottom: 20px; text-align: right;">
            <input type="submit" value="🧹 Limpiar Me Gustas" class="submit-button" />
        </form>

        <div class="community-table">

            <p style="color: #ffd43b; text-align: right; margin-right: 10px;">
                Total de scripts disponibles: <strong><s:property value="scripts.size()" /></strong>
            </p>

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Título</th>
                        <th>Autor</th>
                        <th>Creado</th>
                        <th>Acciones</th>
                        <th>Me gusta</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="scripts" var="s">
                        <tr>
                            <td><s:property value="#s.script_id" /></td>
                            <td><s:property value="#s.title" /></td>
                            <td><s:property value="#s.fighterName" /></td>
                            <td><s:property value="#s.created_at" /></td>
                            <td>
                                <!-- Ver Script -->
                                <button class="view-button" title="Ver contenido del script" onclick="verScript('<s:property value="#s.script_id" />')">👁️</button>

                                <!-- Descargar -->
                                <form action="descargarScript.action" method="post" style="display:inline;" title="Descargar este script">
                                    <input type="hidden" name="script_id" value="<s:property value='#s.script_id'/>" />
                                    <input type="submit" value="📥" class="submit-button" style="background-color:#3da9fc;" />
                                </form>
                            </td>
                            <td>
                                <!-- Me gusta -->
                                <form action="likeSession.action" method="post" style="display:inline;" title="Dar me gusta a este script">
                                    <input type="hidden" name="script_id" value="<s:property value='#s.script_id'/>" />
                                    <button type="submit" class="submit-button" style="padding: 4px 12px;">
                                        ❤️ <s:property value="%{#session.likes[#s.script_id] != null ? #session.likes[#s.script_id] : 0}" />
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </div>

        <!-- MODAL -->
        <div id="scriptModal" class="modal-overlay" onclick="cerrarModal(event)">
            <div class="modal-content">
                <span class="close-button" onclick="cerrarModal()">&times;</span>
                <pre id="scriptContent">Cargando...</pre>
            </div>
        </div>

        <script>
            function verScript(id) {
                fetch('verScriptAjax.action?script_id=' + id)
                        .then(res => res.text())
                        .then(code => {
                            document.getElementById('scriptContent').textContent = code;
                            document.getElementById('scriptModal').style.display = 'flex';
                        });
            }

            function cerrarModal(event) {
                if (!event || event.target.className === 'modal-overlay') {
                    document.getElementById('scriptModal').style.display = 'none';
                }
            }
        </script>
    </body>
</html>