import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day12 {

    public static void main(String []args) {
        BufferedReader reader;
        ArrayList<ArrayList<Character>> heightmap = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();

            while (line != null) {
                ArrayList<Character> heightmapRow = new ArrayList<>();

                for (int i = 0; i < line.length(); i++) {
                    heightmapRow.add(line.charAt(i));
                }

                heightmap.add(heightmapRow);
                line = reader.readLine();
            }

            Point end = new Point(0, 0);

            for (int i = 0; i < heightmap.size(); i++) {
                for (int j = 0; j < heightmap.get(i).size(); j++) {
                    if (heightmap.get(i).get(j) == 'E') {
                        end.x = i;
                        end.y = j;
                    }
                }
            }

            ArrayList<String> visited = new ArrayList<>();
            PointQueue toVisit = new PointQueue(end);
            int stepToStart = -1;
            int stepToA = -1;
            boolean increase = true;
            boolean update = false;
            boolean stop = false;
            Point lastOfVisit = new Point(end.x, end.y);

            while (!toVisit.isEmpty()) {
                Point p = toVisit.dequeue();

                if (p == lastOfVisit) increase = true;

                if (increase) {
                    stepToStart++;
                    stepToA++;
                    increase = false;
                    update = true;
                }

                ArrayList<Point> neighbors = findNearby(p, heightmap);

                for (int i = 0; i < neighbors.size(); i++) {
                    if (heightmap.get(neighbors.get(i).x).get(neighbors.get(i).y) == 'S') System.out.println(stepToStart);
                    if (heightmap.get(neighbors.get(i).x).get(neighbors.get(i).y) == 'a') stop = true;;
                    if (!visited.contains(neighbors.get(i).x + "," + neighbors.get(i).y)) {
                        visited.add(neighbors.get(i).x + "," + neighbors.get(i).y);
                        toVisit.enqueue(neighbors.get(i));
                    }
                }

                if (stop) break;

                if (update) {
                    update = false;
                    if (!toVisit.isEmpty()) lastOfVisit = toVisit.last();
                }
            }

            System.out.println(stepToA);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Point> findNearby(Point p, ArrayList<ArrayList<Character>> heightmap) {
        ArrayList<Point> neighbors = new ArrayList<>();

        if (heightmap.get(p.x).get(p.y) == 'E') {
            if (p.x > 0 && heightmap.get(p.x - 1).get(p.y) == 'z') neighbors.add(new Point(p.x - 1, p.y));
            if (p.y > 0 && heightmap.get(p.x).get(p.y - 1) == 'z') neighbors.add(new Point(p.x, p.y - 1));
            if (p.x < heightmap.size() - 1 && heightmap.get(p.x + 1).get(p.y) == 'z') neighbors.add(new Point(p.x + 1, p.y));
            if (p.y < heightmap.get(0).size() - 1 && heightmap.get(p.x).get(p.y + 1) == 'z') neighbors.add(new Point(p.x, p.y + 1));
        } else {
            if (p.x > 0 && heightmap.get(p.x - 1).get(p.y) + 1 >= heightmap.get(p.x).get(p.y)) neighbors.add(new Point(p.x - 1, p.y));
            if (p.y > 0 && heightmap.get(p.x).get(p.y - 1) + 1 >= heightmap.get(p.x).get(p.y)) neighbors.add(new Point(p.x, p.y - 1));
            if (p.x < heightmap.size() - 1 && heightmap.get(p.x + 1).get(p.y) + 1 >= heightmap.get(p.x).get(p.y)) neighbors.add(new Point(p.x + 1, p.y));
            if (p.y < heightmap.get(0).size() - 1 && heightmap.get(p.x).get(p.y + 1) + 1 >= heightmap.get(p.x).get(p.y)) neighbors.add(new Point(p.x, p.y + 1));
        }

        if (heightmap.get(p.x).get(p.y) == 'a') {
            if (p.x > 0 && heightmap.get(p.x - 1).get(p.y) == 'S') neighbors.add(new Point(p.x - 1, p.y));
            if (p.y > 0 && heightmap.get(p.x).get(p.y - 1) == 'S') neighbors.add(new Point(p.x, p.y - 1));
            if (p.x < heightmap.size() - 1 && heightmap.get(p.x + 1).get(p.y) == 'S') neighbors.add(new Point(p.x + 1, p.y));
            if (p.y < heightmap.get(0).size() - 1 && heightmap.get(p.x).get(p.y + 1) == 'S') neighbors.add(new Point(p.x, p.y + 1));
        }
        return neighbors;
    }
}
