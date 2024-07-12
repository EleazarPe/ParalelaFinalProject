# Proyecto de Simulación de Carretera con Paralelismo y Concurrencia

## Descripción del Proyecto

Para este proyecto nos toco simular 2 intersecciones de carros usando JavaFX y herraminetas de paralelismo en Java.

Para este avanze nos toco hacer el esenario 1. Esta es una interseccion sin semaforos donde el carro que llegue primero a la interseccion pasa primero. El catch es que pueden aparecer ambulancias en las 4 calles. A las ambulancias se les da preferencia, significa que todos los carros alante de la misma deben pasar para que esta pueda cruzar, sin importar el orden previo. Veamos como hicimos esta parte

## Algoritmos de Control

### BlockingQueue

Esta es la parte mas importante del proyecto ya que contiene nuestra lista de carros en orden de llegada. Cada carro tambien conoce la calle en la cual esta asi podemos saber cuales carros debemos mover para que una ambulancia pase. Lo especial del blocking queue es que lo podemos usar entre threads y no causar un data race donde insertamos carros y borramos carros al mismo tiempo

## Estructura del Proyecto

Primero es bueno explicar cada parte porque no utilizamos todas las clases en este momento. Empezando con el folder logica, solo utilizamos Car el cual contiene informacion de su ID, en cual calle esta, y si es un carro de emergencia.

El normal street es nuestro escenario, aqui definimos las calles, botones y toda la logica del escenario. Esta utlima se utiliza en normalStreet.fxml. Viendo los resources, en el folder cars encotramos las imagenes de los 3 tipos de carros y la ambulancia. en direcciones encontramos los botones giroU y reutilizamos linea para el doble derecho, izquierdo y recto.

![image](https://github.com/user-attachments/assets/607f375d-b004-4c98-afa0-8a331b621d86)

## Ejecución del Proyecto

Para ejecutar el proyecto, sigue estos pasos:

Hay 2 maneras de ejecutar el programa.

### Utilizando intellij

- Entrar al proyecto
- seleccionar el archivo MainAplication
- correr el documento

### Utilizando gradle

- Correr el comando "gradle run" en el proyecto.

#### Si todo funciono bien esta pantalla debe salir
![image](https://github.com/user-attachments/assets/95f8f175-bdf5-4e20-8df6-94a282c9cf25)

### Viendolo en accion.

Seleccionar el primero iniciar, mostrara la siguiente patalla.

Para agregar un carro normal seleccionar el boton "Agregar" en la parte arriba izquiera.
Seleccionar Carro o Ambulancia.
Seleccionar uno de los 4 circulos en cada calle.
Repetir

![image](https://github.com/user-attachments/assets/7baa41a1-a5b8-4d86-bd73-9cc347fb7c81)

