package abilities_decorator;
// IncreasedAttackPowerDecorator.java
public class IncreasedAttackPowerDecorator implements PlayerAbility {
    private PlayerAbility playerAbility;

    public IncreasedAttackPowerDecorator(PlayerAbility playerAbility) {
        this.playerAbility = playerAbility;
    }

    @Override
    public int modifyDefense(int damage) {
        return playerAbility.modifyDefense(damage); 
    }

    public int increaseAttackPower(int baseDamage) {
        return (int)(baseDamage * 1.5);
    }
}
