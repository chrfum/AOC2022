import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day6 {

    public static void main(String []args) {
        BufferedReader reader;
        int result = 0;

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();

            for (int i = 0; i < line.length() - 14; i++) {
                if (!differentChars(line.substring(i, i+14))) {
                    result++;
                } else {
                    result += 14;
                    break;
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("The first start-of-merker is detetcted after " + result + " charcters");
    }

    public static boolean differentChars(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) return false;
            }
        }

        return true;
    }
}
