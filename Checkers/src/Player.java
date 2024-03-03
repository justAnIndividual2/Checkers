import java.util.ArrayList;

public class Player {
    // in case of wanting to add another level of difficality
    private static int[] computerLevels = { 1, 2 };
    private String name;
    private boolean isComputer = false;
    private int computerLevel;
    private static String[] icons = { "X", "O" };
    private String icon;
    private boolean isX;
    private ArrayList<Piece> pieces = new ArrayList<Piece>();

    public Player(String name, boolean isX) {
        this.name = name;
        this.isX = isX;
        if (isX) {
            icon = icons[0];
            for (int i = 5; i < 8; i++) {
                if (i % 2 == 0) {
                    for (int j = 0; j < 8; j++) {
                        if (j % 2 != 0) {
                            Piece piece = new Piece(i, j, isX);
                            pieces.add(piece);
                        }
                    }
                }
                if (i % 2 != 0) {
                    for (int j = 0; j < 8; j++) {
                        if (j % 2 == 0) {
                            Piece piece = new Piece(i, j, isX);
                            pieces.add(piece);
                        }
                    }
                }
            }
        } else {
            icon = icons[1];
            for (int i = 0; i < 3; i++) {
                if (i % 2 == 0) {
                    for (int j = 0; j < 8; j++) {
                        if (j % 2 != 0) {
                            Piece piece = new Piece(i, j, isX);
                            pieces.add(piece);
                        }
                    }
                }
                if (i % 2 != 0) {
                    for (int j = 0; j < 8; j++) {
                        if (j % 2 == 0) {
                            Piece piece = new Piece(i, j, isX);
                            pieces.add(piece);
                        }
                    }
                }
            }
        }
    }

    public Player(String name, int computerLevel, boolean isX) {
        this.name = name;
        isComputer = true;
        this.computerLevel = computerLevel;
        this.isX = isX;
        if (isX) {
            icon = icons[0];
            for (int i = 5; i < 8; i++) {
                if (i % 2 == 0) {
                    for (int j = 0; j < 8; j++) {
                        if (j % 2 != 0) {
                            Piece piece = new Piece(i, j, isX);
                            pieces.add(piece);
                        }
                    }
                }
                if (i % 2 != 0) {
                    for (int j = 0; j < 8; j++) {
                        if (j % 2 == 0) {
                            Piece piece = new Piece(i, j, isX);
                            pieces.add(piece);
                        }
                    }
                }
            }
        } else {
            icon = icons[1];
            for (int i = 0; i < 3; i++) {
                if (i % 2 == 0) {
                    for (int j = 0; j < 8; j++) {
                        if (j % 2 != 0) {
                            Piece piece = new Piece(i, j, isX);
                            pieces.add(piece);
                        }
                    }
                }
                if (i % 2 != 0) {
                    for (int j = 0; j < 8; j++) {
                        if (j % 2 == 0) {
                            Piece piece = new Piece(i, j, isX);
                            pieces.add(piece);
                        }
                    }
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public boolean getIsX() {
        return isX;
    }

    public boolean getIsComputer() {
        return isComputer;
    }

    public int getComputerLevel() {
        if (isComputer) {
            return computerLevel;
        }
        return 0;
    }

    public String getIcon() {
        return icon;
    }

    public ArrayList<Integer> getPiecesByNumber() {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (Piece piece : pieces) {
            numbers.add(piece.getNumber());
        }
        return numbers;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public boolean isPieceValid(int number) {
        for (Piece piece : pieces) {
            if (piece.getNumber() == number) {
                return true;
            }
        }
        return false;
    }

    public Piece findPiece(int number) {
        for (Piece piece : pieces) {
            if (piece.getNumber() == number) {
                return piece;
            }
        }
        return pieces.get(0);
    }
}
