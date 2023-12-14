import java.util.Scanner;

public class Day14p1 {

    public static final int SIZE = 100;

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        char[][] grid = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            grid[i] = scnr.nextLine().toCharArray();
        }

        tilt(grid);

        for (int i = 0; i < SIZE; i++) {

            System.out.println(new String(grid[i]));

        }

        int load = getLoad(grid);

        System.out.println("Answer is: " + load);

        scnr.close();
    }

    public static void tilt(char[][] grid) {

        for (int col = 0; col < SIZE; col++) {
            for (int row = 1; row < SIZE; row++) {
                if (grid[row][col] == 'O') {
                    grid[row][col] = '.';

                    int r = row - 1;
                    while (r > 0 && grid[r][col] != 'O' && grid[r][col] != '#') {
                        r--;
                    }

                    if (grid[r][col] == 'O' || grid[r][col] == '#') {
                        while (r < SIZE - 1 && grid[r][col] != '.') {
                            r++;
                        }
                    }

                    grid[r][col] = 'O';

                }
            }
        }
    }

    public static int getLoad(char[][] grid) {
        int load = 0;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 'O') {
                    load += (SIZE - i);
                }
            }
        }

        return load;
    }

}