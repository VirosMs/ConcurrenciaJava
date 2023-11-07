package org.example.concurrenciaBasica.create.way2lambdas;

public class Main {
    public static void main(String[] args) {

        // Primer ejemplo
    Thread hilo = new Thread(() -> System.out.println("My name is " + Thread.currentThread().getName()
            + " state: " + Thread.currentThread().getState() +"!"));

    hilo.start();

    // Segundo ejemplo
    new Thread(() -> System.out.println("My name is " + Thread.currentThread().getName()
            + " state: " + Thread.currentThread().getState() +"!")).start();

    System.out.println("My name is " + Thread.currentThread().getName()
            + " state: " + Thread.currentThread().getState() +"!");

    }
}
