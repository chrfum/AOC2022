public class Configuration {
    public Vertex position;
    public String openedValves;
    public int pressure;
    public boolean open;

    public Configuration (Vertex v, String opened, int pressure, boolean open) {
        this.position = v;
        this.openedValves = opened;
        this.pressure = pressure;
        this.open = open;
    }

    @Override
    public String toString() {
        String result = "ME: Valve: " + position.id + ", openedValves: " + openedValves + ", Pressure: " + pressure;
        if (open) result += " Open";

        return result;
    }
}
