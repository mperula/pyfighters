<%-- 
    Document   : profile
    Created on : 06-jun-2025, 23:26:25
    Author     : empichi
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="menu.jsp" />

<html>
    <head>
        <title>Mi Perfil</title>
        <link rel="stylesheet" href="css/style.css">
        <style>
            .profile-row {
                display: flex;
                align-items: center;
                margin-bottom: 15px;
                gap: 10px;
            }

            .profile-row label {
                width: 160px;
                font-weight: bold;
                text-align: right;
                color: #ffd43b;
            }

            .profile-row span {
                color: #f0f6fc;
            }

            .profile-display {
                background-color: #161b22;
                padding: 25px;
                margin: 0 auto 30px auto;
                max-width: 600px;
                border-radius: 12px;
                box-shadow: 0 0 15px #ffd43b55;
            }
        </style>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    </head>
    <body class="content">
        <h2 style="text-align:center;">Mi Perfil</h2>
        <div class="profile-display">
            <div class="profile-row">
                <label>ID:</label>
                <span><s:property value="fighter.fighter_id" /></span>
            </div>
            <div class="profile-row">
                <label>Nombre de Usuario:</label>
                <span><s:property value="fighter.username" /></span>
            </div>
            <div class="profile-row">
                <label>Correo:</label>
                <span><s:property value="fighter.email" /></span>
            </div>
            <div class="profile-row">

                <label>Contraseña:</label>
                <span id="verPassword" style="letter-spacing: 2px;">******</span>
                <button type="button" class="submit-button" style="margin-left: 10px;" onclick="togglePassword()">👁</button>
            </div>
            <h3 style="text-align:center; margin-top: 40px;">📊 <s:property value="fighter.username" /></h3>
            <canvas id="estadisticasChart" width="400" height="200" style="display:block; margin: 0 auto;"></canvas>
        </div>

        <s:form action="actualizarPerfil" method="post" cssClass="arena-form">
            <s:hidden name="fighter.fighter_id" value="%{fighter.fighter_id}" />

            <div class="form-row">
                <s:textfield name="username" value="%{fighter.username}" cssClass="input-field" placeholder="Nombre de Usuario"/>
            </div>

            <div class="form-row">
                <s:textfield name="email" value="%{fighter.email}" cssClass="input-field" placeholder="Correo"/>
            </div>

            <div class="form-row">
                <s:password name="password" value="%{fighter.password}" cssClass="input-field" placeholder="Contraseña"/>
            </div>

            <input type="submit" value="Actualizar Perfil" class="submit-button"/>

        </s:form>

        <s:if test="hasActionErrors()">
            <div class="error-message">
                <s:actionerror />
            </div>
        </s:if>

        <script>
            let visible = false;
            function togglePassword() {
                const el = document.getElementById("verPassword");
                if (!visible) {
                    el.textContent = "<s:property value='fighter.password' />";
                } else {
                    el.textContent = "******";
                }
                visible = !visible;
            }

            const ctx = document.getElementById('estadisticasChart').getContext('2d');
            const estadisticasChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: ['Victorias', 'Derrotas', 'Empates'],
                    datasets: [{
                            label: 'Resultados',
                            data: [
            <s:property value="fighter.victories" />,
            <s:property value="fighter.defeats" />,
            <s:property value="fighter.draws" />
                            ],
                            backgroundColor: [
                                'rgba(40, 167, 69, 0.6)', // verde
                                'rgba(220, 53, 69, 0.6)', // rojo
                                'rgba(255, 193, 7, 0.6)'   // amarillo
                            ],
                            borderColor: [
                                'rgba(40, 167, 69, 1)',
                                'rgba(220, 53, 69, 1)',
                                'rgba(255, 193, 7, 1)'
                            ],
                            borderWidth: 1
                        }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true,
                            precision: 0
                        }
                    }
                }
            });
        </script>
    </body>
</html>