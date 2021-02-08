# BonitaSIE

[//]: # (https://github.com/fenrosus/BonitaSIE.git)

Proyecto de ejemplo en Bonita BPM para la asignatura **"Sistemas de Información Empresariales"** de 3er curso del Grado en Ingeniería Informática - Tecnologías Informáticas de la Universidad de Sevilla.

El proyecto pretende servir de base para practicar las diferentes funcionalidades que ofrece la herramienta Bonita BPM mientras se desarrolla un proceso de negocio basado en un contexto que le resulte familiar a los alumnos de la asignatura, basado en la gestión de asignaturas, con profesores y alumnos como actores principales.

En concreto se muestra el proceso de solicitar un cambio de grupo, requiriéndose una validación de dos niveles y con diferentes consideraciones que sirven para practicar distintos tipos de elementos BPMN, scripts, interfaces de usuario, etc.

![Diagrama del proceso para solicitar un cambio de grupo](https://raw.githubusercontent.com/fenrosus/BonitaSIE/master/doc/Diagrama.PNG)

El proyecto no pretende ser completo ni exhaustivo, ya que es un material de trabajo que el alumno debe terminar de desarrollar durante el curso, pero su estudio, el intentar duplicarlo y finalmente completarlo ayudará al alumno a adquirir las competencias deseadas.

El plan de trabajo puede incluir:
* Diseñar y desplegar una organización con grupos, subgrupos, roles, usuarios y membresías.
* Diseñar y desplegar la base de datos de negocio (BDM) con el objeto `CambioGrupo` que sirva para almacenar la información relativa a una solicitud de cambio de grupo.
* Configurar un contrato para el formulario de instanciación, usando la opción "Desde datos" con una variable de negocio declarada previamente, configurando una 'constraint' para que el grupo de origen deba ser distinto del de destino, modificando el script de inicialización creado automáticamente,...
* Crear el formulario de inicializacion, obteniendo la información del usuario y los grupos mediante la API Rest (https://documentation.bonitasoft.com/bonita/7.12/_rest-api), configurando la validación del 'form' (destino <> origen) en el botón 'submit' e insertando un mensaje de error opcional bajo el 'select' tocando CSS,...
* Gestionar la primera validación configurando el contrato y el formulario de la tarea con campos 'solo-lectura', adaptando el contenido del formulario generado automáticamente (añadiendo/eliminando campos, cambiando widgets, etc.),...
* Trabajar con flujos condicionales/por defecto en función de las acciones del usuario (solicitud aceptada/rechazada).
* Configuración de una tarea de servicio que use el conector de envío de correo electrónico a los usuarios que deseemos, usando parámetros de proceso para facilitar la puesta en producción del proceso.
* Crear y configurar notificaciones de aceptación/rechazo usando filtros de actor para que las reciban el usuario iniciador (con la posibilidad de copiar elementos existentes para acelerar el desarrollo).
* Crear un nuevo filtro de actor, desde su definición indicando las entradas necesarias, hasta la implementación que hace uso de ellas y de la API interna (https://documentation.bonitasoft.com/javadoc/api/7.12/index.html) o lo que se requiera para seleccionar los usuarios que deben recibir la tarea (¿quién puede responder a una solicitud?... los alumnos del grupo destino).
* Uso de temporizadores para iniciar una revisión de segundo nivel si se cumple un plazo determinado sin que nadie responda a la solicitud para intercambiar grupos, pudiéndose llevar a cabo el cambio de grupo del solicitante sin intercambio con otro alumno, siempre que sea concedido este cambio 'unilateral'.
* Uso de tareas de script para desarrollar en ellas el cambio efectivo de grupos en la organización mediante los métodos disponibles en la API interna del motor de Bonita (https://documentation.bonitasoft.com/javadoc/api/7.12/index.html), principalmente la `IdentityAPI`.
* Crear y configurar una notificación de proceso completado que sea recibida por el solicitante y por el que aceptó el intercambio (si existe), mostrándose aquí la posibilidad de usar la opción 'Iteración' con una variable List<String> para almacenar el id. de los implicados y generar con ella las instancias necesarias, y un filtro de actor 'Usuario' usando la variable `multiInstanceIterator` en un script para obtener el usuario de cada instancia de la tarea.
* Crear una webapp que sirva para mostrar la lista de solicitudes enviadas o respondidas (widgets `Data table` y `Tab container`), muestre gráficas que resuman esos datos (widget `Chart`) y permita iniciar una nueva solicitud pulsando un botón (usando el widget `Link`).
* Trabajar con Git para compartir el proyecto y trabajar en equipo (https://documentation.bonitasoft.com/bonita/7.12/git-versioning-community-edition y https://documentation.bonitasoft.com/bonita/7.12/workspaces-and-repositories).

Durante todo el desarrollo de la solución surgirán caminos alternativos, problemas que requieran refactorizar, etc. Por ejemplo, puede ser más complicado de lo que parece conseguir que los datos del `Data table` se muestren ordenados descendentemente por fecha (y no por `persistence_id`). Se ha creado una query personalizada en la BDM para intentar conseguirlo, pero no da los resultados deseados... ¿por qué?... ¿qué se puede hacer al respecto?.
