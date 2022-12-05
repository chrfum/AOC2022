import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day5 {

    public static void main(String []args) {
        BufferedReader reader;
        String[] stacks = new String[9];

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();
            boolean startMoves = false;

            while (line != null) {
                if (line.length() > 0 && line.charAt(0) == '1') {
                    startMoves = true;
                    line = reader.readLine();
                    continue;
                }

                if (!startMoves) {
                    for (int j = 1;  j < line.length(); j += 4) {
                        int stackCounter = (j - 1) / 4;

                        if (stacks[stackCounter] == null) stacks[stackCounter] = "";
                        if (line.charAt(j) == ' ') continue;

                        stacks[stackCounter] = line.charAt(j) + stacks[stackCounter];
                    }
                }

                if (startMoves) {
                    if (line.equals("")) {
                        line = reader.readLine();
                        continue;
                    }

                    int move = line.charAt(5) - 48;
                    int from = line.charAt(12) - 49;
                    int to = line.charAt(17) - 49;

                    if (line.length() != 18) {
                        move = line.charAt(5) - 48;
                        move = move * 10 + (line.charAt(6) - 48);
                        from = line.charAt(13) - 49;
                        to = line.charAt(18) - 49;
                    }


                    String toMove = stacks[from].substring(stacks[from].length() - move, stacks[from].length());
                    stacks[from] = stacks[from].substring(0, stacks[from].length() - move);
                    stacks[to] = stacks[to] + toMove;
                }

                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("The result is ");
        for (int i = 0; i < 9; i++) {
            System.out.print(stacks[i].charAt(stacks[i].length()-1));
        }

        System.out.println();
    }

    public static String reverse(String s) {
        String result = "";

        for (int i = s.length() - 1; i >= 0; i--) {
            result += s.charAt(i);
        }

        return result;
    }
}
