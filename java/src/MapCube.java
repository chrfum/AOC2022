import java.util.ArrayList;

public class MapCube {
    ArrayList<String> key;
    ArrayList<Boolean> value;

    public MapCube() {
        this.key = new ArrayList<>();
        this.value = new ArrayList<>();
    }

    public void add(Cube c) {
        String entry = c.toString();

        key.add(entry);
        value.add(false);
    }

    public void clear() {
        this.key = new ArrayList<>();
        this.value = new ArrayList<>();
    }

    public boolean contains(Cube c) {
        String entry = c.toString();

        for (int i = 0; i < this.key.size(); i++) {
            if (entry.equals(this.key.get(i))) return true;
        }

        return false;
    }
}
