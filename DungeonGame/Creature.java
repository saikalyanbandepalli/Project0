package DungeonGame;
import java.util.ArrayList;
import java.util.List;

// Abstract Creature class
abstract class Creature {
    protected String name;
    protected int health;

    public Creature(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public abstract void attack(Creature target);

    public void takeDamage(int damage) {
        this.health -= damage;
        System.out.println(name + " takes " + damage + " damage! Health is now " + health + ".");
        if (health <= 0) {
            System.out.println(name + " has been defeated!");
        }
    }
}

// Interactable interface
interface Interactable {
    void interact(Player player);
}
