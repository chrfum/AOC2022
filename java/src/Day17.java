import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day17 {

    public static void main(String[] args) {
        BufferedReader reader;
        int rocksCounter = 0;
        char[][] tower = new char[4000][7];
        int l = 2;
        int r = 5;
        int h = 3;

        for (int i = 0; i < tower.length; i++) {
            for (int j = 0; j < tower[i].length; j++) {
                tower[i][j] = '.';
            }
        }

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();
            reader.close();

            while (rocksCounter <= 2021) {
                for (int i = 0; i < line.length(); i++) {
                    char move = line.charAt(i);

                    switch (rocksCounter % 5) {
                    case 0:
                    if (move == '<' && l > 0 && tower[h][l - 1] == '.') {
                        l--;
                        r--;
                    } else if (move == '>' && r < 6 && tower[h][r + 1] == '.') {
                        l++;
                        r++;
                    }

                    if (h > 0 && tower[h - 1][l] == '.' && tower[h - 1][l + 1] == '.'
                    && tower[h - 1][r - 1] == '.' && tower[h - 1][r] == '.') {
                        h--;
                    } else {
                        tower[h][l] = '#';
                        tower[h][l + 1] = '#';
                        tower[h][r - 1] = '#';
                        tower[h][r] = '#';



                        rocksCounter++;
                        l = 2;
                        r = 4;
                        h = maxHeight(tower) + 4;
                    }
                    break;

                    case 1:
                    if (move == '<' && l > 0 && tower[h + 1][l - 1] == '.'
                    && tower[h][l] == '.'
                    && tower[h + 2][l] == '.') {
                        l--;
                        r--;
                    } else if (move == '>' && r < 6 && tower[h + 1][r + 1] == '.'
                    && tower[h][r] == '.'
                    && tower[h + 2][r] == '.') {
                        l++;
                        r++;
                    }

                    if (h > 0 && tower[h][l] == '.' && tower[h - 1][l + 1] == '.' && tower[h][r] == '.') {
                        h--;
                    } else {
                        tower[h + 1][l] = '#';
                        tower[h + 1][l + 1] = '#';
                        tower[h + 1][r] = '#';
                        tower[h][l + 1] = '#';
                        tower[h + 2][l + 1] = '#';
                        rocksCounter++;
                        l = 2;
                        r = 4;
                        h = maxHeight(tower) + 4;
                    }
                    break;

                    case 2:
                    if (move == '<' && l > 0 && tower[h][l - 1] == '.'
                    && tower[h + 1][r - 1] == '.'
                    && tower[h + 2][r - 1] == '.') {
                        l--;
                        r--;
                    } else if (move == '>' && r < 6 && tower[h][r + 1] == '.'
                    && tower[h + 1][r + 1] == '.'
                    && tower[h + 2][r + 1] == '.') {
                        l++;
                        r++;
                    }

                    if (h > 0 && tower[h - 1][l] == '.' && tower[h - 1][l + 1] == '.'
                    && tower[h - 1][r] == '.') {
                        h--;
                    } else {
                        tower[h][l] = '#';
                        tower[h][l + 1] = '#';
                        tower[h][r] = '#';
                        tower[h + 1][r] = '#';
                        tower[h + 2][r] = '#';
                        rocksCounter++;
                        l = 2;
                        r = 2;
                        h = maxHeight(tower) + 4;
                    }
                    break;

                    case 3:
                    if (move == '<' && l > 0 && tower[h][l - 1] == '.'
                    && tower[h + 1][l - 1] == '.'
                    && tower[h + 2][l - 1] == '.'
                    && tower[h + 3][l - 1] == '.') {
                        l--;
                        r--;
                    } else if (move == '>' && r < 6 && tower[h][r + 1] == '.'
                    && tower[h + 1][r + 1] == '.'
                    && tower[h + 2][r + 1] == '.'
                    && tower[h + 3][r + 1] == '.') {
                        l++;
                        r++;
                    }

                    if (h > 0 && tower[h - 1][l] == '.') {
                        h--;
                    } else {
                        tower[h][l] = '#';
                        tower[h + 1][l] = '#';
                        tower[h + 2][l] = '#';
                        tower[h + 3][l] = '#';
                        rocksCounter++;
                        l = 2;
                        r = 3;
                        h = maxHeight(tower) + 4;
                    }
                    break;

                    case 4:
                    if (move == '<' && l > 0 && tower[h][l - 1] == '.'
                    && tower[h + 1][l - 1] == '.') {
                        l--;
                        r--;
                    } else if (move == '>' && r < 6 && tower[h][r + 1] == '.'
                    && tower[h + 1][r + 1] == '.') {
                        l++;
                        r++;
                    }

                    if (h > 0 && tower[h - 1][l] == '.' && tower[h - 1][r] == '.') {
                        h--;
                    } else {
                        tower[h][l] = '#';
                        tower[h + 1][l] = '#';
                        tower[h][r] = '#';
                        tower[h + 1][r] = '#';
                        rocksCounter++;
                        l = 2;
                        r = 5;
                        h = maxHeight(tower) + 4;
                    }
                    break;

                    }

                    if (rocksCounter == 2022) {
                        System.out.println(maxHeight(tower) + 1);
                        break;
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int maxHeight(char[][] tower) {
        int h = 0;

        for (int i = 0; i < tower.length; i++) {
            for (int j = 0; j < tower[i].length; j++) {
                if (tower[i][j] == '#') {
                    h = i;
                    break;
                } else if (j == tower[i].length - 1) {
                    return h;
                }
            }
        }

        return h;
    }
}
