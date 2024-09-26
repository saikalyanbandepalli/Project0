package DungeonGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DungeonAdventure {
    private Player player;
    private List<Room> dungeon;

    public DungeonAdventure() {
        player = new Player("Hero", 100, 10);
        dungeon = new ArrayList<>();

        // Populate the dungeon with rooms, enemies, and items
        Room room1 = new Room();
        room1.addInteractable(new Goblin("Goblin", 30, 5));
        dungeon.add(room1);

        Room room2 = new Room();
        room2.addInteractable(new Dragon("Dragon", 100, 20));
        dungeon.add(room2);

        Room room3 = new Room();
        room3.addInteractable(new Potion("Healing Potion", 20));
        dungeon.add(room3);
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Dungeon Adventure!");

        for (Room currentRoom : dungeon) {
            System.out.println("\nYou enter a new room.");
            currentRoom.describe();

            while (!currentRoom.isCleared()) {
                System.out.println("Choose an action: (1) Attack (2) Use Item (3) Move to Next Room");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        currentRoom.interactWithEnemies(player);
                        break;
                    case 2:
                        useItem();
                        break;
                    case 3:
                        if (currentRoom.isCleared()) {
                            System.out.println("You move to the next room.");
                        } else {
                            System.out.println("You must clear the room first!");
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                        break;
                }
                if (player.health <= 0) {
                    System.out.println("Game Over! You have been defeated.");
                    return;
                }
            }
        }
        System.out.println("Congratulations! You have cleared the dungeon.");
    }

    private void useItem() {
        if (player.getInventory().isEmpty()) {
            System.out.println("You have no items to use.");
            return;
        }

        System.out.println("Choose an item to use:");
        for (int i = 0; i < player.getInventory().size(); i++) {
            System.out.println((i + 1) + ". " + player.getInventory().get(i).getName());
        }
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice < 1 || choice > player.getInventory().size()) {
            System.out.println("Invalid choice.");
        } else {
            Item item = player.getInventory().get(choice - 1);
            
            player.getInventory().remove(item);
        }
    }

    public static void main(String[] args) {
        DungeonAdventure game = new DungeonAdventure();
        game.startGame();
    }
}
