package task2;

public class Main {
    public static void main(String[] args) {
        Shape circle = new Circle(5, "Красный", "Черный");
        Shape rectangle = new Rectangle(4, 6, "Синий", "Белый");
        Shape triangle = new Triangle(3, 4, 5, "Зеленый", "Желтый");

        Shape[] shapes = {circle, rectangle, triangle};

        System.out.println("Характеристики геометрических фигур");
        for (Shape shape : shapes) {
            shape.printInfo();
        }
    }
}
