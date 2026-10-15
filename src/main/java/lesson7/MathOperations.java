package lesson7;

public class MathOperations {

    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Число должно быть неотрицательным");
        }
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static double triangleArea(double base, double height) {
        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException("Основание и высота должны быть больше 0");
        }
        return 0.5 * base * height;
    }

    public static int add(int a, int b) {
        return a + b;
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Деление на ноль невозможно");
        }
        return (double) a / b;
    }

    public static int compare(int a, int b) {
        return Integer.compare(a, b);
    }
}