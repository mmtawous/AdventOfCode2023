import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solve2 {

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        // Skip seeds:
        scnr.next();

        ArrayList<List<Long>> seeds = new ArrayList<>();
        while (scnr.hasNextLong()) {
            List<Long> temp = new ArrayList<>();
            temp.add(scnr.nextLong());
            temp.add(scnr.nextLong() + temp.get(0));

            seeds.add(temp);
        }

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

            List<List<Long>> temp = new ArrayList<>();
            while (!seeds.isEmpty()) {
                List<Long> seed = seeds.remove(seeds.size() - 1);
                boolean broke = false;
                for (List<Long> range : list) {
                    long s = seed.get(0);
                    long e = seed.get(1);

                    long destStart = range.get(0);
                    long sourceStart = range.get(1);
                    long len = range.get(2);

                    long os = Math.max(s, sourceStart);
                    long oe = Math.min(e, sourceStart + len);

                    if (os < oe) {
                        List<Long> seedRange = new ArrayList<>();
                        seedRange.add(os - sourceStart + destStart);
                        seedRange.add(oe - sourceStart + destStart);
                        temp.add(seedRange);
                        if (os > s) {
                            List<Long> breakRange = new ArrayList<>();
                            breakRange.add(s);
                            breakRange.add(os);
                            seeds.add(breakRange);
                        }
                        if (e > oe) {
                            List<Long> breakRange = new ArrayList<>();
                            breakRange.add(oe);
                            breakRange.add(e);
                            seeds.add(breakRange);
                        }

                        broke = true;
                        break;
                    }
                }

                if (!broke) {
                    temp.add(seed);
                }
            }

            seeds = new ArrayList<>(temp);
        }

        long minLoc = Integer.MAX_VALUE;
        for (int i = 0; i < seeds.size(); i++) {
            if (seeds.get(i).get(0) < minLoc)
                minLoc = seeds.get(i).get(0);
        }

        System.out.println("Answer is: " + minLoc);

        scnr.close();
    }

}