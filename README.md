# Davinci Concurrente + Robótica Educativa

DaVinci Concurrente es un lenguaje orientado a la enseñanza de la programación estructurada para fortalecer los cimientos base de su aprendizaje, tanto en la etapa inicial de la programación secuencial como en la introducción a los conceptos básicos de la programación concurrente.
Permite la visualización de la ejecución a través de una ciudad en la cual se puede controlar uno o más robots. Cada robot puede realizar diferentes acciones (por ejemplo tomar flores en la intersección que se encuentra).

Caracteristicas
- Lenguaje de programación en castellano
- Enseñanaza de programación estructurada
- Enseñanaza de programación concurrente
- Entorno de enseñanaza programación multiplataforma
- Metáforas simples y amigabes


A modo de introducción podemos citar las principales características del intérprete producto de la implementación del lenguaje:

- La ejecución de un programa podrá ser visualizada a través de una ciudad, robots y otros objetos. Estos objetos son flores, papeles y obstáculos. El robot podrá interactuar con éstos depositándolos, tomándolos o esquivándolos dependiendo cual sea el caso.
- El lenguaje soporta la resolución de problemas a través de algoritmos concurrentes mediante el uso de estructuras, tipos de datos y primitivas definidas a tal fin.
- Se puede seleccionar un planificador de ejecución sobre un conjunto de planificadores previamente definidos e implementados.
- La cantidad y ubicación inicial de flores y papeles en la ciudad se pueden definir previa a la ejecución del programa, ya sea de manera aleatoria, manual o una combinación de ambas.
- Los mensajes de error. tanto sintáctico como semántico, son fáciles de interpretar.
- La cantidad de robots ejecutándose, en una “aparente” simultaneidad, estará definida por las características intrínsecas del problema a resolver.
- Un programa puede contener cero o más robots.

Otras características importantes del lenguaje son las siguientes:

- Implementación multiplataforma
- Enriquecimiento de los mensajes de error en tiempo de compilación y de ejecución
- Incorporación del tipo de datos cadena de caracteres
- Lectura de variables en tiempo de ejecución
- Incorporación de un conjunto de primitivas (números aleatorios, conversión texto a número y número a texto, etc.)
- Eliminación de las restricciones de sangrado para delimitar los bloques de código, reemplazándolas por las palabras clave comenzar y fin.
- Aceptación de una distribución de flores, papeles y obstáculos definida por el alumno y posibilidad de conservar la misma para futuras ejecuciones.


Las extensiones principales para la concurrencia consisten en:

- Incorporación del tipo de dato abstracto semáforo, tanto general como binario. Los semáforos permiten resolver los problemas habituales de la concurrencia. Sobre este tipo de dato se puede operar exclusivamente a través de las primitivas iniciarSemaforo, esperar y avisar.
- Incorporación de los planificadores de corto plazo más comunes (FIFO, round robin, aleatorio). Si bien un programa concurrente debe funcionar correctamente con independencia del tipo planificador que utilice el sistema operativo, la posibilidad de elegir entre distintos planificadores permite apreciar de forma práctica como influyen en la ejecución.
- Incorporación de la posibilidad de manipular la secuencia lógica de ejecución. La concurrencia introduce el problema del no determinismo. Es por ello que se considera imprescindible contar con algún mecanismo que permita tanto la reproducción exacta de una ejecución concurrente que ha terminado (en forma correcta o incorrecta), como la posibilidad de ejecutar trazas definidas por el usuario (“trazas forzadas”) que lleven a situaciones de error y que, en función del no determinismo, pueden no producirse aún cuando se realicen un número elevado de ejecuciones del programa.
