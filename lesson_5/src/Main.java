public class Main {

    public static void main(String[] args) {

        String[][] validMatrix = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        String[][] invalidDataMatrix = {
                {"1", "2", "3", "4"},
                {"5", "ABC", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        String[][] invalidSizeMatrix = {
                {"1", "2", "3"},
                {"5", "6", "7"}
        };

        System.out.println("Валидный массив");
        testArray(validMatrix);

        System.out.println("\nМассив с неверными данными");
        testArray(invalidDataMatrix);

        System.out.println("\nМассив неверного размера");
        testArray(invalidSizeMatrix);

        System.out.println("\nГенерация и поимка ArrayIndexOutOfBoundsException");
        demoArrayIndexOutOfBounds();
    }

    public static int processArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array == null || array.length != 4) {
            throw new MyArraySizeException("Размер массива должен быть равен 4");
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null || array[i].length != 4) {
                throw new MyArraySizeException(
                        String.format("Размер строки %d должен быть равен 4", i)
                );
            }
        }

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j, e);
                }
            }
        }

        return sum;
    }

    private static void testArray(String[][] array) {
        try {
            int result = processArray(array);
            System.out.println("Сумма элементов массива равна: " + result);
        } catch (MyArraySizeException e) {
            System.out.println("Поймано MyArraySizeException: " + e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println("Поймано MyArrayDataException: " + e.getMessage());
            System.out.println("Координаты ошибки: строка " + e.getRow() + ", столбец " + e.getCol());
        }
    }

    public static void demoArrayIndexOutOfBounds() {
        int[] numbers = {1, 2, 3};
        try {
            int element = numbers[5];
            System.out.println("Элемент: " + element);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Успешно перехвачено ArrayIndexOutOfBoundsException: " + e.toString());
        }
    }
}