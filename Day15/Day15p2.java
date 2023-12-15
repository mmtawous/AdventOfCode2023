import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Day15p2 {

    public static final int SIZE = 100;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        String[] line = scnr.nextLine().split(",");
        Map<String, Integer>[] boxes = new LinkedHashMap[256];

        for (String s : line) {
            int idx = s.indexOf('=');
            String label = s.substring(0, idx == -1 ? s.indexOf('-') : idx);

            int curr = HASH(label);

            if (idx != -1) {
                // Add case
                Integer focal = Integer.parseInt(s.substring(idx + 1, s.length()));

                if (boxes[curr] == null) {
                    boxes[curr] = new LinkedHashMap<String, Integer>();
                }

                boxes[curr].put(label, focal);

            } else {
                // Remove case
                if (boxes[curr] != null)
                    boxes[curr].remove(label);
            }
        }

        int sum = 0;
        for (int i = 0; i < boxes.length; i++) {
            if (boxes[i] != null) {
                int cnt = 1;
                for (Integer focal : boxes[i].values()) {
                    sum += (i + 1) * cnt * focal;
                    cnt++;
                }
            }
        }

        System.out.println("Answer is: " + sum);

        scnr.close();
    }

    public static int HASH(String label) {
        int curr = 0;
        for (char c : label.toCharArray()) {
            curr += c;
            curr *= 17;
            curr %= 256;
        }

        return curr;
    }
}