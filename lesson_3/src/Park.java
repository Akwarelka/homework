public class Park {
    private String parkName;
    public Park(String parkName) {
        this.parkName = parkName;
    }

    public class Attraction {
        private String attractionName;  // Название аттракциона
        private String workingHours;    // Время работы (например, "10:00 - 22:00")
        private double price;           // Стоимость

        public Attraction(String attractionName, String workingHours, double price) {
            this.attractionName = attractionName;
            this.workingHours = workingHours;
            this.price = price;
        }

        public void printAttractionInfo() {
            System.out.println("Парк: " + parkName);
            System.out.println("Аттракцион: " + attractionName);
            System.out.println("Время работы: " + workingHours);
            System.out.println("Стоимость: " + price + " руб.");
            System.out.println();
        }
    }
}
