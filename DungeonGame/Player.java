package DungeonGame;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

// Player class
class Player extends Creature implements Interactable {
    int attackPower;
    private List<Item> inventory;

    public Player(String name, int health, int attackPower) {
        super(name, health);
        this.attackPower = attackPower;
        this.inventory = new ArrayList<>();
    }

    @Override
    public void attack(Creature target) {
        System.out.println(name + " attacks " + target.name + " for " + attackPower + " damage!");
        target.takeDamage(attackPower);
    }

    @Override
    public void interact(Player player) {
        // Player interacts with self
    }

    public void pickUpItem(Item item) {
        inventory.add(item);
        System.out.println(name + " picked up " + item.getName());
    }

    public List<Item> getInventory() {
        return inventory;
    }
}

// Goblin class
class Goblin extends Creature implements Interactable {
    private int attackPower;

    public Goblin(String name, int health, int attackPower) {
        super(name, health);
        this.attackPower = attackPower;
    }

    @Override
    public void attack(Creature target) {
        System.out.println(name + " attacks " + target.name + " for " + attackPower + " damage!");
        target.takeDamage(attackPower);
    }

    @Override
    public void interact(Player player) {
        System.out.println(name + " attacks " + player.name + "!");
        attack(player);
    }
}

// Dragon class
class Dragon extends Creature implements Interactable {
    private int attackPower;

    public Dragon(String name, int health, int attackPower) {
        super(name, health);
        this.attackPower = attackPower;
    }

    @Override
    public void attack(Creature target) {
        System.out.println(name + " breathes fire on " + target.name + " for " + attackPower + " damage!");
        target.takeDamage(attackPower);
    }

    @Override
    public void interact(Player player) {
        System.out.println(name + " attacks " + player.name + "!");
        attack(player);
    }
}
