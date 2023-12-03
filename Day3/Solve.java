import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solve {

    public static final int SIZE = 140;

    public static void main(String[] args) throws FileNotFoundException {
        // Open input file.
        File file = new File("./Day3/input.txt");
        Scanner scnr = new Scanner(file);
        // Read data into 2d array of chars.
        char[][] data = new char[SIZE][SIZE];

        ArrayList<Integer> nums = new ArrayList<Integer>();

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

        int sum = 0;
        boolean atNumber = false;
        int idx = 0;

        // If you went from being at a number to not then you should consume the number
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {

                // Check all directions if the current char is a digit.
                if (Character.isDigit(data[i][j])) {
                    atNumber = true;
                    for (int[] dir : directions) {
                        int x = j + dir[0];
                        int y = i + dir[1];

                        // If we find a part number then increment j until we hit a non digit or the end
                        // of the line.
                        if (isInBounds(x, y) && data[y][x] != '.' && !Character.isDigit(data[y][x])) {
                            while (j < data[0].length && Character.isDigit(data[i][j])) {
                                j++;
                            }

                            if (i == 139)
                                System.out.println(nums.get(idx));

                            sum += nums.get(idx);

                            idx++;
                            atNumber = false;
                            break;
                        }
                    }

                } else {
                    // If we were at a number and now we are not then consume the number because it
                    // wasn't a part number
                    if (atNumber) {
                        idx++;
                        atNumber = false;
                    }

                }

            }

            if (atNumber) {
                idx++;
                atNumber = false;
            }
        }

        System.out.println(idx);

        System.out.println("Answer is: " + sum);

        scnr.close();
    }

    private static boolean isInBounds(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return false;
        }

        return true;
    }
}