package attack_strategy;
import abilities_decorator.*;

// Context
public class PlayerAttack {
    private AttackStrategy attackStrategy;
    private PlayerAbility playerAbility; // Reference to the player's ability (decorator)


    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    public void setPlayerAbility(PlayerAbility playerAbility) {
        this.playerAbility = playerAbility; // Set the player's ability
    }

    public int applyAttack() {
        int baseDamage = attackStrategy.attack(); 

        // return damage after increased attack power
        if (playerAbility instanceof IncreasedAttackPowerDecorator) {
            return ((IncreasedAttackPowerDecorator) playerAbility).increaseAttackPower(baseDamage);
        }

        // return base damage if there is no increased in attack power
        return baseDamage; 
    }
}
