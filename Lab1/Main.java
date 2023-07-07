package Lab1;

/**
 * 
 * @Author : Wasan Renumat
 * cmu_account : wasan_r@cmu.ac.th
 * std : 652115044
 * :)
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    // We need to replace comma "," in the csv with "||"

    public static void main(String args[]) throws IOException {
        Vector<UniData> myUni = new Vector<UniData>();
        System.out.println("Program start!");

        // Parsing a CSV file into Scanner class constructor
        Scanner sc = new Scanner(new File(
                "/Users/optimushsam/Library/CloudStorage/OneDrive-ChiangMaiUniversity/DB Design/953212_66-main/Lab1/QS-World-University-Rankings-2017_full.csv"));
        sc.useDelimiter(","); // sets the delimiter pattern

        // Skip the header
        sc.nextLine();
        while (sc.hasNextLine()) {
            UniData u = new UniData(sc.next(),
                    sc.next(),
                    sc.next(),
                    sc.next(),
                    sc.next(),
                    sc.next(),
                    sc.next(),
                    sc.next());
            // Covert university to lowercase
            u.setUniName(u.getUniName().toLowerCase());

            // Set empty score to 0
            if (u.getScore().isEmpty()) {
                u.setScore("0");
            }
            // round score
            double num = Double.parseDouble(u.getScore());
            u.setScore(Integer.toString((int) Math.round(num)));

            // remove rank
            if (isNumeric(u.getRank())) {
                myUni.add(u);

            }
            sc.nextLine();
        } // end while loop
        Collections.sort(myUni);
        String csvFilePath = "QS-World-University-Rankings-2017_updated.csv";
        try (FileWriter fileWriter = new FileWriter(csvFilePath)) {
            fileWriter.write("Year,Rank,University Name,Score,Link,Country,City,Logo\n");

            for (UniData uni : myUni) {
                fileWriter.write(uni.getYear() + "," + uni.getRank() + "," + uni.getUniName() + "," + uni.getScore() +
                        "," + uni.getLink() + "," + uni.getCountry() + "," + uni.getCity() + "," + uni.getLogo() + "\n");
            }

            System.out.println("Data saved to " + csvFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for (UniData uni : myUni) {
            System.out.println(uni.toString());
        }

        // Line 14"Nanyang Technological University, Singapore (NTU) Exception in thread
        // "main" java.util.InputMismatchException
        sc.close(); // closes the scanner

        // let insert new instance
        UniData newUni = new UniData();
        newUni.setCity("Chiang Mai");
        myUni.add(newUni);
        System.out.println("Program terminate properly!");
    } // end main

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    
}// end class Main