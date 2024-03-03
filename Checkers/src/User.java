public class User {
    private String userName;
    private String passWord;
    private GameManager gameManager = new GameManager();

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public User() {

    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
