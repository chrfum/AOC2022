public class Interval {
    public int head;
    public int tail;

    public Interval(int head, int tail) {
        this.head = head;
        this.tail = tail;
    }

    public boolean isIn(Interval other) {
        if (this.head == other.head) return true;

        if (this.head < other.head) {
            if (this.tail >= other.head) return true;
        } else if (this.head >= other.head) {
            if (this.head <= other.tail) return true;
        }

        return false;
    }

    public Interval merge(Interval other) {
        if (this.head == other.head) {
            if (this.tail > other.tail) return new Interval(this.head, this.tail);
            else return new Interval(this.head, other.tail);
        }

        if (this.head < other.head) {
            if (this.tail >= other.head) {
                if (this.tail < other.tail) return new Interval(this.head, other.tail);
                else return new Interval(this.head, this.tail);
            }
        } else if (this.head >= other.head) {
            if (this.head <= other.tail) {
                if (this.tail < other.tail) return new Interval(other.head, other.tail);
                else return new Interval(other.head, this.tail);
            }
        }

        return this;
    }

    public int getEl() {
        return (this.tail - (this.head - 1));
    }

    @Override
    public String toString() {
        return head + " - " + tail;
    }
}
