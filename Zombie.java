import java.util.Random;

public class Zombie {
    static int idCount = 1;
    private int id;
    private String name;

    private enum Name { 
        DANIELA, ALEJANDRA, ANDREA, MIGUEL, CAMILA, SEBASTIAN, ERICA, JOE, WESTLEY;

        /**
         * Pick a random value of the Name enum.
         * @return a random Name.
         */
        public static Name getRandomName() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }

    //Constructor
    public Zombie() {
        this.id = idCount++;
        this.name = Name.getRandomName().toString();
    }

    public Zombie(String name) {
        this.id = idCount++;
        this.name = name.toUpperCase();
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name.toString();
    }

    public String toString() {
        return ("Zombie id: " + this.id + "     Name: " + this.name);
    }
}