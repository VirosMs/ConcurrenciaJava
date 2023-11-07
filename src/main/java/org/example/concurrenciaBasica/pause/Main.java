package org.example.concurrenciaBasica.pause;

public class Main {

    public static void main(String[] args){

        // Primer ejemplo
        Thread hilo = new Thread(() -> {
            for (int i = 0; i <= 5; i++){
                System.out.println("Interaccion que estoy " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Me han interrumpido");
                }
            }
        });
        hilo.start();


        // Segundo ejemplo
        new Thread(() -> {
            for (int i = 0; i <= 5; i++){
                System.out.println("Interaccion que estoy " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Me han interrumpido");
                }
            }
        }).start();

    }
}
