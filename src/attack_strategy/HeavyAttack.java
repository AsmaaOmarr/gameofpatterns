package attack_strategy;
import utlis.GameConstants;

// Concrete Strategy - Heavy Attack
public class HeavyAttack implements AttackStrategy {
    @Override
    public int attack() {
        return GameConstants.heavyAttackDamage;
    }
}
