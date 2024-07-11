# Proyecto de Simulación de Carretera con Paralelismo y Concurrencia

## Descripción del Proyecto

Este proyecto simula una intersección de calles en una carretera utilizando JavaFX para la interfaz gráfica y técnicas de paralelismo y concurrencia para gestionar el flujo de vehículos. Los vehículos pueden venir de cuatro direcciones (norte, sur, este y oeste) y deben seguir las reglas de tráfico para cruzar la intersección. La simulación permite estudiar y analizar el comportamiento de los vehículos en situaciones de tráfico concurrido, utilizando estructuras de datos y algoritmos de control avanzados.

## Algoritmos de Control

### BlockingQueue

En este proyecto, utilizamos una BlockingQueue para gestionar el flujo de los vehículos en la intersección. Una BlockingQueue es una estructura de datos que permite manejar operaciones de espera en caso de que la cola esté vacía (para operaciones de extracción) o llena (para operaciones de inserción). Esto es especialmente útil en escenarios concurrentes donde múltiples threads (hilos) pueden estar accediendo y modificando la cola al mismo tiempo.

En nuestro contexto, la BlockingQueue ayuda a asegurar que los vehículos crucen la intersección en el orden correcto, evitando colisiones y garantizando un flujo de tráfico eficiente.

## Configuración del Proyecto

El proyecto está organizado de la siguiente manera:

- *Carpeta logico*: Contiene la clase Car, que define los atributos y comportamientos de los vehículos.
  - *Clase Car*: Esta clase incluye propiedades como ImageView, Rectangle, id, tipoEmergencia, origen, y destino. También gestiona la lógica de movimiento y orientación de los vehículos en la intersección.
  
- *Main*: Aquí se encuentran los controladores principales que gestionan la interfaz gráfica y la lógica de la simulación.
  - *Controladores*: Manejan la creación y movimiento de los vehículos, así como la interacción con la interfaz de usuario en JavaFX.
  
- *Carpeta Image*: Contiene las imágenes utilizadas en la simulación, como los íconos de los vehículos y otros elementos gráficos.

## Ejecución del Proyecto

Para ejecutar el proyecto, sigue estos pasos:

1. Clona el repositorio en tu máquina local.
   sh
   git clone <URL_DEL_REPOSITORIO>
   
2. Abre el proyecto en tu IDE de preferencia (recomendado: IntelliJ IDEA o Eclipse).
3. Asegúrate de tener configurado JavaFX en tu entorno.
4. Ejecuta la clase principal para iniciar la simulación.
   sh
   ./gradlew run
