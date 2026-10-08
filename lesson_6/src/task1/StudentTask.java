package task1;

import java.util.*;

public class StudentTask {

    public static class Student {
        private String name;
        private String group;
        private int course;
        private List<Integer> grades;

        public Student(String name, String group, int course, List<Integer> grades) {
            this.name = name;
            this.group = group;
            this.course = course;
            this.grades = grades != null ? grades : new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public int getCourse() {
            return course;
        }

        public void setCourse(int course) {
            this.course = course;
        }

        public List<Integer> getGrades() {
            return grades;
        }

        public double getAverageGrade() {
            if (grades.isEmpty()) {
                return 0.0;
            }
            int sum = 0;
            for (int grade : grades) {
                sum += grade;
            }
            return (double) sum / grades.size();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return course == student.course &&
                    Objects.equals(name, student.name) &&
                    Objects.equals(group, student.group);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, group, course);
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", group='" + group + '\'' +
                    ", course=" + course +
                    ", avgGrade=" + String.format("%.2f", getAverageGrade()) +
                    '}';
        }
    }

    public static void processStudents(Set<Student> students) {
        if (students == null) return;

        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            double avg = student.getAverageGrade();

            if (avg < 3.0) {
                iterator.remove();
            } else {
                student.setCourse(student.getCourse() + 1);
            }
        }
    }

    public static void printStudents(Set<Student> students, int course) {
        if (students == null) return;

        System.out.println("Студенты, обучающиеся на " + course + " курсе:");
        boolean found = false;
        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println("- " + student.getName());
                found = true;
            }
        }
        if (!found) {
            System.out.println("(нет студентов)");
        }
    }

    public static void main(String[] args) {
        Set<Student> students = new HashSet<>();

        students.add(new Student("Иван Иванов", "БПИ-101", 1, Arrays.asList(4, 5, 3, 4)));
        students.add(new Student("Петр Петров", "БПИ-101", 1, Arrays.asList(2, 3, 2, 2)));
        students.add(new Student("Анна Сидорова", "БПИ-201", 2, Arrays.asList(5, 5, 4, 5)));
        students.add(new Student("Антон Соленко", "БПИ-301", 3, Arrays.asList(3, 3, 2, 3)));
        students.add(new Student("Мария Скворцова", "БПИ-301", 3, Arrays.asList(5, 5, 5, 5)));

        System.out.println("Исходный список:");
        students.forEach(System.out::println);

        processStudents(students);

        System.out.println("\nСписок после обработки:");
        students.forEach(System.out::println);

        System.out.println("\nВывод студентов по курсам:");
        printStudents(students, 2);
        printStudents(students, 3);
        printStudents(students, 4);
    }
}