package DungeonGame;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Room class
class Room {
    private List<Interactable> interactables;

    public Room() {
        interactables = new ArrayList<>();
    }

    public void addInteractable(Interactable interactable) {
        interactables.add(interactable);
    }

    public void describe() {
        for (Interactable interactable : interactables) {
            if (interactable instanceof Creature) {
                System.out.println("There is a " + ((Creature) interactable).name + " here.");
            } else if (interactable instanceof Item) {
                System.out.println("There is a " + ((Item) interactable).getName() + " here.");
            }
        }
    }

    public void interactWithEnemies(Player player) {
        Iterator<Interactable> iterator = interactables.iterator();
        while (iterator.hasNext()) {
            Interactable interactable = iterator.next();
            if (interactable instanceof Creature) {
                player.attack((Creature) interactable);
                if (((Creature) interactable).health > 0) {
                    interactable.interact(player);
                } else {
                    iterator.remove();
                }
            }
        }
    }

    public boolean isCleared() {
        for (Interactable interactable : interactables) {
            if (interactable instanceof Creature && ((Creature) interactable).health > 0) {
                return false;
            }
        }
        return true;
    }
}
