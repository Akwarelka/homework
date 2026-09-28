package task1;

public class Main {
    public static void main(String[] args) {
        Dog dog1 = new Dog("Бобик");
        Cat cat1 = new Cat("Барсик");
        Cat cat2 = new Cat("Мурчик");
        Cat cat3 = new Cat("Рыжик");

        dog1.run(150);
        dog1.swim(5);
        cat1.run(250);
        cat1.swim(10);

        System.out.println("\nКормление котов");
        Bowl bowl = new Bowl(25);
        Cat[] cats = {cat1, cat2, cat3};

        for (Cat cat : cats) {
            cat.eat(bowl, 10);
        }

        System.out.println("\nСтатус сытости котов");
        for (Cat cat : cats) {
            System.out.println(cat.name + " сыт: " + cat.isSatiated());
        }

        System.out.println("\nДобавление еды");
        bowl.addFood(15);
        cat3.eat(bowl, 10);
        System.out.println(cat3.name + " сыт: " + cat3.isSatiated());

        System.out.println("\nСтатистика");
        System.out.println("Всего животных: " + Animal.getAnimalCount());
        System.out.println("Всего собак: " + Dog.getDogCount());
        System.out.println("Всего котов: " + Cat.getCatCount());
    }
}