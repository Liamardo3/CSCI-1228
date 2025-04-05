
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class handles downloading a CSV file with biometric data, loading
 * it into a list of Person objects, and performing operations like summarizing
 * statistics and printing sorted data.
 * 
 * It has three command line commands: download, summary, and print
 *
 * GitHub link for bonus points: https://github.com/Liamardo3/CSCI-1228.git
 *
 * @ Liam Ahern A00463807
 * @author Nikita Neveditsin
 */
public class Summary {

    public static final String URL = "https://people.math.sc.edu/Burkardt/datasets/csv/biostats.csv";

    /**
     * Downloads a file from the specified URL and saves it to the given output
     * file.
     *
     * @param link The URL of the file to be downloaded.
     * @param outFile The path where the downloaded file should be saved.
     * @throws IOException If an I/O error occurs while reading from the URL or
     * writing to the file.
     * @throws InterruptedException If the operation is interrupted while
     * waiting for the HTTP response.
     */
    public static void downloadFile(String link, String outFile) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(link))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            try (PrintWriter outputStream = new PrintWriter(outFile)) {
                outputStream.print(response.body());
            }
        } else {
            throw new IOException("Failed to download file, HTTP response code: "
                    + response.statusCode());
        }
    }

    /**
     * Read and store People and their stats from CSV file skips the header
     * creates an ArrayList of Person objects by iterating through the whole
     * file each line is seperated by commas into variables/array Person
     * constructor is called using these new values That Person is then added to
     * the ArrayList alp mthod created to remove repeated code
     *
     * @param file
     * @return alp
     * @throws FileNotFoundException
     */
    public static ArrayList<Person> loadPeople(String file) throws FileNotFoundException {
        ArrayList<Person> alp = new ArrayList<Person>();
        try (Scanner inputStream = new Scanner(new File(file))) {

            String headerLine = inputStream.nextLine();

            while (inputStream.hasNextLine()) {
                String line = inputStream.nextLine();
                String[] ary = line.split(",");
                if (ary.length == 5) {
                    String name = ary[0];
                    String sex = ary[1];

                    int[] stats = new int[3];
                    for (int i = 0; i < stats.length; i++) {
                        stats[i] = Integer.parseInt(ary[i + 2].strip());
                    }
                    Person p = new Person(name, sex, stats);
                    alp.add(p);
                }
            }
            return alp;
        }
    }

    /**
     * Accepts a file to read using loadPeople() sortby to determine the way to
     * organize the ArrayList reverse will evaluate whether to apply the reverse
     * method at the end the ArrayList is created, then the second argument is
     * iterated over to decide which comparartor to call from the Person class
     * reverse is evaluated Finally print the sorted Person objects in the
     * ArrayList
     *
     * @param file
     * @param sortBy
     * @param reverse
     * @throws FileNotFoundException
     */
    public static void printPeople(String file, String sortBy, boolean reverse) throws FileNotFoundException {
        ArrayList<Person> people = loadPeople(file);

        if (sortBy != null) {
            if (sortBy.equals("n")) {
                Collections.sort(people, Person.byName);
            } else if (sortBy.equals("a")) {
                Collections.sort(people, Person.byAge);
            } else if (sortBy.equals("h")) {
                Collections.sort(people, Person.byHeight);
            }
        }

        if (reverse) {
            Collections.reverse(people);
        }

        for (Person p : people) {
            System.out.println(p.getName().replace("\"", "").strip()
                    + ", " + p.getSex().replace("\"", "").strip()
                    + ", " + p.getAge()
                    + ", " + p.getHeight()
                    + ", " + p.getWeight());
        }
    }

    /**
     * print a summary of the file given calls loadPeople to create an ArrayList
     * print the information in the format required
     *
     * @param file
     * @throws java.io.FileNotFoundException
     */
    public static void summary(String file) throws FileNotFoundException {
        ArrayList<Person> alp = loadPeople(file);
        Collections.sort(alp);

        System.out.println("Summary:");
        System.out.println("Total number of records: " + Person.getTotalRecs(alp));
        System.out.println(Person.getYoungest(alp));
        System.out.println(Person.getOldest(alp));
        System.out.println(Person.avgAge(alp));
        System.out.println(Person.tallestPerson(alp));
        System.out.println(Person.shortLady(alp));
    }

    public static void main(String[] args) throws IOException {

        switch (args[0]) {
            case "download": {
                try {
                    downloadFile(URL, args[1]);
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
            }
            System.out.println("Downloaded successully: " + args[1]);
            break;

            case "summary": {
                //TODO

                try {
                    summary(args[1]);
                } catch (FileNotFoundException e) { //file is closed automatically
                    System.out.println("Error opening the file "
                            + args[1]);
                }
            }
            break;

            case "print": {
                //TODO
                String file = args[1];
                String sortBy;
                if (args.length >= 3) {
                    sortBy = args[2];
                } else {
                    sortBy = null;
                }
                boolean reverse;
                if (args.length >= 4 && args[3].equals("true")) {
                    reverse = true;
                } else {
                    reverse = false;
                }
                try {
                    printPeople(file, sortBy, reverse);
                } catch (FileNotFoundException e) {
                    System.out.println("Error opening the file " + file);
                }
                break;
            }

            default:
                System.out.println("Unknown command");

        }

    }

}
