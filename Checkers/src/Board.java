
public class Board {
    private Cell[][] board = new Cell[8][8];
    private Player playerX;
    private Player playerO;

    public Board(Player playerX, Player playerO) {
        this.playerX = playerX;
        this.playerO = playerO;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Cell(i, j);
                Cell currentCell = board[i][j];
                for (int k = 0; k < playerX.getPieces().size(); k++) {
                    Piece currentPiece = playerX.getPieces().get(k);
                    if (currentPiece.getRow() == i && currentPiece.getColumn() == j) {
                        currentCell.fillCell();
                        break;
                    }
                }
                for (int k = 0; k < playerO.getPieces().size(); k++) {
                    Piece currentPiece = playerO.getPieces().get(k);
                    if (currentPiece.getRow() == i && currentPiece.getColumn() == j) {
                        currentCell.fillCell();
                        break;
                    }
                }
            }
        }
    }

    public void printBoard() {
        String[] columnIndex = { "a", "b", "c", "d", "e", "f", "g", "h" };
        System.out.println("  =================================================");
        for (int i = 0; i < board.length; i++) {
            System.out.print(8 - i + " ");
            for (int j = 0; j < board[0].length; j++) {
                Cell currentCell = board[i][j];
                currentCell.emptyCell();
                for (int k = 0; k < playerX.getPieces().size(); k++) {
                    Piece currentPiece = playerX.getPieces().get(k);
                    if (currentPiece.getRow() == i && currentPiece.getColumn() == j) {
                        System.out.print("|" + currentPiece.getName() + " ");
                        currentCell.fillCell();
                    }

                }
                for (int k = 0; k < playerO.getPieces().size(); k++) {
                    Piece currentPiece = playerO.getPieces().get(k);
                    if (currentPiece.getRow() == i && currentPiece.getColumn() == j) {
                        System.out.print("|" + currentPiece.getName() + " ");
                        currentCell.fillCell();
                    }
                }
                if (currentCell.isCellEmpty()) {
                    System.out.print("|     ");
                }
            }
            System.out.println("|");
            System.out.println("  =================================================");
        }
        System.out.print("  ");
        for (int i = 0; i < 8; i++) {
            System.out.print("   " + columnIndex[i] + "  ");
        }
        System.out.println();
    }

    public Cell[][] getBoard() {
        return board;
    }
}
