## Projecto 1 - Sistemas Distribuidos

Joel Antonio Lopez Cota

Daniela Alejandra Camacho Molano

## Ideas de solución 

Hacer menu por consola y que tenga opciones para que el usuario mande los inputs segun el escenario que quiera modelar. Mientras se realiza el procedimiento se hacen prints en consola para informarle al usuario como se estan comportando los servidores y cuando termine mostrar las metricas obtenidas.

### Clases
1. Balanceador: id,Politica
2. Solicitud: id
3. Servidor: id, fila de solicitudes, tiempo procesamiento, estado
4. Main: para hacer el menu


### Preguntas:
1. Como se maneja lo de tiempo de respuesta. Depende de la solicutd y su tiempo de CPU y I/O, pero también depende del servidor?
2. Se puede hacer por consola o debe tener interfaz?

## Menú:

1. Escoga el tipo de balanceador (Aleatoria, Round Robin, fila mas corta)
2. Escoga el patrón de trafico (Constante, explosión)
3. Digite el número de solicitudes
