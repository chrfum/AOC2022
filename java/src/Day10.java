import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day10 {

    public static void main(String []args) {
        BufferedReader reader;
        ArrayList<String> instructions = new ArrayList<>();
        int regValue = 1;

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();

            while (line != null) {

                if (line.equals("noop")) instructions.add("noop");
                else {
                    instructions.add("noop");
                    instructions.add(line);
                }

                line = reader.readLine();
            }

            int signalStrengths = 0;
            int spritePosition = 1;
            int crtPosition = 0;

            for (int i = 0; i < instructions.size(); i++) {
                if ((i + 1) == 20 || (i + 1) == 60 || (i + 1) == 100
                || (i + 1) == 140 || (i + 1) == 180 || (i + 1) == 220) signalStrengths += (i + 1) * regValue;
                
                if ((i + 1) == 41 || (i + 1) == 81 || (i + 1) == 121 || (i + 1) == 161 || (i + 1) == 201) {
                    System.out.println();
                    crtPosition = 0;
                }

                if(crtPosition == spritePosition || crtPosition == spritePosition + 1 || crtPosition == spritePosition - 1) System.out.print("#");
                else System.out.print(".");

                if (!instructions.get(i).equals("noop")) regValue += Integer.parseInt(instructions.get(i).substring(5, instructions.get(i).length()), 10);
                spritePosition = regValue;

                crtPosition++;
            }

            System.out.println();
            System.out.println(signalStrengths);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
