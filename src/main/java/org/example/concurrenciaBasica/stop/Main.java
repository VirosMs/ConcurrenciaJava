package org.example.concurrenciaBasica.stop;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Thread hilo = new Thread(() -> {
            for (int i = 0; i < 5; i++){
                System.out.println("Interaccion que estoy " + i);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) { // Capturamos la excepcion
                    System.out.println("Me han interrumpido");
                    return; // Salimos del hilo
                }

//                Otra Opcion para interrumpir un hilo
//                if(Thread.interrupted())  Comprobamos si el hilo ha sido interrumpido
//                    System.out.println("Me han interrumpido");
//                    return;  Salimos del hilo
            }
        });

        hilo.start(); // Iniciamos el hilo

        Thread.sleep(5000L); // Esperamos 5 segundos


        if(hilo.isAlive()) // Comprobamos si el hilo esta vivo
            hilo.interrupt(); // Interrumpimos el hilo
    }
}
