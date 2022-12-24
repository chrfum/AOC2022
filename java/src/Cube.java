import java.util.ArrayList;

public class Cube {
    public int x;
    public int y;
    public int z;

    public Cube(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean adjoins(Cube other) {
        if (Math.abs(x - other.x) == 1) {
            if (y == other.y && z == other.z) return true;
        }

        if (Math.abs(y - other.y) == 1) {
            if (x == other.x && z == other.z) return true;
        }

        if (Math.abs(z - other.z) == 1) {
            if (x == other.x && y == other.y) return true;
        }

        return false;
    }

    public boolean inner(ArrayList<Cube> others) {
        boolean right = false;
        boolean left = false;
        boolean up = false;
        boolean down = false;
        boolean front = false;
        boolean back = false;
        for (int i = 0; i < others.size(); i++) {
            if (this.equals(others.get(i))) return false;
            if (x == others.get(i).x && y == others.get(i).y && z > others.get(i).z) left = true;
            if (x == others.get(i).x && y == others.get(i).y && z < others.get(i).z) right = true;
            if (x == others.get(i).x && z == others.get(i).z && y > others.get(i).y) down = true;
            if (x == others.get(i).x && z == others.get(i).z && y < others.get(i).y) up = true;
            if (y == others.get(i).y && z == others.get(i).z && x > others.get(i).x) back = true;
            if (y == others.get(i).y && z == others.get(i).z && x < others.get(i).x) front = true;
        }

        return (right && left && up && down && front && back);
    }

    public int countAdjoins(ArrayList<Cube> others) {
        int counter = 0;

        for (int i = 0; i < others.size(); i++) {
            if (this.adjoins(others.get(i))) counter++;
        }

        return counter;
    }

    @Override
    public String toString() {
        return "x: " + x + " - y: " + y + " - z: " + z;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cube)) return false;

        Cube other = (Cube) o;

        return (x == other.x && y == other.y && z == other.z);
    }
}
