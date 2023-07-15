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

    static public void printBlock(String... lines) {
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
