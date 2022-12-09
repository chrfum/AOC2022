public class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isNear(Point other) {
        if (this.x == other.x) {
            if (this.y - other.y >= -1 && this.y - other.y <= 1) return true;
        } else if (this.x == other.x - 1) {
            if (this.y - other.y >= -1 && this.y - other.y <= 1) return true;
        } else if (this.x == other.x + 1) {
            if (this.y - other.y >= -1 && this.y - other.y <= 1) return true;
        }

        return false;
    }

    public void move(char direction) {
        if (direction == 'U') this.x--;
        if (direction == 'D') this.x++;
        if (direction == 'R') this.y++;
        if (direction == 'L') this.y--;
    }
}
