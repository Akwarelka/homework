import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("Задание 1:");
        printThreeWords();

        System.out.println("\nЗадание 2:");
        checkSumSign();

        System.out.println("\nЗадание 3:");
        printColor();

        System.out.println("\nЗадание 4:");
        compareNumbers();

        System.out.println("\nЗадание 5:");
        System.out.println("Сумма 5 + 7 в пределах [10, 20]: " + isSumBetween10And20(5, 7));

        System.out.println("\nЗадание 6:");
        printNumberSign(-5);

        System.out.println("\nЗадание 7:");
        System.out.println("Число -10 отрицательное? " + isNegative(-10));

        System.out.println("\nЗадание 8:");
        printStringMultipleTimes("Hello, World!", 3);

        System.out.println("\nЗадание 9:");
        System.out.println("2024 год високосный? " + isLeapYear(2024));
        System.out.println("2023 год високосный? " + isLeapYear(2023));
        System.out.println("1900 год високосный? " + isLeapYear(1900));
        System.out.println("2000 год високосный? " + isLeapYear(2000));

        System.out.println("\nЗадание 10:");
        int[] binaryArray = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println("До:    " + Arrays.toString(binaryArray));
        flipBinaryArray(binaryArray);
        System.out.println("После: " + Arrays.toString(binaryArray));

        System.out.println("\nЗадание 11:");
        int[] filledArray = fillArray1To100();
        System.out.println("Первые 10 элементов: " + Arrays.toString(Arrays.copyOf(filledArray, 10)));

        System.out.println("\nЗадание 12:");
        int[] numbers = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("До:    " + Arrays.toString(numbers));
        multiplyLessThanSixByTwo(numbers);
        System.out.println("После: " + Arrays.toString(numbers));

        System.out.println("\nЗадание 13:");
        int[][] matrix = new int[5][5];
        fillDiagonals(matrix);
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("\nЗадание 14:");
        int[] customArray = createArray(7, 42);
        System.out.println(Arrays.toString(customArray));
    }


    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }


    public static void checkSumSign() {
        int a = 10;
        int b = -15;
        int sum = a + b;

        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }


    public static void printColor() {
        int value = 50;

        if (value <= 0) {
            System.out.println("Красный");
        } else if (value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }


    public static void compareNumbers() {
        int a = 8;
        int b = 12;

        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }


    public static boolean isSumBetween10And20(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }


    public static void printNumberSign(int number) {
        if (number >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }
    }


    public static boolean isNegative(int number) {
        return number < 0;
    }


    public static void printStringMultipleTimes(String str, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(str);
        }
    }


    public static boolean isLeapYear(int year) {
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    }


    public static void flipBinaryArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr[i] == 0) ? 1 : 0;
        }
    }


    public static int[] fillArray1To100() {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }


    public static void multiplyLessThanSixByTwo(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }
    }


    public static void fillDiagonals(int[][] matrix) {
        int size = matrix.length;
        for (int i = 0; i < size; i++) {
            matrix[i][i] = 1;
            matrix[i][size - 1 - i] = 1;
        }
    }


    public static int[] createArray(int len, int initialValue) {
        int[] arr = new int[len];
        Arrays.fill(arr, initialValue);
        return arr;
    }
}