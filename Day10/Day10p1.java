import java.util.Scanner;

public class Day10p1 {
    public static final int SIZE = 140;

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        // Read grid
        char[][] grid = new char[SIZE][SIZE];
        for (int i = 0; i < grid.length; i++) {
            grid[i] = scnr.nextLine().toCharArray();
        }

        // Find the S
        int result = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 'S') {
                    result = traverse(i, j, grid);
                    break;
                }
            }
        }

        System.out.println("Answer is: " + (result / 2));

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

        // Start traversing until we reach the original i and j counting steps along the
        // way.
        int steps = 1;
        while (currI != i || currJ != j) {
            if (grid[currI][currJ] == '-') {
                if (dir == Direction.RIGHT) {
                    currJ++;
                } else {
                    currJ--;
                }
            } else if (grid[currI][currJ] == '|') {
                if (dir == Direction.UP) {
                    currI--;
                } else {
                    currI++;
                }
            } else if (grid[currI][currJ] == '7') {

                if (dir == Direction.UP) {
                    dir = Direction.LEFT;
                    currJ--;
                } else {
                    dir = Direction.DOWN; // Change direction to down
                    currI++;
                }

            } else if (grid[currI][currJ] == 'J') {
                if (dir == Direction.RIGHT) {
                    dir = Direction.UP;
                    currI--;
                } else {
                    dir = Direction.LEFT; // Change direction to left
                    currJ--;
                }
            } else if (grid[currI][currJ] == 'L') {
                if (dir == Direction.LEFT) {
                    dir = Direction.UP;
                    currI--;
                } else {
                    dir = Direction.RIGHT;
                    currJ++;
                }
            } else if (grid[currI][currJ] == 'F') {
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

            steps++;
        }

        return steps;
    }

    enum Direction

    {
        RIGHT, DOWN, LEFT, UP
    };
}