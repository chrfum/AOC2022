import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day15 {

    public static void main(String[] args) {
        BufferedReader reader;
        ArrayList<Interval> notBeacons = new ArrayList<>();
        ArrayList<Point> sensors = new ArrayList<>();
        ArrayList<Point> beacons = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = reader.readLine();

            while (line != null) {
                String[] in = line.split(": ");
                String[] splitSens = in[0].split("Sensor at x=");
                String[] inSens = splitSens[1].split(", y=");
                String[] splitBeacon = in[1].split("closest beacon is at x=");
                String[] inBeacon = splitBeacon[1].split(", y=");

                Point sensor = new Point(Integer.parseInt(inSens[0], 10), Integer.parseInt(inSens[1], 10));
                sensors.add(sensor);
                Point beacon = new Point(Integer.parseInt(inBeacon[0], 10), Integer.parseInt(inBeacon[1], 10));
                beacons.add(beacon);

                line = reader.readLine();
            }
            reader.close();


            for (int k = 0; k < 4000000; k++) {
                for (int i = 0; i < sensors.size(); i++) {
                    Point sensor = sensors.get(i);
                    Point beacon = beacons.get(i);
                    int distance = distance(sensor, beacon);

                    if (sensor.y - distance <= k && sensor.y + distance >= k) {
                        int counter = distance - Math.abs(k - sensor.y);

                        notBeacons.add(new Interval(sensor.x - counter, sensor.x+counter));
                    }
                }

                Interval result = notBeacons.get(0);

                while (notBeacons.size() != 0) {
                    Interval prec = result;
                    for (int i = notBeacons.size() - 1; i >= 0; i--) {
                        if (result.isIn(notBeacons.get(i))) {
                            result = result.merge(notBeacons.get(i));
                            notBeacons.remove(i);
                        }
                    }

                    if (prec == result) break;
                }

                if (notBeacons.size() > 0) {
                    long tuningFrequency = 0;
                    if (result.tail > 4000000) tuningFrequency = ((long)4000000 * (long)(result.head - 1)) + k;
                    else tuningFrequency = ((long)4000000 * (long)(result.tail + 1)) + k;
                    System.out.println(tuningFrequency);
                    break;
                }

                notBeacons.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int distance(Point sensor, Point beacon) {
        int x = sensor.x - beacon.x;
        int y = sensor.y - beacon.y;

        return Math.abs(x) + Math.abs(y);
    }
}
