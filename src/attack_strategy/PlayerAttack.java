package attack_strategy;

// Context
public class PlayerAttack {
    private AttackStrategy attackStrategy;

    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    public int applyAttack() {
        return attackStrategy.attack();
    }
}
