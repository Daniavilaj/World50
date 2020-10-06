import java.util.*;

public class Location {
    private String name;
    private HashMap<Integer,Zombie> zombies;

    public Location(String name) {
        this.name = name;
        zombies = new HashMap<>();
    }

    public String getName() {
        return this.name.toLowerCase();
    }

    public Collection<Zombie> getZombies() {
        return zombies.values();
    }

    public Zombie getZombie(int zombieId) {
        return zombies.get(zombieId);
    }
    
    public void addZombie(Zombie zombie) {
        this.zombies.put(zombie.getId(), zombie);
    }

    public Zombie removeZombie(int zombieId) {
        return this.zombies.remove(zombieId);
    }

    public boolean isEmpty() {
        return this.zombies.isEmpty();
    }

    public int getNumZombies() {
        return this.zombies.size();
    }

    public String toString() {
        return ("The " + this.name + " has " + this.getNumZombies() + " zombies quarantined.");
    }
}
