import java.util.*;

public class ZombieManager {
// OK, here’s the deal:

// We've got the zombies under control, but we need to keep track of them. 
// That's where you come in. We need you to build an app for us. We need you 
// to build a zombie manager.

// We can quarantine zombies in three locations: the hospital, the school, 
// and the warehouse. We need the app to keep track of where each zombie 
// is being held, how many zombies are in each location, and we need to be 
// able to move zombies between the locations.

// Please submit your code in a Github repo that I can access.

    Location[] locations;
    public static void main(String[] args) {
        ZombieManager zombieManager = new ZombieManager(9);
        Scanner scanner = new Scanner(System.in); 

        boolean stop = false;
        
        System.out.println("\n\n--------------------------------------");
        System.out.println(" Welcome to ZOMBIE2020 MANAGEMENT INC");
        System.out.println("--------------------------------------");

        System.out.println("\nI'm Daniela, your Zombie Manager, \nI'm here to help you!"
                            + "\n\nThis is the Zombie map of our facilities: ");
        zombieManager.printFacilities();

        while(!stop) {
            System.out.println("\n\nWhat do you want to do?: "
                            + " \n\n a) See Zombie map" 
                            + " \n b) Locate a Zombie" 
                            + " \n c) Place a Zombie in quarantine" 
                            + " \n d) Discharge a Zombie" 
                            + " \n e) Transfer a Zombie to another location"
                            + " \n f) Request Zombie head count"
                            + " \n g) I don't want to do anything else. Bye!\n");

            String option = scanner.nextLine();

            switch(option) {
                case "a":
                    zombieManager.printFacilities();
                    break;
                case "b":
                    System.out.println("\nWhich Zombie are you looking for? Specify the Zombie id: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    zombieManager.locateZombie(id);
                    break;
                case "c":
                    System.out.println("\nWhere do you want to intern a Zombie? hospital, school or warehouse: ");
                    String facility = scanner.nextLine();

                    System.out.println("\nPlease provide the name of the Zombie you want to intern: ");
                    String name = scanner.nextLine();

                    zombieManager.addZombie(facility, name);
                    break;
                case "d":
                    System.out.println("\nWhich Zombie do you want to discharge? Specify the Zombie id: ");
                    int id2 = Integer.parseInt(scanner.nextLine());

                    zombieManager.removeZombie(id2);
                    break;  
                case "e":
                    System.out.println("\nWhich Zombie do you want to transfer? Specify the Zombie id: ");
                    int zombieId = Integer.parseInt(scanner.nextLine());
                    
                    System.out.println("\nWhere do you want to transfer it to? ");
                    String transfer = scanner.nextLine();

                    zombieManager.changeLocation(transfer, zombieId);
                    break; 
                case "f":
                    System.out.println("\nFrom which facility? ");
                    String facility2 = scanner.nextLine();

                    zombieManager.zombieCount(facility2);
                    break; 
                case "g":
                    System.out.println("\nIt was a pleasure to help you..."
                                        + "\n\nStay safe!..."
                                        + "\n\n2020 is scary..."
                                        + "\n\nBye!\n");
                    stop = true;
                    break; 
                default:
                    System.out.println("\nThat is not an option. Try another one...");

            }
        }
        scanner.close();
    }

    /**
     * Constructor
     * @param numZombies Initialize 3 locations with specified number of zombies
     */
    public ZombieManager(int numZombies) {
        locations = new Location[] {new Location("Hospital"), new Location("School"), new Location("Warehouse")};

        for(int i = 0; i < numZombies; i++) {
            Zombie zombie = new Zombie();
            Location loc = locations[i % locations.length];

            loc.addZombie(zombie);
        }
    }

    /**
     * Locate a Zombie
     * @param zombieid id 
     * @return Location were Zombie is found
     */
    public Location locateZombie(int zombieId) {
        boolean found = false;

        for(Location loc : locations) {
            if(!loc.isEmpty()) {
                if(loc.getZombie(zombieId) != null) {
                    Zombie zombie = loc.getZombie(zombieId);
                    found = true;

                    System.out.println("\nZombie " + zombie.getName() + " with id number " 
                    + zombie.getId() + " is being quarantine in the " + loc.getName().toUpperCase());

                    return loc;
                }
            }
        }

        if(!found) {
            System.out.println("\nZombie with id number " + zombieId 
            + " is not quarantine in any of our facilities. Keep looking!");
        }

        return null;
    }

    /**
     * Place a new Zombie in quarantine in a specific location
     * @param location to add zombie
     * @param name of Zombie
     */
    public void addZombie(String location, String name) {
        Zombie zombie = new Zombie(name);
        boolean foundLoc = false;

        for(Location loc : locations) {
            if(loc.getName().equals(location)) {
                foundLoc = true;
                loc.addZombie(zombie);
                System.out.println("\nZombie " + zombie.getName() + " with id number " 
                + zombie.getId() + " just started quarantine in the " + loc.getName().toUpperCase());

                return;
            }
        }

        if(!foundLoc) {
            System.out.println("\nWe don't have that facility under our management. Try other Location!");
        }
    }

    /**
     * Remove a Zombie based in their id 
     * @param zombieId 
     * @return Zombie removed
     */
    public Zombie removeZombie(int zombieId) {
        Location  loc = locateZombie(zombieId);

        if(loc != null) {
            Zombie zombie = loc.removeZombie(zombieId);

            System.out.println("\nZombie " + zombie.getName() + " with id number " 
            + zombie.getId() + " was discharged from the " + loc.getName().toUpperCase());

            return zombie;
        }

        return null;
    }

    /**
     * Transfer an existing Zombie to the specified location 
     * @param location to be transfer to
     * @param zombieId
     */
    public void changeLocation(String location, int zombieId) {
        boolean foundLoc = false;
        
        for(Location loc : locations) {
            if(loc.getName().equals(location)) {
                foundLoc = true;
                Zombie zombie = removeZombie(zombieId);
                if(zombie != null) {
                    loc.addZombie(zombie);

                    System.out.println("\nZombie " + zombie.getName() + " with id number " 
                        + zombie.getId() + " was not getting along with the other zombies "+
                        "so he had to be transfered to the " + loc.getName().toUpperCase());

                    return;
                }
            }
        }

        if(!foundLoc) {
            System.out.println("\nWe don't have that facility under our management. Try other Location!!");
        }
    }

    /**
     * Zombies on a specific location
     * @param location
     */
    public void zombieCount(String location) {
        boolean locFound = false;

        for(Location loc : locations) {
            if(loc.getName().equals(location)) {
                locFound = true;
                System.out.println("\n" + loc.toString());
            }
        }

        if(!locFound) {
            System.out.println("\nWe don't have that facility under our management. Try other Location!");
        }
    }

    //Prints Zombie map
    public void printFacilities() {
        for(Location loc : locations) {
            System.out.println(" ");
            System.out.println("--------------------------------------");
            System.out.println(" Facility: " + loc.getName().toUpperCase() + "   # Zombies: " + loc.getNumZombies());
            System.out.println("--------------------------------------");
            printZombies(loc.getZombies());
            System.out.println("--------------------------------------");
        }
    }

    public void printZombies(Collection<Zombie> zombies) {
        for(Object z : zombies) {
            System.out.println("|    " + z.toString());
        }
    }

}
