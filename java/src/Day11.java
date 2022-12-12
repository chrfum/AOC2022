import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day11 {

    public static void main(String []args) {
        BufferedReader reader;
        ArrayList<Item> items = new ArrayList<>();
        char[] operations = new char[8];
        String[] byValue = new String[8];
        int[] test = new int[8];
        int[] goToT = new int[8];
        int[] goToF = new int[8];
        int[] counters = new int[8];

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line =  reader.readLine();
            int counter = 0;

            while (line != null) {
                if (line.equals("")) line = reader.readLine();

                if (line.charAt(2) == 'S') {
                    String[] inItems = line.split(" ");

                    for (int i = 0; i < inItems.length; i++) {

                        if (inItems[i].length() > 0) {
                            if (inItems[i].charAt(0) >= '0' && inItems[i].charAt(0) <= '9') {
                                if (inItems[i].charAt(inItems[i].length() - 1) == ',') inItems[i] = inItems[i].substring(0, inItems[i].length() - 1);

                                items.add(new Item(Integer.parseInt(inItems[i], 10), counter));
                            }
                        }
                    }

                }

                if (line.charAt(2) == 'O') {
                    String[] inOp = line.split(": new = old ");
                    operations[counter] = inOp[1].charAt(0);
                    byValue[counter] = inOp[1].substring(2, inOp[1].length());
                }


                if (line.charAt(2) == 'T') {
                    String[] inTest = line.split(" ");
                    int value = Integer.parseInt(inTest[inTest.length - 1], 10);
                    test[counter] = value;
                }

                if (line.length() > 12) {
                    if (line.substring(7,11).equals("true")) goToT[counter] = line.charAt(line.length()-1) - '0';
                    if (line.substring(7,12).equals("false"))  {
                        goToF[counter] = line.charAt(line.length()-1) - '0';
                        counter++;
                    }
                }

                line = reader.readLine();
            }

            reader.close();

            for (int i = 0; i < 10000; i++) {
                for (int j = 0; j < 8; j++) {
                    for (int k = 0; k < items.size(); k++) {
                        if (items.get(k).monkey == j) {
                            counters[j]++;

                            long worryLevel = items.get(k).value;

                            if (operations[j] == '*') {
                                if (byValue[j].equals("old")) worryLevel *= worryLevel;
                                else worryLevel *= Integer.parseInt(byValue[j], 10);
                            } else {
                                if (byValue[j].equals("old")) worryLevel += worryLevel;
                                else worryLevel += Integer.parseInt(byValue[j], 10);
                            }

                            worryLevel = worryLevel % 9699690;

                            if (worryLevel % test[j] == 0) items.get(k).change(worryLevel, goToT[j]);
                            else items.get(k).change(worryLevel, goToF[j]);
                        }
                    }
                }
            }

            int max1 = counters[0];
            int max2 = counters[0];

            for (int i = 0; i < 8; i++) {

                if (counters[i] > max2) {
                    if (counters[i] > max1) {
                        max2 = max1;
                        max1 = counters[i];
                    } else {
                        max2 = counters[i];
                    }
                }
            }

            System.out.println(max1 + " * " + max2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
