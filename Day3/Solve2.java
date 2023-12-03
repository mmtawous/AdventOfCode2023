import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class Solve2 {

    public static final int SIZE = 140;

    public static void main(String[] args) throws FileNotFoundException {
        // Open input file.
        File file = new File("./Day3/input.txt");
        Scanner scnr = new Scanner(file);
        // Read data into 2d array of chars.
        char[][] data = new char[SIZE][SIZE];

        ArrayList<Integer> nums = new ArrayList<Integer>();
        LinkedHashMap<Coordinate, List<Integer>> map = new LinkedHashMap<Coordinate, List<Integer>>();

        for (int i = 0; i < SIZE; i++) {
            String line = scnr.nextLine();
            String[] numsArr = line.split("[^0-9]+");

            for (String s : numsArr)
                if (!s.isEmpty())
                    nums.add(Integer.parseInt(s));

            data[i] = line.toCharArray();
        }

        scnr.close();

        final int[][] directions = { { 1, 1 }, { -1, 1 }, { 1, -1 }, { -1, -1 }, { 0, 1 }, { 1, 0 }, { 0, -1 },
                { -1, 0 } };

        // You have a bunch of numbers. Each number has a list of adjacent special
        // characters. Save the coords of the special if it is '*'. The objective is to
        // find, for each '*' all the numbers that are saved for it.
        
        int sum = 0;
        boolean atNumber = false;
        int idx = 0;

        // Keep a list of stars found for the current number. If we don't we risk double
        // count numbers.
        List<Coordinate> temp = new ArrayList<Coordinate>();

        // If you went from being at a number to not then you should consume the number
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {

                // Check all directions if the current char is a digit.
                if (Character.isDigit(data[i][j])) {
                    atNumber = true;
                    for (int[] dir : directions) {
                        int x = j + dir[0];
                        int y = i + dir[1];
                        Coordinate coord = new Coordinate(x, y);

                        // If we find a star make sure the coords are inbound and that we didn't already
                        // find this particular star.
                        if (isInBounds(x, y) && data[y][x] == '*' && !temp.contains(coord)) {

                            if (map.get(coord) == null) {
                                List<Integer> list = new ArrayList<Integer>();
                                list.add(nums.get(idx));
                                map.put(coord, list);

                            } else {
                                List<Integer> list = map.get(coord);
                                list.add(nums.get(idx));
                            }

                            temp.add(coord);
                        }
                    }

                } else {
                    // If we were at a number and now we are not then consume the number because it
                    // wasn't a part number
                    if (atNumber) {
                        idx++;
                        atNumber = false;
                        temp.clear();
                    }
                }

            }

            if (atNumber) {
                idx++;
                atNumber = false;
                temp.clear();
            }
        }

        // Iterate over the stars.
        for (List<Integer> list : map.values()) {
            if (list.size() == 2) {
                sum += (list.get(0) * list.get(1));
            }
        }

        System.out.println("Answer is: " + sum);

        scnr.close();
    }

    private static boolean isInBounds(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return false;
        }

        return true;
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