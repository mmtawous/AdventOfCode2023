import java.util.Scanner;

public class Day14p2 {

    public static final int SIZE = 100;

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        char[][] grid = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            grid[i] = scnr.nextLine().toCharArray();
        }

        /**
         * Statistical analysis shows that total load repeats after 70 spin cycles. The
         * pattern holds true starting from iteration 81. This means that we can
         * calculate the answer for any arbitrary iteration using the following formula
         * where n is the iteration.
         * 
         * Answer = ((n − 81) mod 70) + 81
         */

        long n = 1000000000;
        n -= 81;
        n %= 70;
        n += 81;

        // n should be 90

        for (int i = 0; i < n; i++) {
            spinCycle(grid);
        }

        int load = getLoad(grid);

        System.out.println("Answer is: " + load);

        scnr.close();
    }

    public static void tiltNorth(char[][] grid) {

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

    public static void tiltSouth(char[][] grid) {

        for (int col = 0; col < SIZE; col++) {
            for (int row = SIZE - 2; row >= 0; row--) {
                if (grid[row][col] == 'O') {
                    grid[row][col] = '.';

                    int r = row + 1;
                    while (r < SIZE - 1 && grid[r][col] != 'O' && grid[r][col] != '#') {
                        r++;
                    }

                    if (grid[r][col] == 'O' || grid[r][col] == '#') {
                        while (r > 0 && grid[r][col] != '.') {
                            r--;
                        }
                    }

                    grid[r][col] = 'O';

                }
            }
        }
    }

    public static void tiltWest(char[][] grid) {

        for (int row = 0; row < SIZE; row++) {
            for (int col = 1; col < SIZE; col++) {
                if (grid[row][col] == 'O') {
                    grid[row][col] = '.';

                    int c = col - 1;
                    while (c > 0 && grid[row][c] != 'O' && grid[row][c] != '#') {
                        c--;
                    }

                    if (grid[row][c] == 'O' || grid[row][c] == '#') {
                        while (c < SIZE - 1 && grid[row][c] != '.') {
                            c++;
                        }
                    }

                    grid[row][c] = 'O';

                }
            }
        }
    }

    public static void tiltEast(char[][] grid) {

        for (int row = 0; row < SIZE; row++) {
            for (int col = SIZE - 2; col >= 0; col--) {
                if (grid[row][col] == 'O') {
                    grid[row][col] = '.';

                    int c = col + 1;
                    while (c < SIZE - 1 && grid[row][c] != 'O' && grid[row][c] != '#') {
                        c++;
                    }

                    if (grid[row][c] != 'O' || grid[row][c] != '#') {
                        while (c > 0 && grid[row][c] != '.') {
                            c--;
                        }
                    }

                    grid[row][c] = 'O';

                }
            }
        }
    }

    public static void spinCycle(char[][] grid) {
        tiltNorth(grid);
        tiltWest(grid);
        tiltSouth(grid);
        tiltEast(grid);
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