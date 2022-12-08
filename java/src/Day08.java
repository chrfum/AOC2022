import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day08 {

    public static void main(String []args) {
        BufferedReader reader;
        ArrayList<ArrayList <Integer>> treeMatrix = new ArrayList<>();
        ArrayList<String> visibleTrees = new ArrayList<>();
        int visibleCounter = 0;
        int scenicScore = 0;

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();

            while (line != null) {
                ArrayList<Integer> treeRow = new ArrayList<>();

                for (int i = 0; i < line.length(); i++) {
                    int treeHeight = line.charAt(i) - '0';
                    treeRow.add(treeHeight);
                }

                treeMatrix.add(treeRow);
                line = reader.readLine();
            }

            scenicScore = countScore(treeMatrix, 0, 0);

            for (int i = 0; i < treeMatrix.size(); i++) {
                for (int j = 0; j < treeMatrix.get(i).size(); j++) {
                    String currentTree = i + "," + j;
                    if (countScore(treeMatrix, i, j) > scenicScore) scenicScore = countScore(treeMatrix, i, j);
                    if (i == 0 || i == treeMatrix.size() - 1 || j == 0 || j == treeMatrix.get(i).size() - 1) {
                        visibleCounter++;
                        visibleTrees.add(currentTree);
                    } else {
                        if (isVisibile(treeMatrix, i, j)) {
                            if (!visibleTrees.contains(currentTree)) {
                                visibleCounter++;
                                visibleTrees.add(currentTree);
                            }
                        }
                    }
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("The number of visible trees is " + visibleCounter);
        System.out.println("The highest scenic score is " + scenicScore);
    }

    public static boolean isVisibile(ArrayList<ArrayList<Integer>> matrix, int i, int j) {
        int height = matrix.get(i).get(j);
        boolean visible = true;

        for (int col = i - 1; col >= 0; col--) {
            if (height <= matrix.get(col).get(j)) {
                visible = false;
                break;
            }
        }
        if (visible) return true;

        visible = true;
        for (int col = i + 1; col < matrix.size(); col++) {
            if (height <= matrix.get(col).get(j)) {
                visible = false;
                break;
            }
        }
        if (visible) return true;

        visible = true;
        for (int row = j - 1; row >= 0; row--) {
            if (height <= matrix.get(i).get(row)) {
                visible = false;
                break;
            }
        }
        if (visible) return true;

        visible = true;
        for (int row = j + 1; row < matrix.get(i).size(); row++) {
            if (height <= matrix.get(i).get(row)) {
                visible = false;
                break;
            }
        }
        if (visible) return true;

        return false;
    }

    public static int countScore(ArrayList<ArrayList<Integer>> matrix, int i, int j) {
        int height = matrix.get(i).get(j);
        int score = 0;
        int finalScore = 1;

        for (int col = i - 1; col >= 0; col--) {
            if (height <= matrix.get(col).get(j)) {
                score++;
                break;
            } else {
                score++;
            }
        }
        finalScore *= score;

        score = 0;
        for (int col = i + 1; col < matrix.size(); col++) {
            if (height <= matrix.get(col).get(j)) {
                score++;
                break;
            } else {
                score++;
            }
        }
        finalScore *= score;

        score = 0;
        for (int row = j - 1; row >= 0; row--) {
            if (height <= matrix.get(i).get(row)) {
                score++;;
                break;
            } else {
                score++;
            }
        }
        finalScore *= score;

        score = 0;
        for (int row = j + 1; row < matrix.get(i).size(); row++) {
            if (height <= matrix.get(i).get(row)) {
                score++;
                break;
            } else {
                score++;
            }
        }
        finalScore *= score;

        return finalScore;
    }
}
