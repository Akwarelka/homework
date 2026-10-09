package task2;

import java.util.*;

public class PhoneBook {

    private final Map<String, Set<String>> phoneBook;

    public PhoneBook() {
        this.phoneBook = new HashMap<>();
    }

    public void add(String surname, String phoneNumber) {
        phoneBook.computeIfAbsent(surname, k -> new HashSet<>()).add(phoneNumber);
    }

    public Set<String> get(String surname) {
        return phoneBook.getOrDefault(surname, Collections.emptySet());
    }

    public static void main(String[] args) {
        PhoneBook book = new PhoneBook();

        book.add("Иванов", "+7-999-111-22-33");
        book.add("Петров", "+7-999-222-33-44");
        book.add("Сидоров", "+7-999-333-44-55");
        book.add("Иванов", "+7-999-444-55-66");
        book.add("Смирнов", "+7-999-555-66-77");
        book.add("Петров", "+7-999-666-77-88");
        book.add("Кузнецов", "+7-999-777-88-99");
        book.add("Соколов", "+7-999-888-99-00");

        System.out.println("Телефоны Иванова: " + book.get("Иванов"));
        System.out.println("Телефоны Петрова: " + book.get("Петров"));
        System.out.println("Телефон Сидорова: " + book.get("Сидоров"));
        System.out.println("Телефон Смирнова: " + book.get("Смирнов"));
        System.out.println("Телефон Кузнецова: " + book.get("Кузнецов"));
        System.out.println("Телефон Соколова: " + book.get("Соколов"));
    }
}
