package com.pcconstrutor;

import java.math.BigDecimal;
import java.util.Scanner;

public class Console {
    static public int readInt(String prompt) {
        Scanner teclado = new Scanner(System.in);
        System.out.print(prompt);
        return teclado.nextInt();
    }

    static public String readLine(String prompt) {
        Scanner teclado = new Scanner(System.in);
        System.out.print(prompt);
        return teclado.nextLine();
    }

    static public Long readLong(String prompt) {
        Scanner teclado = new Scanner(System.in);
        System.out.print(prompt);
        return teclado.nextLong();
    }

    static public double readDouble(String prompt) {
        Scanner teclado = new Scanner(System.in);
        System.out.print(prompt);
        return teclado.nextDouble();
    }

    static public String stringBlock(String... strings) {
        StringBuilder bloco = new StringBuilder();
        for (String string : strings) {
            bloco.append(string).append("\n");
        }
        return bloco.toString();
    }

    static public void printBlock(String... strings) {
        for (String line : strings) {
            System.out.println(line);
        }
    }

    public static BigDecimal readBigDecimal(String s) {
        Scanner teclado = new Scanner(System.in);
        System.out.print(s);
        return teclado.nextBigDecimal();
    }
}
