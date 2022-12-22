import java.util.ArrayList;

public class Vertex {
    public int value;
    public ArrayList<Vertex> next;
    public String id;

    public Vertex() {
        this.value = 0;
        this.id = "";
        this.next = new ArrayList<>();
    }

    public Vertex(String id, int value) {
        this.value = value;
        this.next = new ArrayList<>();
        this.id = id;
    }

    public void addVertex(Vertex v) {
        next.add(v);
    }

    @Override
    public String toString() {
        String result = "Vertex: " + id + " Value: " + value + " Next: [";

        for (int i = 0; i < next.size(); i++) {
            if (i < next.size() - 1) result += next.get(i).id + " - ";
            else result += next.get(i).id;
        }

        return result += "]";
    }
}
