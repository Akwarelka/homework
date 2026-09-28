package task1;

class Cat extends Animal {
    private static int catCount = 0;
    private boolean satiety;

    public Cat(String name) {
        super(name, 200, 0);
        this.satiety = false;
        catCount++;
    }

    public static int getCatCount() {
        return catCount;
    }

    public boolean isSatiated() {
        return satiety;
    }

    public void eat(Bowl bowl, int amount) {
        if (satiety) {
            System.out.println(name + " уже сыт и не хочет есть.");
            return;
        }

        if (bowl.decreaseFood(amount)) {
            this.satiety = true;
            System.out.println(name + " покушал " + amount + " еды и теперь сыт!");
        } else {
            System.out.println(name + " хотел покушать " + amount + " еды, но в миске недостаточно (" + bowl.getFood() + "). Кот остался голодным.");
        }
    }
}
