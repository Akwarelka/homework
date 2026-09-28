public class Main {
    public static void main(String[] args) {

        Product[] productsArray = new Product[5];
        productsArray[0] = new Product("Samsung S25 Ultra", "01.02.2025", "Samsung Corp.", "Korea", 5599.0, true);
        productsArray[1] = new Product("iPhone 16 Pro", "15.09.2024", "Apple Inc.", "USA", 1200.0, false);
        productsArray[2] = new Product("PlayStation 5 Pro", "10.11.2024", "Sony", "Japan", 799.0, true);
        productsArray[3] = new Product("MacBook Air M3", "05.03.2024", "Apple Inc.", "China", 1099.0, false);
        productsArray[4] = new Product("Xiaomi 14", "25.02.2024", "Xiaomi", "China", 699.0, false);

        System.out.println("СПИСОК ТОВАРОВ");
        for (Product product : productsArray) {
            product.printInfo();
        }


        System.out.println("ИНФОРМАЦИЯ ОБ АТТРАКЦИОНАХ");
        Park park = new Park("Парк Горького");


        Park.Attraction rollerCoaster = park.new Attraction("Американские горки", "10:00 - 22:00", 500.0);
        Park.Attraction ferrisWheel = park.new Attraction("Колесо обозрения", "10:00 - 23:00", 400.0);

        rollerCoaster.printAttractionInfo();
        ferrisWheel.printAttractionInfo();
    }
}