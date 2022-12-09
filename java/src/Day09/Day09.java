import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day09 {

    public static void main(String []args) {
        BufferedReader reader;
        ArrayList<String> tailPoints = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();
            Point[] knots = new Point[10];
            for (int i = 0; i < knots.length; i++) {
                knots[i] = new Point(0, 0);
            }

            String tailPosition = "0,0";
            tailPoints.add(tailPosition);

            while (line != null) {
                char direction = line.charAt(0);
                int moves = Integer.parseInt(line.substring(2,line.length()), 10);

                for (int i = 0; i < moves; i++) {
                    knots[0].move(direction);

                    for (int j = 0; j < knots.length - 1; j++) {
                        if (knots[j].isNear(knots[j+1])) break;

                        if (knots[j].x == knots[j+1].x) {
                            if (knots[j].y == knots[j+1].y + 2) knots[j+1].move('R');
                            else if (knots[j].y == knots[j+1].y - 2) knots[j+1].move('L');
                        } else if (knots[j].y == knots[j+1].y) {
                            if (knots[j].x == knots[j+1].x + 2) knots[j+1].move('D');
                            else if (knots[j].x == knots[j+1].x - 2) knots[j+1].move('U');
                        } else {
                            if (knots[j].x < knots[j+1].x) {
                                knots[j+1].move('U');
                                if (knots[j].y < knots[j+1].y) knots[j+1].move('L');
                                else if (knots[j].y > knots[j+1].y) knots[j+1].move('R');
                            } else {
                                knots[j+1].move('D');
                                if (knots[j].y < knots[j+1].y) knots[j+1].move('L');
                                else if (knots[j].y > knots[j+1].y) knots[j+1].move('R');
                            }
                        }

                        tailPosition = knots[9].x + "," + knots[9].y;

                        if (!tailPoints.contains(tailPosition)) tailPoints.add(tailPosition);
                    }
                }

                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(tailPoints.size());
    }
}
