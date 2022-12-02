import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day1 {

    public static void main(String []args) {
        BufferedReader reader;
        List<String> inputLines = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();

            while (line != null) {
                inputLines.add(line);
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int maxTotalCalories = 0;
        List<Integer> totalCalories = new ArrayList<>();

        for (int i = 0; i < inputLines.size(); i++) {
            if (inputLines.get(i).length() > 0) {
                maxTotalCalories += Integer.parseInt(inputLines.get(i));
            } else {
                totalCalories.add(maxTotalCalories);
                maxTotalCalories = 0;
            }
        }
        totalCalories.add(maxTotalCalories);

        System.out.println("The maximum total calories is " + sumMax3Int(totalCalories));
    }

    private static int sumMax3Int(List<Integer> values) {
        int max0 = values.get(0);
        int max1 = values.get(0);
        int max2 = values.get(0);

        for (int i = 1; i < values.size(); i++) {
            if (values.get(i) > max2) {
                if (values.get(i) > max1) {
                    if (values.get(i) > max0) {
                        max2 = max1;
                        max1 = max0;
                        max0 = values.get(i);
                    } else {
                        max2 = max1;
                        max1 = values.get(i);
                    }
                } else {
                    max2 = values.get(i);
                }
            }
        }

        int result = (max0 + max1 + max2);
        return result;
    }
}
