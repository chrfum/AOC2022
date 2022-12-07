import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;

public class Day07 {

    public static void main(String []args) {
        BufferedReader reader;
        TreeMap<String, Integer> size = new TreeMap<>();

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();
            ArrayList<String> dirFathers = new ArrayList<>();
            String dirPath = "";
            boolean ls = false;

            while (line != null) {
                if (line.charAt(0) == '$') {

                    if (line.substring(2,4).equals("cd")) {
                        ls = false;
                        if (line.charAt(5) == '.') {
                            String[] allDirs = dirPath.split("/");
                            dirPath = "";
                            for (int i = 0; i < allDirs.length - 1; i++) {
                                dirPath += allDirs[i] + "/";
                            }
                            dirFathers.remove(dirFathers.size() - 1);
                        } else {
                            if (line.substring(5, line.length()).equals("/")) dirPath += "root/";
                            else dirPath += line.substring(5, line.length()) + "/";

                            dirFathers.add(dirPath);
                        }

                    } else {
                        if (ls) {
                            line = reader.readLine();
                            continue;
                        }

                        ls = true;
                    }
                } else {
                    String[] in = line.split(" ");

                    if (!in[0].equals("dir")) {
                        for (int i = 0; i < dirFathers.size(); i++) {
                            if (size.get((Object)dirFathers.get(i)) == null) {
                                size.put(dirFathers.get(i), Integer.parseInt(in[0], 10));
                            } else {
                                size.put(dirFathers.get(i), (Integer)size.get((Object)dirFathers.get(i)) + Integer.parseInt(in[0], 10));
                            }
                        }
                    }
                }

                line = reader.readLine();
            }

            int sum = 0;
            for (Map.Entry m: size.entrySet()) {
                if ((Integer)m.getValue() <= 100000) {
                    sum += (Integer)m.getValue();
                }
            }
            System.out.println("Part1: " + sum);

            int spaceToFree = (Integer)size.get("root/") - 40000000;
            int dirSpace = (Integer)size.get("root/");
            for (Map.Entry m: size.entrySet()) {
                if ((Integer)m.getValue() >= spaceToFree && (Integer)m.getValue() < dirSpace) {
                    dirSpace = (Integer)m.getValue();
                }
            }

            System.out.println("Part2: " + dirSpace);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
