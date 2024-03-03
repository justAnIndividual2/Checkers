public class Cell {
    private int row;
    private int column;
    private boolean isEmpty = true;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    // public void setRow (int row){
    // this.row = row;
    // }
    // public void setColumn (int column){
    // this.column = column;
    // }
    public boolean isCellEmpty() {
        return isEmpty;
    }

    public void emptyCell() {
        isEmpty = true;
    }

    public void fillCell() {
        isEmpty = false;
    }
}
