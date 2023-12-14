import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day13p2 {

    public static int rows = 0;
    public static int cols = 0;

    public static int[] result;

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        while (scnr.hasNextLine()) {
            List<char[]> lines = new ArrayList<>();
            while (scnr.hasNextLine()) {
                String line = scnr.nextLine();
                if (line.isBlank())
                    break;

                lines.add(line.toCharArray());
            }

            // Process the block
            result = null;
            result = process(lines);

            for (int i = 0; i < lines.size(); i++) {
                boolean broke = false;
                for (int j = 0; j < lines.get(0).length; j++) {
                    // Toggle the current char
                    char ch = lines.get(i)[j];

                    if (ch == '.') {
                        lines.get(i)[j] = '#';
                    } else {
                        lines.get(i)[j] = '.';
                    }

                    int[] newResult = process(lines);

                    if (newResult != null) {

                        if (newResult[0] == 0) {
                            rows += newResult[1];
                        } else {
                            cols += newResult[1];
                        }

                        broke = true;
                        break;
                    } else {
                        lines.get(i)[j] = ch;
                    }
                }

                if (broke)
                    break;

            }

        }

        System.out.println("Answer is: " + (cols + (rows * 100)));

        scnr.close();
    }

    public static int[] process(List<char[]> lines) {
        // Check on the horizontal
        for (int i = 0; i < lines.size() - 1; i++) {
            // If we are at a potential line of reflection
            if (Arrays.equals(lines.get(i), lines.get(i + 1))) {

                boolean broke = false;
                for (int j = i - 1, k = i + 2; j >= 0 && k < lines.size(); j--, k++) {
                    if (!Arrays.equals(lines.get(j), lines.get(k))) {
                        broke = true;
                        break;
                    }
                }

                if (!broke) {
                    int[] newResult = { 0, i + 1 };

                    if (!Arrays.equals(result, newResult))
                        return newResult;
                }
            }
        }

        // Check on the vertical
        for (int i = 0; i < lines.get(0).length - 1; i++) {
            // If we are at a potential line of reflection
            if (getCol(lines, i).equals(getCol(lines, i + 1))) {

                boolean broke = false;
                for (int j = i - 1, k = i + 2; j >= 0 && k < lines.get(0).length; j--, k++) {
                    if (!getCol(lines, j).equals(getCol(lines, k))) {
                        broke = true;
                        break;
                    }
                }

                if (!broke) {
                    int[] newResult = { 1, i + 1 };

                    if (!Arrays.equals(result, newResult))
                        return newResult;
                }
            }
        }

        return null;
    }

    public static String getCol(List<char[]> lines, int idx) {
        StringBuilder build = new StringBuilder();

        for (int i = 0; i < lines.size(); i++) {
            build.append(lines.get(i)[idx]);
        }

        return build.toString();
    }

}