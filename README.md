## Projecto 1 - Sistemas Distribuidos

Joel Antonio Lopez Cota

Daniela Alejandra Camacho Molano

## Ideas de solución 

Hacer menu por consola y que tenga opciones para que el usuario mande los inputs segun el escenario que quiera modelar. Mientras se realiza el procedimiento se hacen prints en consola para informarle al usuario como se estan comportando los servidores y cuando termine mostrar las metricas obtenidas.

Hay que usar threads cada que se genera una nueva solicitud y debe haber un reloj para contar los diferentes tiempos.

### Clases
1. Balanceador: id,Politica
2. Solicitud: id
3. Servidor: id, fila de solicitudes, tiempo procesamiento, estado
4. Main: para hacer el menu
5. GeneradorSolicitudes:

## Menú:

1. Generar solicitudes: escoger el rate (lambda)
2. Realizar balanceamento:
    - Escoga el tipo de balanceador (Aleatoria, Round Robin, fila mas corta)
    - Escoga el archivo de las solicitudes.


Hacer main en balanceador, el hilo principal es el balanceador
se crean 3 hilos más, uno por cada servidor.
Las solicutudes se van mandnado a las filas de los servidores y el sevdiro va procesando al mismo tiempo.
Cuando no hayan más solicitudes se acaba el programa (se pone un contador para el timepo total).
