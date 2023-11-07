package org.example.concurrenciaBasica.wait;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // Primer ejemplo
        Thread hilo = new Thread(() -> {for (int i = 0; i <= 5; i++){
            System.out.println("Interaccion que estoy " + i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Me han interrumpido");
            }
        }
        });
        hilo.start();

        hilo.join(); // Espera a que el hilo termine antes de continuar
        System.out.println("My name is " + Thread.currentThread().getName()
                + " state: " + Thread.currentThread().getState() +"!");
    }
}
