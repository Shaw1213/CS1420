package assign07;
/**
 * A tool with specific power value.
 * 
 * @author Shawn
 * @version Oct 26, 2023
 */

public class Tool extends Item {
    private int power;

    /**
     * Constructs a Tool with a name and power.
     * 
     * @param name The name of the tool.
     * @param power The power of the tool.

     */
    public Tool(String name, int power) {
        super(name);
        this.power = power;
    }

    @Override
    public void merge(Item other) {
        if (other instanceof Tool) {
            this.power += ((Tool) other).power;
        }
    }

    @Override
    public int compareTo(Item other) {
        if (other instanceof Tool) {
            return Integer.compare(this.power, ((Tool) other).power);
        }
        return -1; // Tools are considered greater than other types
    }

    @Override
    public String toString() {
        return "Tool: " + getName() + " – power = " + power;
    }
}
