class MyArrayDataException extends Exception {
    private final int row;
    private final int col;

    public MyArrayDataException(int row, int col, Throwable cause) {
        super(String.format("Ошибка преобразования данных в ячейке [%d][%d]", row, col), cause);
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
