import java.util.ArrayList;

public class Packet {
    public ArrayList<String> packets;

    public Packet(String s) {
        this.packets = new ArrayList<>();
        int counter = 0;
        int idx = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '[') counter++;
            if (s.charAt(i) == ']') counter--;

            if (s.charAt(i) == ']' && counter == - 1) {
                this.packets.add(s.substring(idx, i));
                idx = i + 1;
            }

            if (s.charAt(i) == ',' && counter == 0) {
                this.packets.add(s.substring(idx, i));
                idx = i + 1;
            }
        }
    }

    public int size() {
        return (this.packets.size());
    }

    public boolean isInt(int idx) {
        return (this.packets.get(idx).charAt(0) != '[');
    }

    public boolean isNull(int idx) {
        return (this.packets.get(idx).equals(""));
    }
    public int getInt(int idx) {
        return Integer.parseInt(this.packets.get(idx), 10);
    }

    public String getList(int idx) {
        return this.packets.get(idx);
    }

    @Override
    public String toString() {
        String result = "[";

        for (int i = 0; i < this.packets.size(); i++) {
            if (i < this.packets.size() - 1) result += this.packets.get(i) + ",";
            else result += this.packets.get(i);
        }

        return result + "]";
    }
}
