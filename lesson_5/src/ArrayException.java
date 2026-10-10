public class ArrayException {

    public static void main(String[] args) {
        String[][] validArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        String[][] invalidSizeArray = {
                {"1", "2", "3"},
                {"4", "5", "6"}
        };

        String[][] invalidDataArray = {
                {"1", "2", "3", "4"},
                {"5", "ERROR", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        testArray(validArray);
        testArray(invalidSizeArray);
        testArray(invalidDataArray);

        System.out.println("\nГенерация ArrayIndexOutOfBoundsException");
        ArrayIndexOutOfBounds();
    }

    public static int sumArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array == null || array.length != 4) {
            throw new MyArraySizeException("Размер массива должен быть 4x4 (неверное количество строк)");
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null || array[i].length != 4) {
                throw new MyArraySizeException("Размер массива должен быть 4x4 (неверное количество столбцов в строке " + i + ")");
            }
        }

        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }

        return sum;
    }

    private static void testArray(String[][] array) {
        try {
            int result = sumArray(array);
            System.out.println("Расчет успешно выполнен. Сумма элементов = " + result);
        } catch (MyArraySizeException e) {
            System.err.println("Ошибка размера массива: " + e.getMessage());
        } catch (MyArrayDataException e) {
            System.err.println("Ошибка данных в массиве: " + e.getMessage());
        }
    }

    public static void ArrayIndexOutOfBounds() {
        int[] numbers = {1, 2, 3};
        try {
            int value = numbers[5];
            System.out.println("Значение: " + value);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймано исключение ArrayIndexOutOfBoundsException: Выход за пределы массива!");
            System.out.println("Детали ошибки: " + e.toString());
        }
    }
}