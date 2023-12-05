import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solve {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scnr = new Scanner(System.in);

        // Skip seeds:
        scnr.next();

        ArrayList<Long> seeds = new ArrayList<>();
        while (scnr.hasNextLong()) {
            seeds.add(scnr.nextLong());
        }

        System.out.println(seeds.toString());

        for (int i = 0; i < 7; i++) {
            // Skip info line
            scnr.next();
            scnr.next();

            List<List<Long>> list = new ArrayList<List<Long>>();

            while (scnr.hasNextLong()) {
                List<Long> vals = new ArrayList<>();
                vals.add(scnr.nextLong());
                vals.add(scnr.nextLong());
                vals.add(scnr.nextLong());

                list.add(vals);
            }

            System.out.println("Start");
            System.out.println(list.toString());
            System.out.println("End");

            // Convert all fromVals to toVals
            for (int j = 0; j < seeds.size(); j++) {
                long from = seeds.get(j);
                long to = -1;

                for (int k = 0; k < list.size(); k++) {
                    // Check if fromVal is in this range
                    if (from <= list.get(k).get(1) + list.get(k).get(2) && from >= list.get(k).get(1)) {
                        long idx = from - list.get(k).get(1);
                        to = list.get(k).get(0) + idx;
                        break;
                    }
                }

                if (to != -1) {
                    seeds.set(j, to);
                }
            }
        }

        long minLoc = Integer.MAX_VALUE;
        for (int i = 0; i < seeds.size(); i++) {
            if (seeds.get(i) < minLoc)
                minLoc = seeds.get(i);
        }

        System.out.println("Answer is: " + minLoc);

        scnr.close();
    }

}