package abilities_decorator;

// Decorator - Adds Speed ability for dodge
public class SpeedBoostDecorator implements PlayerAbility {
    private PlayerAbility playerAbility;
    private Double chance;

    public SpeedBoostDecorator(PlayerAbility playerAbility, Double chance) {
        this.playerAbility = playerAbility;
        this.chance=chance;
    }

    @Override
    public int modifyDefense(int damage) {
        if (tryDodge()) {
            return 0;
        }
        return playerAbility.modifyDefense(damage);
    }

    public boolean tryDodge() {
        return Math.random() < chance; 
    }
}
