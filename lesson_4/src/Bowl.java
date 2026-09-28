class Bowl {
    private int food;

    public Bowl(int food) {
        this.food = Math.max(0, food);
    }

    public void addFood(int amount) {
        if (amount > 0) {
            this.food += amount;
            System.out.println("В миску добавили " + amount + " еды. Всего еды: " + this.food);
        }
    }

    public boolean decreaseFood(int amount) {
        if (this.food >= amount) {
            this.food -= amount;
            return true;
        }
        return false;
    }

    public int getFood() {
        return food;
    }
}

