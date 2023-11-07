package org.example.concurrenciaBasica.polling;

import java.util.List;

public class Main {

    static List<String> messages = List.of("Hola", "Que", "Tal", "Estas", "Hoy");

    static long patience = 50000L;

    public static void main(String[] args) throws InterruptedException {

        Thread.currentThread().setName("thread1"); // Le damos un nombre al hilo principal

        long startTime = System.currentTimeMillis(); // Obtenemos el tiempo actual

        Thread thread2 = new Thread(() -> {
            Thread.currentThread().setName("thread2"); // Le damos un nombre al hilo
            print("Starting task");

            try{
                for(String msg : messages){ // Recorremos la lista de mensajes
                    Thread.sleep(4000); // Esperamos 4 segundos
                    print(msg); // Imprimimos el mensaje
                }
                print("Finished tasks");
            }catch (InterruptedException e){ // Capturamos la excepcion
                System.out.println("Thread2 interrupted");
            }
        });
        thread2.start(); // Iniciamos el hilo

        while(thread2.isAlive()){ // Mientras el hilo este vivo hacemos lo siguiente

            print("Continuo esperando");
            thread2.join(1000L); // Esperamos 1 segundo a que el hilo termine

            long endTime = System.currentTimeMillis(); // Obtenemos el tiempo actual
            long elapsedTime = endTime - startTime; // Calculamos el tiempo transcurrido

            if(elapsedTime > patience && thread2.isAlive()){ // Comprobamos si el hilo esta vivo y si ha pasado el tiempo de espera
                print("No quiero esperar mas");
                thread2.interrupt(); // Interrumpimos el hilo
                thread2.join(); // Esperamos a que el hilo termine
            }
        }

        print("Fin del programa");
    }

    /**
     * Imprime un mensaje en la consola
     * @param msg mensaje a imprimir
     */
    static void print(String msg){
        String threadName = Thread.currentThread().getName(); // Obtenemos el nombre del hilo actual
        System.out.format("%s: %s%n", threadName, msg); // Imprimimos el mensaje con el nombre del hilo
    }
}
