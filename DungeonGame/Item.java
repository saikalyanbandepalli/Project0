package DungeonGame;
// Abstract Item class
abstract class Item implements Interactable {
    protected String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Potion class
class Potion extends Item {
    private int healAmount;

    public Potion(String name, int healAmount) {
        super(name);
        this.healAmount = healAmount;
    }

    public void use(Player player) {
        System.out.println(player.name + " uses " + name + " and heals for " + healAmount + " health!");
        player.health += healAmount;
    }

    @Override
    public void interact(Player player) {
        System.out.println(player.name + " found a " + name + "!");
        player.pickUpItem(this);
    }
}

// Weapon class
class Weapon extends Item {
    private int attackBoost;

    public Weapon(String name, int attackBoost) {
        super(name);
        this.attackBoost = attackBoost;
    }
    public void use(Player player) {
        System.out.println(player.name + " uses " + name + " and gains " + attackBoost + " attack power!");
        player.attackPower += attackBoost;
    }

    @Override
    public void interact(Player player) {
        System.out.println(player.name + " found a " + name + "!");
        player.pickUpItem(this);
    }
}
