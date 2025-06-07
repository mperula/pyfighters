# PyFighters - Sistema de Gestión de Torneos de Programación

> Proyecto académico - Universidad Pablo de Olavide  
> Aplicación web para la gestión de combates de programación con scripts en Python.

---

## 🎮 ¿Qué es PyFighters?

**PyFighters** es una plataforma web donde usuarios pueden registrarse como luchadores, subir scripts de Python y competir en combates simulados. La aplicación permite gestionar arenas, desafíos, combates, torneos y logros. Se almacenan estadísticas individuales y se presentan visualmente para crear un entorno competitivo, educativo y divertido.

---

## 🧱 Tecnologías utilizadas

- **Java + JSP** para la capa de presentación.
- **Struts 2** como framework MVC.
- **MySQL** para la persistencia de datos.
- **Chart.js** para visualización de estadísticas.
- **iTextPDF** para exportar scripts en PDF.
- **NetBeans 8.2** y **Tomcat** como entorno de desarrollo.

> ⚠️ No se ha integrado SOAP ni REST aún, aunque se ha planteado para fases posteriores.

---

## 📁 Estructura del proyecto

pyfighters/
├── Web Pages/
│   ├── css/                          # Estilos
│   ├── META-INF/                     # Metadatos de despliegue
│   ├── WEB-INF/                      # web.xml y config Struts
│   ├── *.jsp                         # Páginas web (JSP)
│   │   ├── login.jsp, register.jsp
│   │   ├── menu.jsp, home.jsp
│   │   ├── profile.jsp, comunidad.jsp
│   │   ├── misScripts.jsp, scriptsGlobales.jsp
│   │   ├── Matches.jsp, Results.jsp, etc.
│   │   └── errorPage.jsp
├── src/
│   └── com.myapp.struts/            # Controladores (Actions)
│       ├── dao/                     # Acceso a base de datos (DAOs)
│       └── model/                   # Clases de modelo (POJOs)
├── sql/                             # Scripts SQL (db + inserts)
│   ├── pyfighters_db.sql
│   └── inserts.sql
├── lib/                             # Librerías externas (.jar)
│   └── itextpdf-5.5.13.3.jar
├── README.md
└── .gitignore

---

## 🚀 Cómo ejecutar el proyecto

1. **Clona el repositorio**  
   `git clone https://github.com/mperula/pyfighters`

2. **Importa en NetBeans como proyecto Web Struts 2**  
   (Asegúrate de tener configurado Tomcat 7 u 8)

3. **Configura la base de datos:**
   - Crea una BD llamada `pyfighters_db` en phpMyAdmin
   - Ejecuta `sql/pyfighters_db.sql`

4. **Configura el acceso en tu DAO o contexto:**
   - Usuario: `root`
   - Contraseña:
   - Host: `localhost`, Puerto: `3306`

5. **Agrega dependencias necesarias:**
   - iTextPDF (`lib/itextpdf-5.5.13.3.jar`)
   - Todas las `.jar` requeridas están en `Libraries` del proyecto

6. **Despliega en Tomcat y accede en el navegador:**
   `http://localhost:8080/pyfighters`

---

## ✅ Funcionalidades actuales

- Registro e inicio de sesión de luchadores
- Visualización y edición de perfil
- Gestión de scripts (subida, edición de título, eliminación, descarga como PDF)
- Listado de combates y resultados
- Sistema de estadísticas personales en perfil (gráfico de barras)
- Ranking de la comunidad

---

## 🛠️ Funcionalidades previstas (a futuro)

- CRUD completo para torneos, logros, desafíos especiales
- Sistema de logros automáticos (ej. “Invicto”)
- Integración con ejecución real de código (sandbox Python)
- Exportación de estadísticas en PDF
- Servicios REST y/o SOAP

---

## 👨‍💻 Equipo de Desarrollo

| Nombre                  | Entidades asignadas                   |
|-------------------------|----------------------------------------|
| María Pérula            | Usuarios (`Fighters`) y Scripts        |
| Miguel Ángel Martín     | Arenas y Desafíos                      |
| Pablo Sánchez           | Combates y Resultados (`Matches`)      |
| Pablo Uceda             | Torneos y Logros (`Achievements`)      |

---

## 🧪 Ejemplos de datos de prueba (algunos usuarios)

| Usuario      | Email                    | Contraseña |
|--------------|---------------------------|------------|
| IronPython   | iron@pyfighters.com       | 123456     |
| PyNinja      | ninja@pyfighters.com      | 123456     |
| admin        | admin@pyfighters.com      | admin      |

---

## 📜 Licencia

Este proyecto es exclusivamente académico. Puedes usarlo como referencia para trabajos educativos o desarrollo personal.

---

## 🐍 Frase final

> "Tu código no solo debe compilar. Debe conquistar."
– Manual del Luchador Pythonico
