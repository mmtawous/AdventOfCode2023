import java.util.HashSet;
import java.util.Scanner;

public class Day10p2 {
    public static final int SIZEX = 140;
    public static final int SIZEY = 140;

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        // Read grid
        char[][] grid = new char[SIZEY][SIZEX];
        for (int i = 0; i < grid.length; i++) {
            grid[i] = scnr.nextLine().toCharArray();
        }

        // Find the S
        int result = 0;
        for (int i = 0; i < SIZEY; i++) {
            for (int j = 0; j < SIZEX; j++) {
                if (grid[i][j] == 'S') {
                    result = traverse(i, j, grid);
                    break;
                }
            }
        }

        System.out.println("Answer is: " + result);

        scnr.close();
    }

    public static int traverse(int i, int j, char[][] grid) {

        // Move in a direction that makes sense from the start
        int currI = 0, currJ = 0;
        Direction dir = Direction.RIGHT;

        // Check right
        if (grid[i][j + 1] == '-' || grid[i][j + 1] == '7' || grid[i][j + 1] == 'J') {
            currI = i;
            currJ = j + 1;
            dir = Direction.LEFT;
        }

        // Check down
        else if (grid[i + 1][j] == '|' || grid[i + 1][j] == 'L' || grid[i + 1][j] == 'J') {
            currI = i + 1;
            currJ = j;
            dir = Direction.DOWN;
        }

        // Check left
        else if (grid[i][j - 1] == '-' || grid[i][j - 1] == 'L' || grid[i][j - 1] == 'F') {
            currI = i;
            currJ = j - 1;
            dir = Direction.LEFT;
        }

        // Check up
        else if (grid[i - 1][j] == '|' || grid[i - 1][j] == 'F' || grid[i - 1][j] == '7') {
            currI = i - 1;
            currJ = j;
            dir = Direction.UP;
        }

        else {
            System.out.println("Something is wrong");
            return -1;
        }

        int cnt = 0;

        HashSet<Coordinate> loop = new HashSet<>();
        loop.add(new Coordinate(i, j));
        loop.add(new Coordinate(currI, currJ));

        // Start traversing until we reach the original i and j.
        while (currI != i || currJ != j) {
            char ch = grid[currI][currJ];

            if (ch == '-') {
                if (dir == Direction.RIGHT) {
                    currJ++;
                } else {
                    currJ--;
                }

            } else if (ch == '|') {
                if (dir == Direction.UP) {
                    currI--;
                } else {
                    currI++;
                }
            } else if (ch == '7') {

                if (dir == Direction.UP) {
                    dir = Direction.LEFT;
                    currJ--;
                } else {
                    dir = Direction.DOWN; // Change direction to down
                    currI++;
                }

            } else if (ch == 'J') {
                if (dir == Direction.RIGHT) {
                    dir = Direction.UP;
                    currI--;
                } else {
                    dir = Direction.LEFT; // Change direction to left
                    currJ--;
                }
            } else if (ch == 'L') {
                if (dir == Direction.LEFT) {
                    dir = Direction.UP;
                    currI--;
                } else {
                    dir = Direction.RIGHT;
                    currJ++;
                }
            } else if (ch == 'F') {
                if (dir == Direction.LEFT) {
                    dir = Direction.DOWN;
                    currI++;
                } else {
                    dir = Direction.RIGHT;
                    currJ++;
                }
            } else {
                System.out.println("Something is wrong");
            }

            loop.add(new Coordinate(currI, currJ));
        }

        // Make everything that isnt the loop a '.'
        for (int k = 0; k < SIZEY; k++) {
            for (int z = 0; z < SIZEX; z++) {
                if (!loop.contains(new Coordinate(k, z)))
                    grid[k][z] = '.';
            }
        }

        // Hard coding the starting pipe because I am lazy.
        grid[i][j] = 'J';

        // Count the inside tiles
        for (int k = 0; k < SIZEY; k++) {
            boolean within = false;
            boolean up = false;
            for (int z = 0; z < SIZEX; z++) {
                char ch = grid[k][z];
                if (ch == '|') {
                    within = !within;
                } else if ("LF".contains(Character.toString(ch))) {
                    up = ch == 'L';
                } else if ("7J".contains(Character.toString(ch))) {
                    if (ch != (up ? 'J' : '7')) {
                        within = !within;
                    }
                } else if (within && ch == '.') {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    enum Direction

    {
        RIGHT, DOWN, LEFT, UP
    };

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