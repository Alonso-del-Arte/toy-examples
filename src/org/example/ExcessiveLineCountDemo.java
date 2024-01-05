package org.example;

public class ExcessiveLineCountDemo {

    public static void main(String[] args) {
        int stop = Integer.parseInt(args[0]);
        for (int i = 1; i <= stop; i++) {
            System.out.println(i);
        }
    }
}
