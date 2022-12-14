import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day13 {

    public static void main(String[] args) {
        BufferedReader reader;
        int result = 0;
        ArrayList<Packet> orderedPackets = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();
            int pairCounter = 1;
            orderedPackets.add(new Packet("[[2]]"));
            orderedPackets.add(new Packet("[[6]]"));

            while (line != null) {
                if (line.equals("")) {
                    pairCounter++;
                    line = reader.readLine();
                }

                Packet firstPacket = new Packet(line);
                orderedPackets.add(firstPacket);

                line = reader.readLine();
                Packet secondPacket = new Packet(line);
                orderedPackets.add(secondPacket);

                if (compare(firstPacket, secondPacket) == 1) result += pairCounter;

                line = reader.readLine();
            }
            reader.close();

            order(orderedPackets);

            System.out.println(result);
            System.out.println(getResult(orderedPackets));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int compare(Packet first, Packet second) {
        for (int i = 0; i < first.size(); i++) {
            if (i == second.size()) break;

            if (first.isNull(i) && second.isNull(i)) return 2;
            if (first.isNull(i)) return 1;
            if (second.isNull(i)) return 0;

            if (first.isInt(i) && second.isInt(i)) {
                if (first.getInt(i) < second.getInt(i)) return 1;
                if (first.getInt(i) > second.getInt(i)) return 0;
            } else if (!first.isInt(i) && !second.isInt(i)) {
                if (compare(new Packet(first.getList(i)), new Packet(second.getList(i))) == 2) continue;
                else return (compare(new Packet(first.getList(i)), new Packet(second.getList(i))));
            } else {

                if (first.isInt(i)) {
                    if (compare(new Packet("[" + first.getInt(i) + "]"), new Packet(second.getList(i))) == 2) continue;
                    return (compare(new Packet("[" + first.getInt(i) + "]"), new Packet(second.getList(i))));
                }
                if (second.isInt(i)) {
                    if (compare(new Packet(first.getList(i)), new Packet("[" + second.getInt(i) + "]")) == 2) continue;
                    return (compare(new Packet(first.getList(i)), new Packet("[" + second.getInt(i) + "]")));
                }
            }

        }

        if (first.size() < second.size()) return 1;
        if (second.size() < first.size()) return 0;

        return 2;
    }

    public static void order(ArrayList<Packet> orderedPackets) {
        for (int i = 0; i < orderedPackets.size() - 1; i++) {
            Packet min = orderedPackets.get(i);
            Packet current = orderedPackets.get(i);
            int idx = i;

            for (int j = i + 1; j < orderedPackets.size(); j++) {
                if (compare(min, orderedPackets.get(j)) == 0) {
                    min = orderedPackets.get(j);
                    idx = j;
                }
            }

            orderedPackets.set(i, min);
            orderedPackets.set(idx, current);
        }
    }

    public static int getResult(ArrayList<Packet> orderedPackets) {
        int result = 1;

        for (int i = 0; i < orderedPackets.size(); i++) {
            if (orderedPackets.get(i).toString().equals("[[2]]")) result *= i + 1;
            if (orderedPackets.get(i).toString().equals("[[6]]")) {
                result *= i + 1;
                break;
            }
        }

        return result;
    }
}
