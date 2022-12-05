import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day4 {

    public static void main(String []args) {
        BufferedReader reader;
        int containmentCounter = 0;
        int overlaysCounter = 0;

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();

            while (line != null) {
                String[] sectionAssignments = line.split(",");

                if (isContained(sectionAssignments)) containmentCounter++;
                if (isOverlap(sectionAssignments)) overlaysCounter++;

                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("The total number of pairs which fully contains the other are " + containmentCounter);
        System.out.println("The total number of overlaps is " + overlaysCounter);
    }

    public static boolean isContained(String[] input) {
        String[] firstElf = input[0].split("-");
        String[] secondElf = input[1].split("-");

        int start0 = Integer.parseInt(firstElf[0], 10);
        int end0 = Integer.parseInt(firstElf[1], 10);
        int start1 = Integer.parseInt(secondElf[0], 10);
        int end1 = Integer.parseInt(secondElf[1], 10);

        if (start0 <= start1 && end0 >= end1) return true;

        if (start0 >= start1 && end0 <= end1) return true;

        return false;
    }

    public static boolean isOverlap(String[] input) {
        String[] firstElf = input[0].split("-");
        String[] secondElf = input[1].split("-");

        int start0 = Integer.parseInt(firstElf[0], 10);
        int end0 = Integer.parseInt(firstElf[1], 10);
        int start1 = Integer.parseInt(secondElf[0], 10);
        int end1 = Integer.parseInt(secondElf[1], 10);

        if (start0 <= start1 && end0 >= start1) return true;
        else if (start0 >= start1 && start0 <= end1) return true;

        return false;
    }
}
