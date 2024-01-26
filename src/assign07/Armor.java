package assign07;

/**
 * armor with defense value and condition.
 * @author Shawn
 * @version Oct 26, 2023
 */
public class Armor extends Item {
    private int defense;
    private int condition;

    /**
     * Constructs Armor with a name, defense value, and condition.
     * 
     * @param name      The name of the armor.
     * @param defense   The defense value of the armor.
     * @param condition The condition of the armor.
     */
    public Armor(String name, int defense, int condition) {
        super(name);
        this.defense = defense;
        this.condition = condition;
    }

    @Override
    public void merge(Item other) {
        if (other instanceof Armor) {
            Armor otherArmor = (Armor) other;
            this.defense += otherArmor.defense;
            this.condition = Math.max(this.condition, otherArmor.condition);
        }
    }

    @Override
    public int compareTo(Item other) {
        if (!(other instanceof Armor)) {
            return other instanceof Tool ? 1 : -1;
        }
        return Integer.compare(this.defense * this.condition, ((Armor) other).defense * ((Armor) other).condition);
    }

    @Override
    public String toString() {
        return "Armor: " + getName() + " - defense = " + defense + ", condition = " + condition;
    }
}
