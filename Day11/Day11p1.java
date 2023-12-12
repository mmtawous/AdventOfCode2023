import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Day11p1 {
    public static final int SIZE = 140;

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        int sum = 0;
        // Read grid
        List<String> grid = new ArrayList<String>();
        for (int i = 0; i < SIZE; i++) {
            String line = scnr.nextLine();
            grid.add(line);

            if (line.matches("[.]+")) {
                grid.add(line);
            }
        }

        expand(grid);

        for (String s : grid) {
            System.out.println(s);
        }

        HashSet<Coordinate> galaxies = new HashSet<>();
        int cnt = 1;
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(0).length(); j++) {
                if (grid.get(i).charAt(j) == '#') {
                    System.out.println("\n" + cnt);
                    sum += getDistances(i, j, galaxies, grid);
                    cnt++;
                }
            }
        }

        System.out.println("Answer is: " + sum);

        scnr.close();
    }

    public static void expand(List<String> grid) {
        for (int i = 0; i < grid.get(0).length(); i++) {
            boolean broke = false;
            for (int j = 0; j < grid.size(); j++) {
                if (grid.get(j).charAt(i) != '.') {
                    broke = true;
                    break;
                }
            }

            if (!broke) {
                for (int j = 0; j < grid.size(); j++) {
                    String line = grid.get(j);
                    StringBuilder build = new StringBuilder(line);
                    build.insert(i, '.');
                    grid.set(j, build.toString());
                }
                i++;
            }
        }
    }

    public static int getDistances(int r, int c, HashSet<Coordinate> galaxies, List<String> grid) {
        int sum = 0;
        galaxies.add(new Coordinate(r, c));
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(0).length(); j++) {
                if (grid.get(i).charAt(j) == '#' && !galaxies.contains(new Coordinate(i, j))) {
                    System.out.println(Math.abs(r - i) + Math.abs(c - j));
                    sum += Math.abs(r - i) + Math.abs(c - j);
                }
            }
        }

        return sum;
    }

    private static class Coordinate {
        int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Coordinate) {
                Coordinate other = (Coordinate) obj;
                if (other.x == this.x && other.y == this.y)
                    return true;
            }

            return false;
        }

        @Override
        public int hashCode() {
            return x * 31 + y;
        }

        @Override
        public String toString() {
            return "[x: " + x + ", y: " + y + "]";
        }
    }

}