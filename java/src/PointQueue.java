import java.util.ArrayList;

public class PointQueue {
    public ArrayList<Point> points;

    public PointQueue() {
        this.points = new ArrayList<>();
    }

    public PointQueue(Point x) {
        this();
        this.points.add(x);
    }

    public void enqueue(Point x) {
        this.points.add(x);
    }

    public Point dequeue() {
        Point x = this.points.get(0);
        this.points.remove(0);

        return x;
    }

    public int size() {
        return this.points.size();
    }

    public boolean isEmpty() {
        return (this.size() == 0);
    }

    public Point last() {
        return (this.points.get(this.size() - 1));
    }

    @Override
    public String toString() {
        String result = "[";

        if (this.isEmpty()) result += "]";

        for (int i = 0; i < this.size(); i++) {
            if (i != this.size() - 1) result += this.points.get(i) + " - ";
            else result += this.points.get(i) + "]";
        }

        return result;
    }
}
