import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private static Scanner inputReader = new Scanner(System.in);

    public static void StartUI() {
        // LogIn/SignUp
        User user = new User();
        String logInMenu = "1. Log in\n2. Sign up";
        String[] logInOptions = { "1", "2" };
        String logInInput = getValidStringInput(logInMenu, logInOptions, "Invalid input");
        if (logInInput.equals("1")) {
            String userNameInput;
            String passWordInput;
            boolean doesUserNameExist = false;
            boolean isPassWordValid = false;
            do {
                System.out.println();
                System.out.print("UserName: ");
                userNameInput = getStringFromUser();
                System.out.print("PassWord: ");
                passWordInput = getStringFromUser();
                doesUserNameExist = doesUserNameExist(userNameInput);
                if (!doesUserNameExist) {
                    System.out.println("Invalid UserName");
                    continue;
                }
                user = findUser(userNameInput);
                isPassWordValid = isPassWordValid(user, passWordInput);
                if (!isPassWordValid) {
                    System.out.println("Invalid PassWord");
                    continue;
                }
            } while (!doesUserNameExist || !isPassWordValid);
        } else {
            String userNameInput;
            String passWordInput;
            boolean doesUserNameExist = true;
            do {
                System.out.println();
                System.out.print("UserName: ");
                userNameInput = getStringFromUser();
                doesUserNameExist = doesUserNameExist(userNameInput);
                if (doesUserNameExist) {
                    System.out.println("UserName Already Exists");
                    continue;
                }
                System.out.print("PassWord: ");
                passWordInput = getStringFromUser();
                user = new User(userNameInput, passWordInput);
                UserManager.addUser(user);
            } while (doesUserNameExist);
        }
        do{
            System.out.println();
            // Main Menu:1. Play with computer 2. Press and Play 3. History 4. Quit
            String MainMenu = "1. Play with computer\n2. Press and Play\n3. History\n4. Quit";
            String[] mainMenuOptions = { "1", "2", "3", "4"};
            String mainMenuInput = getValidStringInput(MainMenu, mainMenuOptions, "Invalid Input");
            System.out.println();

            // play with computer
            if (mainMenuInput.equals("1")) {
                String[] computerMenuOptions = { "1", "2" };
                String computerMenuINput = getValidStringInput(
                        "1. Easy difficulty\n2. Hard difficulty\n(They are both the same)", computerMenuOptions,
                        "Invalid input");
                startPlayWithComputer1(user);
                // press and play
            } else if (mainMenuInput.equals("2")) {
                startPressAndPlay(user);

                // History
            } else if (mainMenuInput.equals("3")) {
                String historyMenu = "1. Latest games\n2. Oldest games\n3. Wins only\n4. Loses only";
                String[] historyMenuOptions = { "1", "2", "3", "4" };
                String historyMenuInput = getValidStringInput(historyMenu, historyMenuOptions, "Invalid Input");
                ArrayList<Game> sortedGames = user.getGameManager().getGames();
                switch (historyMenuInput) {
                    case "1":
                        sortedGames = user.getGameManager().returnLatestGames();
                        break;
                    case "2":
                        sortedGames = user.getGameManager().returnOldestGames();
                        break;
                    case "3":
                        System.out.print("Player Name: ");
                        String playerName = getStringFromUser();
                        sortedGames = user.getGameManager().returnWinsOnly(playerName);
                        break;
                    case "4":
                        System.out.print("Player Name: ");
                        String playerName2 = getStringFromUser();
                        sortedGames = user.getGameManager().returnLosesOnly(playerName2);
                        break;
                    default:
                        break;
                }
                if (sortedGames.size()==0) {
                    System.out.println("couldn't find any games :(");
                    System.out.println();
                    continue;
                }
                for (int i = 0; i < sortedGames.size(); i++) {
                    Game game = sortedGames.get(i);
                    System.out.println(
                        (i + 1) + ". " + game.getPlayers()[0].getName() + ": X    " + game.getPlayers()[1].getName()
                        + ": O    Winner: " + game.getWinner().getName());
                    System.out.println("Date and time: "+returnFormattedDate(game.getDateTime())+" "+returnFormattedTime(game.getDateTime())
                        + "  Duration: "+returnDuration(game.getDurationInSeconds()));
                }
            }
            else if (mainMenuInput.equals("4")) {
                System.out.println();
                System.out.println("Good Bye!");
                break;
            }
        }while(true);
        // should I delete the line below?
        // inputReader.close();
    }

    private static String returnFormattedDate(LocalDateTime dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDateTime = dateTime.format(dateTimeFormatter);
        return formattedDateTime;
    }

    private static String returnFormattedTime(LocalDateTime dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedDateTime = dateTime.format(dateTimeFormatter);
        return formattedDateTime;
    }

    private static String returnDuration(int durationInSeconds) {
        String duration = String.format("%d:%02d:%02d", durationInSeconds / 3600, (durationInSeconds % 3600) / 60,
                durationInSeconds % 60);
        return duration;
    }

    private static String getStringFromUser() {
        String input = inputReader.nextLine();
        return input;
    }

    private static int getIntFromUser() {
        int input = inputReader.nextInt();
        inputReader.nextLine();
        return input;
    }

    private static String getValidStringInput(String message, String[] inputOptions, String error) {
        System.out.println(message);
        String input = getStringFromUser();
        for (String option : inputOptions) {
            if (input.equals(option)) {
                return input;
            }
        }
        System.out.println(error);
        System.out.println();
        return getValidStringInput(message, inputOptions, error);
    }

    private static int getValidIntInputArrayList(String message, ArrayList<Integer> inputOptions, String error) {
        System.out.println(message);
        int input = getIntFromUser();
        for (int option : inputOptions) {
            if (input == option) {
                return input;
            }
        }
        System.out.println(error);
        System.out.println();
        return getValidIntInputArrayList(message, inputOptions, error);
    }

    private static boolean doesUserNameExist(String userName) {
        if (UserManager.getUsers().size() == 0) {
            return false;
        }
        for (User user : UserManager.getUsers()) {
            if (user.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    private static User findUser(String userName) {
        for (User user : UserManager.getUsers()) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return UserManager.getUsers().get(0);
    }

    private static boolean isPassWordValid(User user, String passWord) {
        if (user.getPassWord().equals(passWord)) {
            return true;
        } else {
            return false;
        }
    }

    public static void startPressAndPlay(User user) {
        System.out.print("Player 1 Name: ");
        String player1Name = getStringFromUser();
        System.out.print("Player 2 Name: ");
        String player2Name = getStringFromUser();
        Game game = new Game(player1Name, player2Name);
        Player playerX = game.getPlayers()[0];
        Player playerO = game.getPlayers()[1];
        Player currentPlayer = playerX;
        Player opponentPlayer = playerO;
        System.out.println(
                playerX.getName() + ": " + playerX.getIcon() + "    " + playerO.getName() + ": " + playerO.getIcon());
        do {
            /* check if current player has any piece left */
            /* check if current player has any moves to play */
            if (!game.doesValidMoveExist(currentPlayer)) {
                game.setWinner(opponentPlayer);
                game.setLoser(currentPlayer);
                continue;
            }
            Piece chosenPiece;
            boolean capturedAPiece;
            // game.getBoard().printBoard();
            // System.out.println("Turn: "+currentPlayer.getIcon());
            do {
                game.getBoard().printBoard();
                System.out.println("Turn: " + currentPlayer.getIcon());
                int chosenPieceNumber = getValidIntInputArrayList("Choose a Piece by number: ",
                        currentPlayer.getPiecesByNumber(), "Invalid Input");
                chosenPiece = currentPlayer.findPiece(chosenPieceNumber);
                ArrayList<String> validMoves = game.returnValidMovesForPiece(chosenPiece);
                printValidMoves(validMoves);
                capturedAPiece = false;
                int input = getIntFromUser();
                if (input == 0) {
                } else if ((input > validMoves.size()) || (input < 0)) {
                    System.out.println("Invalid Input");
                } else if (validMoves.get(input - 1).equals("Capture a Piece")) {
                    int opponentPieceNumber = getValidIntInputArrayList("Choose opponent piece by number: ",
                            opponentPlayer.getPiecesByNumber(), "Invalid Input");
                    Piece opponentPiece = opponentPlayer.findPiece(opponentPieceNumber);
                    if (game.canPieceCaptureOpponentPiece(chosenPiece, opponentPiece)) {
                        game.captureOpponentPiece(chosenPiece, opponentPiece);
                        capturedAPiece = true;
                        break;
                    } else {
                        System.out.println("Can't capture this piece");
                    }
                } else if (validMoves.get(input - 1).equals("Move Upper Right")) {
                    game.movePieceUpperRight(chosenPiece);
                    break;
                } else if (validMoves.get(input - 1).equals("Move Upper Left")) {
                    game.movePieceUpperLeft(chosenPiece);
                    break;
                } else if (validMoves.get(input - 1).equals("Move Downer Left")) {
                    game.movePieceDownerLeft(chosenPiece);
                    break;
                } else if (validMoves.get(input - 1).equals("Move Downer Right")) {
                    game.movePiecesDownerRight(chosenPiece);
                    break;
                }
            } while (true);
            if (game.canCapture(chosenPiece) & capturedAPiece) {
                continue;
            }
            if (currentPlayer.equals(playerX)) {
                currentPlayer = playerO;
                opponentPlayer = playerX;
            } else {
                currentPlayer = playerX;
                opponentPlayer = playerO;
            }
        } while (game.getWinner() == null);
        game.getBoard().printBoard();
        game.endGame();
        System.out.println("Winner: " + game.getWinner().getName());
        // add game to games
        user.getGameManager().addGame(game);

    }

    public static void startPlayWithComputer1(User user) {
        System.out.print("Player Name: ");
        String playerName = getStringFromUser();
        String computerName = "Bob the bot";
        Game game = new Game(playerName, computerName, 1);
        Player playerX = game.getPlayers()[0];
        Player playerO = game.getPlayers()[1];
        Player currentPlayer = playerX;
        Player opponentPlayer = playerO;
        System.out.println(
                playerX.getName() + ": " + playerX.getIcon() + "    " + playerO.getName() + ": " + playerO.getIcon());
        do {
            // -------------------------------Computer-------------------------------------//
            if (currentPlayer.getIsComputer() == true) {
                /* check if current player has any piece left */
                /* check if current player has any moves to play */
                if (!game.doesValidMoveExist(currentPlayer)) {
                    game.setWinner(opponentPlayer);
                    game.setLoser(currentPlayer);
                    continue;
                }
                boolean capturedAPiece;
                Piece chosenPiece;
                game.getBoard().printBoard();
                System.out.println("Turn: " + currentPlayer.getIcon());
                do {
                    chosenPiece = game.chooseRandomPiece(currentPlayer);
                    ArrayList<String> validMoves = game.returnValidMovesForPiece(chosenPiece);
                    capturedAPiece = false;
                    int input = game.chooseRandomMoveForPiece(chosenPiece);
                    if (input == 0) {
                    } else if ((input > validMoves.size()) || (input < 1)) {
                    } else if (validMoves.get(input - 1).equals("Capture a Piece")) {
                        do{
                            Piece opponentPiece = game.chooseRandomPiece(opponentPlayer);
                            if (game.canPieceCaptureOpponentPiece(chosenPiece, opponentPiece)) {
                                game.captureOpponentPiece(chosenPiece, opponentPiece);
                                capturedAPiece = true;
                                break;
                            }
                        }while(!capturedAPiece);
                        break;
                    } else if (validMoves.get(input - 1).equals("Move Upper Right")) {
                        game.movePieceUpperRight(chosenPiece);
                        break;
                    } else if (validMoves.get(input - 1).equals("Move Upper Left")) {
                        game.movePieceUpperLeft(chosenPiece);
                        break;
                    } else if (validMoves.get(input - 1).equals("Move Downer Left")) {
                        game.movePieceDownerLeft(chosenPiece);
                        break;
                    } else if (validMoves.get(input - 1).equals("Move Downer Right")) {
                        game.movePiecesDownerRight(chosenPiece);
                        break;
                    }
                } while (true);
                if (game.canCapture(chosenPiece) && capturedAPiece) {
                    continue;
                }
                if (currentPlayer.equals(playerX)) {
                    currentPlayer = playerO;
                    opponentPlayer = playerX;
                } else {
                    currentPlayer = playerX;
                    opponentPlayer = playerO;
                }
            }
            // ----------------------------------------Player------------------------------------//
            else {
                /* check if current player has any piece left */
                /* check if current player has any moves to play */
                if (!game.doesValidMoveExist(currentPlayer)) {
                    game.setWinner(opponentPlayer);
                    game.setLoser(currentPlayer);
                    continue;
                }
                boolean capturedAPiece;
                Piece chosenPiece;
                do {
                    game.getBoard().printBoard();
                    System.out.println("Turn: " + currentPlayer.getIcon());
                    int chosenPieceNumber = getValidIntInputArrayList("Choose a Piece by number: ",
                            currentPlayer.getPiecesByNumber(), "Invalid Input");
                    chosenPiece = currentPlayer.findPiece(chosenPieceNumber);
                    ArrayList<String> validMoves = game.returnValidMovesForPiece(chosenPiece);
                    printValidMoves(validMoves);
                    capturedAPiece = false;
                    int input = getIntFromUser();
                    if (input == 0) {
                    } else if ((input > validMoves.size()) || (input < 0)) {
                        System.out.println("Invalid Input");
                    } else if (validMoves.get(input - 1).equals("Capture a Piece")) {
                        int opponentPieceNumber = getValidIntInputArrayList("Choose opponent piece by number: ",
                                opponentPlayer.getPiecesByNumber(), "Invalid Input");
                        Piece opponentPiece = opponentPlayer.findPiece(opponentPieceNumber);
                        if (game.canPieceCaptureOpponentPiece(chosenPiece, opponentPiece)) {
                            game.captureOpponentPiece(chosenPiece, opponentPiece);
                            capturedAPiece = true;
                            break;
                        } else {
                            System.out.println("Can't capture this piece");
                        }
                    } else if (validMoves.get(input - 1).equals("Move Upper Right")) {
                        game.movePieceUpperRight(chosenPiece);
                        break;
                    } else if (validMoves.get(input - 1).equals("Move Upper Left")) {
                        game.movePieceUpperLeft(chosenPiece);
                        break;
                    } else if (validMoves.get(input - 1).equals("Move Downer Left")) {
                        game.movePieceDownerLeft(chosenPiece);
                        break;
                    } else if (validMoves.get(input - 1).equals("Move Downer Right")) {
                        game.movePiecesDownerRight(chosenPiece);
                        break;
                    }
                } while (true);
                if (game.canCapture(chosenPiece) && capturedAPiece) {
                    continue;
                }
                if (currentPlayer.equals(playerX)) {
                    currentPlayer = playerO;
                    opponentPlayer = playerX;
                } else {
                    currentPlayer = playerX;
                    opponentPlayer = playerO;
                }
            }
        } while (game.getWinner() == null);
        game.endGame();
        game.getBoard().printBoard();
        System.out.println("Winner: " + game.getWinner().getName());
        // add game to games
        user.getGameManager().addGame(game);

    }

    public static void printValidMoves(ArrayList<String> validMoves) {
        int i = 0;
        for (; i < validMoves.size(); i++) {
            System.out.println((i + 1) + ". " + validMoves.get(i));
        }
        System.out.println("0. Choose another piece");
    }
}
