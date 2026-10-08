class MyArrayDataException extends Exception {
    public MyArrayDataException(int row, int col) {
        super(String.format("Некорректные данные в ячейке [%d][%d]", row, col));
    }
}
