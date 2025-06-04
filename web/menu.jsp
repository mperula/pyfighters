<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/style.css">

<div class="sidebar">
    <div class="menu-title">🐍 PyFighters</div>

    <div class="accordion">
        <div class="accordion-item">
            <button class="accordion-toggle">🧑‍🚀 Fighters</button>
            <div class="accordion-content">
                <a href="#">Registro</a>
                <a href="#">Mi Perfil</a>
            </div>
        </div>

        <div class="accordion-item">
            <button class="accordion-toggle">📜 Scripts</button>
            <div class="accordion-content">
                <a href="#">Subir Script</a>
                <a href="#">Gestionar Scripts</a>
            </div>
        </div>

        <div class="accordion-item">
            <button class="accordion-toggle">📊 Estadísticas y Logros</button>
            <div class="accordion-content">
                <a href="#">Estadísticas</a>
                <a href="#">Logros</a>
            </div>
        </div>

        <div class="accordion-item">
            <button class="accordion-toggle">🏟️ Arenas y Desafíos</button>
            <div class="accordion-content">
                <a href="#">Crear Arena</a>
                <a href="#">Listar Arenas</a>
                <a href="#">Editar/Eliminar Arena</a>
                <a href="#">Desafíos Especiales</a>
            </div>
        </div>

        <div class="accordion-item">
            <button class="accordion-toggle">⚔️ Matches</button>
            <div class="accordion-content">
                <a href="#">Crear Combate</a>
                <a href="#">Eliminar Combate</a>
                <a href="#">Modificar Combate</a>
                <a href="#">Ver todos los Combates</a>
                <a href="#">Buscar Combate</a>
                <a href="#">Crear Resultado</a>
                <a href="#">Modificar resultado</a>
                <a href="#">Eliminar Resultado</a>
                <a href="#">Resultados</a>
                <a href="#">Buscar Resultado</a>
            </div>
        </div>

        <div class="accordion-item">
            <button class="accordion-toggle">🏆 Torneos</button>
            <div class="accordion-content">
                <a href="#">Torneos</a>
                <a href="#">Clasificación</a>
            </div>
        </div>
    </div>

    <!-- Botón de cerrar sesión -->
    <form action="logout" method="post" style="margin-top: auto;">
        <button type="submit" class="logout-button">🔓 Cerrar Sesión</button>
    </form>
</div>

<script>
    document.querySelectorAll(".accordion-toggle").forEach(button => {
        button.addEventListener("click", () => {
            const item = button.parentElement;
            item.classList.toggle("active");
        });
    });
</script>