import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solve {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        Map<String, Integer> maxVals = new HashMap<String, Integer>();
        maxVals.put("red", 12);
        maxVals.put("green", 13);
        maxVals.put("blue", 14);
        int game = 1;
        int sum = 0;
        while (scnr.hasNextLine()) {
            String line = scnr.nextLine().split(":")[1];
            String[] sets = line.split(";");
            boolean possible = true;

            for (String s : sets) {
                String[] colors = s.split(",");

                for (String c : colors) {
                    Scanner cScanner = new Scanner(c);
                    int val = cScanner.nextInt();
                    String color = cScanner.next();
                    cScanner.close();

                    int max = maxVals.get(color);
                    if (val > max) {
                        possible = false;
                        break;
                    }
                }

                if (!possible)
                    break;
            }

            if (possible)
                sum += game;

            game++;
        }

        System.out.println("Answer is: " + sum);

        scnr.close();
    }
}