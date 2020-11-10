//Brandon Cao
//I pledge my honor that I have abided by the Stevens Honor System

public class Complexity {

    public static void method1(int n) {
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n ; j++) {
                System.out.println(i + ", " + j);
            }

        }

    }

    public static void method2(int n) {
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n ; j++) {
                for (int k = 1; k < n; k++) {
                    System.out.println(i + ", " + j + ", "+ k);
                }
            }
        }
    }

    public static void method3(int n) {
        for (int i = 1; i < n; i *= 2) {
            System.out.println(i);
        }
    }

    public static void method4(int n) {
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j *= 2) {
                System.out.println(i + ", " + j);
            }
        }
    }

    public static void method5(int n) {
        for (int i = 1; i < n; i *= 2) {
            for (int j = 1; j < n; j *= 2) {
                System.out.println(i + ", " + j);
            }
        }
    }
}
