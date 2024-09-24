package abilities_decorator;

// Concrete Component - Default Defense
public class BasicDefense implements PlayerAbility {
    @Override
    public int modifyDefense(int damage) {
        //no modification
        return damage;  
    }
}
