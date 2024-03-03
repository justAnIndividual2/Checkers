import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Game {
    private static int BASE_ID = 0;
    private int id;
    private Player[] players = new Player[2];
    private Player playerX;
    private Player playerO;
    private Board board;
    private Player winner;
    private Player loser;
    private LocalDateTime dateTime;
    private LocalDateTime end;
    private int durationInSeconds;

    // Press and play
    public Game(String player1Name, String player2Name) {
        setPlayersRandomly(player1Name, player2Name);
        players[0] = playerX;
        players[1] = playerO;
        board = new Board(playerX, playerO);
        dateTime = LocalDateTime.now();
        id = BASE_ID++;
    }

    // play with computer
    public Game(String playerName, String computerName, int computerLevel) {
        setPlayerAndComputerRandomly(playerName, computerName, computerLevel);
        players[0] = playerX;
        players[1] = playerO;
        board = new Board(playerX, playerO);
        dateTime = LocalDateTime.now();
        id = BASE_ID++;

    }

    private void setPlayersRandomly(String player1Name, String player2Name) {
        int randomNumber = (int) Math.floor(Math.random() * 2);
        if (randomNumber == 0) {
            playerX = new Player(player1Name, true);
            playerO = new Player(player2Name, false);
        } else {
            playerX = new Player(player2Name, true);
            playerO = new Player(player1Name, false);
        }
    }

    private void setPlayerAndComputerRandomly(String playerName, String computerName, int computerLevel) {
        int randomNumber = (int) Math.floor(Math.random() * 2);
        if (randomNumber == 0) {
            playerX = new Player(playerName, true);
            playerO = new Player(computerName, computerLevel, false);
        } else {
            playerX = new Player(computerName, computerLevel, true);
            playerO = new Player(playerName, false);
        }
    }

    public ArrayList<String> returnValidMovesForPiece(Piece piece) {
        ArrayList<String> validMoves = new ArrayList<>();
        if (canCaptureUpperRight(piece) || canCaptureUpperLeft(piece) || canCaptureDownerLeft(piece)
                || canCaptureDownerRight(piece)) {
            validMoves.add("Capture a Piece");
        }
        if (canMoveUpperRight(piece)) {
            validMoves.add("Move Upper Right");
        }
        if (canMoveUpperLeft(piece)) {
            validMoves.add("Move Upper Left");
        }
        if (canMoveDownerLeft(piece)) {
            validMoves.add("Move Downer Left");
        }
        if (canMoveDownerRight(piece)) {
            validMoves.add("Move Downer Right");
        }
        return validMoves;
    }

    public boolean doesValidMoveExist(Player player) {
        for (Piece piece : player.getPieces()) {
            if (canPlay(piece)) {
                return true;
            }
        }
        return false;
    }

    public boolean canPlay(Piece piece) {
        if (canMove(piece) || canCapture(piece)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean canMove(Piece piece) {
        if (canMoveUpperRight(piece) || canMoveUpperLeft(piece) || canMoveDownerLeft(piece)
                || canMoveDownerRight(piece)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean canMoveUpperRight(Piece piece) {
        if (piece.getIsX() == true) {
            boolean isColumnOutOfBound = (piece.getColumn() + 1) > 7;
            boolean isRowOutOfBound = (piece.getRow() - 1) < 0;
            if (isColumnOutOfBound || isRowOutOfBound) {
                return false;
            } else {
                Cell targetedCell = board.getBoard()[piece.getRow() - 1][piece.getColumn() + 1];
                if (targetedCell.isCellEmpty()) {
                    if (canPlayerCapture(playerX)) {
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return false;
                }
            }
        } else {
            if (piece.getIsKing() == false) {
                return false;
            } else {
                boolean isColumnOutOfBound = (piece.getColumn() + 1) > 7;
                boolean isRowOutOfBound = (piece.getRow() - 1) < 0;
                if (isColumnOutOfBound || isRowOutOfBound) {
                    return false;
                } else {
                    Cell targetedCell = board.getBoard()[piece.getRow() - 1][piece.getColumn() + 1];
                    if (targetedCell.isCellEmpty()) {
                        if (canPlayerCapture(playerO)) {
                            return false;
                        } else {
                            return true;
                        }
                    } else {
                        return false;
                    }
                }
            }
        }
    }

    public boolean canMoveUpperLeft(Piece piece) {
        if (piece.getIsX() == true) {
            boolean isColumnOutOfBound = (piece.getColumn() - 1) < 0;
            boolean isRowOutOfBound = (piece.getRow() - 1) < 0;
            if (isColumnOutOfBound || isRowOutOfBound) {
                return false;
            } else {
                Cell targetedCell = board.getBoard()[piece.getRow() - 1][piece.getColumn() - 1];
                if (targetedCell.isCellEmpty()) {
                    if (canPlayerCapture(playerX)) {
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return false;
                }
            }
        } else {
            if (piece.getIsKing() == false) {
                return false;
            } else {
                boolean isColumnOutOfBound = (piece.getColumn() - 1) < 0;
                boolean isRowOutOfBound = (piece.getRow() - 1) < 0;
                if (isColumnOutOfBound || isRowOutOfBound) {
                    return false;
                } else {
                    Cell targetedCell = board.getBoard()[piece.getRow() - 1][piece.getColumn() - 1];
                    if (targetedCell.isCellEmpty()) {
                        if (canPlayerCapture(playerO)) {
                            return false;
                        } else {
                            return true;
                        }
                    } else {
                        return false;
                    }
                }
            }
        }
    }

    public boolean canMoveDownerLeft(Piece piece) {
        if (piece.getIsX() == false) {
            boolean isColumnOutOfBound = (piece.getColumn() - 1) < 0;
            boolean isRowOutOfBound = (piece.getRow() + 1) > 7;
            if (isColumnOutOfBound || isRowOutOfBound) {
                return false;
            } else {
                Cell targetedCell = board.getBoard()[piece.getRow() + 1][piece.getColumn() - 1];
                if (targetedCell.isCellEmpty()) {
                    if (canPlayerCapture(playerO)) {
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return false;
                }
            }
        } else {
            if (piece.getIsKing() == false) {
                return false;
            } else {
                boolean isColumnOutOfBound = (piece.getColumn() - 1) < 0;
                boolean isRowOutOfBound = (piece.getRow() + 1) > 7;
                if (isColumnOutOfBound || isRowOutOfBound) {
                    return false;
                } else {
                    Cell targetedCell = board.getBoard()[piece.getRow() + 1][piece.getColumn() - 1];
                    if (targetedCell.isCellEmpty()) {
                        if (canPlayerCapture(playerX)) {
                            return false;
                        } else {
                            return true;
                        }
                    } else {
                        return false;
                    }
                }
            }
        }
    }

    public boolean canMoveDownerRight(Piece piece) {
        if (piece.getIsX() == false) {
            boolean isColumnOutOfBound = (piece.getColumn() + 1) > 7;
            boolean isRowOutOfBound = (piece.getRow() + 1) > 7;
            if (isColumnOutOfBound || isRowOutOfBound) {
                return false;
            } else {
                Cell targetedCell = board.getBoard()[piece.getRow() + 1][piece.getColumn() + 1];
                if (targetedCell.isCellEmpty()) {
                    if (canPlayerCapture(playerO)) {
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return false;
                }
            }
        } else {
            if (piece.getIsKing() == false) {
                return false;
            } else {
                boolean isColumnOutOfBound = (piece.getColumn() + 1) > 7;
                boolean isRowOutOfBound = (piece.getRow() + 1) > 7;
                if (isColumnOutOfBound || isRowOutOfBound) {
                    return false;
                } else {
                    Cell targetedCell = board.getBoard()[piece.getRow() + 1][piece.getColumn() + 1];
                    if (targetedCell.isCellEmpty()) {
                        if (canPlayerCapture(playerX)) {
                            return false;
                        } else {
                            return true;
                        }
                    } else {
                        return false;
                    }
                }
            }
        }
    }

    public boolean canCapture(Piece piece) {
        if (canCaptureUpperRight(piece) || canCaptureUpperLeft(piece) || canCaptureDownerLeft(piece)
                || canCaptureDownerRight(piece)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean canPlayerCapture(Player player) {
        for (Piece piece : player.getPieces()) {
            if (canCapture(piece)) {
                return true;
            }
        }
        return false;
    }

    private boolean canCaptureUpperRight(Piece piece) {
        if (piece.getIsX() == true) {
            boolean isColumnOutOfBound = (piece.getColumn() + 2) > 7;
            boolean isRowOutOfBound = (piece.getRow() - 2) < 0;
            if (isColumnOutOfBound || isRowOutOfBound) {
                return false;
            } else {
                Cell targetedCell = board.getBoard()[piece.getRow() - 2][piece.getColumn() + 2];
                if (targetedCell.isCellEmpty()) {
                    for (Piece OpponentPiece : playerO.getPieces()) {
                        if ((OpponentPiece.getRow() == piece.getRow() - 1)
                                && (OpponentPiece.getColumn() == piece.getColumn() + 1)) {
                            return true;
                        }
                    }
                    return false;
                } else {
                    return false;
                }
            }
        } else {
            if (piece.getIsKing() == false) {
                return false;
            } else {
                boolean isColumnOutOfBound = (piece.getColumn() + 2) > 7;
                boolean isRowOutOfBound = (piece.getRow() - 2) < 0;
                if (isColumnOutOfBound || isRowOutOfBound) {
                    return false;
                } else {
                    Cell targetedCell = board.getBoard()[piece.getRow() - 2][piece.getColumn() + 2];
                    if (targetedCell.isCellEmpty()) {
                        for (Piece OpponentPiece : playerX.getPieces()) {
                            if ((OpponentPiece.getRow() == piece.getRow() - 1)
                                    && (OpponentPiece.getColumn() == piece.getColumn() + 1)) {
                                return true;
                            }
                        }
                        return false;
                    } else {
                        return false;
                    }
                }
            }
        }
    }

    private boolean canCaptureUpperLeft(Piece piece) {
        if (piece.getIsX() == true) {
            boolean isColumnOutOfBound = (piece.getColumn() - 2) < 0;
            boolean isRowOutOfBound = (piece.getRow() - 2) < 0;
            if (isColumnOutOfBound || isRowOutOfBound) {
                return false;
            } else {
                Cell targetedCell = board.getBoard()[piece.getRow() - 2][piece.getColumn() - 2];
                if (targetedCell.isCellEmpty()) {
                    for (Piece OpponentPiece : playerO.getPieces()) {
                        if ((OpponentPiece.getRow() == piece.getRow() - 1)
                                && (OpponentPiece.getColumn() == piece.getColumn() - 1)) {
                            return true;
                        }
                    }
                    return false;
                } else {
                    return false;
                }
            }
        } else {
            if (piece.getIsKing() == false) {
                return false;
            } else {
                boolean isColumnOutOfBound = (piece.getColumn() - 2) < 0;
                boolean isRowOutOfBound = (piece.getRow() - 2) < 0;
                if (isColumnOutOfBound || isRowOutOfBound) {
                    return false;
                } else {
                    Cell targetedCell = board.getBoard()[piece.getRow() - 2][piece.getColumn() - 2];
                    if (targetedCell.isCellEmpty()) {
                        for (Piece OpponentPiece : playerX.getPieces()) {
                            if ((OpponentPiece.getRow() == piece.getRow() - 1)
                                    && (OpponentPiece.getColumn() == piece.getColumn() - 1)) {
                                return true;
                            }
                        }
                        return false;
                    } else {
                        return false;
                    }
                }
            }
        }
    }

    private boolean canCaptureDownerLeft(Piece piece) {
        if (piece.getIsX() == false) {
            boolean isColumnOutOfBound = (piece.getColumn() - 2) < 0;
            boolean isRowOutOfBound = (piece.getRow() + 2) > 7;
            if (isColumnOutOfBound || isRowOutOfBound) {
                return false;
            } else {
                Cell targetedCell = board.getBoard()[piece.getRow() + 2][piece.getColumn() - 2];
                if (targetedCell.isCellEmpty()) {
                    for (Piece OpponentPiece : playerX.getPieces()) {
                        if ((OpponentPiece.getRow() == piece.getRow() + 1)
                                && (OpponentPiece.getColumn() == piece.getColumn() - 1)) {
                            return true;
                        }
                    }
                    return false;
                } else {
                    return false;
                }
            }
        } else {
            if (piece.getIsKing() == false) {
                return false;
            } else {
                boolean isColumnOutOfBound = (piece.getColumn() - 2) < 0;
                boolean isRowOutOfBound = (piece.getRow() + 2) > 7;
                if (isColumnOutOfBound || isRowOutOfBound) {
                    return false;
                } else {
                    Cell targetedCell = board.getBoard()[piece.getRow() + 2][piece.getColumn() - 2];
                    if (targetedCell.isCellEmpty()) {
                        for (Piece OpponentPiece : playerO.getPieces()) {
                            if ((OpponentPiece.getRow() == piece.getRow() + 1)
                                    && (OpponentPiece.getColumn() == piece.getColumn() - 1)) {
                                return true;
                            }
                        }
                        return false;
                    } else {
                        return false;
                    }
                }
            }
        }
    }

    private boolean canCaptureDownerRight(Piece piece) {
        if (piece.getIsX() == false) {
            boolean isColumnOutOfBound = (piece.getColumn() + 2) > 7;
            boolean isRowOutOfBound = (piece.getRow() + 2) > 7;
            if (isColumnOutOfBound || isRowOutOfBound) {
                return false;
            } else {
                Cell targetedCell = board.getBoard()[piece.getRow() + 2][piece.getColumn() + 2];
                if (targetedCell.isCellEmpty()) {
                    for (Piece OpponentPiece : playerX.getPieces()) {
                        if ((OpponentPiece.getRow() == piece.getRow() + 1)
                                && (OpponentPiece.getColumn() == piece.getColumn() + 1)) {
                            return true;
                        }
                    }
                    return false;
                } else {
                    return false;
                }
            }
        } else {
            if (piece.getIsKing() == false) {
                return false;
            } else {
                boolean isColumnOutOfBound = (piece.getColumn() + 2) > 7;
                boolean isRowOutOfBound = (piece.getRow() + 2) > 7;
                if (isColumnOutOfBound || isRowOutOfBound) {
                    return false;
                } else {
                    Cell targetedCell = board.getBoard()[piece.getRow() + 2][piece.getColumn() + 2];
                    if (targetedCell.isCellEmpty()) {
                        for (Piece OpponentPiece : playerO.getPieces()) {
                            if ((OpponentPiece.getRow() == piece.getRow() + 1)
                                    && (OpponentPiece.getColumn() == piece.getColumn() + 1)) {
                                return true;
                            }
                        }
                        return false;
                    } else {
                        return false;
                    }
                }
            }
        }
    }

    public boolean canPieceCaptureOpponentPiece(Piece piece, Piece opponentPiece) {
        if (canCaptureUpperRight(piece) && (opponentPiece.getRow() == piece.getRow() - 1
                && opponentPiece.getColumn() == piece.getColumn() + 1)) {
            return true;
        } else if (canCaptureUpperLeft(piece) && (opponentPiece.getRow() == piece.getRow() - 1
                && opponentPiece.getColumn() == piece.getColumn() - 1)) {
            return true;
        } else if (canCaptureDownerLeft(piece) && (opponentPiece.getRow() == piece.getRow() + 1
                && opponentPiece.getColumn() == piece.getColumn() - 1)) {
            return true;
        } else if (canCaptureDownerRight(piece) && (opponentPiece.getRow() == piece.getRow() + 1
                && opponentPiece.getColumn() == piece.getColumn() + 1)) {
            return true;
        } else {
            return false;
        }
    }

    public void captureOpponentPiece(Piece piece, Piece opponentPiece) {
        if (opponentPiece.getRow() == piece.getRow() - 1 && opponentPiece.getColumn() == piece.getColumn() + 1) {
            removePiece(opponentPiece);
            movePieceUpperRight(piece);
            movePieceUpperRight(piece);
        } else if (opponentPiece.getRow() == piece.getRow() - 1 && opponentPiece.getColumn() == piece.getColumn() - 1) {
            removePiece(opponentPiece);
            movePieceUpperLeft(piece);
            movePieceUpperLeft(piece);
        } else if (opponentPiece.getRow() == piece.getRow() + 1 && opponentPiece.getColumn() == piece.getColumn() - 1) {
            removePiece(opponentPiece);
            movePieceDownerLeft(piece);
            movePieceDownerLeft(piece);
        } else if (opponentPiece.getRow() == piece.getRow() + 1 && opponentPiece.getColumn() == piece.getColumn() + 1) {
            removePiece(opponentPiece);
            movePiecesDownerRight(piece);
            movePiecesDownerRight(piece);
        }
    }

    // in case of wanting to see the available captures
    private Piece findOpponentPieceUpperRight(Piece piece) {
        if (piece.getIsX() == true) {
            for (Piece opponentPiece : playerO.getPieces()) {
                if ((opponentPiece.getRow() == piece.getRow() - 1)
                        && (opponentPiece.getColumn() == piece.getColumn() + 1)) {
                    return opponentPiece;
                }
            }
            return piece;
        } else {
            for (Piece opponentPiece : playerX.getPieces()) {
                if ((opponentPiece.getRow() == piece.getRow() - 1)
                        && (opponentPiece.getColumn() == piece.getColumn() + 1)) {
                    return opponentPiece;
                }
            }
            return piece;
        }
    }

    private Piece findOpponentPieceUpperLeft(Piece piece) {
        if (piece.getIsX() == true) {
            for (Piece opponentPiece : playerO.getPieces()) {
                if ((opponentPiece.getRow() == piece.getRow() - 1)
                        && (opponentPiece.getColumn() == piece.getColumn() - 1)) {
                    return opponentPiece;
                }
            }
            return piece;
        } else {
            for (Piece opponentPiece : playerX.getPieces()) {
                if ((opponentPiece.getRow() == piece.getRow() - 1)
                        && (opponentPiece.getColumn() == piece.getColumn() - 1)) {
                    return opponentPiece;
                }
            }
            return piece;
        }
    }

    private Piece findOpponentPieceDownerLeft(Piece piece) {
        if (piece.getIsX() == true) {
            for (Piece opponentPiece : playerO.getPieces()) {
                if ((opponentPiece.getRow() == piece.getRow() + 1)
                        && (opponentPiece.getColumn() == piece.getColumn() - 1)) {
                    return opponentPiece;
                }
            }
            return piece;
        } else {
            for (Piece opponentPiece : playerX.getPieces()) {
                if ((opponentPiece.getRow() == piece.getRow() + 1)
                        && (opponentPiece.getColumn() == piece.getColumn() - 1)) {
                    return opponentPiece;
                }
            }
            return piece;
        }
    }

    private Piece findOpponentPieceDownerRight(Piece piece) {
        if (piece.getIsX() == true) {
            for (Piece opponentPiece : playerO.getPieces()) {
                if ((opponentPiece.getRow() == piece.getRow() + 1)
                        && (opponentPiece.getColumn() == piece.getColumn() + 1)) {
                    return opponentPiece;
                }
            }
            return piece;
        } else {
            for (Piece opponentPiece : playerX.getPieces()) {
                if ((opponentPiece.getRow() == piece.getRow() + 1)
                        && (opponentPiece.getColumn() == piece.getColumn() + 1)) {
                    return opponentPiece;
                }
            }
            return piece;
        }
    }

    private void checkForPromotion(Piece piece) {
        if (piece.getIsX() == true) {
            if (piece.getRow() == 0) {
                piece.promoteToKing();
            }
        } else {
            if (piece.getRow() == 7) {
                piece.promoteToKing();
            }
        }
    }

    public void movePieceUpperRight(Piece piece) {
        board.getBoard()[piece.getRow()][piece.getColumn()].emptyCell();
        piece.setRow(piece.getRow() - 1);
        piece.setColumn(piece.getColumn() + 1);
        board.getBoard()[piece.getRow()][piece.getColumn()].fillCell();
        checkForPromotion(piece);
    }

    public void movePieceUpperLeft(Piece piece) {
        board.getBoard()[piece.getRow()][piece.getColumn()].emptyCell();
        piece.setRow(piece.getRow() - 1);
        piece.setColumn(piece.getColumn() - 1);
        board.getBoard()[piece.getRow()][piece.getColumn()].fillCell();
        checkForPromotion(piece);
    }

    public void movePiecesDownerRight(Piece piece) {
        board.getBoard()[piece.getRow()][piece.getColumn()].emptyCell();
        piece.setRow(piece.getRow() + 1);
        piece.setColumn(piece.getColumn() + 1);
        board.getBoard()[piece.getRow()][piece.getColumn()].fillCell();
        checkForPromotion(piece);
    }

    public void movePieceDownerLeft(Piece piece) {
        board.getBoard()[piece.getRow()][piece.getColumn()].emptyCell();
        piece.setRow(piece.getRow() + 1);
        piece.setColumn(piece.getColumn() - 1);
        board.getBoard()[piece.getRow()][piece.getColumn()].fillCell();
        checkForPromotion(piece);
    }

    // computer methods
    public int chooseRandomMoveForPiece(Piece piece) {
        if (canPlay(piece)) {
            int randomPlayIndex = (int) Math.floor(Math.random() * returnValidMovesForPiece(piece).size()) + 1;
            return randomPlayIndex;
        }
        return 0;
    }

    public Piece chooseRandomPiece(Player player) {
        int randomPieceIndex = (int) Math.floor(Math.random() * player.getPieces().size());
        Piece piece = player.getPieces().get(randomPieceIndex);
        return piece;
    }

    public void removePiece(Piece piece) {
        board.getBoard()[piece.getRow()][piece.getColumn()].emptyCell();
        if (piece.getIsX() == true) {
            playerX.getPieces().remove(piece);
        } else {
            playerO.getPieces().remove(piece);
        }
    }

    public int getId() {
        return id;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public Player getWinner() {
        return winner;
    }

    public Player getLoser() {
        return loser;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void setLoser(Player loser) {
        this.loser = loser;
    }

    public void endGame() {
        end = LocalDateTime.now();
        durationInSeconds = (int) Duration.between(dateTime, end).toSeconds();
    }
}

