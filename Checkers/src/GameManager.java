import java.util.ArrayList;

public class GameManager {
    private ArrayList<Game> games = new ArrayList<>();

    public GameManager() {
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public ArrayList<Game> returnLatestGames() {
        if (games.size() < 2) {
            return games;
        }
        ArrayList<Game> latestGames = games;
        for (int i = 0; i < latestGames.size(); i++) {
            for (int j = i + 1; j < latestGames.size(); j++) {
                if (latestGames.get(i).getDateTime().isAfter(latestGames.get(j).getDateTime())) {
                    Game valueHolder = latestGames.get(i);
                    latestGames.set(i, latestGames.get(j));
                    latestGames.set(j, valueHolder);
                }
            }
        }
        return latestGames;
    }

    public ArrayList<Game> returnOldestGames() {
        if (games.size() < 2) {
            return games;
        }
        ArrayList<Game> oldestGames = games;
        for (int i = 0; i < oldestGames.size(); i++) {
            for (int j = i + 1; j < oldestGames.size(); j++) {
                if (oldestGames.get(i).getDateTime().isBefore(oldestGames.get(j).getDateTime())) {
                    Game valueHolder = oldestGames.get(i);
                    oldestGames.set(i, oldestGames.get(j));
                    oldestGames.set(j, valueHolder);
                }
            }
        }
        return oldestGames;
    }

    public ArrayList<Game> returnWinsOnly(String playerName){
        if (games.size()<1) {
            return games;
        }
        ArrayList<Game> winsOnly = returnLatestGames();
        for (int i = 0; i < winsOnly.size(); i++) {
            if (!winsOnly.get(i).getWinner().getName().equals(playerName)) {
                winsOnly.remove(i);
            }
        }
        return winsOnly;
    }
    public ArrayList<Game> returnLosesOnly(String playerName){
        if (games.size()<1) {
            return games;
        }
        ArrayList<Game> LosesOnly = returnLatestGames();
        for (int i = 0; i < LosesOnly.size(); i++) {
            if (!LosesOnly.get(i).getLoser().getName().equals(playerName)) {
                LosesOnly.remove(i);
            }
        }
        return LosesOnly;
    }
}
