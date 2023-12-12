import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Day11p2 {
    public static final int SIZE = 140;

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        long sum = 0;
        // Read grid
        List<String> grid = new ArrayList<String>();
        for (int i = 0; i < SIZE; i++) {
            String line = scnr.nextLine();

            if (line.matches("[.]+")) {
                line = line.replaceAll(".", "x");
            }
            grid.add(line);
        }

        expand(grid);

        for (String s : grid) {
            System.out.println(s);
        }

        HashSet<Coordinate> galaxies = new HashSet<>();
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(0).length(); j++) {
                if (grid.get(i).charAt(j) == '#') {
                    sum += getDistances(i, j, galaxies, grid);
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
                if (grid.get(j).charAt(i) != '.' && grid.get(j).charAt(i) != 'x') {
                    broke = true;
                    break;
                }
            }

            if (!broke) {
                for (int j = 0; j < grid.size(); j++) {
                    String line = grid.get(j);
                    StringBuilder build = new StringBuilder(line);
                    build.setCharAt(i, 'x');
                    grid.set(j, build.toString());
                }
                i++;
            }
        }
    }

    public static long getDistances(int r, int c, HashSet<Coordinate> galaxies, List<String> grid) {
        long sum = 0;
        galaxies.add(new Coordinate(r, c));
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(0).length(); j++) {
                if (grid.get(i).charAt(j) == '#' && !galaxies.contains(new Coordinate(i, j))) {
                    sum += bfs(r, c, i, j, grid);
                }
            }
        }

        return sum;
    }

    private static long bfs(int sI, int sJ, int dI, int dJ, List<String> grid) {
        int rows = grid.size();
        int cols = grid.get(0).length();

        boolean[][] visited = new boolean[rows][cols];

        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(new Coordinate(sI, sJ));
        visited[sI][sJ] = true;

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();

            // Check if we reached the destination
            if (current.x == dI && current.y == dJ) {
                return current.dist;
            }

            // Explore neighbors (up, down, left, right)
            int[] dr = { -1, 1, 0, 0 };
            int[] dc = { 0, 0, -1, 1 };

            for (int i = 0; i < 4; i++) {
                int newRow = current.x + dr[i];
                int newCol = current.y + dc[i];

                // Check if the neighbor is within the matrix bounds and hasn't been visited
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && !visited[newRow][newCol]) {

                    Coordinate coord = new Coordinate(newRow, newCol);
                    if (grid.get(newRow).charAt(newCol) == 'x') {
                        coord.setDistance(current.dist + 1000000);
                    } else {
                        coord.setDistance(current.dist + 1);
                    }
                    queue.add(coord);
                    visited[newRow][newCol] = true;
                }
            }
        }

        // If no path is found
        return -1;
    }

    private static class Coordinate {
        int x, y;
        long dist;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setDistance(long dist) {
            this.dist = dist;
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