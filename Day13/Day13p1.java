import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day13p1 {

    public static int rows = 0;
    public static int cols = 0;

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        while (scnr.hasNextLine()) {
            List<String> lines = new ArrayList<>();
            while (scnr.hasNextLine()) {
                String line = scnr.nextLine();
                if (line.isBlank())
                    break;

                lines.add(line);
            }
            // Process the block
            process(lines);
        }

        System.out.println("Answer is: " + (cols + (rows * 100)));

        scnr.close();
    }

    public static void process(List<String> lines) {
        // Check on the horizontal
        for (int i = 0; i < lines.size() - 1; i++) {
            // If we are at a potential line of reflection
            if (lines.get(i).equals(lines.get(i + 1))) {

                boolean broke = false;
                for (int j = i - 1, k = i + 2; j >= 0 && k < lines.size(); j--, k++) {
                    if (!lines.get(j).equals(lines.get(k))) {
                        broke = true;
                        break;
                    }
                }

                if (!broke) {
                    rows += i + 1;
                    return;
                }
            }
        }

        // Check on the vertical
        for (int i = 0; i < lines.get(0).length() - 1; i++) {
            // If we are at a potential line of reflection
            if (getCol(lines, i).equals(getCol(lines, i + 1))) {

                boolean broke = false;
                for (int j = i - 1, k = i + 2; j >= 0 && k < lines.get(0).length(); j--, k++) {
                    if (!getCol(lines, j).equals(getCol(lines, k))) {
                        broke = true;
                        break;
                    }
                }

                if (!broke) {
                    cols += i + 1;
                    return;
                }
            }
        }
    }

    public static String getCol(List<String> lines, int idx) {
        StringBuilder build = new StringBuilder();

        for (int i = 0; i < lines.size(); i++) {
            build.append(lines.get(i).charAt(idx));
        }

        return build.toString();
    }

}