public class Piece {
    private static String[] icons = { "X", "O" };
    private static int BASE_NUMBER = 1;
    private String icon;
    private int number;
    private int row;
    private int column;
    private String name;
    private boolean isX;
    private boolean isKing = false;

    public Piece(int row, int column, boolean isX) {
        this.row = row;
        this.column = column;
        this.isX = isX;
        if (isX) {
            icon = icons[0];
        } else {
            icon = icons[1];
        }
        if (BASE_NUMBER == 13) {
            BASE_NUMBER = 1;
        }
        number = BASE_NUMBER++;
        name = " " + icon + String.format("%02d", number);
    }

    public String geticon() {
        return icon;
    }

    public int getNumber() {
        return number;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean getIsX() {
        return isX;
    }

    public boolean getIsKing() {
        return isKing;
    }

    public String getName() {
        return name;
    }

    public void promoteToKing() {
        isKing = true;
        if (name.equals(" " + icon + String.format("%02d", number))) {
            name = "K" + name.trim();
        }
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
