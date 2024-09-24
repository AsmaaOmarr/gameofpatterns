package attack_strategy;
import utlis.*;

public class LightAttack implements AttackStrategy {
    @Override
    public int attack() {
        return GameConstants.lightAttackDamage;
    }
}