package lesson7;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MathOperationsTestNGTest {

    private final MathOperations mathOps = new MathOperations();

    @Test(priority = 1)
    public void testFactorial() {
        long result = mathOps.factorial(5);
        System.out.println("Тест вычисления факториала: 5! = " + result);
        Assert.assertEquals(result, 120, "Факториал 5 должен быть равен 120");
    }

    @Test(priority = 2)
    public void testTriangleArea() {
        double area = mathOps.calculateTriangleArea(10, 5);
        System.out.println("Тест вычисления площади треугольника (base=10, height=5): " + area);
        Assert.assertEquals(area, 25.0, 0.0001, "Площадь треугольника должна быть 25.0");
    }

    @Test(priority = 3)
    public void testArithmeticOperations() {
        int sum = mathOps.add(10, 5);
        int diff = mathOps.subtract(10, 5);
        int prod = mathOps.multiply(10, 5);
        double div = mathOps.divide(10, 5);

        System.out.println("Тест арифметических операций:");
        System.out.println("   10 + 5 = " + sum);
        System.out.println("   10 - 5 = " + diff);
        System.out.println("   10 * 5 = " + prod);
        System.out.println("   10 / 5 = " + div);

        Assert.assertEquals(sum, 15);
        Assert.assertEquals(diff, 5);
        Assert.assertEquals(prod, 50);
        Assert.assertEquals(div, 2.0, 0.0001);
    }

    @DataProvider(name = "compareData")
    public Object[][] provideCompareData() {
        return new Object[][] {
                { 10, 5, 1 },
                { 3, 7, -1 },
                { 4, 4, 0 }
        };
    }

    @Test(priority = 4, dataProvider = "compareData")
    public void testCompareNumbers(int a, int b, int expected) {
        int result = mathOps.compare(a, b);
        System.out.println("Тест сравнения чисел: compare(" + a + ", " + b + ") = " + result);
        Assert.assertEquals(result, expected, "Ошибка при сравнении чисел " + a + " и " + b);
    }
}