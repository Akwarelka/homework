package task2;

interface Shape {
    double getArea();
    double getPerimeter();
    String getFillColor();
    String getBorderColor();

    default void printInfo() {
        System.out.printf("Фигура: %s | Площадь: %.2f | Периметр: %.2f | Цвет фона: %s | Цвет границ: %s%n",
                getClass().getSimpleName(), getArea(), getPerimeter(), getFillColor(), getBorderColor());
    }
}