package assign07;

/**
 *magic item with power and cost.
 * 
 * @author Shawn
 * @version Oct 26, 2023
 */
public class Magic extends Item {
    private int power;
    private int cost;

    /**
     * Constructs Magic with a name, power, and cost.
     * 
     * @param name  The name of the magic item.
     * @param power The power of the magic.
     * @param cost  The cost of using the magic.
     */
    public Magic(String name, int power, int cost) {
        super(name);
        this.power = power;
        this.cost = cost;
    }

    @Override
    public void merge(Item other) {
        if (other instanceof Magic) {
            this.power += ((Magic) other).power;
            this.cost += 1;
        }
    }

    @Override
    public int compareTo(Item other) {
        if (!(other instanceof Magic)) {
            return -1;
        }
        Magic otherMagic = (Magic) other;
        return Double.compare((double) this.power / this.cost, (double) otherMagic.power / otherMagic.cost);
    }

    @Override
    public String toString() {
        return "Magic: " + getName() + " - power = " + power + ", cost = " + cost;
    }
}
