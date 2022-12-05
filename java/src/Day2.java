import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day2 {

    public static void main(String []args) {
        BufferedReader reader;
        int score = 0;

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();

            while (line != null) {
                char resultExpected = line.charAt(2);
                char elfShape = line.charAt(0);

                if (resultExpected == 'X') {
                    score += 0;
                    if (elfShape == 'A') score += 3;
                    else if (elfShape == 'B') score += 1;
                    else score += 2;
                } else if (resultExpected == 'Y') {
                    score += 3;
                    if (elfShape == 'A') score += 1;
                    else if (elfShape == 'B') score += 2;
                    else score += 3;
                } else {
                    score += 6;
                    if (elfShape == 'A') score += 2;
                    else if (elfShape == 'B') score += 3;
                    else score += 1;
                }

                line = reader.readLine();
            }

            reader.close();
            System.out.println("The total score is " + score);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
