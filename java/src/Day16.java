import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day16_1 {

    public static void main(String []args) {
        BufferedReader reader;
        ArrayList<String> nextVert = new ArrayList<>();
        ArrayList<Vertex> graph = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();

            while (line != null) {
                String[] in = line.split(";");

                String[] getRate = in[0].split(" has flow rate=");
                String[] getValve = getRate[0].split(" ");
                String id = getValve[1];
                int val = Integer.parseInt(getRate[1], 10);

                graph.add(new Vertex(id, val));
                if (in[1].length() > 25) {
                    String[] vertices = in[1].split("tunnels lead to valves ");
                    nextVert.add(vertices[1]);
                } else {
                    String[] vertices = in[1].split("tunnel leads to valve ");
                    nextVert.add(vertices[1]);
                }

                line = reader.readLine();
            }
            reader.close();

            Vertex aa = new Vertex();

            for (int i = 0; i < nextVert.size(); i++) {
                String[] vertices =  nextVert.get(i).split(", ");
                Vertex current = graph.get(i);
                for (int k = 0; k < vertices.length; k++) {
                    current.addVertex(search(graph, vertices[k]));
                }
                if (current.id.equals("AA")) aa = current;
            }

            ArrayList<Configuration> confs = new ArrayList<>();
            confs.add(new Configuration(aa, "", 0, false));

            for (int minute = 0; minute < 30; minute++) {
                ArrayList<Configuration> newConfs = new ArrayList<>();
                for (int i = 0; i < confs.size(); i++) {
                    Configuration current = confs.get(i);
                    /**
                    * Change this for other inputs
                    */
                    if (minute > 8 && current.pressure < 800) continue;
                    if (minute > 13 && current.pressure < 1200) continue;
                    if (minute > 18 && current.pressure < 1600) continue;
                    if (minute > 23 && current.pressure < 1800) continue;
                    if (minute > 25 && current.pressure < 1900) continue;
                    /**
                    *
                    */
                    if (current.position.value > 0 && !current.open && !current.openedValves.contains(current.position.id)) {
                        newConfs.add(new Configuration(current.position, current.openedValves, current.pressure, true));
                    }


                    for (int j = 0; j < current.position.next.size(); j++) {
                        Vertex next = current.position.next.get(j);
                        if (current.open) {
                            String valves = current.openedValves + "-" + current.position.id + "-";
                            newConfs.add(new Configuration(next, valves, current.pressure + current.position.value * (30 - minute), false));
                        } else newConfs.add(new Configuration(next, current.openedValves, current.pressure, false));
                    }
                }

                confs = newConfs;
            }

            System.out.println(maxPressure(confs));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Vertex search(ArrayList<Vertex> graph, String s) {
        for (int i = 0; i < graph.size(); i++) {
            if (s.equals(graph.get(i).id)) return graph.get(i);
        }

        return null;
    }

    public static int maxPressure(ArrayList<Configuration> confs) {
        int max = confs.get(0).pressure;

        for (int i = 1; i < confs.size(); i++) {
            if (max < confs.get(i).pressure) max = confs.get(i).pressure;
        }

        return max;
    }
}
