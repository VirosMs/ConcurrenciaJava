package org.example.concurrenciaBasica.async;

public class Main {
    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            System.out.println("Hola");
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Que");
        });

        Thread thread3 = new Thread(() -> {
            System.out.println("Tal");
        });


//        -> asicrono: el hilo principal continua su ejecucion sin esperar a que los hilos secundarios terminen
        System.out.println("Inicio asincrono");
        thread1.start();
        thread2.start();
        thread3.start();
        System.out.println("Fin");


        // -> sincrono : el hilo principal queda bloqueado hasta que los hilos secundarios terminen
        System.out.println("Inicio sincrono");
        thread1.run();
        thread2.run();
        thread3.run();
        System.out.println("Fin");
    }
}
