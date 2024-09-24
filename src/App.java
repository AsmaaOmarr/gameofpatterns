import attack_strategy.*;
import notification_observer.*;
import utlis.GameConstants;
import java.util.Random;
import java.util.Scanner;
import abilities_decorator.*;

public class App {
    public static void main(String[] args) throws Exception {

        BattleSystem battleSystem = new BattleSystem(); // observer
        Player player = new Player("Player 1");
        battleSystem.addObserver(player);

        // Player and Enemy Health
        int playerHealth = GameConstants.playerInitialHealth;
        int enemyHealth = GameConstants.enemyInitialHealth;
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        // strategy
        PlayerAttack playerAttack = new PlayerAttack();
        // decorator
        PlayerAbility basicDefense = new BasicDefense();
        PlayerAbility playerDefense = basicDefense;

        // turns counter for random abilities
        int turnCounter = 0;

        System.out.println("Welcome to Battle of Patterns!");
        System.out.println("You and the enemy start with 100 health.");

        while (playerHealth > 0 && enemyHealth > 0) {
            turnCounter++;
            System.out.println("\nTurn Number " + turnCounter);

            if (turnCounter % 2 == 0) {
                // System.out.println("\nRandom ability granted!");
                battleSystem.notifyObservers("Random ability granted!");
                int randomAbility = random.nextInt(3); // Randomly choose one of the three abilities
                switch (randomAbility) {
                    case 0:
                        playerDefense = new IncreasedAttackPowerDecorator(playerDefense);
                        playerAttack.setPlayerAbility(playerDefense);
                        battleSystem.notifyObservers("You received Increased Attack Power!");
                        break;
                    case 1:
                        playerDefense = new ShieldDecorator(playerDefense);
                        battleSystem.notifyObservers("You received a Shield!");
                        // System.out.println("You received a Shield!");
                        break;
                    case 2:
                        playerDefense = new SpeedBoostDecorator(playerDefense, 0.75);
                        battleSystem.notifyObservers("You received a Speed Boost!");
                        // System.out.println("You received a Speed Boost!");
                        break;
                }
            }

            // Player's turn
            System.out.println("\nYour Turn: Choose an action: 1) Attack 2) Defend 3) Dodge");
            int playerChoice = scanner.nextInt();
            switch (playerChoice) {
                case 1: // Attack
                    System.out.println("Choose attack strategy: 1) Light 2) Heavy");
                    int attackChoice = scanner.nextInt();
                    if (attackChoice == 1) {
                        playerAttack.setAttackStrategy(new LightAttack());
                    } else {
                        playerAttack.setAttackStrategy(new HeavyAttack());
                    }
                    int damage = playerAttack.applyAttack();
                    enemyHealth -= damage;
                    battleSystem.notifyObservers("You attacked the enemy and dealt " + damage + " damage!");
                    break;
                case 2: // Defend
                    playerDefense = new ShieldDecorator(basicDefense);
                    battleSystem.notifyObservers("You chose to defend!");
                    break;
                case 3: // Dodge
                    playerDefense = new SpeedBoostDecorator(basicDefense, 0.5);
                    battleSystem.notifyObservers("You chose to dodge!");
                    break;
                default:
                    System.out.println("Invalid action. Try again.");
                    continue;
            }

            // Check if enemy is defeated
            if (enemyHealth <= 0) {
                System.out.println("Congratulations! Winner!");
                break;
            }

            // Enemy's turn
            System.out.println("\nEnemy's Turn:");
            int randomValue = random.nextInt(10); // Generate a random number between 0 and 9
            int enemyAction;

            if (randomValue < 6) { // 60% chance for attack
                enemyAction = 0;
            } else if (randomValue < 8) { // 20% chance for defend
                enemyAction = 1;
            } else { // 20% chance for dodge
                enemyAction = 2;
            }
            // int enemyAction = random.nextInt(3); // Randomly choose between attack,
            // defend, or dodge
            switch (enemyAction) {
                case 0: // Enemy Attack
                    int enemyDamage = random.nextBoolean() ? 10 : 20;// choose 10 or 20
                    // System.out.println("_______________________"+playerDefense.modifyDefense(enemyDamage));
                    playerHealth -= playerDefense.modifyDefense(enemyDamage);
                    battleSystem.notifyObservers("Enemy attacked you and dealt " + enemyDamage + " damage!");
                    break;
                case 1: // Enemy Defend
                    battleSystem.notifyObservers("Enemy chose to defend!");
                    break;
                case 2: // Enemy Dodge
                    if (random.nextBoolean()) { // 50% chance to dodge
                        battleSystem.notifyObservers("Enemy dodged your attack!");
                    } else {
                        battleSystem.notifyObservers("Enemy tried to dodge but failed.");
                    }
                    break;
            }
            // Check if player is defeated
            if (playerHealth <= 0) {
                System.out.println("You were defeated by the enemy. Game over.");
                break;
            }
            System.out.println(playerDefense);
            // Display current health
            System.out.println("\nPlayer Health: " + playerHealth + " | Enemy Health: " + enemyHealth);

            // Remove the defense or dodge decorator after each round
            playerDefense = basicDefense;
        
        }

        scanner.close();

    }
}
