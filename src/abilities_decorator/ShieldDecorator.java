package abilities_decorator;

// ShieldDecorator.java
public class ShieldDecorator implements PlayerAbility {
    private PlayerAbility playerAbility;

    public ShieldDecorator(PlayerAbility playerAbility) {
        this.playerAbility = playerAbility;
    }

    @Override
    public int modifyDefense(int damage) {
        // Reduce damage by half 
        return playerAbility.modifyDefense(damage) / 2;
    }
}
