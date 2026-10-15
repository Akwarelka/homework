package lesson7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class MathOperationsJUnit5Test {
    @BeforeEach
    void startTest(TestInfo testInfo) {
        System.out.println("Старт: " + testInfo.getDisplayName()+ "\n");
    }

    @AfterEach
    void endTest(TestInfo testInfo) {
        System.out.println("Завершен: " + testInfo.getDisplayName() + "\n");
    }

    @Test
    @DisplayName("Тест вычисления факториала")
    void testFactorial() {
        long res0 = MathOperations.factorial(0);
        System.out.println("Результат factorial(0) = " + res0);
        assertEquals(1L, res0);

        long res1 = MathOperations.factorial(1);
        System.out.println("Результат factorial(1) = " + res1);
        assertEquals(1L, res1);

        long res5 = MathOperations.factorial(5);
        System.out.println("Результат factorial(5) = " + res5);
        assertEquals(120L, res5);
    }

    // 2) Тест вычисления площади треугольника
    @Test
    @DisplayName("Тест вычисления площади треугольника")
    void testTriangleArea() {
        double base = 10.0;
        double height = 5.0;
        double area = MathOperations.triangleArea(base, height);

        System.out.println("Основание = " + base + ", Высота = " + height);
        System.out.println("Рассчитанная площадь треугольника = " + area);

        assertEquals(25.0, area, 0.0001);
    }

    // 3) Тест арифметических операций
    @Test
    @DisplayName("Тест арифметических операций")
    void testArithmeticOperations() {
        int a = 10;
        int b = 2;

        int sum = MathOperations.add(a, b);
        System.out.println("Сложение: " + a + " + " + b + " = " + sum);
        assertEquals(12, sum);

        int diff = MathOperations.subtract(a, b);
        System.out.println("Вычитание: " + a + " - " + b + " = " + diff);
        assertEquals(8, diff);

        int prod = MathOperations.multiply(a, b);
        System.out.println("Умножение: " + a + " * " + b + " = " + prod);
        assertEquals(20, prod);

        double div = MathOperations.divide(a, b);
        System.out.println("Деление: " + a + " / " + b + " = " + div);
        assertEquals(5.0, div, 0.0001);
    }

    @ParameterizedTest(name = "Цикл {index}: сравнение {0} и {1}, ожидается result = {2}")
    @DisplayName("Тест сравнения двух целых чисел")
    @CsvSource({
            "5, 10, -1",
            "10, 10, 0",
            "15, 3, 1"
    })
    void testCompareNumbersParameterized(int a, int b, int expected) {
        int result = MathOperations.compare(a, b);

        System.out.println("Сравнение " + a + " и " + b + " -> Получено: " + result + ", Ожидалось: " + expected);
        assertEquals(expected, result);
    }
}
