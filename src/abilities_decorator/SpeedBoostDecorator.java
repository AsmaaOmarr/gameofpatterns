package abilities_decorator;

// Decorator - Adds Speed ability for dodge
public class SpeedBoostDecorator implements PlayerAbility {
    private PlayerAbility playerAbility;

    public SpeedBoostDecorator(PlayerAbility playerAbility) {
        this.playerAbility = playerAbility;
    }

    @Override
    public int modifyDefense(int damage) {
        return playerAbility.modifyDefense(damage); 
    }

    public boolean tryDodge() {
        return Math.random() < 0.75; 
    }
}
