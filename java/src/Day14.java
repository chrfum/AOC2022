import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day14 {

    public static void main(String[] args) {
        BufferedReader reader;
        char[][] matrix = new char[161][1000];

        for (int i = 0; i < 161; i++) {
            char[] row = new char[1000];

            for (int j = 0; j < 1000; j++) {
                if (i == 160) row[j] = '#';
                else if (i == 0 && j == 500) row[j] = '+';
                else row[j] = '.';
            }

            matrix[i] = row;
        }

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();

            while (line != null) {
                String[] rocks = line.split(" -> ");

                for (int i = 0; i < rocks.length - 1; i++) {
                    String[] coordinates = rocks[i].split(",");
                    Point start = new Point(Integer.parseInt(coordinates[0], 10), Integer.parseInt(coordinates[1], 10));
                    matrix[start.y][start.x] = '#';

                    coordinates = rocks[i+1].split(",");
                    Point end = new Point(Integer.parseInt(coordinates[0], 10), Integer.parseInt(coordinates[1], 10));
                    matrix[end.y][end.x] = '#';

                    if (start.x == end.x) {
                        if (start.y > end.y) {
                            for (int j = end.y + 1; j < start.y; j++) {
                                Point rock = new Point(start.x, j);
                                matrix[rock.y][rock.x] = '#';
                            }
                        } else {
                            for (int j = start.y + 1; j < end.y; j++) {
                                Point rock = new Point(start.x, j);
                                matrix[rock.y][rock.x] = '#';
                            }
                        }
                    } else if (start.y == end.y) {
                        if (start.x > end.x) {
                            for (int j = end.x + 1; j < start.x; j++) {
                                Point rock = new Point(j, start.y);
                                matrix[rock.y][rock.x] = '#';
                            }
                        } else {
                            for (int j = start.x + 1; j < end.x; j++) {
                                Point rock = new Point(j, start.y);
                                matrix[rock.y][rock.x] = '#';
                            }
                        }
                    }
                }

                line = reader.readLine();
            }
            int counter = 0;
            boolean exit = false;


            while (!exit) {
                counter++;
                int j = 500;
                for (int i = 0; i < 161; i++) {
                    if (matrix[i+1][j] == '#' || matrix[i+1][j] == 'o') {
                        if (matrix[i+1][j-1] == '.') j = j - 1;
                        else if (matrix[i+1][j+1] == '.') j = j + 1;
                        else {
                            matrix[i][j] = 'o';
                            if (matrix[0][500] == 'o') exit = true;
                            break;
                        }
                    }
                }
            }
            reader.close();

            System.out.println(counter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
