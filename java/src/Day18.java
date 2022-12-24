import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day18 {

    public static void main(String[] args) {
        BufferedReader reader;
        ArrayList<Cube> surfaceArea = new ArrayList<>();
        int area = 0;

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();

            while (line != null) {
                String[] position = line.split(",");
                int x = Integer.parseInt(position[0], 10);
                int y = Integer.parseInt(position[1], 10);
                int z = Integer.parseInt(position[2], 10);

                Cube current = new Cube(x, y, z);

                int counter = 0;
                for (int i = 0; i < surfaceArea.size(); i++) {
                    if (current.adjoins(surfaceArea.get(i))) counter++;
                }

                area += 6 - (counter * 2);
                surfaceArea.add(current);

                line = reader.readLine();
            }
            reader.close();

            System.out.println("Part 1: " + area);

            for (int i = 0; i < surfaceArea.size(); i++) {
                Cube current = surfaceArea.get(i);
                int x = current.x;
                int y = current.y;
                int z = current.z;
                MapCube visited = new MapCube();

                if (new Cube(x + 1, y, z).inner(surfaceArea)) if (innerSearch(new Cube(x + 1, y, z), surfaceArea, visited)) area--;
                visited.clear();
                if (new Cube(x - 1, y, z).inner(surfaceArea)) if (innerSearch(new Cube(x - 1, y, z), surfaceArea, visited)) area--;
                visited.clear();
                if (new Cube(x, y + 1, z).inner(surfaceArea)) if (innerSearch(new Cube(x, y + 1, z), surfaceArea, visited)) area--;
                visited.clear();
                if (new Cube(x, y - 1, z).inner(surfaceArea)) if (innerSearch(new Cube(x, y - 1, z), surfaceArea, visited)) area--;
                visited.clear();
                if (new Cube(x, y, z + 1).inner(surfaceArea)) if (innerSearch(new Cube(x, y, z + 1), surfaceArea, visited)) area--;
                visited.clear();
                if (new Cube(x, y, z - 1).inner(surfaceArea)) if (innerSearch(new Cube(x, y, z - 1), surfaceArea, visited)) area--;
                visited.clear();
            }

            System.out.println("Part 2: " + area);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean innerSearch(Cube c, ArrayList<Cube> area, MapCube visited) {
        if (visited.contains(c)) return true;

        visited.add(c);
        for (int i = 0; i < area.size(); i++) {
            if (area.get(i).equals(c)) return true;
        }

        if (!c.inner(area)) return false;

        int x = c.x;
        int y = c.y;
        int z = c.z;

        boolean result = innerSearch(new Cube(x + 1, y, z), area, visited);
        if (!result) return false;
        result = innerSearch(new Cube(x - 1, y, z), area, visited);
        if (!result) return false;
        result = innerSearch(new Cube(x, y + 1, z), area, visited);
        if (!result) return false;
        result = innerSearch(new Cube(x, y - 1, z), area, visited);
        if (!result) return false;
        result = innerSearch(new Cube(x, y, z + 1), area, visited);
        if (!result) return false;
        result = innerSearch(new Cube(x, y, z - 1), area, visited);
        if (!result) return false;

        return true;
    }
}
