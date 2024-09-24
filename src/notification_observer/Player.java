package notification_observer;

public class Player implements Observer {
    private String playerName;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public void update(String message) {
        System.out.println(playerName + " received notification: " + message);
    }
}
