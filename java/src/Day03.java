import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day03 {
    public static void main(String []args) {
        BufferedReader reader;
        int resultPart1 = 0;
        int resultPart2 = 0;
        ArrayList<String> input = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();
            int counter = 0;

            while (line != null) {
                input.add(line);
                counter++;

                if (counter == 3) {
                    int max = input.get(0).length();
                    int maxIdx = 0;
                    for (int i = 1; i <= 2; i++) {
                        if (input.get(i).length() > max) {
                            max = input.get(i).length();
                            maxIdx = i;
                        }
                    }

                    for (int j = 0; j < max; j++) {
                        char currentChar = input.get(maxIdx).charAt(j);

                        if (input.get(1).indexOf(currentChar) != -1 && input.get(2).indexOf(currentChar) != -1 && input.get(0).indexOf(currentChar) != -1) {
                            if (currentChar>= 97 && currentChar <= 122) {
                                int value = currentChar - 96;
                                resultPart2 += value;
                            } else if (currentChar >= 65 && currentChar <= 90) {
                                int value = currentChar - 38;
                                resultPart2 += value;
                            }
                            break;
                        }
                    }
                    input.remove(2);
                    input.remove(1);
                    input.remove(0);
                    counter = 0;
                }


                String alreadyFound = "";

                String firstComp = line.substring(0, line.length() / 2);
                int idx = line.length() - line.length() / 2;
                String secondComp = line.substring(idx, line.length());

                for (int i = 0; i < firstComp.length(); i++) {
                    char firstChar = firstComp.charAt(i);
                    for (int j = 0; j < secondComp.length(); j++) {
                        char secondChar = secondComp.charAt(j);

                        if (firstChar == secondChar) {
                            if (alreadyFound.indexOf(firstChar) == -1) {
                                alreadyFound += firstChar;
                                if (firstChar >= 97 && firstChar <= 122) {
                                    int value = firstChar - 96;
                                    resultPart1 += value;
                                } else if (firstChar >= 65 && firstChar <= 90) {
                                    int value = firstChar - 38;
                                    resultPart1 += value;
                                }
                            }
                        }
                    }
                }

                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Part1: " + resultPart1);
        System.out.println("Part2: " + resultPart2);
    }
}
