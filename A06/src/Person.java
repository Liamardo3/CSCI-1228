
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * A class representing a person -- suitable for extending into interesting
 * kinds of people -- like students or truck-drivers.
 *
 * @author Mark Young (A00000000)
 */
public class Person implements Comparable<Person> {

    // ********** instance variables ********** //
    private String name;
    private String sex;
    private int[] stats;

    // ********** constructors ********** //
    /**
     * Create a Person with the given name.
     *
     * @param initialName this Person's name
     */
    public Person(String initialName) {
        name = initialName;
    }

    /**
     * Create a person with a place-holder name.
     */
    public Person() {
        this("Baby Human");
    }

    public Person(String name, String sex, int[] stats) {
        this.name = name;
        this.sex = sex;
        this.stats = stats;
    }

    // ********** instance methods ********** //
    /**
     * Change this Person's name.
     *
     * @param newName this Person's new name.
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * Return this Person's name.
     *
     * @return this Person's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Return this Person's sex.
     *
     * @return this Person's sex.
     */
    public String getSex() {
        return sex;
    }

    /**
     * Return this Person's age.
     *
     * @return this Person's age.
     */
    public int getAge() {
        return stats[0];
    }

    /**
     * Return this Person's height.
     *
     * @return this Person's height.
     */
    public int getHeight() {
        return stats[1];
    }

    /**
     * Return a person weight
     *
     * @return weight
     */
    public int getWeight() {
        return stats[2];
    }

    /**
     * Get the number of people
     *
     * @param p
     * @return int count of person objects
     */
    public static String getTotalRecs(ArrayList<Person> p) {
        return Integer.toString(p.size());
    }

    /**
     * Get the youngest persons name and age
     *
     * @param p
     * @return name of youngest person
     */
    public static String getYoungest(ArrayList<Person> p) {
        if (p == null || p.isEmpty()) {
            return "No index to read";
        }

        int youngest = p.get(0).getAge();
        String youngName = p.get(0).getName();
        for (int i = 1; i < p.size(); i++) {
            if (youngest > p.get(i).getAge()) {
                youngest = p.get(i).getAge();
                youngName = p.get(i).getName();
            }
        }

        return "Youngest Person: " + youngName.replace("\"", "").strip() + " (" + Integer.toString(youngest) + " years old)";
    }

    /**
     * Get the oldest person
     *
     * @param p
     * @return name of youngest person
     */
    public static String getOldest(ArrayList<Person> p) {
        if (p == null || p.isEmpty()) {
            return "No index to read";
        }

        int oldest = p.get(0).getAge();
        String oldName = p.get(0).getName();
        for (int i = 1; i < p.size(); i++) {
            if (oldest < p.get(i).getAge()) {
                oldest = p.get(i).getAge();
                oldName = p.get(i).getName();
            }
        }
        return "Oldest Person: " + oldName.replace("\"", "").strip() + " (" + Integer.toString(oldest) + " years old)";
    }

    /**
     * Add the ages and the divide by the number of people
     *
     * @param p
     * @return double average
     */
    public static String avgAge(ArrayList<Person> p) {
        double avg = 0;
        int count = 0;
        for (Person p1 : p) {
            avg += p1.getAge();
            count++;
        }

        double rounded = Math.round((avg / count) * 100.0) / 100.0;
        return "Average age: " + Double.toString(rounded);
    }

    /**
     * Return string of the tallest persons name and height
     *
     * @param p
     * @return
     */
    public static String tallestPerson(ArrayList<Person> p) {
        if (p == null || p.isEmpty()) {
            return "No people in list";
        }
        String tallName = "";
        int tallHeight = 0;

        for (int i = 0; i < p.size(); i++) {
            if (tallHeight < p.get(i).getHeight()) {
                tallHeight = p.get(i).getHeight();
                tallName = p.get(i).getName();
            }
        }

        return "Tallest person: " + tallName.replace("\"", "").strip() + " (" + Integer.toString(tallHeight) + " inches)";
    }

    /**
     * take the heights of all people into new array sort the array, take the
     * first element cycle back through the people and get the matching person
     * check if sex starts ignore case with F call their name concact the name
     * and height to string and return
     *
     * @param p
     * @return
     */
    public static String shortLady(ArrayList<Person> p) {
        if (p == null || p.isEmpty()) {
            return "No people in list";
        }
        String shortName = "";
        double shortHeight = 10000;

        for (int i = 0; i < p.size(); i++) {
            if (shortHeight > p.get(i).getHeight() && p.get(i).getSex().replace("\"", "").strip().equalsIgnoreCase("F")) {
                shortHeight = p.get(i).getHeight();
                shortName = p.get(i).getName();
            }
        }

        return "Shortest female: " + shortName.replace("\"", "").strip();
    }

    /**
     * Provide a simple report on this Person.
     */
    public void writeOutput() {
        System.out.println("Name: " + name);
    }

    /**
     * Check whether this Person has the same name as another.
     *
     * @param other the other Person (who possibly has the same name).
     * @return true if these people's names are identical; false otherwise.
     */
    public boolean hasSameName(Person other) {
        if (other == null) {
            return false;
        }
        return name.equalsIgnoreCase(other.name);
    }

    /**
     * Create a String representing this Person.
     *
     * @return a String with this Person's name.
     */
    @Override
    public String toString() {
        return "Person: " + name;
    }

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }

    /**
     * comparator to sort Person objects alphabetically by name, ignoring case 
     * can be used with Collections.sort()
     *
     */
    public static Comparator<Person> byName = new Comparator<Person>() {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.getName().compareToIgnoreCase(p2.getName());
        }
    };

    /**
     * comparator to sort Person objects numerically by age
     * can be used with Collections.sort()
     *
     */
    public static Comparator<Person> byAge = new Comparator<Person>() {
        @Override
        public int compare(Person p1, Person p2) {
            return Integer.compare(p1.getAge(), p2.getAge());
        }
    };

    /**
     * comparator to sort Person objects numerically by height 
     * can be used with Collections.sort()
     *
     */
    public static Comparator<Person> byHeight = new Comparator<Person>() {
        @Override
        public int compare(Person p1, Person p2) {
            return Integer.compare(p1.getHeight(), p2.getHeight());
        }
    };
}
