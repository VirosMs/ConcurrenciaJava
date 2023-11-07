package org.example.concurrenciaBasica.allthreads;


public class Main {
    public static void main(String[] args) {
        Thread.getAllStackTraces().keySet().forEach(t -> {
            System.out.println(t.getId() + " " + t.getName() + " " + t.getState());
        });
    }
}