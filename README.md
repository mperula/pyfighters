# PyFighters - Torneos de Programación en Python

> Proyecto académico de la asignatura Integración de Tecnologías (Universidad Pablo de Olavide).  
> Gestión de competiciones de programación en ferias, eventos y aulas... con combates y Python.

---

## ¿Qué es PyFighters?

PyFighters es una plataforma para organizar, gestionar y registrar batallas de código entre luchadores (usuarios que suben scripts en Python). Cada usuario compite en arenas con desafíos distintos, y los resultados se almacenan para generar estadísticas, torneos y logros.

Piensa en ello como el "Street Fighter" de los frikis de Python.

---

## Tecnologías utilizadas

- Java + JSP + Struts 2 para la interfaz web y controladores.
- SOAP para servicios relacionados con usuarios.
- REST para recursos dinámicos como combates, torneos y estadísticas.
- MySQL como base de datos relacional.
- Git + GitHub para control de versiones.

---

## Equipo de desarrollo

| Nombre                      | Entidades Asignadas                      | Tipo de Servicio |
|-----------------------------|------------------------------------------|------------------|
| Maria Pérula                | Usuarios (`Fighters`), Scripts           | SOAP             |
| Miguel Angel Martín         | Arenas, Desafíos                         | REST             |
| Pablo Sánchez               | Combates (`Matches`), Resultados         | REST             |
| Pablo Uceda                 | Torneos, Logros (`Achievements`)         | REST             |

---

## Estructura del Proyecto (Revisar posteriormente) ---

pyfighters/
├── src/                  # Código Java
├── WebContent/           # JSPs, recursos web
├── sql/                  # Scripts SQL
│   ├── pyfighters_db.sql
│   └── inserts.sql
├── docs/                 # Documentación interna
├── .gitignore
└── README.md             # Este archivo

---

## Cómo ejecutar el proyecto (Revisar posteriormente) ---

1. Clona el repositorio:

git clone https://github.com/mperula/pyfighters

2. Importa el proyecto en NetBeans 8.2 como proyecto web con soporte Struts.

3. Importa la base de datos en phpMyAdmin:

   3.1. Abre http://localhost/phpmyadmin

   3.2. Crea una nueva base de datos llamada pyfighters_db

   3.3. Importa el archivo sql/pyfighters_db.sql

   3.4. Luego importa sql/inserts.sql para cargar datos de ejemplo

   3.5. Configura el datasource en web.xml o directamente en código Java (si aplica) con:

        Usuario: root
        Contraseña:
        Host: localhost
        Puerto: 3306

   3.6. Despliega el proyecto en Tomcat (7 u 8) desde NetBeans o XAMPP.

   3.7. ¡Abre el navegador en http://localhost:8080/pyfighters y comienza la batalla!

---

## Funcionalidades principales

- Registro e inicio de sesión de luchadores (SOAP)
- Subida y gestión de scripts de combate
- Gestión de arenas y desafíos especiales (REST)
- Creación de combates y almacenamiento de resultados
- Torneos, clasificación y logros especiales

---

## Extras y mejoras propuestas (A estudiar según el tiempo final de desarrollo) (Revisar posteriormente) ---

- Logros especiales como `Invicto`, `Primer Sangre`, etc.
- Vista de estadísticas globales y por jugador.
- Posible integración futura con ejecución real de scripts vía consola o API.

---

## Modo friki activado

> "En PyFighters no compilas para sobrevivir. Compilas para vencer."
> - Manual del Código Ninja, página 42

---

## Licencia

Este proyecto es educativo. Si lo mejoras, ¡invítanos a una batalla!
